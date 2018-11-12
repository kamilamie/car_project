package dao;

import entities.Car;
import entities.Model;
import entities.User;
import helpers.MainHelper;

import javax.servlet.http.Part;
import java.io.*;
import java.sql.*;
import java.util.List;

public class CarDAO {
    private Connection connection = MainHelper.getConn();

    public Car getCarById(int id) {
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from car");
            while (rs.next()) {
                if (rs.getInt("id") == id) {
                    return MainHelper.makeORMCar(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Car> getAllCars() {
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from car");
            return MainHelper.makeCarORMList(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Car> getAllFavorites(int id) {
        try {
            PreparedStatement ps = connection.prepareStatement("select * from user_car where user_id=? order by date");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            return MainHelper.makeFavoriteCarORMList(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void addFavorite(int carId, int userId) {
        try {
            PreparedStatement statement = connection.prepareStatement("insert into user_car (date, car_id, user_id) values ('now', ?,?)");
            statement.setInt(1, carId);
            statement.setInt(2, userId);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addCar(String color, int horsepower, int seats, int price, String transmission,
                       int model_id, int class_id, String description, Part filePart, String path) {
        try {
            PreparedStatement statement = connection.prepareStatement("insert into car (color, horsepower, number_of_seats, price, transmission, model_id, class_id, description)" +
                    "values (?,?,?,?,?,?,?,?) returning id");
            statement.setString(1, color);
            statement.setInt(2, horsepower);
            statement.setInt(3, seats);
            statement.setInt(4, price);
            statement.setString(5, transmission);
            statement.setInt(6, model_id);
            statement.setInt(7, class_id);
            statement.setString(8, description);
            ResultSet rs = statement.executeQuery();
            rs.next();
            int carId = rs.getInt("id");
            if (filePart.getSize() != 0) {
                savePic(filePart, path, carId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void savePic(Part filePart, String path, int carId) {
        File file = new File(path + File.separator + carId);
        file.mkdirs();
        OutputStream out = null;
        InputStream filecontent = null;
        String ext = getFileName(filePart).substring(getFileName(filePart).lastIndexOf(".")).toLowerCase();
        String fileName = carId + "/" + System.currentTimeMillis() + ext;
        try {
            out = new FileOutputStream(new File(path + File.separator
                    + fileName));
            filecontent = filePart.getInputStream();

            int read = 0;
            final byte[] bytes = new byte[1024];

            while ((read = filecontent.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
        } catch (FileNotFoundException fne) {
            fne.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            try {
                if (out != null) {
                    out.close();
                }
                if (filecontent != null) {
                    filecontent.close();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement("update car set photo_path=? where id=?");
            ps.setString(1, "/files/cars/" + fileName);
            ps.setInt(2, carId);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private String getFileName(final Part part) {
        final String partHeader = part.getHeader("content-disposition");
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(
                        content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }

    public void deleteFavorite(int fav_id, int user_id) {
        try {
            PreparedStatement statement = connection.prepareStatement("delete from user_car where user_id = ? and car_id= ?");
            statement.setInt(1, user_id);
            statement.setInt(2, fav_id);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Car> getCarsByMask(String mask) {
        try {
            PreparedStatement ps = connection.prepareStatement("select * from car c inner join model m on c.model_id = m.id inner join producer p on m.producer_id=p.id GROUP BY m.id, c.id, p.id HAVING m.name ilike ? or p.name ilike ?");
            ps.setString(1, "%" + mask + "%");
            ps.setString(2, "%" + mask + "%");
            ResultSet rs = ps.executeQuery();
            return MainHelper.makeCarORMList(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int maxClassId() {
        try {
            PreparedStatement ps = connection.prepareStatement("select max(id) from class");
            ResultSet rs = ps.executeQuery();
            rs.next();
            return rs.getInt("max");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int maxProducerId() {
        try {
            PreparedStatement ps = connection.prepareStatement("select max(id) from producer");
            ResultSet rs = ps.executeQuery();
            rs.next();
            return rs.getInt("max");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public List<Car> sortCars(String carClass, String producer, String transmission, String horsepower, String seats, String price) {
        String conditions = "";
        if (carClass.length() > 0) {
            conditions += "where c2.id = ?";
        }
        if (producer.length() > 0) {
            if (conditions.length() > 0) {
                conditions += "and t.pr = ?";
            } else {
                conditions += "where t.pr = ?";
            }
        }
        if (transmission.length() > 0) {
            if (conditions.length() > 0) {
                conditions += "and car.transmission = ?";
            } else {
                conditions += "where car.transmission = ?";
            }
        }
        if (horsepower.length() > 0) {
            if (conditions.length() > 0) {
                conditions += "and car.horsepower >= ?";
            } else {
                conditions += "where car.horsepower >= ?";
            }
        }
        if (seats.length() > 0) {
            if (conditions.length() > 0) {
                conditions += "and car.number_of_seats >= ?";
            } else {
                conditions += "where car.number_of_seats >= ?";
            }
        }
        if (price.length() > 0) {
            if (conditions.length() > 0) {
                conditions += "and car.price >= ?";
            } else {
                conditions += "where car.price >= ?";
            }
        }
        String st = "select * from car inner join class c2 on car.class_id = c2.id inner join (select m.id, p.id as pr, m.name, p.name from model m inner join producer p on m.producer_id = p.id) as t on car.model_id = t.id " + conditions;


        try {
            PreparedStatement ps = connection.prepareStatement(st + ";");
            int count = 1;
            if (carClass.length() > 0) {
                ps.setInt(count, Integer.parseInt(carClass));
                count++;
            }
            if (producer.length() > 0) {
                ps.setInt(count, Integer.parseInt(producer));
                count++;
            }
            if (transmission.length() > 0) {
                ps.setString(count, transmission);
                count++;
            }
            if (horsepower.length() > 0) {
                ps.setInt(count, Integer.parseInt(horsepower));
                count++;
            }
            if (seats.length() > 0) {
                ps.setInt(count, Integer.parseInt(seats));
                count++;
            }
            if (price.length() > 0) {
                ps.setInt(count, Integer.parseInt(price));
            }
            ResultSet rs = ps.executeQuery();
            return MainHelper.makeCarORMList(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }
}

package helpers;

import dao.*;
import entities.*;
import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;

import javax.servlet.ServletContext;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MainHelper {
    private static Connection conn;
    private static ProducerDAO producerDAO = new ProducerDAO();
    private static ModelDAO modelDAO = new ModelDAO();
    private static ClassDAO classDAO = new ClassDAO();
    private static SimpleUserDAO userDAO = new SimpleUserDAO();
    private static CarDAO carDAO = new CarDAO();


    public static Connection getConn() {
        try {
            if (conn==null) {
                Class.forName("org.postgresql.Driver");
                conn = DriverManager.getConnection(
                        "jdbc:postgresql://localhost:5433/car_project",
                        "postgres",
                        "kamilasql2");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    private static Configuration cfg = null;

    public static Configuration getConfig(ServletContext sc) {
        if (cfg == null) {
            cfg = new Configuration();
            cfg.setServletContextForTemplateLoading(
                    sc,
                    "/WEB-INF/templates"
            );
            cfg.setTemplateExceptionHandler(
                    TemplateExceptionHandler.HTML_DEBUG_HANDLER
            );
        }
        return cfg;
    }

    public static User makeORMUser(ResultSet rs) {
        try {
            return new User(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("login"),
                    rs.getString("password"),
                    rs.getInt("age"),
                    rs.getInt("experience"),
                    rs.getString("photo_path"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Car makeORMCar(ResultSet rs) {
        try {
            return new Car(
                    rs.getInt("id"),
                    rs.getString("color"),
                    rs.getInt("horsepower"),
                    rs.getInt("number_of_seats"),
                    rs.getInt("price"),
                    rs.getString("transmission"),
                    modelDAO.getModelById(rs.getInt("model_id")),
                    classDAO.getClassById(rs.getInt("class_id")),
                    rs.getString("description"),
                    rs.getString("photo_path"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Producer makeORMProducer(ResultSet rs) {
        try {
            return new Producer(
                    rs.getInt("id"),
                    rs.getString("name"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Model makeORMModel(ResultSet rs) {
        try {
            return new Model(
                    rs.getInt("id"),
                    rs.getString("name"),
                    producerDAO.getProducerById(rs.getInt("producer_id")));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static CarClass makeORMClass(ResultSet rs) {
        try {
            return new CarClass(
                    rs.getInt("id"),
                    rs.getString("letter"),
                    rs.getString("name"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Comment makeORMComment(ResultSet rs) {
        try {
            return new Comment(
                    rs.getInt("id"),
                    rs.getString("text"),
                    rs.getTimestamp("date"),
                    userDAO.getById(rs.getInt("user_id")),
                    carDAO.getCarById(rs.getInt("car_id")));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<Car> makeCarORMList(ResultSet rs) throws SQLException {
        List<Car> cars = new ArrayList<>();
        Car car;
        while (rs.next()) {
            car = makeORMCar(rs);
            cars.add(car);
        }
        return cars;
    }
    public static List<Car> makeFavoriteCarORMList(ResultSet rs) throws SQLException {
        List<Car> cars = new ArrayList<>();
        while (rs.next()) {
            Car car = carDAO.getCarById(rs.getInt("car_id"));
            cars.add(car);
        }
        return cars;
    }

    public static List<Comment> makeCommentORMList(ResultSet rs) throws SQLException {
        List<Comment> comments = new ArrayList<>();
        Comment comment;
        while (rs.next()) {
            comment = makeORMComment(rs);
            comments.add(comment);
        }
        return comments;
    }

    public static List<CarClass> makeClassORMList(ResultSet rs) throws SQLException {
        List<CarClass> carClasses = new ArrayList<>();
        CarClass carClass;
        while (rs.next()) {
            carClass = makeORMClass(rs);
            carClasses.add(carClass);
        }
        return carClasses;
    }


    public static List<Producer> makeProducerORMList(ResultSet rs) throws SQLException {
        List<Producer> producers = new ArrayList<>();
        Producer producer;
        while (rs.next()) {
            producer = makeORMProducer(rs);
            producers.add(producer);
        }
        return producers;
    }


    public static List<Model> makeModelORMList(ResultSet rs) throws SQLException {
        List<Model> models = new ArrayList<>();
        Model model;
        while (rs.next()) {
            model = makeORMModel(rs);
            models.add(model);
        }
        return models;
    }
}

package dao;

import entities.CarClass;
import entities.Producer;
import helpers.MainHelper;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class ClassDAO {
    private Connection connection = MainHelper.getConn();
    public CarClass getClassById(int id) {
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from class");
            while (rs.next()) {
                if (rs.getInt("id") == id) {
                    return MainHelper.makeORMClass(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<CarClass> getAllClasses() {
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from class");
            return MainHelper.makeClassORMList(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

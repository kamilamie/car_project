package dao;

import entities.Producer;
import helpers.MainHelper;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class ProducerDAO {
    private Connection connection = MainHelper.getConn();
    public Producer getProducerById(int id) {
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from producer");
            while (rs.next()) {
                if (rs.getInt("id") == id) {
                    return MainHelper.makeORMProducer(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Producer> getAllProducers() {
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from producer");
            return MainHelper.makeProducerORMList(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

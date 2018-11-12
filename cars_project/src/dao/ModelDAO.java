package dao;

import entities.Model;
import helpers.MainHelper;

import java.sql.*;
import java.util.List;

public class ModelDAO {
    private Connection connection = MainHelper.getConn();

    public Model getModelByName(String name){
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from model");
            while (rs.next()) {
                if (rs.getString("name").equals(name)) {
                    return MainHelper.makeORMModel(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public Model getModelById(int id) {
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from model");
            while (rs.next()) {
                if (rs.getInt("id") == id) {
                    return MainHelper.makeORMModel(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Model addModel(String name, int producer_id){
        try {
            PreparedStatement statement = connection.prepareStatement("insert into model (name, producer_id) values (?,?)");
            statement.setString(1,name);
            statement.setInt(2, producer_id);
            statement.execute();
            return getModelByName(name);

        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public List<Model> getModelsByMask(String mask) {
        try {
            PreparedStatement ps = connection.prepareStatement("select * from model Inner join producer On model.producer_id = producer.id GROUP BY producer.id, model.id HAVING model.name ilike ? or producer.name ilike ?");
            ps.setString(1, "%" + mask + "%");
            ps.setString(2, "%" + mask + "%");
            ResultSet rs = ps.executeQuery();
            return MainHelper.makeModelORMList(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

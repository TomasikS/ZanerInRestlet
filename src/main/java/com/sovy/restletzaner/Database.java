package com.sovy.restletzaner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author stefan.tomasik
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author stefan.tomasik
 */
public class Database {

    private static final String QUERY = "SELECT * from zaner;";
    Connection connection;

    public List readData() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/bookstore", "postgres", "postgres");
        List<Zaner> list = new ArrayList();
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(QUERY);) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String firstname = rs.getString("meno");
                list.add(new Zaner(id, firstname));
            }
        }
        connection.close();
        return list;
    }

    public void insertData(Zaner meno) throws SQLException {
        String sql = "INSERT INTO zaner (meno) VALUES (?)";
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/bookstore", "postgres", "postgres");
        PreparedStatement statement = connection.prepareStatement(sql);
        // statement.setInt(1, id);
        statement.setString(1, meno.getName());

        int rowsInserted = statement.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("A new user was inserted successfully!");
        }
        connection.close();

    }

    void remove(Zaner zaner) throws SQLException {

        String url = "jdbc:postgresql://localhost:5432/bookstore";
        String user = "postgres";
        String password = "postgres";
        Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/bookstore", "postgres", "postgres");
        String sql = "DELETE FROM zaner WHERE id=? ";

        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, zaner.getId());

        int rowsDeleted = statement.executeUpdate();
        if (rowsDeleted > 0) {
            System.out.println("A user was deleted successfully!");
        }

    }

    public Zaner getZanerById(Integer id) {
        String url = "jdbc:postgresql://localhost:5432/bookstore";
        String user = "postgres";
        String password = "postgres";
        String query = "select *  from zaner where id=" + id;
        Zaner zaner = null;
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/bookstore", "postgres", "postgres");
                PreparedStatement preparedStatement = connection.prepareStatement(query);) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int idzaner = rs.getInt(1);
                String name = rs.getString(2);

                zaner = (new Zaner(idzaner, name));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, "Cannot connect to database " + ex.getMessage(), ex);
        }

        return zaner;
    }

    public void updateById(Integer id, Zaner zaner) {
        String url = "jdbc:postgresql://localhost:5432/bookstore";
        String user = "postgres";
        String password = "postgres";
        //Zaner Foundedzaner = getZanerById(id)
        String sql = "update zaner set meno = '" +  zaner.getName()  + "'where id=" + id;

        try (Connection conn = DriverManager.getConnection(url, user, password);
                Statement stmt = conn.createStatement();) {

            stmt.executeUpdate(sql);
            System.out.println("Database updated successfully ");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}

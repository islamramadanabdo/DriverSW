package com.example.last_try.Database;

import com.example.last_try.Models.*;
import com.example.last_try.Models.rate;


import static java.lang.System.out;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class DriverDatabase {

    private final String database_name="root";
    private final String database_password="";

    public ArrayList<User> get_all_trips() {
        return null;
    }

    public Driver get_driver(String UserID) {
        Driver current_driver = new Driver();
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");

            conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/advancedsw", database_name, database_password);

            String sql = "SELECT * FROM driver WHERE userID = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, UserID);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                current_driver.setId(result.getInt("UserID"));
                current_driver.setUsername(result.getString("username"));
                current_driver.setEmail(result.getString("email"));
                current_driver.setPhone(result.getString("mobile"));
                current_driver.setLicence(result.getString("license"));
                current_driver.setNationalID(result.getString("nationalID"));
                current_driver.setApproved(result.getString("approved"));
                current_driver.setRole(result.getString("role"));

            }
        } catch (ClassNotFoundException | SQLException ex) {
            //ex.printStackTrace();
            //out.println("not connected");
            System.out.println(ex.getMessage());
        }
        return current_driver;
    }


    public ArrayList<favorite_area> get_favorite_areas(User user) {
        ArrayList<favorite_area> all_favorite = new ArrayList<favorite_area>();
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");

            conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/advancedsw", database_name, database_password);

            String sql = "SELECT * FROM favorite WHERE UserID = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, user.getId());
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                favorite_area area = new favorite_area();
                area.setDriver_id(result.getInt("UserID"));
                area.setLocation(result.getString("area"));

                all_favorite.add(area);

            }
        } catch (ClassNotFoundException | SQLException ex) {
            //ex.printStackTrace();
            //out.println("not connected");
            System.out.println(ex.getMessage());
        }
        return all_favorite;
    }

    public database.database_response add_fev_area(favorite_area FA) {
        Connection conn = null;
        database.database_response respond = new database.database_response();
        try {
            Class.forName("com.mysql.jdbc.Driver");

            conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/advancedsw", database_name, database_password);

            String query = " insert into favorite (UserID, area)"
                    + " values (?, ?)";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt(1, FA.getDriver_id());
            preparedStmt.setString(2, FA.getLocation());
            try {
                preparedStmt.execute();
                respond.setStatus(true);
            } catch (Exception e) {
                respond.setStatus(false);
            }
            conn.close();
        } catch (ClassNotFoundException | SQLException ex) {
            //ex.printStackTrace();
            out.print("not connected");
        }
        return respond;
    }

    public ArrayList<Driver> get_all_drivers(String approved) {
        ArrayList<Driver> drivers = new ArrayList<Driver>();

        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");

            conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/advancedsw", database_name, database_password);

            String sql = "SELECT * FROM driver WHERE role = ? and approved=? ";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, "Driver");
            statement.setString(2, approved);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Driver Driver = new Driver();
                Driver.setId(result.getInt("UserID"));
                Driver.setUsername(result.getString("username"));
                Driver.setEmail(result.getString("email"));
                Driver.setPhone(result.getString("mobile"));
                Driver.setLicence(result.getString("license"));
                Driver.setNationalID(result.getString("nationalID"));
                Driver.setApproved(result.getString("approved"));
                drivers.add(Driver);

            }
        } catch (ClassNotFoundException | SQLException ex) {
            //ex.printStackTrace();
            //out.println("not connected");
            System.out.println(ex.getMessage());
        }
        return drivers;
    }

    public ArrayList<rate> get_all_rates(int UserID) {
        ArrayList<rate> rates = new ArrayList<rate>();
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");

            conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/advancedsw",database_name, database_password);

            String sql = "SELECT * FROM rate WHERE driverID = ? ";
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setInt(1, UserID);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                rate current_rate = new rate();
                current_rate.setDriver_id(UserID);

                current_rate.setUser_id(result.getInt("userID"));
                current_rate.setRate(result.getInt("rate"));
                rates.add(current_rate);

            }
        } catch (ClassNotFoundException | SQLException ex) {
            //ex.printStackTrace();
            out.print("not connected");
        }
        return rates;
    }
//	

    public void rate_driver(rate new_rate) {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");

            conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/advancedsw", database_name, database_password);

            String query = " insert into rate (driverID, userID ,rate )"
                    + " values (?, ? , ?)";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt(1, new_rate.getDriver_id());
            preparedStmt.setInt(2, new_rate.getUser_id());
            preparedStmt.setInt(3, new_rate.getRate());
            try {
                preparedStmt.execute();
            } catch (Exception e) {
            }

            conn.close();
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            out.print("not connected");
        }
    }


}

package com.example.last_try.Database;

import com.example.last_try.Models.*;
import com.example.last_try.Models.Driver;

import static java.lang.System.out;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DriversDatabase {

    database.database_response response = new database.database_response();

    public database.database_response create_driver(Driver new_driver) {
        java.sql.Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");

            conn = (java.sql.Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/advancedsw", "root", "");

            String query = " insert into driver ( username, password,  email, mobile, license , nationalID ,  role ,  approved)"
                    + " values ( ?,?, ?, ? , ? , ? , ?, ?)";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, new_driver.getUsername());
            preparedStmt.setString(2, new_driver.getPassword());
            preparedStmt.setString(3, new_driver.getEmail());
            preparedStmt.setString(4, new_driver.getPhone());
            preparedStmt.setString(5, new_driver.getLicence());
            preparedStmt.setString(6, new_driver.getNationalID());
            preparedStmt.setString(7, new_driver.getRole());
            preparedStmt.setString(8, new_driver.getApproved());
            try {

                preparedStmt.execute();

                response.setStatus(true);
            } catch (Exception e) {
                response.setStatus(false);
            }

            conn.close();
        } catch (ClassNotFoundException | SQLException ex) {
            //ex.printStackTrace();
            out.print(" faild not connected");
        }
        return response;
    }

    public Driver read_driver(String uname, String upassword) {
        Driver current_driver = new Driver();
        java.sql.Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = (java.sql.Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/advancedsw", "root", "");

            String sql = "SELECT * FROM driver WHERE username = ? and password = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, uname);
            statement.setString(2, upassword);
            ResultSet result = statement.executeQuery();
            if (!result.next()) {
                out.println("can't find this driver");

            } else {
                current_driver.setRole(result.getString("role"));
                current_driver.setApproved(result.getString("approved"));
                current_driver.setId(result.getInt("UserID"));
                current_driver.setEmail(result.getString("email"));
                current_driver.setLicence(result.getString("license"));
                current_driver.setNationalID(result.getString("nationalID"));
                current_driver.setPhone(result.getString("mobile"));
                current_driver.setUsername(uname);
                current_driver.setPassword(upassword);

                return current_driver;
            }

        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            out.print("not connected");
        }

        return current_driver;
    }

    public database.database_response approve_or_susbend(int User_id, String approved) {
        database.database_response response = new database.database_response();
        java.sql.Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");

            conn = (java.sql.Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/advancedsw", "root", "");
            PreparedStatement pstatement;
            String update1 = "UPDATE driver "
                    + "SET "
                    + "approved ='" + approved + "'"
                    + "where UserID ='" + User_id + "' ";
            pstatement = conn.prepareStatement(update1);
            int updateQuery = pstatement.executeUpdate();
            if (updateQuery == 1) {
                response.setStatus(true);
            } else {
                response.setStatus(false);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            out.print("not connected");
        }
        return response;
    }

    public ArrayList<Driver> listAllPendingDrivers() {
        DriverDatabase driver_database = new DriverDatabase();
        ArrayList<Driver> all_drivers = new ArrayList<Driver>();
        all_drivers = driver_database.get_all_drivers("0");
        return  all_drivers;
    }

    public ArrayList<Driver> listDriverUsers() {
        DriverDatabase driver_database = new DriverDatabase();
        ArrayList<Driver> all_drivers = new ArrayList<Driver>();
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");

            conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/advancedsw", "root", "");

            String sql = "SELECT * FROM driver WHERE approved=? ";
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, "1");
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
                all_drivers.add(Driver);

            }
        } catch (ClassNotFoundException | SQLException ex) {
            //ex.printStackTrace();
            //out.println("not connected");
            System.out.println(ex.getMessage());
        }
        return all_drivers;
    }

}

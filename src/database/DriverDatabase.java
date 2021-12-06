package database;

import static java.lang.System.out;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import sw2_project.user;
import sw2_project.driver;
import sw2_project.favorite_area;
import sw2_project.rate;

public class DriverDatabase {

    public ArrayList<user> get_all_trips() {
        return null;
    }

    public driver get_driver(String UserID) {
        driver current_driver = new driver();
        java.sql.Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");

            conn = (java.sql.Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/advancedsw", "root", "");

            String sql = "SELECT * FROM driver WHERE userID = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, UserID);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                current_driver.setId(result.getInt("UserID"));
                current_driver.setUsername(result.getString("username"));
                current_driver.setEmail(result.getString("email"));
                current_driver.setPhone(result.getString("mobile"));
                current_driver.setDrivingLicence(result.getString("license"));
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

    public ArrayList<favorite_area> get_favorite_areas(String UserID) {
        ArrayList<favorite_area> all_favorite = new ArrayList<favorite_area>();
        java.sql.Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");

            conn = (java.sql.Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/advancedsw", "root", "");

            String sql = "SELECT * FROM favorite WHERE UserID = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, UserID);
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

    public database_response add_fev_area(String UserID, String area) {
        database_response response = new database_response();
        java.sql.Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");

            conn = (java.sql.Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/advancedsw", "root", "");

            String query = " insert into favorite (UserID, area)"
                    + " values (?, ?)";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, UserID);
            preparedStmt.setString(2, area);
            try {
                preparedStmt.execute();
                response.setStatus(true);
            } catch (Exception e) {
                response.setStatus(false);
            }
            conn.close();
        } catch (ClassNotFoundException | SQLException ex) {
            //ex.printStackTrace();
            out.print("not connected");
        }
        return response;
    }

    public ArrayList<driver> get_all_drivers(String approved) {
        ArrayList<driver> drivers = new ArrayList<driver>();

        java.sql.Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");

            conn = (java.sql.Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/advancedsw", "root", "");

            String sql = "SELECT * FROM driver WHERE role = ? and approved=? ";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, "Driver");
            statement.setString(2, approved);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                driver Driver = new driver();
                Driver.setId(result.getInt("UserID"));
                Driver.setUsername(result.getString("username"));
                Driver.setEmail(result.getString("email"));
                Driver.setPhone(result.getString("mobile"));
                Driver.setDrivingLicence(result.getString("license"));
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

    public ArrayList<rate> get_all_rates(String UserID) {
        ArrayList<rate> rates = new ArrayList<rate>();
        java.sql.Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");

            conn = (java.sql.Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/advancedsw", "root", "");

            String sql = "SELECT * FROM rate WHERE driverID = ? ";
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, UserID);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                rate current_rate = new rate();
                current_rate.setDriver_id(UserID);
                current_rate.setUser_id(result.getString("userID"));
                current_rate.setRate(result.getString("rate"));
                rates.add(current_rate);

            }
        } catch (ClassNotFoundException | SQLException ex) {
            //ex.printStackTrace();
            out.print("not connected");
        }
        return rates;
    }
//	

    public database_response rate_driver(rate new_rate) {
        database_response response = new database_response();
        java.sql.Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");

            conn = (java.sql.Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/advancedsw", "root", "");

            String query = " insert into rate (driverID, userID ,rate )"
                    + " values (?, ? , ?)";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, new_rate.getDriver_id());
            preparedStmt.setString(2, new_rate.getUser_id());
            preparedStmt.setString(3, new_rate.getRate());
            try {
                preparedStmt.execute();
                response.setStatus(true);
            } catch (Exception e) {
                response.setStatus(false);
            }

            conn.close();
        } catch (ClassNotFoundException | SQLException ex) {
            //ex.printStackTrace();
            out.print("not connected");
            response.setStatus(false);
        }
        return response;
    }
}

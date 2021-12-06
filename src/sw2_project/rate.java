/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sw2_project;

/**
 *
 * @author HP
 */
import static java.lang.System.out;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class rate {

    private String rate_id;
    private String user_id;
    private String driver_id;
    private String rate;

    public String getRate_id() {
        return rate_id;
    }

    public void setRate_id(String rate_id) {
        this.rate_id = rate_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getDriver_id() {
        return driver_id;
    }

    public void setDriver_id(String driver_id) {
        this.driver_id = driver_id;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public Double calc_rate(String DriverID) {
        int num = 0, sum = 0;
        java.sql.Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");

            conn = (java.sql.Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/advancedsw", "root", "");

            String query = " SELECT * FROM rate Where driverID = ?";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, DriverID);
            ResultSet result = preparedStmt.executeQuery();
            while (result.next()) {
                num++;
                sum += result.getInt("rate");
            }

            conn.close();
        } catch (ClassNotFoundException | SQLException ex) {
            //ex.printStackTrace();
            out.print("not connected");
        };

        Double total_rate = (double) (sum / num);
        return total_rate;

    }

}

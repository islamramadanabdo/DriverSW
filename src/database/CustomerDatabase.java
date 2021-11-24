package database;

import static java.lang.System.out;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import sw2_project.user;

public class CustomerDatabase {

    public ArrayList<user> get_all_customers() {
        ArrayList<user> all_customers = new ArrayList<user>();
        java.sql.Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");

            conn = (java.sql.Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/advancedsw", "root", "");

            String sql = "SELECT * FROM driver WHERE role = ? ";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, "User");
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                user customer = new user();
                customer.setId(result.getInt("UserID"));
                customer.setUsername(result.getString("username"));
                customer.setEmail(result.getString("email"));
                customer.setPhone(result.getString("mobile"));
                customer.setApproved(result.getString("approved"));
                all_customers.add(customer);

            }
        } catch (ClassNotFoundException | SQLException ex) {
            //ex.printStackTrace();
            out.print("not connected");
        }
        return all_customers;
    }

}

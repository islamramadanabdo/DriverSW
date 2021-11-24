/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import static java.lang.System.out;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author HP
 */
public class DBConnection {

    public static java.sql.Connection connect() {
        java.sql.Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");

            conn = (java.sql.Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/advancedsw", "root", "");

            System.out.println("connection ");
        } 
        catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            out.print(" faild not connected");
        }
            return conn;
    }
}

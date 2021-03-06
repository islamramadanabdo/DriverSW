/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

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
import java.util.ArrayList;

import sw2_project.offer;

public class OfferDatabase {

    public database_response create(offer new_offer) {
        database_response response = new database_response();
        java.sql.Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");

            conn = (java.sql.Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/advancedsw", "root", "");

            String query = " insert into offer( driverID, UserID, tripID , money )"
                    + " values (?,?, ? , ?)";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, new_offer.getDriverID());
            preparedStmt.setString(2, new_offer.getUserID());
            preparedStmt.setInt(3, new_offer.getTripID());
            preparedStmt.setDouble(4, new_offer.getMoney());
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
//

    public offer get_offer(int offer_id) {
        
        offer current_offer = new offer();
       java.sql.Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");

            conn = (java.sql.Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/advancedsw", "root", "");

            String query = " SELECT * FROM offer WHERE offerID = ?";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt(1, offer_id);
            ResultSet result = preparedStmt.executeQuery();

            while (result.next()) {

                current_offer.setDriverID(Integer.toString(result.getInt("driverID")));
                current_offer.setUserID(Integer.toString(result.getInt("userID")));
                current_offer.setOfferID(result.getInt("offerID"));
                current_offer.setTripID(result.getInt("tripID"));
                current_offer.setStatus(result.getString("status"));
                current_offer.setMoney(result.getDouble("money"));

            }
            conn.close();
        } catch (ClassNotFoundException | SQLException ex) {
            
            System.out.println(ex.getMessage());
        }
        return current_offer;
    }

    public ArrayList<offer> get_trip_offers(int trip_id) {
        ArrayList<offer> all_offers = new ArrayList<offer>();
      java.sql.Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");

            conn = (java.sql.Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/advancedsw", "root", "");

            String query = " SELECT * FROM offer WHERE tripID = ?";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt(1, trip_id);
            ResultSet result = preparedStmt.executeQuery();

            while (result.next()) {
                offer current_offer = new offer();
                current_offer.setDriverID(Integer.toString(result.getInt("driverID")));
                current_offer.setUserID(Integer.toString(result.getInt("userID")));
                current_offer.setOfferID(result.getInt("offerID"));
                current_offer.setTripID(result.getInt("tripID"));
                current_offer.setStatus(result.getString("status"));
                current_offer.setMoney(result.getDouble("money"));
                all_offers.add(current_offer);
            }
            conn.close();
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return all_offers;
    }

    public database_response update_offer_status(int offer_id, String msg) {
        database_response response = new database_response();
        java.sql.Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");

            conn = (java.sql.Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/advancedsw", "root", "");

            String query = "UPDATE offer set status=? WHERE offerID= ?";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, msg);
            preparedStmt.setInt(2, offer_id);
            response.setStatus(preparedStmt.execute());

            conn.close();
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex.getMessage());
            response.setStatus(false);
        }
        return response;
    }

}

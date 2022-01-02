/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.last_try.Database;

/**
 *
 * @author HP
 */
import com.example.last_try.Models.offer;

import static java.lang.System.out;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class OfferDatabase
{

    private final String database_name="root";
    private final String database_password="";
    public void create(offer new_offer)
    {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");

            conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/advancedsw", database_name, database_password);

            String query = " insert into offer( driverID, UserID, tripID , money )"
                    + " values (?,?, ? , ?)";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, new_offer.getDriverID());
            preparedStmt.setString(2, new_offer.getUserID());
            preparedStmt.setInt(3, new_offer.getTripID());
            preparedStmt.setDouble(4, new_offer.getMoney());
            try {
                preparedStmt.execute();
            } catch (Exception e) {
                e.printStackTrace();
            }

            conn.close();
        } catch (ClassNotFoundException | SQLException ex) {
            //ex.printStackTrace();
            out.print("not connected");
        }
    }
//

    public offer get_offer(int offer_id) {
        
        offer current_offer = new offer();
       Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");

            conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/advancedsw", database_name, database_password);

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
      Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");

            conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/advancedsw", database_name, database_password);

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

    public void update_offer_status(int offer_id, String msg) {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");

            conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/advancedsw", database_name, database_password);

            String query = "UPDATE offer set status=? WHERE offerID= ?";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, msg);
            preparedStmt.setInt(2, offer_id);
            preparedStmt.execute();

            conn.close();
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

}

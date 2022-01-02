package com.example.last_try.Database;

import com.example.last_try.Models.Trip;
import org.springframework.stereotype.Repository;

import static java.lang.System.out;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Repository("trip_DB")
public class TripDatabase {
    private final String database_name="root";
    private final String database_password="";

    public void create_trip(Trip new_trip)
    {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");

            conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/advancedsw", database_name, database_password);

            String query = " insert into trip (UserID, source ,destination )"
                    + " values (?, ? , ?)";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt(1, new_trip.getUserID());
            preparedStmt.setString(2, new_trip.getSourcee());
            preparedStmt.setString(3, new_trip.getDistination());
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

    public ArrayList<Trip> get_trips_by_source(String favorite_area)
    {
        ArrayList<Trip> available_trips = new ArrayList<Trip>();
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");

            conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/advancedsw", database_name, database_password);

            String sql = "SELECT * FROM trip WHERE source = ? AND confirmed != 1";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, favorite_area);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Trip new_trip = new Trip();
                new_trip.setSourcee(result.getString("source"));
                new_trip.setDistination(result.getString("destination"));
                new_trip.setUserID(result.getInt("UserID"));
                new_trip.setTripID(result.getInt("tripID"));

                available_trips.add(new_trip);

            }
        } catch (ClassNotFoundException | SQLException ex) {
            //ex.printStackTrace();
            //out.println("not connected");
            System.out.println(ex.getMessage());
        }
        return available_trips;
    }

    public Trip get_trip_by_id(int id)
    {
        Trip current_trip = new Trip();
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");

            conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/advancedsw", database_name, database_password);

            String query = " SELECT * FROM trip WHERE tripID= ?";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt(1, id);
            ResultSet result = preparedStmt.executeQuery();
            while (result.next()) {

                current_trip.setSourcee(result.getString("source"));
                current_trip.setDistination(result.getString("destination"));
                current_trip.setTripID(result.getInt("tripID"));
                current_trip.setUserID(result.getInt("userID"));

            }

            conn.close();
        } catch (ClassNotFoundException | SQLException ex) {
            //ex.printStackTrace();
            out.print("not connected");
        }
        return current_trip;
    }

    public ArrayList<Trip> get_all()
    {
        ArrayList<Trip> all_trips = new ArrayList<Trip>();
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");

            conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/advancedsw", database_name, database_password);

            String query = " SELECT * FROM trip WHERE confirmed != 1";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            ResultSet result = preparedStmt.executeQuery();
            while (result.next()) {
                Trip current_trip = new Trip();
                current_trip.setSourcee(result.getString("source"));
                current_trip.setDistination(result.getString("destination"));
                current_trip.setTripID(result.getInt("tripID"));
                current_trip.setUserID(result.getInt("userID"));
                all_trips.add(current_trip);
            }

            conn.close();
        } catch (ClassNotFoundException | SQLException ex) {
            //ex.printStackTrace();
            out.print("not connected");
        };
        return all_trips;
    }

    public ArrayList<Trip> get_user_trips(String UserID)
    {
        ArrayList<Trip> all_trips = new ArrayList<Trip>();
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");

            conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/advancedsw", database_name, database_password);

            String query = " SELECT * FROM trip WHERE userID= ? And confirmed!= 1";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, UserID);
            ResultSet result = preparedStmt.executeQuery();
            while (result.next()) {
                Trip current_trip = new Trip();
                current_trip.setSourcee(result.getString("source"));
                current_trip.setDistination(result.getString("destination"));
                current_trip.setTripID(result.getInt("tripID"));
                current_trip.setUserID(result.getInt("userID"));
                all_trips.add(current_trip);
            }

            conn.close();
        } catch (ClassNotFoundException | SQLException ex) {
            //ex.printStackTrace();
            out.print("not connected");
        }

        return all_trips;
    }

    public void confirm_trip(int trip_id)
    {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");

            conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/advancedsw", database_name, database_password);

            String query = " Update trip set confirmed='1' WHERE tripID= ?";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt(1, trip_id);

            conn.close();
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

}

package database;

import static java.lang.System.out;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import sw2_project.Trip;

public class TripDatabase {

    public database_response create_trip(Trip new_trip) {
        database_response respond = new database_response();
        java.sql.Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");

            conn = (java.sql.Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/advancedsw", "root", "");

            String query = " insert into trip (UserID, source ,destination )"
                    + " values (?, ? , ?)";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, new_trip.getUserID());
            preparedStmt.setString(2, new_trip.getSourcee());
            preparedStmt.setString(3, new_trip.getDistination());
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

    public ArrayList<Trip> get_trips_by_source(String favorite_area) {
        ArrayList<Trip> available_trips = new ArrayList<Trip>();
        java.sql.Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");

            conn = (java.sql.Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/advancedsw", "root", "");

            String sql = "SELECT * FROM trip WHERE source = ? AND confirmed != 1";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, favorite_area);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Trip new_trip = new Trip();
                new_trip.setSourcee(result.getString("source"));
                new_trip.setDistination(result.getString("destination"));
                new_trip.setUserID(result.getString("UserID"));
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

    public Trip get_trip_by_id(int id) {
        Trip current_trip = new Trip();
        java.sql.Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");

            conn = (java.sql.Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/advancedsw", "root", "");

            String query = " SELECT * FROM trip WHERE tripID= ?";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt(1, id);
            ResultSet result = preparedStmt.executeQuery();
            while (result.next()) {

                current_trip.setSourcee(result.getString("source"));
                current_trip.setDistination(result.getString("destination"));
                current_trip.setTripID(result.getInt("tripID"));
                current_trip.setUserID(Integer.toString(result.getInt("userID")));

            }

            conn.close();
        } catch (ClassNotFoundException | SQLException ex) {
            //ex.printStackTrace();
            out.print("not connected");
        }
        return current_trip;
    }

    public ArrayList<Trip> get_all() {
        ArrayList<Trip> all_trips = new ArrayList<Trip>();
        java.sql.Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");

            conn = (java.sql.Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/advancedsw", "root", "");

            String query = " SELECT * FROM trip WHERE confirmed != 1";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            ResultSet result = preparedStmt.executeQuery();
            while (result.next()) {
                Trip current_trip = new Trip();
                current_trip.setSourcee(result.getString("source"));
                current_trip.setDistination(result.getString("destination"));
                current_trip.setTripID(result.getInt("tripID"));
                current_trip.setUserID(Integer.toString(result.getInt("userID")));
                all_trips.add(current_trip);
            }

            conn.close();
        } catch (ClassNotFoundException | SQLException ex) {
            //ex.printStackTrace();
            out.print("not connected");
        };
        return all_trips;
    }

}

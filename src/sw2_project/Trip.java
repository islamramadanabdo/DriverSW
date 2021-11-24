/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sw2_project;

import database.TripDatabase;
import database.UsersDatabase;
import java.util.ArrayList;

/**
 *
 * @author HP
 */
public class Trip {

    private int tripID;
    private String UserID;
    private String sourcee;
    private String distination;
     TripDatabase database = new TripDatabase();
        UsersDatabase user_database = new UsersDatabase();

    public int getTripID() {
        return tripID;
    }

    public void setTripID(int tripID) {
        this.tripID = tripID;
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String userID) {
        UserID = userID;
    }

    public String getSourcee() {
        return sourcee;
    }

    public void setSourcee(String sourcee) {
        this.sourcee = sourcee;
    }

    public String getDistination() {
        return distination;
    }

    public void setDistination(String distination) {
        this.distination = distination;
    }

    public void view_all() {
        ArrayList<Trip> all_trips = new ArrayList<Trip>();

        all_trips = database.get_all();
        for (int i = 0; i < all_trips.size(); i++) {
            System.out.println("		*****");
            System.out.println("	Trip Source : " + all_trips.get(i).getSourcee());
            System.out.println("  Trip Destination : " + all_trips.get(i).getDistination());
            System.out.println("  Trip ID : " + all_trips.get(i).getTripID());
            user customer = new user();
            customer = user_database.get_user_by_id(all_trips.get(i).getUserID());
            System.out.println("  Trip Customer Name : " + customer.getUsername());
        }
        System.out.println("		*****");

    }

    public void view_trip(int trip_id) {
        Trip current_trip = new Trip();
        current_trip = database.get_trip_by_id(trip_id);
        System.out.println("	 Trip ID: " + current_trip.getTripID());
        System.out.println("	 Source: " + current_trip.getSourcee());
        System.out.println("	 Destination: " + current_trip.getDistination());
        user current_user = user_database.get_user_by_id(current_trip.getUserID());
        System.out.println("	 Customer name: " + current_user.getUsername());
    }
}

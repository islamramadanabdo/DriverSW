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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.DriverDatabase;
import database.UsersDatabase;
import java.util.Scanner;
import database.TripDatabase;
import static java.lang.System.out;
import java.util.ArrayList;
import database.database_response;

public class driverActivities extends Activities {

    Scanner input = new Scanner(System.in);
    TripDatabase trip_database = new TripDatabase();
    DriverDatabase driver_database = new DriverDatabase();

    public void addFavoriteArea(String UserID) {
        DriverDatabase database = new DriverDatabase();
        database_response status = new database_response();

        String area = null;
        out.println("Enter area you want to add ");
        area = input.next();

        status = database.add_fev_area(UserID, area);

        if (status.getStatus()) {
            System.out.println("Added successfully");
        } else {
            System.out.println("Something went wrong, please try later!");
        }
    }

    public void listUserRate(String UserID) {
        UsersDatabase user_database = new UsersDatabase();
        DriverDatabase driver_database = new DriverDatabase();
        ArrayList<rate> rates = new ArrayList<rate>();

        rates = driver_database.get_all_rates(UserID);
        for (int i = 0; i < rates.size(); i++) {
            String customer_id = rates.get(i).getUser_id();
            System.out.println("customer name : " + user_database.get_user_by_id(customer_id).getUsername());
            System.out.println("Rate : " + rates.get(i).getRate());

            out.println();
            out.println("_________________________________________________________________________________________________");
        }
    }

    public void list_all_trips() {

        ArrayList<Trip> available_trips = new ArrayList<Trip>();
        available_trips = trip_database.get_all();
        if (available_trips.size() == 0) {
            System.out.println("	 No current trips!!");
        } else {
            for (int j = 0; j < available_trips.size(); j++) {
                System.out.println("  Trip num " + (j + 1) + " :");
                Trip current_trip = new Trip();
                current_trip.view_trip(available_trips.get(j).getTripID());
                System.out.println("_______________________________________________________________");
            }
        }

    }

    public void list_favorite_trips(String UserID) {
        ArrayList<favorite_area> favorite_areas = new ArrayList<favorite_area>();

        favorite_areas = driver_database.get_favorite_areas(UserID);
        for (int i = 0; i < favorite_areas.size(); i++) {
            ArrayList<Trip> available_trips = new ArrayList<Trip>();
            available_trips = trip_database.get_trips_by_source(favorite_areas.get(i).getLocation());
            System.out.println((i + 1) + ") " + favorite_areas.get(i).getLocation() + " : ");
            if (available_trips.size() == 0) {
                System.out.println("	 No current trips!!");
            } else {
                for (int j = 0; j < available_trips.size(); j++) {
                    System.out.println("  Trip num " + (j + 1) + " :");
                    Trip current_trip = new Trip();
                    current_trip.view_trip(available_trips.get(j).getTripID());
                    System.out.println("_______________________________________________________________");
                }
            }

        }
    }

    public void suggestPrice(Trip t) {

    }

    public void notifyUser(user u) {

    }
}

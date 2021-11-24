/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sw2_project;

import database.DriverDatabase;
import java.util.Scanner;
import database.TripDatabase;
import static java.lang.System.out;
import java.util.ArrayList;
import database.database_response;

/**
 *
 * @author HP
 */
public class userAvtivities extends Activities {

    Scanner input = new Scanner(System.in);
    TripDatabase trip_database = new TripDatabase();
    DriverDatabase driver_database = new DriverDatabase();

    public void requestTrip(String UserID) {

        Trip new_trip = new Trip();
        out.println("request a Ride ");
        out.println("Enter source area`s name");
        new_trip.setSourcee(input.next());
        out.println("Enter destination area`s name");
        new_trip.setDistination(input.next());
        new_trip.setUserID(UserID);
        trip_database.create_trip(new_trip);
    }

    public void rateDriver(String UserID) {
        rate new_rate = new rate();
        // listDrivers();

        int rate;
        out.println("Enter Driver ID");
        new_rate.setDriver_id(input.next());
        new_rate.setUser_id(UserID);
        out.println("Enter rate from 1 to 5");
        rate = input.nextInt();
        if (rate >= 5) {
            rate = 5;
        } else if (rate <= 1) {
            rate = 1;
        }
        new_rate.setRate(Integer.toString(rate));
        database_response status = new database_response();
        status = driver_database.rate_driver(new_rate);
        if (status.getStatus()) {
            System.out.println("Rated successfully");
        } else {
            System.out.println("Something went wrong, please try again later!");
        }
    }

    public float averageRateDriver() {

        return 3;
    }

}

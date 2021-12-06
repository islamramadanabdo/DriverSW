/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sw2_project;

import java.util.ArrayList;
import java.util.Scanner;

import database.DriverDatabase;
import database.OfferDatabase;
import database.TripDatabase;
import database.database_response;

/**
 *
 * @author HP
 */
public class offer {

    private int offerID;
    private int tripID;
    private String driverID;
    private String userID;
    private String status;
    private Double money;
    private OfferDatabase database = new OfferDatabase();
    private TripDatabase trip_database = new TripDatabase();

    Scanner input = new Scanner(System.in);

    public int getOfferID() {
        return offerID;
    }

    public void setOfferID(int offerID) {
        this.offerID = offerID;
    }

    public int getTripID() {
        return tripID;
    }

    public void setTripID(int tripID) {
        this.tripID = tripID;
    }

    public String getDriverID() {
        return driverID;
    }

    public void setDriverID(String driverID) {
        this.driverID = driverID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public Boolean make_offer(String UserID, int trip_id, Double money) {
        offer new_offer = new offer();
        Trip current_trip = new Trip();
        database_response response = new database_response();
        new_offer.setTripID(trip_id);
        current_trip = trip_database.get_trip_by_id(trip_id);
        new_offer.setMoney(money);
        new_offer.setDriverID(UserID);
        new_offer.setUserID(current_trip.getUserID());
        response = database.create(new_offer);
        userAvtivities d = new userAvtivities();
        d.get_offer_notification(new_offer);

        if (response.getStatus()) {
     
            return true;
        } else {
            return false;
        }

    }

    public void read_offer(offer current_offer) {
        DriverDatabase driver_database = new DriverDatabase();
        System.out.println("		*****");
        System.out.println("	 Offer ID : " + current_offer.getOfferID());
        System.out.println("	 Driver ID : " + current_offer.getDriverID());
        System.out.println("	 Driver Name : " + driver_database.get_driver(current_offer.getDriverID()).getUsername());
        System.out.println("	 Price : " + current_offer.getMoney());
    }

    public database_response view_trip_offers(String UserID) {
        database_response response = new database_response();
        ArrayList<Trip> all_trips = new ArrayList<Trip>();
        TripDatabase trip_database = new TripDatabase();
        Trip T = new Trip();

        all_trips = trip_database.get_user_trips(UserID);
        for (int i = 0; i < all_trips.size(); i++) {
//                     System.out.println( "trip is " +all_trips.get(i));
            T.view_trip(all_trips.get(i).getTripID());
            ArrayList<offer> all_offers = new ArrayList<offer>();
            all_offers = database.get_trip_offers(all_trips.get(i).getTripID());
            if (all_offers.size() > 0) {
                for (int j = 0; j < all_offers.size(); j++) {
                    read_offer(all_offers.get(i));

                }
                response.setStatus(true);
            } else {
                System.out.println("No offers yet!");
                response.setStatus(false);
            }

        }
        return response;
    }

    public void confirm_offer(int offerID) {
        offer current_offer = new offer();
        current_offer = database.get_offer(offerID);

        if (current_offer == null) {
            System.out.println("Invalied offer ID, please check it again");
            return;
        } else {
            database_response response1 = new database_response();
            try {
                database.update_offer_status(offerID, "Confirmed");
                response1.setStatus(true);
            } catch (Exception ex) {
                response1.setStatus(false);
                ex.getMessage();
            }
            if (response1.getStatus()) {
                database_response response2 = new database_response();
                try {
                    trip_database.confirm_trip(current_offer.getTripID());
                    response2.setStatus(true);
                } catch (Exception ex) {
                    ex.getMessage();
                    response2.setStatus(false);
                }
                if (response2.getStatus()) {
                    System.out.println("Confirmed successfully");
                } else {
                    response1 = database.update_offer_status(offerID, "null");
                    System.out.println("Something went wrong, please try laater!");
                    return;

                }
            }

        }

    }

}

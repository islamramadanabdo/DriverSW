package com.example.last_try.Services;


import com.example.last_try.Database.DriverDatabase;
import com.example.last_try.Database.OfferDatabase;
import com.example.last_try.Database.TripDatabase;
import com.example.last_try.Database.UserDatabase;
import com.example.last_try.Models.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserServices
{
    private UserDatabase database;
    UserServices(@Qualifier("user_DB") UserDatabase user_database)
    {
        this.database=user_database;
    }

    public void add_user( User new_user)

    {
        database.create(new_user);
    }

    public ArrayList<User> get_all()
    {
        ArrayList<User>all=new ArrayList<>();
        all=database.get_all();
        return all;
    }
    public User read_user(String name, String password)
    {
        User current_user= database.read_user(name,password);
        return current_user;
    }

    public User get_user_by_id(int ID)
    {
        return database.get_by_id(ID);
    }

    public void create_trip(Trip new_trip)
    {
        TripDatabase trip_database=new TripDatabase();
        trip_database.create_trip(new_trip);
    }

    public void rateDriver(rate new_rate)
    {
        DriverDatabase driver_database=new DriverDatabase();
        driver_database.rate_driver(new_rate);

    }


    public Driver view_driver_profile(int driver_id) {

        DriverDatabase driver_database=new DriverDatabase();
        Driver current_driver = new Driver();
        Double driver_rate = driver_database.calc_rate(driver_id);
        current_driver = driver_database.get_driver(Integer.toString(driver_id));
        return current_driver;
    }

    public ArrayList<Driver> listDrivers()
    {
        DriverDatabase driver_database= new DriverDatabase();
        return driver_database.get_all_drivers("1");

    }

    public ArrayList<offer> view_trip_offers(String UserID)
    {
        ArrayList<offer>result=new ArrayList<offer>();
        ArrayList<Trip> all_trips = new ArrayList<Trip>();
        TripDatabase trip_database = new TripDatabase();
        Trip T = new Trip();
        all_trips = trip_database.get_user_trips(UserID);
        for (int i = 0; i < all_trips.size(); i++)
        {
            T=all_trips.get(i);
            OfferDatabase offer_database=new OfferDatabase();
            ArrayList<offer> all_offers= offer_database.get_trip_offers(all_trips.get(i).getTripID());
            if (all_offers.size() > 0)
            {
                for (int j = 0; j < all_offers.size(); j++)
                {
                    result.add(all_offers.get(j));
                }
            }


        }
        return result;
    }
    public ArrayList<offer> list_offers(String UserID)
    {
        return view_trip_offers(UserID);

    }

    public void confirm_offer(int offerID) {
        offer current_offer = new offer();
        OfferDatabase offer_database=new OfferDatabase();
        TripDatabase trip_database=new TripDatabase();
        current_offer = offer_database.get_offer(offerID);

        if (current_offer != null)
        {
            try
            {
                offer_database.update_offer_status(offerID, "Confirmed");
            }
            catch (Exception ex) {

                ex.getMessage();
            }
            trip_database.confirm_trip(current_offer.getTripID());

        }

    }


    public void get_offer_notification(offer new_offer) {
        UserDatabase user_database = new UserDatabase();
        User reciever = new User();
        reciever = user_database.get_by_id(new_offer.getUserID());
        sendingEmail send_notify = new sendingEmail();
        try {
            send_notify.send_offer_notification(reciever, new_offer);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}

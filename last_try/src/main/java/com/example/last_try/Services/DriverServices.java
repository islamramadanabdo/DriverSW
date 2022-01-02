package com.example.last_try.Services;

import com.example.last_try.Database.OfferDatabase;
import com.example.last_try.Database.TripDatabase;
import com.example.last_try.Database.UserDatabase;
import com.example.last_try.Models.Trip;
import com.example.last_try.Models.User;
import com.example.last_try.Models.offer;
import org.springframework.stereotype.Service;

@Service
public class DriverServices
{

    public void make_offer(offer new_offer)
    {
        OfferDatabase offer_database=new OfferDatabase();
        Trip current_trip=new Trip();
        TripDatabase trip_database=new TripDatabase();
        current_trip=trip_database.get_trip_by_id(new_offer.getTripID());
        new_offer.setUserID(current_trip.getUserID());

         offer_database.create(new_offer);
        send_offer_notification(new_offer);

    }
    public void send_offer_notification(offer new_offer) {
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

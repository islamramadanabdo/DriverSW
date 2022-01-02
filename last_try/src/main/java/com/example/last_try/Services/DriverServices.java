package com.example.last_try.Services;
import com.example.last_try.Database.TripDatabase;
import com.example.last_try.Models.*;

import com.example.last_try.Database.DriverDatabase;

import java.util.ArrayList;

public class DriverServices {

    private final DriverDatabase driver_database = new DriverDatabase();
    private final TripDatabase trip_database = new TripDatabase();

    public database.database_response addFavoriteArea( favorite_area  FA){
        return  driver_database.add_fev_area(FA) ;
    }

    public ArrayList<rate> listUserRate(int UserID){
        return driver_database.get_all_rates(UserID);
    }

    public ArrayList<Trip> list_all_trips(){
        return trip_database.get_all();
    }

    public ArrayList<favorite_area> list_favorite_trips(User user){
        return  driver_database.get_favorite_areas(user);
    }
}

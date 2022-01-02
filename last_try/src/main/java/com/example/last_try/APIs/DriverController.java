package com.example.last_try.APIs;

import com.example.last_try.Models.*;
import com.example.last_try.Services.DriverServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RequestMapping("api/v1/driver")
@RestController

public class DriverController {

    private final DriverServices driver_services = new DriverServices();
    private final Trip user_trip = new Trip();

    //done
    @PostMapping
    public  void  addFavoriteArea(@RequestBody favorite_area  FA){
        driver_services.addFavoriteArea(FA);
    }

    @GetMapping(path="userRate")
    public ArrayList<rate> listUserRate(@RequestBody rate R){
        return driver_services.listUserRate(R.getUser_id());
    }


    @GetMapping(path="getAllTrip")
    public ArrayList<Trip> list_all_trips(){
        return driver_services.list_all_trips();
    }

    @GetMapping(path="getFavoriteTrip")
    public ArrayList<favorite_area> list_favorite_trips(@RequestBody  User user){
            return driver_services.list_favorite_trips(user);
    }


}

package com.example.last_try.APIs;



import com.example.last_try.Models.*;
import com.example.last_try.Services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RequestMapping("api/v1/user")
@RestController
public class UserController
{
    private final UserServices user_services;


    @Autowired
    public UserController(UserServices user_services) {
        this.user_services = user_services;
    }

    //Done
    @PostMapping
    public void add_user(@RequestBody User new_user)
    {

        user_services.add_user(new_user);
    }

    //Done
    @GetMapping
    public ArrayList<User> get_all_users()
    {
        return user_services.get_all();
    }

    //Done
    @GetMapping(path="read")
    public User read_user(@RequestBody User user_to_get)
    {
        String name=user_to_get.getUsername();
        String password=user_to_get.getPassword();
        return user_services.read_user(name,password);
    }

    //Done
    @GetMapping(path="read_by_id/{id}")
    public User get_user_by_id(@PathVariable("id") int id)
    {
        return user_services.get_user_by_id(id);
    }

    //Done
    @PostMapping(path=("request_trip"))
    public void request_trip(@RequestBody Trip new_trip)
    {
       user_services.create_trip(new_trip);
    }

    //Done
    @PostMapping(path="rate_driver")
    public void rate_driver(@RequestBody rate new_rate)
    {
        user_services.rateDriver(new_rate);
    }

    @GetMapping(path="view_driver_profile/{id}")
    public Driver view_driver_profile(@PathVariable("id") int driver_id)
    {
       return user_services.view_driver_profile(driver_id);
    }

    @GetMapping(path="get_all_drivers")
    public ArrayList<Driver> get_all_drivers()
    {
        return user_services.listDrivers();
    }

    @GetMapping(path="get_all_offers/{id}")
    public ArrayList<offer> get_all_offers(@PathVariable("id") int userId)
    {
      return user_services.list_offers(Integer.toString(userId));
    }

    @PutMapping(path="confirm_offer/{id}")
    public void confirm_offer(@PathVariable("id") int offer_id)
    {
        user_services.confirm_offer(offer_id);
    }
    @PostMapping(path="notify_new_offer")
    public void notify_new_offer(@RequestBody offer new_offer)
    {
        user_services.get_offer_notification(new_offer);
    }



}

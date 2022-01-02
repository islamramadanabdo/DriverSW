package com.example.last_try.Services;


import com.example.last_try.Database.DriverDatabase;
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



    public void rateDriver(rate new_rate)
    {
        DriverDatabase driver_database=new DriverDatabase();
        driver_database.rate_driver(new_rate);

    }

    public Driver view_driver_profile(int driver_id) {

        DriverDatabase driver_database=new DriverDatabase();
        Driver current_driver = new Driver();
        rate current_driver_rate = new rate();
        Double driver_rate = current_driver_rate.calc_rate(driver_id);
        current_driver = driver_database.get_driver(Integer.toString(driver_id));
        return current_driver;
    }

    public ArrayList<Driver> listDrivers()
    {
        DriverDatabase driver_database= new DriverDatabase();
        return driver_database.get_all_drivers("1");

    }

    public ArrayList<offer> list_offers(String UserID) {
        offer Offer = new offer();
        return Offer.view_trip_offers(UserID);


    }

}

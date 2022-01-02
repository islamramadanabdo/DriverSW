package com.example.last_try.Services;
import com.example.last_try.Models.*;
import com.example.last_try.Database.*;

import java.util.ArrayList;

public class AdminServices {
    DriversDatabase database = new DriversDatabase();

    public database.database_response PendingDriversUsers(User user){
        database.database_response response = new database.database_response();
        response = database.approve_or_susbend(user.getId(), "-1");
        return response;
    }



    public  database.database_response  VerifyPendingDriver(User user){

        database.database_response response = new database.database_response();

        response = database.approve_or_susbend(user.getId(), "1");
        return response;
    }


    public ArrayList<Driver> listAllPendingDrivers(){
            return database.listAllPendingDrivers();
    }


    public ArrayList<Driver> listDriverUsers(){
        return database.listDriverUsers();
    }
}

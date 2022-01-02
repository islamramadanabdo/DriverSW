/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.last_try.Models;


import com.example.last_try.Database.TripDatabase;
import com.example.last_try.Database.UserDatabase;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 *
 * @author HP
 */

@Service
public class Trip {

    private int tripID;
    private int UserID;
    private String sourcee;
    private String distination;
    TripDatabase database = new TripDatabase();

    public Trip(){}
    public Trip(@JsonProperty("trip_id") int id,@JsonProperty("user_id") int UID,@JsonProperty("source") String s,@JsonProperty("distination") String d)
    {
        this.tripID=id;
        this.UserID=UID;
        this.sourcee=s;
        this.distination=d;
    }
    public int getTripID() {
        return tripID;
    }

    public void setTripID(int tripID) {
        this.tripID = tripID;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }

    public String getSourcee() {
        return sourcee;
    }

    public void setSourcee(String sourcee) {
        this.sourcee = sourcee;
    }

    public String getDistination() {
        return distination;
    }

    public void setDistination(String distination) {
        this.distination = distination;
    }

    public ArrayList<Trip> get_all()
    {
        return database.get_all();
    }

    public void create_trip(Trip new_trip)
    {
        database.create_trip(new_trip);
    }
    public Trip get_trip(int trip_id)
    {
        return database.get_trip_by_id(trip_id);
    }
}

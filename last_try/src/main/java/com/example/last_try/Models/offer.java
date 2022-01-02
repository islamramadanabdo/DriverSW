/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.last_try.Models;

import com.example.last_try.Database.DriverDatabase;
import com.example.last_try.Database.OfferDatabase;
import com.example.last_try.Database.TripDatabase;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


/**
 *
 * @author HP
 */
public class offer {

    private int offerID;
    private int tripID;
    private int driverID;
    private int userID;
    private String status;
    private Double money;
    private OfferDatabase database = new OfferDatabase();
    private TripDatabase trip_database = new TripDatabase();
    public offer(){}
    public offer(@JsonProperty("offer_id") int id,@JsonProperty("trip_id") int t_id,
                 @JsonProperty("driver_id") int d_id,@JsonProperty("user_id") int u_id,
                 @JsonProperty("status") String s,@JsonProperty("money") Double m)
    {
        this.offerID=id;
        this.tripID=t_id;
        this.driverID=d_id;
        this.userID=u_id;
        this.status=s;
        this.money=m;
    }

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

    public int getDriverID() {
        return driverID;
    }

    public void setDriverID(int driverID) {
        this.driverID = driverID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
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



}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.last_try.Models;

/**
 *
 * @author HP
 */
import com.fasterxml.jackson.annotation.JsonProperty;

import static java.lang.System.out;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class rate {

    private int rate_id;
    private int user_id;
    private int driver_id;
    private int rate;
    public rate(){}
    public rate(@JsonProperty("rate_id") int id, @JsonProperty("user_id") int u_id,@JsonProperty("driver_id") int d_id,@JsonProperty("rate") int rate)
    {
        this.rate_id=id;
        this.user_id=u_id;
        this.driver_id=d_id;
        this.rate=rate;
    }

    public int getRate_id() {
        return rate_id;
    }

    public void setRate_id(int rate_id) {
        this.rate_id = rate_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getDriver_id() {
        return driver_id;
    }

    public void setDriver_id(int driver_id) {
        this.driver_id = driver_id;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }


}

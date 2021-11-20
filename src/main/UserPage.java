package main;


import java.util.ArrayList;
import java.util.Scanner;

import database.DriverDatabase;
import database.TripDatabase;
import database.database_response;

import static java.lang.System.out;

public class UserPage
{
    Scanner input = new Scanner(System.in);
    TripDatabase trip_database=new TripDatabase();
    DriverDatabase driver_database=new DriverDatabase();
    
    public void requestRide(String UserID)
    {
    	trip new_trip=new trip();
        out.println("request a Ride ");
        out.println("Enter source area`s name");
        new_trip.setSourcee(input.next());
        out.println("Enter destination area`s name");
        new_trip.setDistination(input.next());
        new_trip.setUserID(UserID);
        trip_database.create_trip(new_trip);
    }
public void  rateDriver(String UserID)
{
	rate new_rate=new rate();
   // listDrivers();

    int rate;
    out.println("Enter Driver ID");
    new_rate.setDriver_id( input.next());
    new_rate.setUser_id(UserID);
    out.println("Enter rate from 1 to 5");
    rate = input.nextInt();
    if(rate>=5)
    {
    	rate=5;
    }
    else if(rate<=1)
    {
    	rate=1;
    }
   new_rate.setRate(Integer.toString(rate));
   database_response status=new database_response();
   status=driver_database.rate_driver(new_rate);
   if(status.getStatus())
   {
	   System.out.println("Rated successfully");
   }
   else
   {
	   System.out.println("Something went wrong, please try again later!");
   }
   
}

    public void listDrivers() 
    {
    	ArrayList<User> all_drivers=new ArrayList<User>();
    	all_drivers=driver_database.get_all_drivers("1");
    	for(int i=0;i<all_drivers.size();i++)
    	{
    		System.out.println("Driver ID : "+all_drivers.get(i).getUserID());
    		System.out.println("Driver name : "+all_drivers.get(i).getUsername());
    		System.out.println("Driver Email : "+all_drivers.get(i).getEmail());
    		System.out.println("Driver Mobile : "+all_drivers.get(i).getMobile());
    	}
    	
    }
}

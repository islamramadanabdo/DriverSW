package main;


import java.util.ArrayList;
import java.util.Scanner;

import database.DriverDatabase;
import database.TripDatabase;
import database.UsersDatabase;
import database.database_response;

import static java.lang.System.out;

public class UserPage
{
    Scanner input = new Scanner(System.in);
    UsersDatabase user_database=new UsersDatabase();
    TripDatabase trip_database=new TripDatabase();
    DriverDatabase driver_database=new DriverDatabase();
    
    public void requestRide(String UserID)
    {
    	trip new_trip=new trip();
    	new_trip.create_trip(UserID);
    }
    
    public void view_driver_profile()
    {
    	System.out.println("Enter the ID of the driver you  want to view his profile");
    	String DriverID=input.nextLine();
    	User current_driver=new User();
    	rate current_driver_rate=new rate();
    	Double driver_rate=current_driver_rate.calc_rate(DriverID);
    	current_driver=driver_database.get_driver(DriverID);
    	System.out.println("Driver ID : "+current_driver.getUserID());
		System.out.println("Driver name : "+current_driver.getUsername());
		System.out.println("Driver Email : "+current_driver.getEmail());
		System.out.println("Driver Mobile : "+current_driver.getMobile());
    	System.out.println("Driver Rate : "+ driver_rate);
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
    
    public void list_offers(String UserID)
    {
    	offer Offer=new offer();
       database_response response=new database_response();
    	response=Offer.view_trip_offers(UserID);
    	if(response.getStatus())
    	{
    		System.out.println("Do you want to choose offer? enter 1 to choose, 0 to quit");
        	int choice=input.nextInt();
        	input.nextLine();
        	if(choice==1)
        	{
        		System.out.println("Enter the offer ID you want to corfirm");
        		int offer_id=input.nextInt();
        		Offer.confirm_offer(offer_id);
        	}
        	else
        	{
        		return;
        	}
    	}
    	
    	
    }

    public void get_offer_notification(offer new_offer) 
    {
    	User reciever=new User();
    	reciever=user_database.get_user_by_id(new_offer.getUserID());
    	sendingEmails send_notify=new sendingEmails();
    	try 
    	{
				send_notify.send_offer_notification(reciever, new_offer);
		}
    	catch (Exception e) 
    	{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    }
    
}

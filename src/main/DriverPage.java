package main;


import java.util.ArrayList;
import java.util.Scanner;

import database.CustomerDatabase;
import database.DriverDatabase;
import database.OfferDatabase;
import database.TripDatabase;
import database.UsersDatabase;
import database.database_response;

import static java.lang.System.out;

public class DriverPage
{
    Scanner input = new Scanner(System.in);
    DriverDatabase driver_database=new DriverDatabase();
    UsersDatabase user_database=new UsersDatabase();
    CustomerDatabase customer_database =new CustomerDatabase();
    TripDatabase trip_database=new TripDatabase();
    OfferDatabase offer_database=new OfferDatabase();
    public void addFavoriteArea(String UserID)
    {
    	database_response status=new database_response();
        String area = null;
        out.println("Enter area you want to add ");
        area = input.next();
        
        status=driver_database.add_fev_area(UserID, area);
        if(status.getStatus())
        {
        	System.out.println("Added successfully");
        }
        else
        {
        	System.out.println("Something went wrong, please try later!");
        }

        
    }

    public void listUserRating(String UserID)
    {
    	ArrayList<rate> rates=new ArrayList<rate>();
    	rates =driver_database.get_all_rates(UserID);
    	for(int i=0;i<rates.size();i++)
    	{
    		String customer_id=rates.get(i).getUser_id();
    		System.out.println("customer name : "+user_database.get_user_by_id(customer_id).getUsername());
    		System.out.println("Rate : "+rates.get(i).getRate());

            out.println();
            out.println("_________________________________________________________________________________________________");
    	}
    	

    }
    
    public void list_favorite_trips(String UserID)
    {
    	ArrayList<favorite_area>favorite_areas=new ArrayList<favorite_area>();
    	
    	favorite_areas=driver_database.get_favorite_areas(UserID);
    	for(int i=0;i<favorite_areas.size();i++)
    	{
    		ArrayList<trip>available_trips=new ArrayList<trip>();
    		available_trips=trip_database.get_trips_by_source(favorite_areas.get(i).getLocation());
    		System.out.println((i+1)+") "+favorite_areas.get(i).getLocation()+" : ");
    		if(available_trips.size()==0)
    		{
    			System.out.println("	 No current trips!!");
    		}
    		else
    		{
    			for(int j=0;j<available_trips.size();j++)
        		{
        			System.out.println("  Trip num "+(j+1)+" :");
        			trip current_trip=new trip();
        			current_trip.view_trip(available_trips.get(j).getTripID());
        		   System.out.println("_______________________________________________________________");
        		}
    		}
    		
    	}
    }
    
    public void make_offer(String UserID)
    {
    	offer new_offer=new offer();
    	trip current_trip=new trip();
    	current_trip.view_all();
    	System.out.println("Enter trip ID you want to offer");
    	int trip_id=input.nextInt();
    	input.nextLine();
    	System.out.println("Enter the money amount you want to offer");
    	Double money =input.nextDouble();
    	input.nextLine();
    	Boolean status=new_offer.make_offer(UserID,trip_id,money);
    	if(status)
    	{
    		System.out.println("You will recieve an email if the user accepts.");
    	}
    	else
    	{
    		System.out.println("Something went wrong, please try later!");
    	}
    	
    }
    public void list_all_trips()
    {
    	
    		ArrayList<trip>available_trips=new ArrayList<trip>();
    		available_trips=trip_database.get_all();
    		if(available_trips.size()==0)
    		{
    			System.out.println("	 No current trips!!");
    		}
    		else
    		{
    			for(int j=0;j<available_trips.size();j++)
        		{
        			System.out.println("  Trip num "+(j+1)+" :");
        			trip current_trip=new trip();
        			current_trip.view_trip(available_trips.get(j).getTripID());
        		   System.out.println("_______________________________________________________________");
        		}
    		}
    		
    }
    
    public void get_trip_notification(trip new_trip) 
    {
    	ArrayList <User>all_drivers=new ArrayList<User>();
    	all_drivers=driver_database.get_all_drivers("1");
    	sendingEmails send_notify=new sendingEmails();
    	for(int i=0;i<all_drivers.size();i++)
    	{
    		try {
				send_notify.send_trip_notification(all_drivers.get(i), new_trip);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    }
}

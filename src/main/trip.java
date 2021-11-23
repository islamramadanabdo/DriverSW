package main;

import static java.lang.System.out;

import java.util.ArrayList;
import java.util.Scanner;

import database.TripDatabase;
import database.UsersDatabase;

public class trip 
{
	private int tripID;
	private String UserID;
	private String sourcee;
	private String distination;
	private int confirmed;
	private TripDatabase database=new TripDatabase();
	private UsersDatabase user_database = new UsersDatabase();
	public int getTripID() {
		return tripID;
	}
	public void setTripID(int tripID) {
		this.tripID = tripID;
	}
	public String getUserID() {
		return UserID;
	}
	public void setUserID(String userID) {
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
	public int getConfirmed() {
		return confirmed;
	}
	public void setConfirmed(int confirmed) {
		this.confirmed = confirmed;
	}
	
	public void create_trip(String UserId)
	{
		Scanner input=new Scanner(System.in);
		trip new_trip=new trip();
        out.println("request a Ride ");
        out.println("Enter source area`s name");
        new_trip.setSourcee(input.next());
        out.println("Enter destination area`s name");
        new_trip.setDistination(input.next());
        new_trip.setUserID(UserId);
        database.create_trip(new_trip);
        DriverPage d=new DriverPage();
        d.get_trip_notification(new_trip);
        
	}
	public void view_trip(int trip_id)
	{
		trip current_trip=new trip();
		current_trip = database.get_trip_by_id(trip_id);
		System.out.println("	 Trip ID: "+current_trip.getTripID());
		System.out.println("	 Source: "+current_trip.getSourcee());
		System.out.println("	 Destination: "+current_trip.getDistination());
		User current_user=user_database.get_user_by_id(current_trip.getUserID());
		System.out.println("	 Customer name: "+current_user.getUsername());
	}
	
	
	public void view_all()
	{
	  ArrayList<trip>all_trips=new ArrayList<trip>();
	  all_trips=database.get_all();
	  for(int i=0;i<all_trips.size();i++)
	  {
		  System.out.println("		*****");
		  System.out.println("	Trip Source : "+all_trips.get(i).getSourcee());
		  System.out.println("  Trip Destination : "+all_trips.get(i).getDistination());
		  System.out.println("  Trip ID : "+all_trips.get(i).getTripID());
		  User customer =new User();
		  customer=user_database.get_user_by_id(all_trips.get(i).getUserID());
		  System.out.println("  Trip Customer Name : "+customer.getUsername());
	  }
	  System.out.println("		*****");
	 
	}
	
	public void view_user_trip(String UserID)
	{
		ArrayList<trip>all_trips=new ArrayList<trip>();
		  all_trips=database.get_user_trips(UserID);
		  for(int i=0;i<all_trips.size();i++)
		  {
			  System.out.println("		*****");
			  System.out.println("	Trip Source : "+all_trips.get(i).getSourcee());
			  System.out.println("  Trip Destination : "+all_trips.get(i).getDistination());
			  System.out.println("  Trip ID : "+all_trips.get(i).getTripID());
			  User customer =new User();
			  customer=user_database.get_user_by_id(all_trips.get(i).getUserID());
			  System.out.println("  Trip Customer Name : "+customer.getUsername());
		  }
		  System.out.println("		*****");
	}
	
	public void view_trip(trip current_trip)
	{
		 System.out.println("		*****");
		  System.out.println("	Trip Source : "+current_trip.getSourcee());
		  System.out.println("  Trip Destination : "+current_trip.getDistination());
		  System.out.println("  Trip ID : "+current_trip.getTripID());
		  User customer =new User();
		  customer=user_database.get_user_by_id(current_trip.getUserID());
		  System.out.println("  Trip Customer Name : "+customer.getUsername());
	}
	
	
}

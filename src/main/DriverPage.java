package main;


import java.util.ArrayList;
import java.util.Scanner;

import database.DriverDatabase;
import database.UsersDatabase;
import database.database_response;

import static java.lang.System.out;

public class DriverPage
{
    Scanner input = new Scanner(System.in);
    public void addFavoriteArea(String UserID)
    {
    	DriverDatabase database=new DriverDatabase();
    	database_response status=new database_response();
        String area = null;
        out.println("Enter area you want to add ");
        area = input.next();
        
        status=database.add_fev_area(UserID, area);
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
    	UsersDatabase user_database=new UsersDatabase();
    	DriverDatabase driver_database=new DriverDatabase();
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
}

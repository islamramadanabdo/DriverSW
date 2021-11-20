package main;

import java.util.ArrayList;
import java.util.Scanner;

import database.CustomerDatabase;
import database.DriverDatabase;
import database.UsersDatabase;
import database.database_response;

import static java.lang.System.out;

public class AdminPage {
  Scanner input = new Scanner(System.in);

  //Done
  public void approveDriver() 
  {
    listDrivers("0");
    verifyPendingDrivers();
  }
//Done
  public void listDrivers(String approved) 
  {
	  DriverDatabase driver_database=new DriverDatabase();
	  ArrayList<User>all_drivers=new ArrayList<User>();
	  all_drivers=driver_database.get_all_drivers(approved);
	  for(int i=0;i<all_drivers.size();i++)
	  {
		  System.out.println("Driver no. "+(i+1));
		  System.out.println("   Driver ID : "+all_drivers.get(i).getUserID());
		  System.out.println("   Driver name :"+all_drivers.get(i).getUsername());
		  System.out.println("   Driver email : "+all_drivers.get(i).getEmail());
		  System.out.println("   Driver mobile : "+all_drivers.get(i).getMobile());
		  System.out.println("   Driver License : "+all_drivers.get(i).getLicense());
		  System.out.println("   Driver National ID : "+all_drivers.get(i).getNationalID());
		  System.out.println("   Approved status : "+all_drivers.get(i).getApproved());
		  System.out.println("_______________________________________________");
	  }
  
  }
//Done this is the approve 
  public void verifyPendingDrivers() {
	  String UserID;int choice;
	  database_response response=new database_response();
	  UsersDatabase database=new UsersDatabase();
	  do
	  {
		  out.println("Enter Driver ID you want to approve");
	       UserID = input.nextLine();
	       response=database.approve_or_susbend(UserID, "1");
	       if(response.getStatus())
	       {
	    	   out.println("if you want to continue press 1 ");
	 	      choice=input.nextInt();
	 	      input.nextLine();
	       }
	       else
	    	   
	       {
	    	   System.out.println("Something went wron, please try later!");
	    	   choice=0;
	       }
	      
	  }
	  while(choice==1);
	 
  }

//Done
  public void SuspendDriverUsers() {
    listDriverUsers();
    PendingDriversUsers();
  }
//Done
  public void listDriverUsers() 
  {
	  DriverDatabase driver_database = new DriverDatabase();
      CustomerDatabase  customer_database =new CustomerDatabase();
	  try 
	  {
		  ArrayList<User>all_drivers=new ArrayList<User>();
		  ArrayList<User>all_customers=new ArrayList<User>();
		  all_drivers=driver_database.get_all_drivers("1");
		  all_customers=customer_database.get_all_customers();
	       System.out.println("***Drivers***");
		  for(int i=0;i<all_drivers.size();i++)
	    	{
		        out.print("   ID = " +all_drivers.get(i).getUserID());
		        out.print("   Name = " + all_drivers.get(i).getUsername());
		        out.print("   Email = " + all_drivers.get(i).getEmail());
		        out.print("   Mobile = " + all_drivers.get(i).getMobile());
		        out.print("   License = " + all_drivers.get(i).getLicense());
		        out.print("   NationalID = " + all_drivers.get(i).getNationalID());
		        out.print("   Approved = " + all_drivers.get(i).getApproved());
		        out.println();
		        out.println("____________________________________________________________________");

	         }
		     System.out.println("***Users***");
	    	for(int i=0;i<all_customers.size();i++)
	    	{	      
		        out.print("   ID = " +all_customers.get(i).getUserID());
		        out.print("   Name = " + all_customers.get(i).getUsername());
		        out.print("   Email = " + all_customers.get(i).getEmail());
		        out.print("   Mobile = " + all_customers.get(i).getMobile());
		        out.print("   License = " + all_customers.get(i).getLicense());
		        out.print("   NationalID = " + all_customers.get(i).getNationalID());
		        out.print("   Approved = " + all_customers.get(i).getApproved());
		        out.println();
		        out.println("__________________________________________________________________________");
	
	        }
    	
       } 
	  catch( Exception ex)
	  {
	      //ex.printStackTrace();
	      System.out.println(ex.getMessage());
	    
	   } 
	 
  
  }
  //Done, this is the suspend
  public void PendingDriversUsers() {
	  String UserID;int choice;
	  database_response response=new database_response();
	  UsersDatabase database=new UsersDatabase();
	  do
	  {
		  out.println("Enter user(Customer/Driver) ID you want to pending");
	       UserID = input.nextLine();
	       response=database.approve_or_susbend(UserID, "-1");
	       if(response.getStatus())
	       {
	    	   out.println("if you want to continue press 1 ");
	 	      choice=input.nextInt();
	 	      input.nextLine();
	       }
	       else
	    	   
	       {
	    	   System.out.println("Something went wron, please try later!");
	    	   choice=0;
	       }
	      
	  }
	  while(choice==1);
	 
  }
}

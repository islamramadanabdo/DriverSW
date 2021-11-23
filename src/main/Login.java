package main;

import static java.lang.System.out;
import java.sql.*;
import java.util.Objects;
import java.util.Scanner;

import database.UsersDatabase;

public class Login
{
    Scanner input = new Scanner(System.in);
    UsersDatabase database=new UsersDatabase();
    AdminPage a = new AdminPage();
    DriverPage d = new DriverPage();
    UserPage u = new UserPage();
    public void login()
    {
        String username=null , upassword=null , role = null , userID=null;
        User current_user=new User();
        out.println("Welcome in Login page");
        out.println("Please enter your name :- ");
        username = input.nextLine();
        out.println("Please enter your password :- ");
        upassword = input.nextLine();
        current_user = database.read_user(username ,upassword );
        if(current_user.getApproved()==null)
        {
        	return;
        }
        if (current_user.getApproved() == "-1")
        {
        	System.out.println("Your account has been suspended, so you can not login or use any other features.");
        	
        }
        else if(current_user.getApproved().equals("0"))
        {
        	System.out.println("Please wait for the admin to approve your profile");
        }
        else if(current_user.getApproved()==null)
        {
        	System.out.println("This account does not exist, please make sure you wrote the right name and password or register.");
        }
        else
        {
            userID =Integer.toString(current_user.getUserID());
            role = current_user.getRole();

            if(Objects.equals(role, "Admin"))
            {
                while (true)
                {
                    System.out.println("Admin page");
                    System.out.println("[1] View all Users");
                    System.out.println("[2] Approve Drivers");
                    System.out.println("[3] suspend Drivers and Users");
                    System.out.println("[4] Exit.");
                    System.out.println("Please Enter your choice ");
                    int adminChoice = input.nextInt();
                    input.nextLine();
                    switch (adminChoice)
                    {
	                    case 1:
	                    {
	                    	a.listDriverUsers();
	                    	break;
	                    }
                        case 2:
                        {
                        	a.approveDriver();
                            break;
                        }
                            
                        case 3:
                        {
                        	a.SuspendDriverUsers();

                            break;
                        }
                        case 4:
                        {
                        	 out.println("GOOD BYE");
                             System.exit(0);
                        }
                        default:
                        {
                        	System.out.println("Invalid choice!");
                        }
                           

                    }
                }
            }
            if(Objects.equals(role, "User"))
            {
                while (true)
                {
                    System.out.println("User page");
                    System.out.println("[1] request a Ride");
                    System.out.println("[2] List trip offers and confirm offer");
                    System.out.println("[3] View Driver profile and his rate");
                    System.out.println("[4] rate Drivers");
                    System.out.println("[5] Exit");
                    System.out.println("Please Enter your choice ");
                    int adminChoice = input.nextInt();
                    input.nextLine();
                    switch (adminChoice)
                    {
                        case 1:
                        {
                        	 u.requestRide(userID);
                             break;
                        }
                        case 2:
                        {
                        	 u.list_offers(userID);
                             break;
                        } 
                        case 3:
                        {
                        	 u.view_driver_profile();
                             break;
                        } 
                        case 4:
                        {
                        	 u.rateDriver(userID);
                             break;
                        }
                           
                        case 5:
                        {
                        	 out.println("GOOD BYE");
                             System.exit(0);	
                        }
                        default:
                        {
                        	System.out.println("Invalid choice!");                    }
                    }
                }
            }

            if(Objects.equals(role, "Driver"))
            {
                while (true)
                {
                    System.out.println("Driver page");
                    System.out.println("[1] add Favorite Area ");
                    System.out.println("[2] list Rating");
                    System.out.println("[3] List all available trips");
                    System.out.println("[4] List available trips of my favorite areas");
                    System.out.println("[5] Make offer of specific trip");
                    System.out.println("[6] Exit");
                    System.out.println("Please Enter your choice ");
                    int adminChoice = input.nextInt();
                    switch (adminChoice)
                    {
                        case 1:
                        {
                        	 d.addFavoriteArea(userID);
                             break;
                        }
                           
                        case 2:
                        {
                        	d.listUserRating(userID);
                            break;
                        }
                        
                        case 3:
                        {
                        	d.list_all_trips();
                        	break;
                        }
                        case 4:
                        {
                        	d.list_favorite_trips(userID);
                        	break;
                        }
                        case 5:
                        {
                        	d.make_offer(userID);
                        	 break;
                        }
                        case 6:
                        {
                        	out.println("GOOD BYE");
                            System.exit(0);
                        }

                        default:
                        {
                        	System.out.println("Invalid choice!");
                        	break;
                        }
                           

                    }
                }
            }
        }
      
        

    }
}

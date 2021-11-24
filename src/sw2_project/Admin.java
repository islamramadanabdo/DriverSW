/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sw2_project;

/**
 *
 * @author HP
 */
import java.util.ArrayList;
import java.util.Scanner;

import database.CustomerDatabase;
import database.DriverDatabase;
import database.UsersDatabase;
import database.DriversDatabase;
import database.database_response;
import static java.lang.System.out;

public class Admin {

    Scanner input = new Scanner(System.in);

    public void listAllPendingDrivers() {
        DriverDatabase driver_database = new DriverDatabase();
        CustomerDatabase customer_database = new CustomerDatabase();
        try {
            ArrayList<driver> all_drivers = new ArrayList<driver>();
            ArrayList<user> all_customers = new ArrayList<user>();

            all_drivers = driver_database.get_all_drivers("1");
            all_customers = customer_database.get_all_customers();

            System.out.println("***Drivers***");
            for (int i = 0; i < all_drivers.size(); i++) {
                out.print("   ID = " + all_drivers.get(i).getId());
                out.print("   Name = " + all_drivers.get(i).getUsername());
                out.print("   Email = " + all_drivers.get(i).getEmail());
                out.print("   Mobile = " + all_drivers.get(i).getPhone());
                out.print("   License = " + all_drivers.get(i).getDrivingLicence());
                out.print("   NationalID = " + all_drivers.get(i).getNationalID());
                out.print("   Approved = " + all_drivers.get(i).getApproved());
                out.println();
                out.println("____________________________________________________________________");

            }
            System.out.println("***Users***");
            for (int i = 0; i < all_customers.size(); i++) {
                out.print("   ID = " + all_customers.get(i).getId());
                out.print("   Name = " + all_customers.get(i).getUsername());
                out.print("   Email = " + all_customers.get(i).getEmail());
                out.print("   Mobile = " + all_customers.get(i).getPhone());
                out.print("   Approved = " + all_customers.get(i).getApproved());
                out.println();
                out.println("__________________________________________________________________________");

            }

        } catch (Exception ex) {
            //ex.printStackTrace();
            System.out.println(ex.getMessage());

        }
    }

    public void verifyPendingDrivers() {
        String UserID;
        int choice;
        database_response response = new database_response();
        DriversDatabase database = new DriversDatabase();
        do {
            out.println("Enter Driver ID you want to approve");
            UserID = input.nextLine();
            response = database.approve_or_susbend(UserID, "1");

            if (response.getStatus()) {
                out.println("if you want to continue press 1 ");
                choice = input.nextInt();
                input.nextLine();
            } else {
                System.out.println("Something went wron, please try later!");
                choice = 0;
            }

        } while (choice == 1);

    }

    public void listDriverUsers() {
        DriverDatabase driver_database = new DriverDatabase();
        CustomerDatabase customer_database = new CustomerDatabase();
        try {
            ArrayList<driver> all_drivers = new ArrayList<driver>();
            ArrayList<user> all_customers = new ArrayList<user>();
            all_drivers = driver_database.get_all_drivers("1");
            all_customers = customer_database.get_all_customers();
            System.out.println("***Drivers***");
            for (int i = 0; i < all_drivers.size(); i++) {
                out.print("   ID = " + all_drivers.get(i).getId());
                out.print("   Name = " + all_drivers.get(i).getUsername());
                out.print("   Email = " + all_drivers.get(i).getEmail());
                out.print("   Mobile = " + all_drivers.get(i).getPhone());
                out.print("   License = " + all_drivers.get(i).getDrivingLicence());
                out.print("   NationalID = " + all_drivers.get(i).getNationalID());
                out.print("   Approved = " + all_drivers.get(i).getApproved());
                out.println();
                out.println("____________________________________________________________________");

            }
            System.out.println("***Users***");
            for (int i = 0; i < all_customers.size(); i++) {
                out.print("   ID = " + all_customers.get(i).getId());
                out.print("   Name = " + all_customers.get(i).getUsername());
                out.print("   Email = " + all_customers.get(i).getEmail());
                out.print("   Mobile = " + all_customers.get(i).getPhone());
                out.print("   Approved = " + all_customers.get(i).getApproved());
                out.println();
                out.println("__________________________________________________________________________");

            }

        } catch (Exception ex) {
            //ex.printStackTrace();
            System.out.println(ex.getMessage());

        }

    }
    //Done, this is the suspend

    public void PendingDriversUsers() {
        String UserID;
        int choice;
        database_response response = new database_response();
        DriversDatabase database = new DriversDatabase();
        do {
            out.println("Enter user(Customer/Driver) ID you want to pending");
            UserID = input.nextLine();
            response = database.approve_or_susbend(UserID, "-1");
            if (response.getStatus()) {
                out.println("if you want to continue press 1 ");
                choice = input.nextInt();
                input.nextLine();
            } else {
                System.out.println("Something went wron, please try later!");
                choice = 0;
            }

        } while (choice == 1);

    }

    public void suspend(Person p) {
        listDriverUsers();
        PendingDriversUsers();
    }

}

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
import java.util.regex.Pattern;
import static java.lang.System.out;
import java.util.Scanner;
import database.UsersDatabase;
import database.DriversDatabase;

abstract class Person {
    
    public int id;
    public String username;
    public String phone;
    public String email;
    public String password;
    public String Role;
    public String approved;
    
    public Person() {
        
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getApproved() {
        return approved;
    }
    
    public void setApproved(String approved) {
        this.approved = approved;
    }
    
    public String getRole() {
        return Role;
    }
    
    public void setRole(String Role) {
        this.Role = Role;
    }
    
    public String getUsername() {
        return username;
    }
    
    public String getPhone() {
        return phone;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void login() {
        {
            DriversDatabase databaseD = new DriversDatabase();
            
            String username = null, upassword = null, role = null, userID = null;
            
            driver current_driver = new driver();
            
            Scanner input = new Scanner(System.in);
            
            out.println("Welcome in Login page");
            out.println("Please enter your name :- ");
            username = input.nextLine();
            
            out.println("Please enter your password :- ");
            upassword = input.nextLine();
            
            current_driver = databaseD.read_driver(username, upassword);
            
            if (current_driver.getApproved() == null) {
                return;
            }
            if (current_driver.getApproved() == "-1") {
                System.out.println("Your account has been suspended, so you can not login or use any other features.");
                
            } else if (current_driver.getApproved().equals("0")) {
                System.out.println("Please wait for the admin to approve your profile");
            } else if (current_driver.getApproved() == null) {
                System.out.println("This account does not exist, please make sure you wrote the right name and password or register.");
            } else {
                userID = Integer.toString(current_driver.getId());
                role = current_driver.getRole();
                
                if (role.equals("Admin")) {
                    Admin a = new Admin();
                    while (true) {
                        System.out.println("Admin page");
                        System.out.println("[1] View all Users");
                        System.out.println("[2] Approve Drivers");
                        System.out.println("[3] suspend Drivers and Users");
                        System.out.println("[4] Exit.");
                        System.out.println("Please Enter your choice ");
                        int adminChoice = input.nextInt();
                        input.nextLine();
                        switch (adminChoice) {
                            case 1: {
                                
                                a.listAllPendingDrivers();
                                break;
                            }
                            case 2: {
                                a.verifyPendingDrivers();
                                break;
                            }
                            
                            case 3: {
                                a.suspend(this);
                                
                                break;
                            }
                            case 4: {
                                out.println("GOOD BYE");
                                System.exit(0);
                            }
                            default: {
                                System.out.println("Invalid choice!");
                            }
                            
                        }
                    }
                }
                if (role.equals("User")) {
                    userAvtivities u = new userAvtivities();
                    while (true) {
                        System.out.println("User page");
                        System.out.println("[1] request a Ride");
                        System.out.println("[2] List trip offers and confirm offer");
                        System.out.println("[3] View Driver profile and his rate");
                        System.out.println("[4] rate Drivers");
                        System.out.println("[5] Exit");
                        System.out.println("Please Enter your choice ");
                        int adminChoice = input.nextInt();
                        input.nextLine();
                        userAvtivities userActi = new userAvtivities();
                        switch (adminChoice) {
                            case 1: {
                                u.requestTrip(userID);
                                break;
                            }
                            case 2: {
                                u.list_offers(userID);
                                break;
                            }
                            case 3: {
                                u.view_driver_profile();
                                break;
                            }
                            case 4: {
                                u.rateDriver(userID);
                                break;
                            }
                            
                            case 5: {
                                out.println("GOOD BYE");
                                System.exit(0);
                            }
                            default: {
                                System.out.println("Invalid choice!");
                            }
                        }
                    }
                }
            }
            
            if (role.equals("Driver")) {
                driverActivities d = new driverActivities();
                while (true) {
                    System.out.println("Driver page");
                    System.out.println("[1] add Favorite Area ");
                    System.out.println("[2] list Rating");
                    System.out.println("[3] List all available trips");
                    System.out.println("[4] List available trips of my favorite areas");
                    System.out.println("[5] Make offer of specific trip");
                    System.out.println("[6] Exit");
                    System.out.println("Please Enter your choice ");
                    int adminChoice = input.nextInt();
                    switch (adminChoice) {
                        case 1: {
                            d.addFavoriteArea(userID);
                            break;
                        }
                        
                        case 2: {
                            d.listUserRate(userID);
                            break;
                        }
                        case 3: {
//                                 list all trip of favorities area
                            d.list_all_trips();
                            break;
                        }
                        case 4: {
//                                 list all trip of favorities area
                            d.list_favorite_trips(userID);
                            break;
                        }
                        
                        case 5: {
//                                 make offer of spacific trip
                            d.make_offer(userID);
                            break;
                        }
                        
                        case 6: {
                            out.println("GOOD BYE");
                            System.exit(0);
                        }
                        
                        default: {
                            System.out.println("Invalid choice!");
                        }
                        
                    }
                }
            }
        }
        
    }
}


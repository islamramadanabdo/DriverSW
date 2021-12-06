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
import java.sql.SQLException;
import java.util.Scanner;
import static java.lang.System.out;

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.println("[1] Register as user");
            System.out.println("[2] Register as driver ");
            System.out.println("[3] Login ");
            System.out.println("Please Enter your choice ");
            int choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1: {
                   
                   Register new_user = new user();
                    new_user.register();
                    break;
                }

                 case 2: {
                   
                   driver new_driver = new driver();
                    new_driver.register();            
                    break;
                }
                case 3: {
                    Person P = new driver();
                    P.login();
                    break;

                }

                default: {
                    out.println("GOOD BYE");
                    System.exit(0);
                }

            }
        }

    }
}

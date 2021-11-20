package main;

import java.sql.SQLException;
import java.util.Scanner;
import static java.lang.System.out;

public class Main {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        while (true)
        {
	        System.out.println("[1] Register");
	        System.out.println("[2] Login ");
	        Scanner input = new Scanner(System.in);
	        System.out.println("Please Enter your choice ");
	        int choice = input.nextInt();
	        input.nextLine();
	        switch (choice)
	        {
		        case 1:
		        {
		        	Register r = new Register();
		            r.takeInputs();
		            break;
		        }
		             
		        case 2:
		        {
	        	 Login l = new Login();
	             l.login();
		             break;
	
		        }
		           
		        default:
		        {
		        	out.println("GOOD BYE");
		            System.exit(0);
		        }
	        
	        }
        }





    }
}

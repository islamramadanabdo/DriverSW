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
import java.sql.SQLException;
import java.util.Scanner;
import static java.lang.System.out;
import database.UsersDatabase;

public class user extends Person implements Register {

    public user() {
     
       
    }


    @Override
    public void register() {
        Scanner input = new Scanner(System.in);
        user new_user = new user();
        UsersDatabase database = new UsersDatabase();
        int checkpass = 0;
        String email, number, upassword = null;
        out.println("Welcome in Register page");
        out.println("Please enter your name :- ");
        new_user.setUsername(input.nextLine());
        while (checkpass == 0) {
            out.println("Please enter your password");
            upassword = input.nextLine();
            checkpass = check_password(upassword);
        }
        new_user.setPassword(upassword);
        do {
            out.println("Please enter your email");
            email = input.nextLine();
            if (isEmail(email) == false) {
                out.println("Enter valid mail");
            }
        } while (isEmail(email) == false);
        new_user.setEmail(email);
        do {
            out.println("Please enter your mobile number");
            number = input.nextLine();
            if (isNumber(number) == false) {
                out.println("Enter valid mobile number");
            }
        } while (isNumber(number) == false);
        new_user.setPhone(number);
        new_user.setApproved("1");
        new_user.setRole("User");
//        out.println("Thanks for your regestration ... welcome in our site ");
        database.create_user(new_user);
    }

    public int check_password(String password) {
        boolean hasLetter = false;
        boolean hasDigit = false;
        if (password.length() >= 8) {
            for (int i = 0; i < password.length(); i++) {
                char x = password.charAt(i);
                if (Character.isLetter(x)) {
                    hasLetter = true;
                } else if (Character.isDigit(x)) {
                    hasDigit = true;
                }
                if (hasLetter && hasDigit) {
                    break;
                }
            }
            if (hasLetter && hasDigit) {
                System.out.println("STRONG");
                return 1;
            } else {
                System.out.println("NOT STRONG");
                return 0;
            }
        } else {
            System.out.println("HAVE AT LEAST 8 CHARACTERS");
            return 0;
        }

    }

    public boolean isEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."
                + "[a-zA-Z0-9_+&*-]+)*@"
                + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
                + "A-Z]{2,7}$";
        Pattern p = Pattern.compile(emailRegex);
        if (email == null) {
            return false;
        }
        return p.matcher(email).matches();
    }

    public boolean isNumber(String number) {
        char ch1 = number.charAt(0);
        char ch2 = number.charAt(1);
        return number.length() == 11 && ch1 == '0' && ch2 == '1';

    }

    public void insetMembersInDB(String username, String upassword, String email, String number, String license, String nID, String role, String approved) {
    }
    
    
   

}

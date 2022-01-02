package com.example.last_try.Database;


import com.example.last_try.Models.User;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;

@Repository("user_DB")
public class UserDatabase implements PersonDatabase
{
    //Done
    @Override
    public void create(User new_user) {


        java.sql.Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = (java.sql.Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/advancedsw", "root", "");


            String query = " insert into driver ( username, password,  email, mobile,license , nationalID , role ,  approved)"
                    + " values ( ?,?, ?, ?, ? , ? , ?, ?)";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, new_user.getUsername());
            preparedStmt.setString(2, new_user.getPassword());
            preparedStmt.setString(3, new_user.getEmail());
            preparedStmt.setString(4, new_user.getPhone());
            preparedStmt.setString(5, "");
            preparedStmt.setString(6, "");

            preparedStmt.setString(7, new_user.getRole());
            preparedStmt.setString(8, new_user.getApproved());
            try {

                preparedStmt.execute();

            } catch (Exception e) {

            }

            conn.close();
        } catch (ClassNotFoundException  | SQLException ex) {
            ex.printStackTrace();
            System.out.println(" faild not connected");
        }

    }
    //Done
    @Override
    public ArrayList<User> get_all()
    {
            ArrayList<User>all=new ArrayList<User>();
            try {

                Class.forName("com.mysql.jdbc.Driver");
                String url = "jdbc:mysql://localhost:3306/advancedSW";
                String user = "root";
                String password = "";
                Connection Con = null;
                Con = DriverManager.getConnection(url, user, password);

                String sql = "SELECT * FROM driver WHERE role = ? ";
                PreparedStatement statement = Con.prepareStatement(sql);
                statement.setString(1, "User");
                ResultSet result = statement.executeQuery();
                while (result.next())
                {
                    User customer= new User(0,null,null,null,null,null,null);
                    customer.setId( result.getInt("UserID"));
                    customer.setUsername(result.getString("username"));
                    customer.setEmail( result.getString("email"));
                    customer.setPhone(result.getString("mobile"));
                    customer.setApproved(result.getString("approved"));
                    all.add(customer);



                }
            } catch (ClassNotFoundException | SQLException ex) {
                //ex.printStackTrace();
                System.out.println("not connected");
            }
            return all;

    }

    //Done
    @Override
    public User read_user(String uname, String upassword) {
        User current_user=new User(0,null,null,null,null,null,null);
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/advancedSW";
            String user = "root";
            String password = "";
            Connection Con = null;
            Con = DriverManager.getConnection(url, user, password);

            String sql = "SELECT * FROM driver WHERE username = ? and password = ?";
            PreparedStatement statement = Con.prepareStatement(sql);
            statement.setString(1, uname);
            statement.setString(2, upassword);
            ResultSet result = statement.executeQuery();
            if(!result.next())
            {
                System.out.println("can't find this user");

            }
            else
            {
                current_user.setRole( result.getString("role"));
                current_user.setApproved(result.getString("approved"));
                current_user.setId(result.getInt("UserID"));
                current_user.setEmail(result.getString("email"));
                current_user.setPhone(result.getString("mobile"));
                current_user.setUsername(uname);
                current_user.setPassword(upassword);
                return current_user;
            }



        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            System.out.println("not connected");
        }

        return current_user;
    }

    //Done
    @Override
    public User get_by_id(int UserID)
    {
        User current_user=new User();
        java.sql.Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");

            conn = (java.sql.Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/advancedsw", "root", "");

            String sql = "SELECT * FROM driver WHERE UserID = ? ";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, UserID);
            ResultSet result = statement.executeQuery();
            if(!result.next())
            {
                System.out.println("can't find this user");

            }
            else
            {
                current_user.setRole( result.getString("role"));
                current_user.setApproved(result.getString("approved"));
                current_user.setId(result.getInt("UserID"));
                current_user.setEmail(result.getString("email"));
                current_user.setPhone(result.getString("mobile"));
                current_user.setUsername(result.getString("username"));
                current_user.setPassword(result.getString("password"));

                //          return current_user;
            }



        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            System.out.print("not connected");
        }

        return current_user;
    }


}

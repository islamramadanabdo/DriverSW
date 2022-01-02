package com.example.last_try.Database;
import com.example.last_try.Models.*;


import java.sql.*;
import java.util.ArrayList;

import static java.lang.System.out;

public class CustomerDatabase 
{
	public ArrayList<User>get_all_customers()
	{
		ArrayList<User>all_customers=new ArrayList<User>();
		  try {
			   
	            Class.forName("com.mysql.jdbc.Driver");
	            String url = "jdbc:mysql://localhost:3306/advancedSW";
	            String user = "root";
	            String password = "root";
	            Connection Con = null;
	            Con = DriverManager.getConnection(url, user, password);

	            String sql = "SELECT * FROM user WHERE role = ? ";
	            PreparedStatement statement = Con.prepareStatement(sql);
	            statement.setString(1, "User");
	            ResultSet result = statement.executeQuery();
	            while (result.next()) 
	            {
	            	User customer=new User();
	            	customer.setId( result.getInt("UserID"));
	                customer.setUsername(result.getString("username"));
	                customer.setEmail( result.getString("email"));
	                customer.setPhone(result.getString("mobile"));
	                customer.setApproved(result.getString("approved"));
	                all_customers.add(customer);
	                
	               

	            }
	        } catch (ClassNotFoundException | SQLException ex) {
	            //ex.printStackTrace();
	            out.print("not connected");
	        }
		return all_customers;
	}

}

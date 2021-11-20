package database;

import static java.lang.System.out;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import main.User;
import main.rate;

public class DriverDatabase 
{
	
	public  ArrayList<User> get_all_trips()
	{
		return null;
	}
	public database_response add_fev_area(String UserID,String area)
	{
		database_response response=new database_response();
		try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/advancedSW";
            String user = "root";
            String password = "root";
            Connection Con = null;
            Con = DriverManager.getConnection(url, user, password);

            String query = " insert into favorite (UserID, area)"
                    + " values (?, ?)";
           PreparedStatement preparedStmt = Con.prepareStatement(query);
            preparedStmt.setString (1, UserID);
            preparedStmt.setString (2, area);
            try
            {
            	preparedStmt.execute();
            	response.setStatus(true);
            }
            catch(Exception e)
            {
            	response.setStatus(false);
            }
            Con.close();
        } catch (ClassNotFoundException | SQLException ex) {
            //ex.printStackTrace();
            out.print("not connected");
        }
		return response;
	}
	public ArrayList<User>get_all_drivers(String approved)
	{
		ArrayList<User>drivers=new ArrayList<User>();
		
		   try {
			   
	            Class.forName("com.mysql.jdbc.Driver");
	            String url = "jdbc:mysql://localhost:3306/advancedSW";
	            String user = "root";
	            String password = "root";
	            Connection Con = null;
	            Con = DriverManager.getConnection(url, user, password);

	            String sql = "SELECT * FROM user WHERE role = ? and approved=? ";
	            PreparedStatement statement = Con.prepareStatement(sql);
	            statement.setString(1, "Driver");
	            statement.setString(2,approved);
	            ResultSet result = statement.executeQuery();
	            while (result.next()) 
	            {
	            	User Driver=new User();
	            	Driver.setUserID( result.getInt("UserID"));
	                Driver.setUsername(result.getString("username"));
	                Driver.setEmail( result.getString("email"));
	                Driver.setMobile(result.getString("mobile"));
	                Driver.setLicense( result.getString("license"));
	                Driver.setNationalID(result.getString("nationalID"));
	                Driver.setApproved(result.getString("approved"));
	                drivers.add(Driver);
	                
	               

	            }
	        } catch (ClassNotFoundException | SQLException ex) {
	            //ex.printStackTrace();
	            //out.println("not connected");
	        	System.out.println(ex.getMessage());
	        }
		   return drivers;
	}
	
	
	public ArrayList<rate>get_all_rates(String UserID)
	{
		ArrayList<rate>rates=new ArrayList<rate>();
		 try {
	            Class.forName("com.mysql.jdbc.Driver");
	            String url = "jdbc:mysql://localhost:3306/advancedSW";
	            String user = "root";
	            String password = "root";
	            Connection Con = null;
	            Con = DriverManager.getConnection(url, user, password);

	            String sql = "SELECT * FROM rate WHERE driverID = ? ";
	            PreparedStatement statement = Con.prepareStatement(sql);

	            statement.setString(1, UserID);
	            ResultSet result = statement.executeQuery();
	            while (result.next()) 
	            {
	            	rate current_rate=new rate();
	                current_rate.setDriver_id(UserID);
	                current_rate.setUser_id(result.getString("userID"));
	                current_rate.setRate(result.getString("rate"));
	                rates.add(current_rate);

	            }
	        } catch (ClassNotFoundException | SQLException ex) {
	            //ex.printStackTrace();
	            out.print("not connected");
	        }
		 return rates;
	}
	

	public database_response rate_driver(rate new_rate)
	{
		database_response response=new database_response();
		 try {
		        Class.forName("com.mysql.jdbc.Driver");
		        String url = "jdbc:mysql://localhost:3306/advancedSW";
		        String user = "root";
		        String password = "root";
		        Connection Con = null;
		        Con = DriverManager.getConnection(url, user, password);

		        String query = " insert into rate (driverID, userID ,rate )"
		                + " values (?, ? , ?)";
		        PreparedStatement preparedStmt = Con.prepareStatement(query);
		        preparedStmt.setString (1, new_rate.getDriver_id());
		        preparedStmt.setString (2, new_rate.getUser_id());
		        preparedStmt.setString (3, new_rate.getRate());
		        try
		        {
		        	 preparedStmt.execute();
		        	 response.setStatus(true);
		        }
		        catch(Exception e)
		        {
		        	response.setStatus(false);
		        }
		       
		        Con.close();
		    } catch (ClassNotFoundException | SQLException ex) {
		        //ex.printStackTrace();
		        out.print("not connected");
		        response.setStatus(false);
		    }
		 return response;
	}
	

}

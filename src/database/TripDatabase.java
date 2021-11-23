package database;

import static java.lang.System.out;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import main.rate;
import main.trip;

public class TripDatabase 
{
	public database_response create_trip(trip new_trip)
	{
		
		database_response respond=new database_response();
		 try {
	            Class.forName("com.mysql.jdbc.Driver");
	            String url = "jdbc:mysql://localhost:3306/advancedSW";
	            String user = "root";
	            String password = "root";
	            Connection Con = null;
	            Con = DriverManager.getConnection(url, user, password);

	            String query = " insert into trip (UserID, source ,destination )"
	                    + " values (?, ? , ?)";
	            PreparedStatement preparedStmt = Con.prepareStatement(query);
	            preparedStmt.setString (1,new_trip.getUserID());
	            preparedStmt.setString (2, new_trip.getSourcee());
	            preparedStmt.setString (3, new_trip.getDistination());
	            try
	            {
	            	 preparedStmt.execute();
	            	 respond.setStatus(true);
	            }
	            catch(Exception e)
	            {
	            	respond.setStatus(false);
	            }
	           
	            
	            Con.close();
	        } catch (ClassNotFoundException | SQLException ex) {
	            //ex.printStackTrace();
	            out.print("not connected");
	        }
		 return respond;
	}
	public  ArrayList<trip> get_trips_by_source(String favorite_area)
	{
		ArrayList<trip> available_trips=new ArrayList<trip>();
		  try {
			   
	            Class.forName("com.mysql.jdbc.Driver");
	            String url = "jdbc:mysql://localhost:3306/advancedSW";
	            String user = "root";
	            String password = "root";
	            Connection Con = null;
	            Con = DriverManager.getConnection(url, user, password);

	            String sql = "SELECT * FROM trip WHERE source = ? AND confirmed != 1";
	            PreparedStatement statement = Con.prepareStatement(sql);
	            statement.setString(1, favorite_area);
	            ResultSet result = statement.executeQuery();
	            while (result.next()) 
	            {
	                trip new_trip=new trip();
	            	new_trip.setSourcee(result.getString("source"));
	            	new_trip.setDistination(result.getString("destination"));
	            	new_trip.setUserID(result.getString("UserID"));
	            	new_trip.setTripID(result.getInt("tripID"));
	            	
	            	available_trips.add(new_trip);

	            }
	        } catch (ClassNotFoundException | SQLException ex) {
	            //ex.printStackTrace();
	            //out.println("not connected");
	        	System.out.println(ex.getMessage());
	        }
		return available_trips;
	}


	
	public ArrayList<trip> get_all()
	{
		ArrayList<trip>all_trips=new ArrayList<trip>();
   	 try {
	            Class.forName("com.mysql.jdbc.Driver");
	            String url = "jdbc:mysql://localhost:3306/advancedSW";
	            String user = "root";
	            String password = "root";
	            Connection Con = null;
	            Con = DriverManager.getConnection(url, user, password);

	            String query = " SELECT * FROM trip WHERE confirmed != 1";
	            PreparedStatement preparedStmt = Con.prepareStatement(query);
	            ResultSet result = preparedStmt.executeQuery();
	            while (result.next()) 
	            {
	              trip current_trip=new trip();
	              current_trip.setSourcee(result.getString("source"));
	              current_trip.setDistination(result.getString("destination"));
	              current_trip.setTripID(result.getInt("tripID"));
	              current_trip.setUserID(Integer.toString(result.getInt("userID")));
	              all_trips.add(current_trip);
	            }
	            
	            Con.close();
	        } catch (ClassNotFoundException | SQLException ex) {
	            //ex.printStackTrace();
	            out.print("not connected");
	        };
	        return all_trips;
	}
	
    public trip get_trip_by_id(int id)
    {
    	trip current_trip=new trip();
    	 try {
	            Class.forName("com.mysql.jdbc.Driver");
	            String url = "jdbc:mysql://localhost:3306/advancedSW";
	            String user = "root";
	            String password = "root";
	            Connection Con = null;
	            Con = DriverManager.getConnection(url, user, password);

	            String query = " SELECT * FROM trip WHERE tripID= ?";
	            PreparedStatement preparedStmt = Con.prepareStatement(query);
	            preparedStmt.setInt (1,id);
	            ResultSet result = preparedStmt.executeQuery();
	            while (result.next()) 
	            {
	            	
	              current_trip.setSourcee(result.getString("source"));
	              current_trip.setDistination(result.getString("destination"));
	              current_trip.setTripID(result.getInt("tripID"));
	              current_trip.setUserID(Integer.toString(result.getInt("userID")));

	            }
	            
	            Con.close();
	        } catch (ClassNotFoundException | SQLException ex) {
	            //ex.printStackTrace();
	            out.print("not connected");
	        }
    	 return current_trip;
    }
    
    public ArrayList<trip>get_user_trips(String UserID)
    {
    	ArrayList<trip>all_trips=new ArrayList<trip>();
    	 try {
	            Class.forName("com.mysql.jdbc.Driver");
	            String url = "jdbc:mysql://localhost:3306/advancedSW";
	            String user = "root";
	            String password = "root";
	            Connection Con = null;
	            Con = DriverManager.getConnection(url, user, password);

	            String query = " SELECT * FROM trip WHERE userID= ? And confirmed!= 1";
	            PreparedStatement preparedStmt = Con.prepareStatement(query);
	            preparedStmt.setString(1,UserID);
	            ResultSet result = preparedStmt.executeQuery();
	            while (result.next()) 
	            {
	               trip current_trip=new trip();   	
	              current_trip.setSourcee(result.getString("source"));
	              current_trip.setDistination(result.getString("destination"));
	              current_trip.setTripID(result.getInt("tripID"));
	              current_trip.setUserID(Integer.toString(result.getInt("userID")));
	              all_trips.add(current_trip);
	            }
	            
	            Con.close();
	        } catch (ClassNotFoundException | SQLException ex) {
	            //ex.printStackTrace();
	            out.print("not connected");
	        }
    	
    	return all_trips;
    }
    
    public database_response confirm_trip(int trip_id)
    {
    	database_response response=new database_response();
    	 try {
	            Class.forName("com.mysql.jdbc.Driver");
	            String url = "jdbc:mysql://localhost:3306/advancedSW";
	            String user = "root";
	            String password = "root";
	            Connection Con = null;
	            Con = DriverManager.getConnection(url, user, password);

	            String query = " Update trip set confirmed='1' WHERE tripID= ?";
	            PreparedStatement preparedStmt = Con.prepareStatement(query);
	            preparedStmt.setInt(1,trip_id);
	            response.setStatus( preparedStmt.execute());
	           
	            Con.close();
	        } catch (ClassNotFoundException | SQLException ex) {
	          System.out.println(ex.getMessage());
	          response.setStatus(false);
	        }
 	return response;
    }
}

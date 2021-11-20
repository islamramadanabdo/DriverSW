package database;

import static java.lang.System.out;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

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
	public trip read_trip()
	{
		return null;
	}
	public ArrayList<trip> get_all()
	{
		return null;
	}
	

}

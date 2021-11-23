package database;

import static java.lang.System.out;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import main.offer;

public class OfferDatabase 
{

	public database_response create(offer new_offer)
	{
		database_response response=new database_response();
		 try {
		        Class.forName("com.mysql.jdbc.Driver");
		        String url = "jdbc:mysql://localhost:3306/advancedSW";
		        String user = "root";
		        String password = "root";
		        Connection Con = null;
		        Con = DriverManager.getConnection(url, user, password);

		        String query = " insert into offer( driverID, UserID, tripID , money )"
		                + " values (?,?, ? , ?)";
		        PreparedStatement preparedStmt = Con.prepareStatement(query);
		        preparedStmt.setString (1, new_offer.getDriverID());
		        preparedStmt.setString (2, new_offer.getUserID());
		        preparedStmt.setInt(3, new_offer.getTripID());
		        preparedStmt.setDouble (4,new_offer.getMoney());
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
	public offer get_offer(int offer_id)
	{
		offer current_offer=new offer();
		try {
	        Class.forName("com.mysql.jdbc.Driver");
	        String url = "jdbc:mysql://localhost:3306/advancedSW";
	        String user = "root";
	        String password = "root";
	        Connection Con = null;
	        Con = DriverManager.getConnection(url, user, password);

	        String query = " SELECT * FROM offer WHERE offerID = ?";
	        PreparedStatement preparedStmt = Con.prepareStatement(query);
	        preparedStmt.setInt(1,offer_id);
	        ResultSet result= preparedStmt.executeQuery();
	        
	        while(result.next())
	        {
	        	
	        	current_offer.setDriverID(Integer.toString(result.getInt("driverID")));
	        	current_offer.setUserID(Integer.toString(result.getInt("userID")));
	        	current_offer.setOfferID(result.getInt("offerID"));
	        	current_offer.setTripID(result.getInt("tripID"));
	        	current_offer.setStatus(result.getString("status"));
	        	current_offer.setMoney(result.getDouble("money"));
	        	
	        }
	        Con.close();
	    } catch (ClassNotFoundException | SQLException ex) {
	      System.out.println(ex.getMessage());
	    }
		return current_offer;
	}
	public ArrayList<offer>get_trip_offers(int trip_id)
	{
		ArrayList<offer>all_offers=new ArrayList<offer>();
		try {
	        Class.forName("com.mysql.jdbc.Driver");
	        String url = "jdbc:mysql://localhost:3306/advancedSW";
	        String user = "root";
	        String password = "root";
	        Connection Con = null;
	        Con = DriverManager.getConnection(url, user, password);

	        String query = " SELECT * FROM offer WHERE tripID = ?";
	        PreparedStatement preparedStmt = Con.prepareStatement(query);
	        preparedStmt.setInt(1,trip_id);
	        ResultSet result= preparedStmt.executeQuery();
	        
	        while(result.next())
	        {
	        	offer current_offer=new offer();
	        	current_offer.setDriverID(Integer.toString(result.getInt("driverID")));
	        	current_offer.setUserID(Integer.toString(result.getInt("userID")));
	        	current_offer.setOfferID(result.getInt("offerID"));
	        	current_offer.setTripID(result.getInt("tripID"));
	        	current_offer.setStatus(result.getString("status"));
	        	current_offer.setMoney(result.getDouble("money"));
	        	all_offers.add(current_offer);
	        }
	        Con.close();
	    } catch (ClassNotFoundException | SQLException ex) {
	      System.out.println(ex.getMessage());
	    }
	 return all_offers;
	}
	public database_response update_offer_status(int offer_id,String msg)
	{
		database_response response=new database_response();
		try {
	        Class.forName("com.mysql.jdbc.Driver");
	        String url = "jdbc:mysql://localhost:3306/advancedSW";
	        String user = "root";
	        String password = "root";
	        Connection Con = null;
	        Con = DriverManager.getConnection(url, user, password);

	        String query = "UPDATE offer set status=? WHERE offerID= ?";
	        PreparedStatement preparedStmt = Con.prepareStatement(query);
	        preparedStmt.setString(1,msg);
	        preparedStmt.setInt(2,offer_id);
	        response.setStatus(preparedStmt.execute());
	       
	        Con.close();
	    } catch (ClassNotFoundException | SQLException ex) {
	      System.out.println(ex.getMessage());
	      response.setStatus(false);
	    }
		return response;
	}

}

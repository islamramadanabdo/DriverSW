package database;

import static java.lang.System.out;

import java.sql.*;

import main.User;

public class UsersDatabase
{
	database_response response =new database_response();

	public database_response create_user(User new_user) 
	{
		
		  try {
	            Class.forName("com.mysql.jdbc.Driver");
	            String url = "jdbc:mysql://localhost:3306/advancedSW";
	            String user = "root";
	            String password ="root";
	            Connection Con = null;
	            Con = DriverManager.getConnection(url, user, password);

	            String query = " insert into user (username, password,  email, mobile,  license,  nationalID,  role ,  approved)"
	                    + " values (?, ?, ?, ?, ? , ? , ? , ?)";
	            PreparedStatement preparedStmt = Con.prepareStatement(query);
	            preparedStmt.setString (1, new_user.getUsername());
	            preparedStmt.setString (2, new_user.getPassword());
	            preparedStmt.setString (3, new_user.getEmail());
	            preparedStmt.setString (4, new_user.getMobile());
	            preparedStmt.setString (5, new_user.getLicense());
	            preparedStmt.setString (6, new_user.getNationalID());
	            preparedStmt.setString (7, new_user.getRole());
	            preparedStmt.setString (8, new_user.getApproved());
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

	
	public User read_user(String uname, String upassword) {
		User current_user=new User();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/advancedSW";
            String user = "root";
            String password = "root";
            Connection Con = null;
            Con = DriverManager.getConnection(url, user, password);

            String sql = "SELECT * FROM user WHERE username = ? and password = ?";
            PreparedStatement statement = Con.prepareStatement(sql);
            statement.setString(1, uname);
            statement.setString(2, upassword);
            ResultSet result = statement.executeQuery();
            if(!result.next())
            {
                out.println("can't find this user");

            }
            else
            {
                current_user.setRole( result.getString("role"));
                current_user.setApproved(result.getString("approved"));
                current_user.setUserID(result.getInt("UserID"));
                current_user.setEmail(result.getString("email"));
                current_user.setLicense(result.getString("license"));
                current_user.setNationalID(result.getString("nationalID"));
                current_user.setMobile(result.getString("mobile"));
               current_user.setUsername(uname);
               current_user.setPassword(upassword);
               return current_user;
            }

           

        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            out.print("not connected");
        }
     
       return current_user;
	}
	
	public User get_user_by_id(String UserID)
	{
		User current_user=new User();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/advancedSW";
            String user = "root";
            String password = "root";
            Connection Con = null;
            Con = DriverManager.getConnection(url, user, password);

            String sql = "SELECT * FROM user WHERE UserID = ? ";
            PreparedStatement statement = Con.prepareStatement(sql);
            statement.setString(1, UserID);
            ResultSet result = statement.executeQuery();
            if(!result.next())
            {
                out.println("can't find this user");

            }
            else
            {
                current_user.setRole( result.getString("role"));
                current_user.setApproved(result.getString("approved"));
                current_user.setUserID(result.getInt("UserID"));
                current_user.setEmail(result.getString("email"));
                current_user.setLicense(result.getString("license"));
                current_user.setNationalID(result.getString("nationalID"));
                current_user.setMobile(result.getString("mobile"));
               current_user.setUsername(result.getString("username"));
               current_user.setPassword(result.getString("password"));
     
     //          return current_user;
            }

           

        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            out.print("not connected");
        }
     
       return current_user;
	}

	
	public database_response approve_or_susbend(String User_id,String approved) {
		database_response response=new database_response();
		
		 try {
		      Class.forName("com.mysql.jdbc.Driver");
		      String url = "jdbc:mysql://localhost:3306/advancedSW";
		      String user = "root";
		      String password = "root";
		      Connection Con = null;
		      PreparedStatement pstatement;
		      Con = DriverManager.getConnection(url, user, password);
		     String update1="UPDATE user "
		                + "SET "
		                + "approved ='"+approved+"'"
		                + "where UserID ='"+User_id+"' ";
		        pstatement = Con.prepareStatement(update1);
		        int updateQuery = pstatement.executeUpdate();
		       if(updateQuery==1) {
		    	   response.setStatus(true);
		       }
		       else
		       {
		    	   response.setStatus(false);
		       }
		    } catch (ClassNotFoundException | SQLException ex) {
		      ex.printStackTrace();
		      out.print("not connected");
		    }
		return response;
	}



}

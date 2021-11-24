package database;

import static java.lang.System.out;

import java.sql.*;

import sw2_project.user;
import java.sql.DriverManager;
import com.mysql.jdbc.Connection;

public class UsersDatabase {

    database_response response = new database_response();

    public database_response create_user(user new_user) {
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
            preparedStmt.setString(4, "");
            preparedStmt.setString(5, "");
            preparedStmt.setString(6, new_user.getPhone());
            preparedStmt.setString(7, new_user.getRole());
            preparedStmt.setString(8, new_user.getApproved());
            try {
                
                preparedStmt.execute();
                response.setStatus(true);
            } catch (Exception e) {
                response.setStatus(false);
            }

            conn.close();
        } catch (ClassNotFoundException | SQLException ex) {
            //ex.printStackTrace();
            out.print(" faild not connected");
        }
        return response;
    }

	public user read_user(String uname, String upassword) {
		user current_user=new user();
       java.sql.Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = (java.sql.Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/advancedsw", "root", "");

            String sql = "SELECT * FROM user WHERE username = ? and password = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
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
                current_user.setId(result.getInt("UserID"));
                current_user.setEmail(result.getString("email"));
                current_user.setPhone(result.getString("mobile"));
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
//	
	public user get_user_by_id(String UserID)
	{
		user current_user=new user();
        java.sql.Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");

            conn = (java.sql.Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/advancedsw", "root", "");

            String sql = "SELECT * FROM user WHERE UserID = ? ";
            PreparedStatement statement = conn.prepareStatement(sql);
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
                current_user.setId(result.getInt("UserID"));
                current_user.setEmail(result.getString("email"));
                current_user.setPhone(result.getString("mobile"));
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
//	public database_response approve_or_susbend(String User_id,String approved) {
//		database_response response=new database_response();
//		
//		 try {
//		      Class.forName("com.mysql.jdbc.Driver");
//		      String url = "jdbc:mysql://localhost:3306/advancedSW";
//		      String user = "root";
//		      String password = "root";
//		      Connection Con = null;
//		      PreparedStatement pstatement;
//		      Con = DriverManager.getConnection(url, user, password);
//		     String update1="UPDATE user "
//		                + "SET "
//		                + "approved ='"+approved+"'"
//		                + "where UserID ='"+User_id+"' ";
//		        pstatement = Con.prepareStatement(update1);
//		        int updateQuery = pstatement.executeUpdate();
//		       if(updateQuery==1) {
//		    	   response.setStatus(true);
//		       }
//		       else
//		       {
//		    	   response.setStatus(false);
//		       }
//		    } catch (ClassNotFoundException | SQLException ex) {
//		      ex.printStackTrace();
//		      out.print("not connected");
//		    }
//		return response;
//	}

}

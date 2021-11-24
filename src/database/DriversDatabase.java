package database;

import static java.lang.System.out;

import java.sql.*;

import sw2_project.driver;
import java.sql.DriverManager;
import com.mysql.jdbc.Connection;

public class DriversDatabase {

    database_response response = new database_response();

    public database_response create_driver(driver new_driver) {
        java.sql.Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");

            conn = (java.sql.Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/advancedsw", "root", "");

            String query = " insert into driver ( username, password,  email, mobile, license , nationalID ,  role ,  approved)"
                    + " values ( ?,?, ?, ? , ? , ? , ?, ?)";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, new_driver.getUsername());
            preparedStmt.setString(2, new_driver.getPassword());
            preparedStmt.setString(3, new_driver.getEmail());
            preparedStmt.setString(4, new_driver.getPhone());
            preparedStmt.setString(5, new_driver.getDrivingLicence());
            preparedStmt.setString(6, new_driver.getNationalID());
            preparedStmt.setString(7, new_driver.getRole());
            preparedStmt.setString(8, new_driver.getApproved());
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

    public driver read_driver(String uname, String upassword) {
        driver current_driver = new driver();
        java.sql.Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = (java.sql.Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/advancedsw", "root", "");

            String sql = "SELECT * FROM driver WHERE username = ? and password = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, uname);
            statement.setString(2, upassword);
            ResultSet result = statement.executeQuery();
            if (!result.next()) {
                out.println("can't find this driver");

            } else {
                current_driver.setRole(result.getString("role"));
                current_driver.setApproved(result.getString("approved"));
                current_driver.setId(result.getInt("UserID"));
                current_driver.setEmail(result.getString("email"));
                current_driver.setDrivingLicence(result.getString("license"));
                current_driver.setNationalID(result.getString("nationalID"));
                current_driver.setPhone(result.getString("mobile"));
                current_driver.setUsername(uname);
                current_driver.setPassword(upassword);

                return current_driver;
            }

        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            out.print("not connected");
        }

        return current_driver;
    }
//	
//	public User get_user_by_id(String UserID)
//	{
//		User current_user=new User();
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            String url = "jdbc:mysql://localhost:3306/advancedSW";
//            String user = "root";
//            String password = "root";
//            Connection Con = null;
//            Con = DriverManager.getConnection(url, user, password);
//
//            String sql = "SELECT * FROM user WHERE UserID = ? ";
//            PreparedStatement statement = Con.prepareStatement(sql);
//            statement.setString(1, UserID);
//            ResultSet result = statement.executeQuery();
//            if(!result.next())
//            {
//                out.println("can't find this user");
//
//            }
//            else
//            {
//                current_user.setRole( result.getString("role"));
//                current_user.setApproved(result.getString("approved"));
//                current_user.setUserID(result.getInt("UserID"));
//                current_user.setEmail(result.getString("email"));
//                current_user.setLicense(result.getString("license"));
//                current_user.setNationalID(result.getString("nationalID"));
//                current_user.setMobile(result.getString("mobile"));
//               current_user.setUsername(result.getString("username"));
//               current_user.setPassword(result.getString("password"));
//     
//     //          return current_user;
//            }
//
//           
//
//        } catch (ClassNotFoundException | SQLException ex) {
//            ex.printStackTrace();
//            out.print("not connected");
//        }
//     
//       return current_user;
//	}

    public database_response approve_or_susbend(String User_id, String approved) {
        database_response response = new database_response();
        java.sql.Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");

            conn = (java.sql.Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/advancedsw", "root", "");
            PreparedStatement pstatement;
            String update1 = "UPDATE driver "
                    + "SET "
                    + "approved ='" + approved + "'"
                    + "where UserID ='" + User_id + "' ";
            pstatement = conn.prepareStatement(update1);
            int updateQuery = pstatement.executeUpdate();
            if (updateQuery == 1) {
                response.setStatus(true);
            } else {
                response.setStatus(false);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            out.print("not connected");
        }
        return response;
    }

}

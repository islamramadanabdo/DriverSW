/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sw2_project;
import java.util.Properties;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
/**
 *
 * @author HP
 */
public class sendingEmail {
    
    
    
	public static void send_trip_notification(Person reciever, Trip new_trip ) throws Exception 
    {
        
      Properties properties=new Properties();
      properties.put("mail.smtp.auth", "true");
      properties.put("mail.smtp.starttls.enable","true");
      properties.put("mail.smtp.host","smtp.gmail.com");
      properties.put("mail.smtp.port", "587");
      properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
      properties.put("mail.smtp.host", "smtp.gmail.com");    
        properties.put("mail.smtp.socketFactory.port", "587");       
       
      
        final String sender="driverin07@gmail.com";
	    final String password="driverIn1234";
	    Session session = Session.getDefaultInstance(properties,    
	            new javax.mail.Authenticator() {    
	            protected PasswordAuthentication getPasswordAuthentication() {    
	            return new PasswordAuthentication(sender,password);  
	            }    
	           });
	           MimeMessage message=new MimeMessage(session);
	         try {
	             message.setFrom(new InternetAddress(sender));
	             message.setRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(reciever.getEmail()));
	             message.setSubject("New Trip");
	             message.setText("Hello , "+reciever.getUsername()+"\n"+
	             "There is a new trip :"+"\n"+
	              "  Source: "+ new_trip.getSourcee()+"\n"+
	              "  Distination: "+new_trip.getDistination()+"\n"+" Go to the app for more information.");
	         } catch (Exception ex) {
	             //Logger.getLogger(player.class.getName()).log(Level.SEVERE, null, ex);
	         }
	           javax.mail.Transport.send(message); 
     
    }
	public static void send_offer_notification(Person reciever,offer new_offer ) throws Exception 
    {
        
      Properties properties=new Properties();
      properties.put("mail.smtp.auth", "true");
      properties.put("mail.smtp.starttls.enable","true");
      properties.put("mail.smtp.host","smtp.gmail.com");
      properties.put("mail.smtp.port", "587");
      properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
      properties.put("mail.smtp.host", "smtp.gmail.com");    
        properties.put("mail.smtp.socketFactory.port", "587");       
       
      
        final String sender="advancedswmail@gmail.com";
	    final String password="advancedsw12345";
      
     
      Session session = Session.getDefaultInstance(properties,    
         new javax.mail.Authenticator() {    
         protected javax.mail.PasswordAuthentication getPasswordAuthentication() {    
         return new PasswordAuthentication(sender , password);  
         }    
        });
        MimeMessage message=new MimeMessage(session);
      try {
          message.setFrom(new InternetAddress(sender));
          message.setRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(reciever.getEmail()));
          message.setSubject("New trip");
          message.setText("Hello , "+reciever.getUsername()+"\n"+
          "You got a new offer for your trip. \n"+
        		  " Offer price :"+new_offer.getMoney()+"\n"+
        		  "Go to the app for more info");
      } catch (Exception ex) {
          //Logger.getLogger(player.class.getName()).log(Level.SEVERE, null, ex);
      }
        javax.mail.Transport.send(message);
      System.out.println("We sent a code to your mail,please check your mail...");   
    }
}

package myBooking;
import java.awt.Component;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;


public class MysqlAction {
  private Connection connect = null;
  private Statement statement = null;
  private ResultSet resultSet = null;
  
  public void databaseConnection() throws Exception{
	// This will load the MySQL driver
      Class.forName("com.mysql.jdbc.Driver");
      // Setup the connection with the DB
      connect = DriverManager.getConnection("jdbc:mysql://localhost?"
              + "user=admin&password=admin");

      // Statements allow to issue SQL queries to the database
      statement = connect.createStatement();
 
  }
//method to get the users
  public User getUserbyName(String username) throws Exception {
	    try {
	    	databaseConnection();
	      // Result set get the result of the SQL query
	      resultSet = statement.executeQuery("select * from room_booking.user where userName = '"+ username+"'");
	      User newuser = new User();
	       // ResultSet is initially before the first data set
	      while (resultSet.next()) {
	        //  to get the columns via name
	      	  int userID = resultSet.getInt("userid");
	      	  String uname = resultSet.getString("userName");
	          String password = resultSet.getString("password");
	          String firstName = resultSet.getString("firstname");
	          String lastName = resultSet.getString("lastname");
	          String uType = resultSet.getString("usertype");
	          
	          
	          newuser.setUserId(userID);
	          newuser.setUserName(uname);
	          newuser.setPassWord(password);
	          newuser.setFName(firstName);
	          newuser.setLName(lastName);
	          newuser.setUserType(uType);
     
	          
	      } 
	      return newuser; 
	      
	    } catch (Exception e) {
	      throw e;
	     
	    } finally {
	      close();
	    }
			    
	  }
  
 //method to add a new booking
  public void addBooking(String name, Date date,int time) throws Exception {
	    try {
	    	databaseConnection();
	      	      // change the format of the date
	      	SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
	      	//System.out.println("insert into room_booking.booking set name='"+ name+"',date='"+DATE_FORMAT.format(date)+"',timeslot='"+time+"'");
	      	//sql to add a new booking to the booking table
	     	statement.execute("insert into room_booking.booking set name='"+ name+"',date='"+DATE_FORMAT.format(date)+"',timeslot='"+time+"'");
	     	
	      
	    } catch (Exception e) {
	      throw e;
	    			
	    } finally {
	      close();
	    }
	    Component frame = null;
  		JOptionPane.showMessageDialog(frame,
  	    	    "Booking is added successfully",
  	    	    "",
  	    	    JOptionPane.PLAIN_MESSAGE);
	    
	   	  }
 
  //  to close the resultSet and connection
  private void close() {
    try {
      if (resultSet != null) {
        resultSet.close();
      }

      if (statement != null) {
        statement.close();
      }

      if (connect != null) {
        connect.close();
      }
    } catch (Exception e) {

    }
  }
//method to get the available time slots
    public String getAvailableTimes(Date date) throws Exception {
	    try {
	    	databaseConnection();
	    	SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
	      // Result set get the result of the SQL query
	      resultSet = statement.executeQuery("select ID,timeslots.timeslot from room_booking.timeslots "
	      		+ "where ID not in (select timeslot from room_booking.booking where date='"+DATE_FORMAT.format(date)+"') order by ID desc");
	     String timeslotstring = new String();//new String object to get the time slots
	      while (resultSet.next()) {
	       //accessing the time slot by ID  	 
	    	  timeslotstring = timeslotstring+ resultSet.getString("ID")+":("+resultSet.getString("timeslot")+"),";
	         	          
	          
	      } 
	      return timeslotstring;
	     	      
	    } catch (Exception e) {
	      throw e;
	     
	    } finally {
	      close();
	    }
    
	  }
    //method to get all the users from the user table
    public String getAllUsers() throws Exception {
	    try {
	    	databaseConnection();
	    	
	      // Result set get the result of the SQL query
	      resultSet = statement.executeQuery("select userName from room_booking.user");
	     String allusers = new String();//new String object to store the users
	      while (resultSet.next()) {
	       //accessing the users by username 
	    	  allusers = allusers+ resultSet.getString("userName")+",";
	                
	               
	      } 
	      return allusers;
	     	      
	    } catch (Exception e) {
	      throw e;
	     
	    } finally {
	      close();
	    }
    
	  }
//method to get the already booked bookings
  public String getBookedTimes(Date date) throws Exception {
	    try {
	    	 databaseConnection();
	         SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
	      // Result set get the result of the SQL query
	      resultSet = statement.executeQuery("select ID,timeslots.timeslot,name from room_booking.timeslots,room_booking.booking "
	      		+ "where ID=booking.timeslot and date='"+DATE_FORMAT.format(date)+"' order by ID desc");
	     String timeslotstring = new String();
	     while (resultSet.next()) {
	    //getting the id, timeslot and username to the string. because user can see to whom that booking is belongs to and can
	    	 //delete only his/her bookings.
	    timeslotstring = timeslotstring+ resultSet.getString("ID")+";"+resultSet.getString("name")+";("+resultSet.getString("timeslot")+"),";
	       
	          
	      } 
	      return timeslotstring;
	     	      
	    } catch (Exception e) {
	      throw e;
	     
	    } finally {
	      close();
	    }
			    
	  }
  //method to delete a booking
 public void deleteBooking(String name, Date date,int time) throws Exception {
	 try {
		 	databaseConnection();
	         // change the format of the date
	      	SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
	      	//sql to delete a booking from the booking table
	      	statement.execute("delete from room_booking.booking where name='"+ name+"' and date='"+DATE_FORMAT.format(date)+"' and timeslot='"+time+"'");
	    } catch (Exception e) {
	      throw e;
	     
	    } finally {
	      close();
	    }
	    Component frame = null;//message
		JOptionPane.showMessageDialog(frame,
	    	    "Booking is deleted successfully",
	    	    "",
	    	    JOptionPane.PLAIN_MESSAGE);
	   	  }
 
 // method to a add a new user to the user table
 public void addUser(int userId, String username, String pass, String fname, String lname, String userType) throws Exception {
	    try {
	    	databaseConnection();
	      	//add a new user to the user table
	      	statement.execute("insert into room_booking.user set userid='"+ userId+"',userName='"+username+
	      			"',password='"+pass+"',firstname='"+fname+"',lastname='"+lname+"',usertype='"+userType+"'");
	      	
	      
	    } catch (Exception e) {
	      throw e;
	     
	    } finally {
	      close();
	    }
	    Component frame = null;//message
		JOptionPane.showMessageDialog(frame,
	    	    "user is added successfully",
	    	    "",
	    	    JOptionPane.PLAIN_MESSAGE);	    
 		}
 
//method to delete a user
 public void deleteUser(String username) throws Exception {
	 try {
		 	databaseConnection();
	      	statement.execute("delete from room_booking.booking where name='"+ username+"'");//delete the users bookings when deleting of 
		 	//the corresponding user
		 	//sql to delete a booking from the booking table
	      	statement.execute("delete from room_booking.user where userName='"+ username+"'");
	      
	      	
	    } catch (Exception e) {
	      throw e;
	     
	    } finally {
	      close();
	    }
	 	Component frame = null;//message
		JOptionPane.showMessageDialog(frame,
	    	    "user is deleted successfully",
	    	    "",
	    	    JOptionPane.PLAIN_MESSAGE);

	   	  }
 }


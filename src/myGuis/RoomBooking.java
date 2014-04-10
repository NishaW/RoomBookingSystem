package myGuis;
//RoomBooking GUI
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.SpringLayout;
import javax.swing.JLabel;

import com.toedter.calendar.JDateChooser;

import javax.swing.JList;
import java.awt.Component;
import java.awt.Font;
import java.awt.Color;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

import myBooking.Booking;
import myBooking.MysqlAction;
import myGuis.Login;
import javax.swing.UIManager;

public class RoomBooking extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	JDateChooser dateChooser = new JDateChooser();
	static MysqlAction actions = new MysqlAction();
	Date sysdate = new Date();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
	DefaultListModel listModel = new DefaultListModel();
	JList timelist = new JList(listModel);
	
	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public RoomBooking() {
		setBackground(UIManager.getColor("CheckBoxMenuItem.selectionBackground"));
		setForeground(Color.WHITE);
		setFont(new Font("Cordia New", Font.BOLD, 14));
		setTitle("Conference Room Booking System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		SpringLayout sl_contentPane = new SpringLayout();
		sl_contentPane.putConstraint(SpringLayout.EAST, dateChooser, -153, SpringLayout.EAST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, timelist, 15, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, timelist, -277, SpringLayout.EAST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.NORTH, dateChooser, 10, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.NORTH, timelist, 72, SpringLayout.NORTH, contentPane);
		contentPane.setLayout(sl_contentPane);
		
		
		
		JLabel lblSelectTheDate = new JLabel("Select the Date");
		sl_contentPane.putConstraint(SpringLayout.WEST, dateChooser, 12, SpringLayout.EAST, lblSelectTheDate);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, lblSelectTheDate, 0, SpringLayout.SOUTH, dateChooser);
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblSelectTheDate, 0, SpringLayout.NORTH, dateChooser);
		sl_contentPane.putConstraint(SpringLayout.EAST, lblSelectTheDate, -292, SpringLayout.EAST, contentPane);
		contentPane.add(lblSelectTheDate);
	
		dateChooser.setDate(sysdate);
		contentPane.add(dateChooser);
		
		JLabel lblSelectTheTime = new JLabel("Select the Time");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblSelectTheTime, 15, SpringLayout.SOUTH, lblSelectTheDate);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, lblSelectTheTime, -13, SpringLayout.NORTH, timelist);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblSelectTheDate, 0, SpringLayout.WEST, lblSelectTheTime);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblSelectTheTime, 10, SpringLayout.WEST, contentPane);
		contentPane.add(lblSelectTheTime);
	
		
		
		JButton btnBook = new JButton("Book");
		sl_contentPane.putConstraint(SpringLayout.WEST, btnBook, 65, SpringLayout.EAST, timelist);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, btnBook, -101, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, btnBook, -70, SpringLayout.EAST, contentPane);
		btnBook.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		btnBook.addActionListener(new ActionListener() {// book button actions
			public void actionPerformed(ActionEvent arg0) {
				
		 		  Date date =dateChooser.getDate(); //get the date from datechooser
				  int timeslot = Integer.valueOf(timelist.getSelectedValue().toString().substring(0, 1));// get the time from the list
				  Booking newBooking = new Booking();
				  String uname =Login.myUser.getUserName();//get the logged user
				   
				  newBooking.setName(uname);
			      newBooking.setDate(date);
			      newBooking.setTime(timeslot);
			   try {
				newBooking.AddBooking();//call the method from booking class
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			   
			    
			}
		});
		contentPane.add(btnBook);
		
		JButton btnExit = new JButton("Exit");
		sl_contentPane.putConstraint(SpringLayout.WEST, btnExit, 65, SpringLayout.EAST, timelist);
		sl_contentPane.putConstraint(SpringLayout.EAST, btnExit, -70, SpringLayout.EAST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, timelist, 0, SpringLayout.SOUTH, btnExit);
		sl_contentPane.putConstraint(SpringLayout.NORTH, btnExit, 195, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, btnExit, -23, SpringLayout.SOUTH, contentPane);
		btnExit.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnExit.addActionListener(new ActionListener() {//exit button actions
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		contentPane.add(btnExit);
		contentPane.add(timelist);
		
		JButton btnViewAvailable = new JButton("View Availables");
		sl_contentPane.putConstraint(SpringLayout.EAST, lblSelectTheTime, -98, SpringLayout.WEST, btnViewAvailable);
		sl_contentPane.putConstraint(SpringLayout.WEST, btnViewAvailable, 0, SpringLayout.WEST, btnBook);
		sl_contentPane.putConstraint(SpringLayout.EAST, btnViewAvailable, -70, SpringLayout.EAST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.NORTH, btnViewAvailable, 15, SpringLayout.SOUTH, dateChooser);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, btnViewAvailable, -174, SpringLayout.SOUTH, contentPane);
		btnViewAvailable.addActionListener(new ActionListener() {//viewavailable button actions
			public void actionPerformed(ActionEvent e) {
				
       		 String times;
			try {
				times = actions.getAvailableTimes(dateChooser.getDate());//call the method by date and assign to a string variable
				 	
		       		//System.out.println(times.toString());
		       		listModel.clear();
		       		String[] available = times.split(",");//split the string
		       		int j=0;
		       	
		       		for (String s:available){
		       			listModel.add(j,s.toString());	//add values to list
		       		j=j++;	
		       		}
		       		
		       		
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
       		
			}
		});
		btnViewAvailable.setFont(new Font("Tahoma", Font.BOLD, 11));
		contentPane.add(btnViewAvailable);
		
		JButton btnViewBooked = new JButton("View Booked");
		sl_contentPane.putConstraint(SpringLayout.NORTH, btnViewBooked, 1, SpringLayout.SOUTH, btnViewAvailable);
		sl_contentPane.putConstraint(SpringLayout.WEST, btnViewBooked, 65, SpringLayout.EAST, timelist);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, btnViewBooked, -139, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, btnViewBooked, -70, SpringLayout.EAST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.NORTH, btnBook, 6, SpringLayout.SOUTH, btnViewBooked);
		btnViewBooked.addActionListener(new ActionListener() {//viewbooked button actions to get the booked time slots
			public void actionPerformed(ActionEvent e) {
				  String bookings;
					try {
						bookings = actions.getBookedTimes(dateChooser.getDate());
						 	
				       		//System.out.println(bookings.toString());
				       		listModel.clear();
				       		String[] booked = bookings.split(",");
				       		int j=0;
				       	
				       		for (String s:booked){
				       			listModel.add(j,s.toString());	// add them to the list
				       		j=j++;	
				       		}
				       		
				       		
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			}
		});
		btnViewBooked.setFont(new Font("Tahoma", Font.BOLD, 11));
		contentPane.add(btnViewBooked);
		
		JButton btnDeleteBooking = new JButton("Delete Booking");
		sl_contentPane.putConstraint(SpringLayout.WEST, btnDeleteBooking, 65, SpringLayout.EAST, timelist);
		sl_contentPane.putConstraint(SpringLayout.EAST, btnDeleteBooking, -70, SpringLayout.EAST, contentPane);
		
		btnDeleteBooking.addActionListener(new ActionListener() {//deletebooking button actions
			public void actionPerformed(ActionEvent e) {
				
				String name = Login.myUser.getUserName();
				Date date =  dateChooser.getDate();
				int time  = Integer.valueOf(timelist.getSelectedValue().toString().substring(0, 1));//select the time from list
				int endIndex = timelist.getSelectedValue().toString().lastIndexOf(";");
				String bookingUser=timelist.getSelectedValue().toString().substring(2, endIndex);//get the booking user
				try {
					
					if(name.equals(bookingUser)){// check if the booking is belong to the logged user
						actions.deleteBooking( name,date,time);// call the method to delete
					}
					else{//if not error message
						
						Component frame = null;
						JOptionPane.showMessageDialog(frame,
							    "You do not have rights to delete this booking",
							    "access error",
							    JOptionPane.ERROR_MESSAGE);
					}
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnDeleteBooking.setFont(new Font("Tahoma", Font.BOLD, 11));
		sl_contentPane.putConstraint(SpringLayout.NORTH, btnDeleteBooking, 44, SpringLayout.SOUTH, btnViewBooked);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, btnDeleteBooking, -6, SpringLayout.NORTH, btnExit);
		contentPane.add(btnDeleteBooking);
	}
}

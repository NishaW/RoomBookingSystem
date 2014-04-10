package myGuis;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.SpringLayout;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JButton;

import myBooking.MysqlAction;
import myBooking.User;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
// Admin GUI 
public class Admin extends JFrame {

	/**
	 * define components and create new objects
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtPass;
	private JTextField txtFname;
	private JTextField txtLname;
	private JTextField txtUtype;
	private JTextField txtUsername;
	private JTextField txtUserid;
	User newUser = new User();
	MysqlAction actions = new MysqlAction();
	DefaultComboBoxModel<String>  comboModel = new DefaultComboBoxModel<String>();
	static MysqlAction sqlaction = new MysqlAction();
	JComboBox comboBox = new JComboBox(comboModel);

	/**
	 * Launch the application.
	

	/**
	 * Create the frame.
	 */
	 
	public Admin() {
		// initializing components
		setTitle("Conference Room Booking System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		SpringLayout sl_contentPane = new SpringLayout();
		contentPane.setLayout(sl_contentPane);
		
		
		
		JLabel lblAdministrator = new JLabel("Administrator");
		sl_contentPane.putConstraint(SpringLayout.SOUTH, lblAdministrator, -218, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, lblAdministrator, -38, SpringLayout.EAST, contentPane);
		lblAdministrator.setFont(new Font("Arial Unicode MS", Font.BOLD, 15));
		contentPane.add(lblAdministrator);
		
		txtPass = new JTextField();
		contentPane.add(txtPass);
		txtPass.setColumns(10);
		
		txtFname = new JTextField();
		sl_contentPane.putConstraint(SpringLayout.NORTH, txtFname, 125, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, txtPass, 0, SpringLayout.WEST, txtFname);
		sl_contentPane.putConstraint(SpringLayout.EAST, txtPass, 0, SpringLayout.EAST, txtFname);
		sl_contentPane.putConstraint(SpringLayout.WEST, txtFname, 189, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, txtFname, -52, SpringLayout.EAST, contentPane);
		contentPane.add(txtFname);
		txtFname.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("User ID");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblNewLabel, 36, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblNewLabel, 20, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, lblNewLabel, -190, SpringLayout.SOUTH, contentPane);
		contentPane.add(lblNewLabel);
		
		JLabel lblDepartment = new JLabel("First Name");
		sl_contentPane.putConstraint(SpringLayout.WEST, lblDepartment, 20, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, lblDepartment, -279, SpringLayout.EAST, contentPane);
		contentPane.add(lblDepartment);
		
		JButton btnAdd = new JButton("Add User");
		sl_contentPane.putConstraint(SpringLayout.WEST, btnAdd, 0, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, btnAdd, -313, SpringLayout.EAST, contentPane);
		btnAdd.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnAdd.addActionListener(new ActionListener() {//add user button actions
			public void actionPerformed(ActionEvent e) {
				//assign the variable values to text boxes
				int id = Integer.valueOf(txtUserid.getText());
				newUser.setUserId(id);
				newUser.setUserName(txtUsername.getText());
				newUser.setPassWord(txtPass.getText());
				newUser.setFName(txtFname.getText());
				newUser.setLName(txtLname.getText());
				newUser.setUserType(txtUtype.getText());
				// get the values
				int userid = newUser.getUserId();
				String username = newUser.getUserName();
				String pass = newUser.getPassWord();
				String fname = newUser.getFirstName();
				String lname = newUser.getLastName();
				String userType = newUser.getUserType();
				
				try {//call the method to add new user
					actions.addUser(userid, username, pass, fname, lname, userType);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			   	
			}
		});
		contentPane.add(btnAdd);
		
		JButton btnDelete = new JButton("Delete");
		sl_contentPane.putConstraint(SpringLayout.NORTH, btnDelete, 0, SpringLayout.NORTH, btnAdd);
		sl_contentPane.putConstraint(SpringLayout.WEST, btnDelete, 6, SpringLayout.EAST, btnAdd);
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {//delete button actions
				String name = txtUsername.getText();
				try {
					sqlaction.deleteUser(name);//call the delete user method
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		contentPane.add(btnDelete);
		
		JButton btnExit = new JButton("Exit");
		sl_contentPane.putConstraint(SpringLayout.EAST, btnDelete, -109, SpringLayout.WEST, btnExit);
		sl_contentPane.putConstraint(SpringLayout.NORTH, btnExit, 0, SpringLayout.NORTH, btnAdd);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, btnExit, 23, SpringLayout.NORTH, btnAdd);
		sl_contentPane.putConstraint(SpringLayout.WEST, btnExit, 325, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, btnExit, 0, SpringLayout.EAST, contentPane);
		btnExit.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnExit.addActionListener(new ActionListener() {//exit button actions
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		contentPane.add(btnExit);
		
		JLabel lblLastName = new JLabel("Last Name");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblLastName, 153, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblLastName, 20, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, lblLastName, -75, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, lblDepartment, -6, SpringLayout.NORTH, lblLastName);
		contentPane.add(lblLastName);
		
		txtLname = new JTextField();
		sl_contentPane.putConstraint(SpringLayout.NORTH, txtLname, 151, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, txtLname, 189, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, txtLname, -52, SpringLayout.EAST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, lblLastName, -39, SpringLayout.WEST, txtLname);
		contentPane.add(txtLname);
		txtLname.setColumns(10);
		
		JLabel lblUserType = new JLabel("User Type");
		sl_contentPane.putConstraint(SpringLayout.NORTH, btnAdd, 18, SpringLayout.SOUTH, lblUserType);
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblUserType, 182, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, lblUserType, -49, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblUserType, 20, SpringLayout.WEST, contentPane);
		contentPane.add(lblUserType);
		
		txtUtype = new JTextField();
		sl_contentPane.putConstraint(SpringLayout.NORTH, txtUtype, 177, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, txtUtype, 0, SpringLayout.WEST, txtPass);
		sl_contentPane.putConstraint(SpringLayout.EAST, txtUtype, 0, SpringLayout.EAST, txtPass);
		contentPane.add(txtUtype);
		txtUtype.setColumns(10);
		
		
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblAdministrator, -7, SpringLayout.NORTH, comboBox);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblAdministrator, 113, SpringLayout.EAST, comboBox);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, comboBox, -221, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, lblNewLabel, -10, SpringLayout.EAST, comboBox);
		sl_contentPane.putConstraint(SpringLayout.WEST, comboBox, 10, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, comboBox, -303, SpringLayout.EAST, contentPane);
		contentPane.add(comboBox);
		
		JLabel lblUserName = new JLabel("User Name");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblUserName, 72, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblUserName, 20, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, lblUserName, 0, SpringLayout.EAST, lblNewLabel);
		contentPane.add(lblUserName);
		
		JLabel lblPassword = new JLabel("Password");
		sl_contentPane.putConstraint(SpringLayout.WEST, lblPassword, 20, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, lblPassword, -44, SpringLayout.WEST, txtPass);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, lblUserName, -17, SpringLayout.NORTH, lblPassword);
		sl_contentPane.putConstraint(SpringLayout.NORTH, txtPass, -3, SpringLayout.NORTH, lblPassword);
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblDepartment, 6, SpringLayout.SOUTH, lblPassword);
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblPassword, 103, SpringLayout.NORTH, contentPane);
		contentPane.add(lblPassword);
		
		txtUsername = new JTextField();
		sl_contentPane.putConstraint(SpringLayout.WEST, txtUsername, 0, SpringLayout.WEST, txtPass);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, txtUsername, -11, SpringLayout.NORTH, txtPass);
		sl_contentPane.putConstraint(SpringLayout.EAST, txtUsername, 0, SpringLayout.EAST, txtFname);
		contentPane.add(txtUsername);
		txtUsername.setColumns(10);
		
		txtUserid = new JTextField();
		sl_contentPane.putConstraint(SpringLayout.WEST, txtUserid, 78, SpringLayout.EAST, lblNewLabel);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, txtUserid, -11, SpringLayout.NORTH, txtUsername);
		sl_contentPane.putConstraint(SpringLayout.EAST, txtUserid, 0, SpringLayout.EAST, txtPass);
		txtUserid.setColumns(10);
		contentPane.add(txtUserid);
		
		JButton btnGet = new JButton("Get");
		sl_contentPane.putConstraint(SpringLayout.EAST, btnGet, -49, SpringLayout.WEST, lblAdministrator);
		btnGet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {// get button actions....i could not find a method 
															//to get the nemes directly by combobox action method.
															//then jumped to a button
				try {
				String userlist = sqlaction.getAllUsers();// call the method
				System.out.println(userlist.toString());
				String[] available = userlist.split(",");//split the string
				int j=0;
				comboBox.removeAllItems();// to clear
				for (String s:available){
					
					comboModel.addElement(s);	//add users 
					j=j++;	
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnGet.setFont(new Font("Tahoma", Font.BOLD, 11));
		sl_contentPane.putConstraint(SpringLayout.NORTH, btnGet, 6, SpringLayout.NORTH, lblAdministrator);
		contentPane.add(btnGet);
		comboBox.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent arg0)//action to selecting of an item of comboBox
            {
               try {
            	   String name = (String) comboBox.getSelectedItem();
				newUser = sqlaction.getUserbyName(name);// call the method
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}//assign the variable values to the textboxes
              String id= String.valueOf(newUser.getUserId());
              txtUserid.setText(id); 
              txtUsername.setText(newUser.getUserName());
              txtPass.setText(newUser.getPassWord());
              txtFname.setText(newUser.getFirstName());
              txtLname.setText(newUser.getLastName());
              txtUtype.setText(newUser.getUserType());
            }
        });   
		
		
		JButton btnClear = new JButton("Clear");
		sl_contentPane.putConstraint(SpringLayout.WEST, btnClear, 6, SpringLayout.EAST, btnDelete);
		sl_contentPane.putConstraint(SpringLayout.EAST, btnClear, -6, SpringLayout.WEST, btnExit);
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {//clear button actions
				  txtUserid.setText(""); 
	              txtUsername.setText("");
	              txtPass.setText("");
	              txtFname.setText("");
	              txtLname.setText("");
	              txtUtype.setText("");
			}
		});
		btnClear.setFont(new Font("Tahoma", Font.BOLD, 11));
		sl_contentPane.putConstraint(SpringLayout.NORTH, btnClear, 0, SpringLayout.NORTH, btnAdd);
		contentPane.add(btnClear);
		
		
	}
	
}

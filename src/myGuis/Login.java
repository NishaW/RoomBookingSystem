package myGuis;
import myBooking.*;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SpringLayout;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
//Login GUI, this is the starting screen
public class Login extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public JTextField txtuserName;
	private JPasswordField txtpass;
	public static Login framelogin = new Login();
	public MysqlAction tabrec = new MysqlAction();
	public static User myUser = new User();
	public static Admin frameAdmin = new Admin();
	/**
	 * Launch the application.
	 */

	public static void main(String[] args) {//main method which call the login window
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					framelogin.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
			}

	/**
	 * Create the frame.
	 */
	public Login() {
		setFont(new Font("Dialog", Font.BOLD, 14));
		setTitle("Conference Room Booking System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		SpringLayout sl_contentPane = new SpringLayout();
		contentPane.setLayout(sl_contentPane);
		
		JLabel lblLogin = new JLabel("LOGIN");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblLogin, 10, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblLogin, 186, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, lblLogin, -90, SpringLayout.EAST, contentPane);
		lblLogin.setFont(new Font("Tahoma", Font.BOLD, 14));
		contentPane.add(lblLogin);
		
		JLabel lblUserName = new JLabel("User Name");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblUserName, 95, SpringLayout.NORTH, contentPane);
		lblUserName.setFont(new Font("Times New Roman", Font.BOLD, 14));
		sl_contentPane.putConstraint(SpringLayout.WEST, lblUserName, 65, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, lblUserName, -262, SpringLayout.EAST, contentPane);
		contentPane.add(lblUserName);
		
		JLabel lblPass = new JLabel("Password");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblPass, 133, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, lblUserName, -11, SpringLayout.NORTH, lblPass);
		lblPass.setFont(new Font("Times New Roman", Font.BOLD, 14));
		sl_contentPane.putConstraint(SpringLayout.SOUTH, lblPass, -91, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblPass, 65, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, lblPass, 162, SpringLayout.WEST, contentPane);
		contentPane.add(lblPass);
		
		 txtuserName = new JTextField();
		sl_contentPane.putConstraint(SpringLayout.WEST, txtuserName, 41, SpringLayout.EAST, lblUserName);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, txtuserName, -124, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, txtuserName, 159, SpringLayout.EAST, lblUserName);
		contentPane.add(txtuserName);
		txtuserName.setColumns(10);
		
		txtpass = new JPasswordField();
		sl_contentPane.putConstraint(SpringLayout.NORTH, txtpass, 15, SpringLayout.SOUTH, txtuserName);
		sl_contentPane.putConstraint(SpringLayout.WEST, txtpass, 41, SpringLayout.EAST, lblPass);
		sl_contentPane.putConstraint(SpringLayout.EAST, txtpass, 0, SpringLayout.EAST, txtuserName);
		contentPane.add(txtpass);
		
				
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {//login button actions
			public void actionPerformed(ActionEvent e) {
					
				RoomBooking frameBooking = new RoomBooking();
				
				try {
					myUser = tabrec.getUserbyName(txtuserName.getText());//call the method
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				String name = myUser.getUserName();
				String pass = myUser.getPassWord();
				String guiname = txtuserName.getText();//assign values to textboxs
				@SuppressWarnings("deprecation")
				String guipass = txtpass.getText();
				String type = myUser.getUserType();
				
				
				if(guiname.equals(name) && guipass.equals(pass)){//check the username and password
					
					if( type.equals("Admin")){//check the user type
							
						frameAdmin.setVisible(true);
						framelogin.setVisible(false);
		
					} else{
						frameBooking.setVisible(true);
						framelogin.setVisible(false);
					}
				
										
				}else {//login error messages
					JOptionPane.showMessageDialog(frameAdmin,
						    "Invalid Username or Password..Try again",
						    "Login error",
						    JOptionPane.ERROR_MESSAGE);


				}
					
			}
		});
		sl_contentPane.putConstraint(SpringLayout.WEST, btnLogin, -280, SpringLayout.EAST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, btnLogin, -22, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, btnLogin, -135, SpringLayout.EAST, contentPane);
		btnLogin.setFont(new Font("Tahoma", Font.BOLD, 11));
		contentPane.add(btnLogin);
	}
	
	public String getCurrentUser(String username){//method to get the current user
		username = myUser.getUserName();// call the user class method
		return username;
		
	}
}

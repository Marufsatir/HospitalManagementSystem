package hospitalmanagementsystem;
/**
 * This class shows the list of patients from database
 *
 * @author Maruf Þatýr
 * @date 2019.12.21
 */
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


  /**
 * This class generate a user as an admin to manage
 * @author Muhammed Maruf Satir
 * @date 2019.12.10
 */
public class AdminMain extends javax.swing.JFrame
{
	private JButton aLoginBtn;
	private JPasswordField aPassField;
	private JTextField aUserField;
	private JLabel aUserLabel;
	private javax.swing.JButton dExitBtn;
	private javax.swing.JLabel jLabel2;
	private java.awt.Label mLabel;

	String username;

	public AdminMain() 
	{
		initComponents();
		connect();
	}
   Connection con;	    PreparedStatement pst;   ResultSet rs;
	    
   /**
    * connect method establish the link between database and java codes
    */
   public void connect()
   {
	   try {
		   Class.forName("com.mysql.jdbc.Driver");
		   con = DriverManager.getConnection("jdbc:mysql://localhost/hospitalmanagementsystem", "root", "");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
			e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    }


   
   /**
    * connect method establish the link between database and java codes
    */
	private void initComponents() 
	{
		aUserLabel = new javax.swing.JLabel();
		jLabel2 = new javax.swing.JLabel();
		aUserField = new javax.swing.JTextField();
		aPassField = new javax.swing.JPasswordField();
		dExitBtn = new javax.swing.JButton();
		aLoginBtn = new javax.swing.JButton();
		mLabel = new java.awt.Label();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setTitle("Welcome !");
		setMaximizedBounds(new java.awt.Rectangle(0, 0, 1000, 550));
		setResizable(true);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double screenHeight = screenSize.height;
		double screenWidth = screenSize.width;
		setSize(new java.awt.Dimension(1000, 550));
		

		aUserLabel.setFont(new java.awt.Font("Tahoma", 0, 24)); 
		aUserLabel.setText("Username:");

		jLabel2.setFont(new java.awt.Font("Tahoma", 0, 24));
		jLabel2.setText("Password:");

		aUserField.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
			}
		});

		dExitBtn.setBackground(new java.awt.Color(0, 204, 204));
		dExitBtn.setFont(new java.awt.Font("Tahoma", 0, 18)); 
		dExitBtn.setForeground(new java.awt.Color(255, 255, 255));
		dExitBtn.setText("CreateUser");
		dExitBtn.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				UserCreation user = new UserCreation();
				user.setVisible(true);
				setVisible(false);
		}
		});

		aLoginBtn.setBackground(new java.awt.Color(0, 153, 153));
		aLoginBtn.setFont(new java.awt.Font("Tahoma", 0, 18)); 
		aLoginBtn.setForeground(new java.awt.Color(255, 255, 255));
		aLoginBtn.setText("Login");
		aLoginBtn.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				setVisible(false);
				String userName = aUserField.getText();
				String password = aPassField.getText();
				
				connect();
				try {
					pst = con.prepareStatement("select * from user where username = ? and password = ?");
					pst.setString(1, userName);
					pst.setString(2, password);
					
					rs = pst.executeQuery();

					if(rs.next())
					{
						int userid = rs.getInt("id");
						String uType = rs.getString("utype");
						
						if(uType.equals("Doctor"))
						{
							new DoctorPanel(userid, userName, password).setVisible(true);
						}else if(uType.equals("Receptionist")) 
						{
							new ReceptionistPanel(userid, userName, password).setVisible(true);
						}else if(uType.equals("Manager"))
						{
							new ManagerPanel(userid, userName, password).setVisible(true);
						}
						}else
					{
						JOptionPane.showMessageDialog(aLoginBtn, "Username or password is not true");
						new AdminMain().setVisible(true);
						aUserField.setText("");
						aPassField.setText("");
						aUserField.requestFocus();
						
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});

		mLabel.setAlignment(java.awt.Label.CENTER);
		mLabel.setBackground(new java.awt.Color(0, 153, 153));
		mLabel.setFont(new java.awt.Font("Arial", 0, 24));
		mLabel.setForeground(new java.awt.Color(255, 255, 255));
		mLabel.setText("Hospital Management System");

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(mLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 1000, javax.swing.GroupLayout.PREFERRED_SIZE)
				.addGroup(layout.createSequentialGroup()
						.addGap(262, 262, 262)
						.addComponent(aUserLabel)
						.addGap(10, 10, 10)
						.addComponent(aUserField, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE))
				.addGroup(layout.createSequentialGroup()
						.addGap(261, 261, 261)
						.addComponent(jLabel2)
						.addGap(18, 18, 18)
						.addComponent(aPassField, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE))
				.addGroup(layout.createSequentialGroup()
						.addGap(388, 388, 388)
						.addComponent(dExitBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(97, 97, 97)
						.addComponent(aLoginBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
				);
		layout.setVerticalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addGap(35, 35, 35)
						.addComponent(mLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(64, 64, 64)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(aUserLabel)
								.addComponent(aUserField, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGap(105, 105, 105)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(jLabel2)
								.addComponent(aPassField, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGap(73, 73, 73)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(dExitBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(aLoginBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addContainerGap(110, Short.MAX_VALUE))
				);

		pack();
		setLocationRelativeTo(null);
	}

	public static void main(String args[]) {
		new AdminMain().setVisible(true);
	}


}
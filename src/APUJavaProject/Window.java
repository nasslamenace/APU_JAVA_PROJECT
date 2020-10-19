package APUJavaProject;

//file reading and writing
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

//UI
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;

import java.awt.CardLayout;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;

//LIST
import java.util.ArrayList;

public class Window extends JFrame {
	
	private MyPanel container = new MyPanel();
	
	private CardLayout mainLayout = new CardLayout();
	
	
	
	//LOGIN PANEL ATTRIBUTES : 
	
	private MyPanel logInPan = new MyPanel();
	
	private MyLabel userIdLabel = new MyLabel("UserName :");
	private MyLabel passwordLabel = new MyLabel("Password :");
	private MyLabel logInLabel = new MyLabel("LOG IN", SwingConstants.CENTER);
	private MyLabel userTypeLabel = new MyLabel("you are a : ");
	
	private JComboBox  userTypeBox = new JComboBox();
	
	
	private JTextField  userIdTF = new JTextField("John_Doe");
	private JPasswordField  passwordTF = new JPasswordField("AZERTYUIOP");
	
	private MyButton logInButton = new MyButton("log in");
	
	
	//MANAGER PANEL ATTRIBUTES :
	private MyButton MlogOutBtn = new MyButton("LOG OUT");
	
	private ManagerPan managerPan;
	
	//TECHNICIAN PANEL ATTRIBUTES :
	private MyButton TlogOutBtn = new MyButton("LOG OUT");
	
	private TechnicianPan technicianPan;
	
	//CUSTOMER PANEL ATTRIBUTES :
	

	private MyButton ClogOutBtn = new MyButton("LOG OUT");
	
	private CustomerPan customerPan;
	
	
	
	  //Constructor
	  public Window(){
		    this.setSize(400, 440);
		    this.setTitle("APU Automotive Service Center");
		    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    this.setLocationRelativeTo(null);
		    this.setResizable(true);
		    //initialize the container and its component
		    initComponent();
			//adding the container
		    logInPan.setOpaque(true);
		    logInPan.setBackground(new Color(61, 97, 166));
		    this.setContentPane(container);
		    this.setVisible(true);
		  }
	  
	  //UI
	  public void initComponent() {
		  
		  //LOGIN PANEL 
		  Font myFont = new Font( "ChalkBoard", Font.BOLD, 20);
		  userIdLabel.setFont(myFont);
		  passwordLabel.setFont(myFont);
		  logInLabel.setFont(new Font( "ChalkBoard", Font.BOLD, 40));
		  logInLabel.setForeground(Color.white);
		  
		  passwordTF.setEchoChar('*');
		  
		  userTypeBox.addItem("Manager");
		  userTypeBox.addItem("Technician");
		  userTypeBox.addItem("Customer");
		  
		  logInButton.addActionListener(new logInListener());
		  MlogOutBtn.addActionListener(new logOutListener());
		  TlogOutBtn.addActionListener(new logOutListener());
		  ClogOutBtn.addActionListener(new logOutListener());
		  
		  MyPanel myLogInPan = new MyPanel();
		  myLogInPan.setLayout(new BoxLayout(myLogInPan, BoxLayout.PAGE_AXIS));
		  myLogInPan.add(Box.createVerticalStrut(45)); 
		  myLogInPan.add(userIdLabel);
		  myLogInPan.add(userIdTF);
		  myLogInPan.add(Box.createVerticalStrut(45));  
		  myLogInPan.add(passwordLabel);
		  myLogInPan.add(passwordTF);
		  myLogInPan.add(Box.createVerticalStrut(20));  
		  myLogInPan.add(userTypeLabel);
		  myLogInPan.add(userTypeBox);
		  
		  
		  logInPan.setLayout(new BorderLayout());
		  
		  MyPanel btnPan = new MyPanel();
		  btnPan.add(logInButton);
		  
		  logInPan.add(logInLabel, BorderLayout.NORTH);
		  logInPan.add(myLogInPan, BorderLayout.CENTER);
		  logInPan.add(btnPan, BorderLayout.SOUTH);
		  
		  
		  //MANAGER PANEL
		  
		 
		  managerPan = new ManagerPan();
		  

		  managerPan.add(MlogOutBtn, BorderLayout.SOUTH);
		  
		  

		  //TECHNICIAN PANEL
			
		  technicianPan = new TechnicianPan();
		  

		  technicianPan.add(TlogOutBtn, BorderLayout.SOUTH);
		  
		  //CUSTOMER PANEL
		  
		  customerPan = new CustomerPan();

		  

		  customerPan.add(ClogOutBtn, BorderLayout.SOUTH);
		 //--------------------------------------------------------------------
		  
		  
		  container.setLayout(mainLayout);
		  
		  container.add(logInPan, "logIN");
		  container.add(managerPan, "Manager");
		  container.add(technicianPan, "Technician");
		  container.add(customerPan, "Customer");
		  
		  
		  
		  
	  }
	  
	  //internal class that implements ActionListener wich allow us to listen to the login button
	  class logInListener implements ActionListener{
	    public void actionPerformed(ActionEvent arg0) {
	    	
	    	
	    	//list of our users (Manager, Technician or customer)
	    	
	    	
	    	
	    	ArrayList<User> usersList = (ArrayList<User>) FileManager.readFile(((String)userTypeBox.getSelectedItem()) + ".txt");
	    	
	    	
	    	
	    	/*
	    	//we open the file according to the user choice
	    	String file = ((String)userTypeBox.getSelectedItem()) + ".txt";
	    	
	    	FileInputStream fis = null;
	    	  ObjectInputStream ois = null;
	    	  try {
				fis = new FileInputStream(file);
	    	    ois = new ObjectInputStream(fis);
	    	     
	    	    //we read into our file the users list
	    	    usersList = (ArrayList<User>) ois.readObject();
	    	    
	    	  } catch (FileNotFoundException fnfe) {
	    	    System.out.println("Could not find file");
	    	    fnfe.printStackTrace();
	    	  } catch (ClassNotFoundException cnfe) {
	    	    System.out.println("File format is wrong :(");
	    	    cnfe.printStackTrace();
	    	  } catch (IOException ioe) {
	    	    System.out.println("I/O Exception while reading file");
	    	    ioe.printStackTrace();
	    	  } finally {
	    	    if (fis != null) {
	    	      safeClose(fis);
	    	    }
	    	  }*/
	    	  
	    	  boolean found = false;
	    	  
	    	  //if the username AND the password exist in our list, we open the correct login page (Manager, technician or customer)
	    	  for(int i = 0; i < usersList.size(); i++) {
	    		  
	    		  
	    		  if(userIdTF.getText().equals(usersList.get(i).getUserName()) && passwordTF.getText().equals(usersList.get(i).getUserPassword())) {
	    			  
	    			  
	    			  found = true;
	    			  
	    			  switch((String)userTypeBox.getSelectedItem()) {
	    			  case "Manager":
	    				  managerPan.setConnectedManager(new Manager(userIdTF.getText(), passwordTF.getText()));
	    				  break;
	    			  case "Technician":
	    				  technicianPan.setConnectedTechnician(FileManager.returnSpecificTechnician(userIdTF.getText(), passwordTF.getText()));
	    				  break;
	    			  case "Customer":
	    				  customerPan.setConnectedCustomer(FileManager.returnSpecificCustomer(userIdTF.getText(), passwordTF.getText()));
	    				  break;
	    			  }
	    			  	    			  
	    			  mainLayout.show(container, (String)userTypeBox.getSelectedItem());
	    			  
	    		  }
	    		  
	    	  }
	    	  
	    	  if(!found) {
	    		//error message if the username or the password does not exist in our list
	    		JOptionPane message = new JOptionPane();
	    		message.showMessageDialog(null, "Username or password is incorrect", "Error", JOptionPane.ERROR_MESSAGE);
	    	  }
	    	
	    }
	    
	    //allow us to close safely the stream
	    private void safeClose(Closeable closeable) {
			  try {
			    closeable.close();
			  } catch (IOException ioe) {
			    throw new RuntimeException(ioe);
			  }
		}
	  }
	  
	  //Allow us to listen to the log out button. When it is pressed, we come back tp the log in page thanks to our cardLayout
	  public class logOutListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			mainLayout.show(container, "logIN");
			
		}
		  
		  
		  
	  }
	  


}


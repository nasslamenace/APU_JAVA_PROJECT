package APUJavaProject;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Closeable;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;

public class RegisterWindow extends JDialog {
	
	private MyPanel container = new MyPanel();
	
	private MyLabel userNameLbl = new MyLabel("Input the user-name : ");
	private MyLabel passwordLbl = new MyLabel("Input the password : ");
	private MyLabel departmentLbl = new MyLabel("Input the department : ");
	private MyLabel phoneNumberLbl = new MyLabel("Input the phone number : ");
	private MyLabel emailLbl = new MyLabel("Input the email : ");
	private MyLabel adressLbl = new MyLabel("Input the mailing adress : ");

	private JTextField userNameTf = new JTextField("John_Doe");
	private JPasswordField passwordTf = new JPasswordField("AZERTYUIOP");
	
	private JTextField phoneTf = new JTextField("+33777494661");
	private JTextField mailTf = new JTextField("john_doe@gmail.com");
	private JTextField adressTf = new JTextField("8 rue amédée Simon Villeneuve le roi 94290 France");
	
	
	private JComboBox<String> userTypeBox = new JComboBox<String>();
	private JComboBox<String> departmentBox = new JComboBox<String>();
	
	private MyButton registerButton = new MyButton("Register");
	
	
	private MyPanel centerPan = new MyPanel();
	
	
	
	  public RegisterWindow(JFrame parent, String title, boolean modal){
		    super(parent, title, modal);
		    this.setSize(430, 440 );
		    this.setLocationRelativeTo(null);
		    this.setResizable(true);
		    
		    initComponent();
		    this.setContentPane(container);
		    this.setVisible(true);
		  }
	  
	  private void initComponent() {
		  
		  userTypeBox.addItem("Manager");
		  userTypeBox.addItem("Technician");
		  userTypeBox.addItem("Customer");
		  
		  userTypeBox.addActionListener(new DepartmentListener());
		  
		  departmentBox.addItem("Technician");
		  
		  registerButton.addActionListener(new registerListener());
		  
		  
		  
		  
		  centerPan.setLayout(new BoxLayout(centerPan, BoxLayout.Y_AXIS));
		  
		  centerPan.add(userTypeBox);
		  
		  centerPan.add(userNameLbl);
		  
		  centerPan.add(userNameTf);
		  
		  centerPan.add(passwordLbl);
		  centerPan.add(passwordTf);
		  
		  centerPan.add(registerButton);
		 
		  
		  centerPan.setSize(this.getWidth(), this.getHeight());
		  
		  container.add(centerPan);
		  
		  
		  
		  
	  }
	  
	  
	  public class DepartmentListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String s = (String) userTypeBox.getSelectedItem();
			
			switch(s) {
			
			case "Technician":
				  
				  centerPan.remove(registerButton); 
				  centerPan.add(phoneNumberLbl);
				  centerPan.add(phoneTf);
				  
				  centerPan.add(emailLbl);
				  centerPan.add(mailTf);
				  
				  centerPan.add(adressLbl);
				  centerPan.add(adressTf);
				  
				  centerPan.add(departmentLbl);
				  centerPan.add(departmentBox); 
				  
				  centerPan.add(registerButton);
				  
				  centerPan.revalidate();
				  centerPan.repaint();
				  break;
			case "Customer":
				  centerPan.remove(registerButton);
				  
				  centerPan.add(phoneNumberLbl);
				  centerPan.add(phoneTf);
				  
				  centerPan.add(emailLbl);
				  centerPan.add(mailTf);
				  
				  centerPan.add(adressLbl);
				  centerPan.add(adressTf);
				  
				  centerPan.remove(departmentLbl);
			   	  centerPan.remove(departmentBox);
			   	  
			      centerPan.add(registerButton);
			      
				  centerPan.revalidate();
				  centerPan.repaint();
				  break;
			case "Manager":
				  centerPan.remove(phoneNumberLbl);
				  centerPan.remove(phoneTf);
				  
				  centerPan.remove(emailLbl);
				  centerPan.remove(mailTf);
				  
				  centerPan.remove(adressLbl);
				  centerPan.remove(adressTf);
				  
				  centerPan.remove(departmentLbl);
				  centerPan.remove(departmentBox);
				  
				  centerPan.revalidate();
				  centerPan.repaint();
				  break;
				
				  
				
					
			
			}
			

				
			
		}
		  
		  
		  
		  
	  }
	  
	  
	  public class registerListener implements ActionListener {
		  
			@Override
			public void actionPerformed(ActionEvent e) {
				
		    	String file = ((String)userTypeBox.getSelectedItem()) + ".txt";
		    	
		        boolean found = false;
		    	
				
				switch((String)userTypeBox.getSelectedItem()) {
				
				case "Manager":
					ArrayList<Manager> usersList = (ArrayList<Manager>) FileManager.readFile(file);
			    	  
			    	  
			    	  for(int i = 0; i < usersList.size(); i++) {
			    		  
			    		  if(userNameTf.getText().equals(usersList.get(i).userName)) {
			    			  
			    			  JOptionPane.showMessageDialog(null, "This user is already registered !", "Erreur", JOptionPane.ERROR_MESSAGE);
			    			  found = true;
			    			  
			    		  }	  
			    	  }
			    	  
			    	  if(!found) {
			    		  usersList.add(new Manager(userNameTf.getText(), passwordTf.getText()));
			    		  
			    		  FileManager.updateManagerFile(usersList);
			    		  JOptionPane.showMessageDialog(null, "Registration succeded !", "Information", JOptionPane.INFORMATION_MESSAGE);
			    		  
			    	  }
			    	  break;
				case "Technician":
					ArrayList<Technician> TusersList = (ArrayList<Technician>) FileManager.readFile(file);
					
					if(!TusersList.isEmpty()){
						
						int maxId = 0;
						
						for(int i = 0 ; i < TusersList.size(); i++) {
							
							if(TusersList.get(i).getIdentityCardNumber() > maxId)
								maxId = TusersList.get(i).getIdentityCardNumber();
						}
						
						Customer.customerNumber =  maxId + 1;
					}
					else
						Technician.technicianNumber = 0;
			    	  
			    	  for(int i = 0; i < TusersList.size(); i++) {
			    		  
			    		  if(userNameTf.getText().equals(TusersList.get(i).userName)) {
			    			  
			    			  JOptionPane message = new JOptionPane();
			    			  message.showMessageDialog(null, "This user is already registered !", "Erreur", JOptionPane.ERROR_MESSAGE);
			    			  found = true;
			    			  
			    		  }
			    		  
			    	  }
			    	  
			    	  if(!found) {
			    		  TusersList.add(new Technician(userNameTf.getText(), passwordTf.getText(), (String)departmentBox.getSelectedItem(), phoneTf.getText(), mailTf.getText(), adressTf.getText()));
			    		  
			    		  FileManager.updateTechnicianFile(TusersList);
			    		  JOptionPane.showMessageDialog(null, "Registration succeded !", "Information", JOptionPane.INFORMATION_MESSAGE);
			    	  }
			    	  break;
				case "Customer":
					ArrayList<Customer> CusersList = (ArrayList<Customer>) FileManager.readFile(file);
			    	  
					if(!CusersList.isEmpty()) {
						
						int maxId = 0;
						
						for(int i = 0 ; i < CusersList.size(); i++) {
							
							if(CusersList.get(i).getIdentityCardNumber() > maxId)
								maxId = CusersList.get(i).getIdentityCardNumber();
						}
						
						Customer.customerNumber =  maxId + 1;
					}
					else
						Customer.customerNumber = 0;
					
			    	  for(int i = 0; i < CusersList.size(); i++) {
			    		  
			    		  if(userNameTf.getText().equals(CusersList.get(i).userName)) {
			    			  
			    			  JOptionPane message = new JOptionPane();
			    			  message.showMessageDialog(null, "This user is already registered !", "Erreur", JOptionPane.ERROR_MESSAGE);
			    			  found = true;
			    			  
			    		  }
			    		  
			    	  }
			    	  
			    	  if(!found) {
			    		  CusersList.add(new Customer(userNameTf.getText(), passwordTf.getText(), phoneTf.getText(), mailTf.getText(), adressTf.getText()));		
			    		  FileManager.updateCustomerFile(CusersList);	
			    		  JOptionPane.showMessageDialog(null, "Registration succeded !", "Information", JOptionPane.INFORMATION_MESSAGE);
			    	  }
			    	  break;
				}
				
			}
	  }
	  

}

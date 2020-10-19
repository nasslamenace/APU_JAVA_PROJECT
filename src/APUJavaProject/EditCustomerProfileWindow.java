package APUJavaProject;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class EditCustomerProfileWindow extends JDialog {
	
	private MyPanel container = new MyPanel();
	

	private MyLabel title = new MyLabel("here is your informations, feel free to change what you need :");
	
	private MyButton saveUpdatesBtn = new MyButton("Save Updates");
	
	private JTable myTab;
	
	private Customer myCustomer, updatedCustomer;
	
	  public EditCustomerProfileWindow(JFrame parent, String title, boolean modal, int customerId){
		    super(parent, title, modal);
		    this.setSize(700, 200);
		    this.setLocationRelativeTo(null);
		    this.setResizable(false);
		    
		    this.myCustomer = FileManager.returnSpecificCustomer(customerId);
		    
		    initComponent();
		    this.setContentPane(container);
		    this.setVisible(true);
		  }
	
	  private void initComponent() {
		  
		  
		  
		  
		  updatedCustomer = FileManager.returnSpecificCustomer(myCustomer.getUserName(), myCustomer.getUserPassword());

		  
		  String[] titles = {"user-name", "user-password", "phone", "email", "mailing"};
		  
		  Object[][] data = {{myCustomer.getUserName(), myCustomer.getUserPassword(), myCustomer.getPhoneNumber(), myCustomer.getEmailAdress(), myCustomer.getMailingAdress()}};
		  
		  
		  myTab = new JTable(data, titles);
		  MyTable.applyDesign(myTab);
		  
		  
		  saveUpdatesBtn.addActionListener(new saveUpdateListener());
		  title.setAlignmentX(CENTER_ALIGNMENT);
		  container.setLayout(new BorderLayout());
		  container.add(title, BorderLayout.NORTH);
		  container.add(new JScrollPane(myTab), BorderLayout.CENTER);
		  container.add(saveUpdatesBtn, BorderLayout.SOUTH);
		  
		  
	  }
	  public class saveUpdateListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			myCustomer.setUserName((String) myTab.getModel().getValueAt(0, 0));
			myCustomer.setUserPassword((String) myTab.getModel().getValueAt(0, 1));
			myCustomer.setPhoneNumber((String) myTab.getModel().getValueAt(0, 2));
			myCustomer.setEmailAdress((String) myTab.getModel().getValueAt(0, 3));
			myCustomer.setMailingAdress((String) myTab.getModel().getValueAt(0, 4));
			
			
			
			
			ArrayList<Customer> usersList = (ArrayList<Customer>) FileManager.readFile("Customer.txt");
			
			for(int i = 0; i < usersList.size(); i++) {
				
				if(usersList.get(i).getIdentityCardNumber() == myCustomer.getIdentityCardNumber())
				{
					usersList.remove(i);
					i = usersList.size();
				}
				
			}
			usersList.add(myCustomer);
			FileManager.updateCustomerFile(usersList);
			dispose();
			
		}
		  
	  }
	

}

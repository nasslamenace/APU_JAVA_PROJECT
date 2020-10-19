package APUJavaProject;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class ManageUsersWindow extends JDialog {
	
	private ManagerTableModel myManagerModel;
	private TechnicianTableModel myTechnicianModel;
	private CustomerTableModel myCustomerModel;
	
	private MyPanel container = new MyPanel();
	private MyPanel buttonsPan = new MyPanel();
	private MyPanel searchPan = new MyPanel();
	
	private MyButton deleteUserBtn = new MyButton("delete user");
	private MyButton saveUpdateBtn = new MyButton("save updates");
	private MyButton searchBtn = new MyButton("search");
	
    private MyLabel searchLbl = new MyLabel("search by name :");
    
    private JTextField searchTxtFld = new JTextField();
	
	private MyTable myTab;
	
	private String userType;
	
	 public ManageUsersWindow(JFrame parent, String title, boolean modal, String userType){
		    super(parent, title, modal);
		    this.setSize(700,700);
		    this.setLocationRelativeTo(null);
		    this.setResizable(true);
		    this.userType = userType;
		    
		    container.setLayout(new BorderLayout());
		    searchPan.setLayout(new BoxLayout(searchPan, BoxLayout.X_AXIS));
		    
		    initComponent();
		    this.setContentPane(container);
		    this.setVisible(true);
		  }
	 
	 private void initComponent() {
		 
		String file = userType + ".txt";
	    	
	    
	    
		 
		 switch(userType) {
		 case "Manager":
			 ArrayList<Manager> usersList = (ArrayList<Manager>) FileManager.readFile(file);
	    	  
	    	  myManagerModel = new ManagerTableModel(usersList);

	    	  myTab = new MyTable(myManagerModel);
	    	  
	    	  
	    	  myTab.setRowSelectionInterval(1, 1);
	    	  
	    	  deleteUserBtn.addActionListener(new DeleteListener());
	    	  saveUpdateBtn.addActionListener(new SaveUpdateListener());
	    	  
	    	  buttonsPan.add(deleteUserBtn);
	    	  buttonsPan.add(saveUpdateBtn);
	    	  
	    	  
	          
	          
	    	  
	    	  //container.add(myTab.getTableHeader(), BorderLayout.NORTH);
	    	  container.add(new JScrollPane(myTab), BorderLayout.CENTER);
	    	  container.add(buttonsPan, BorderLayout.SOUTH);
	    	  
	    	  
	    	  break;
		 case "Technician":
			 ArrayList<Technician> TusersList = (ArrayList<Technician>) FileManager.readFile(file);
	    	  
	    	  myTechnicianModel = new TechnicianTableModel(TusersList);

	    	  myTab = new MyTable(myTechnicianModel);
	    	  
	    	  deleteUserBtn.addActionListener(new DeleteListener());
	    	  saveUpdateBtn.addActionListener(new SaveUpdateListener());
	    	  
	    	  buttonsPan.add(deleteUserBtn);
	    	  buttonsPan.add(saveUpdateBtn);
	    	  
	          
	          
	    	  
	    	  //container.add(myTab.getTableHeader(), BorderLayout.NORTH);
	    	  container.add(new JScrollPane(myTab), BorderLayout.CENTER);
	    	  container.add(buttonsPan, BorderLayout.SOUTH);
	    	  break;
		 case "Customer":
			 ArrayList<Customer> CusersList = (ArrayList<Customer>) FileManager.readFile(file);
	    	  
	    	  myCustomerModel = new CustomerTableModel(CusersList);

	    	  myTab = new MyTable(myCustomerModel);
	    	  
	    	  
	    	  deleteUserBtn.addActionListener(new DeleteListener());
	    	  saveUpdateBtn.addActionListener(new SaveUpdateListener());
	    	  
	    	  buttonsPan.add(deleteUserBtn);
	    	  buttonsPan.add(saveUpdateBtn);
	    	  
	    	  JScrollPane myScroll = new JScrollPane(myTab);
	    	  

	          
	    	  
	    	  //container.add(myTab.getTableHeader(), BorderLayout.NORTH);
	    	  container.add(new JScrollPane(myTab), BorderLayout.CENTER);
	    	  container.add(buttonsPan, BorderLayout.SOUTH);
	    	  break;
			 
		 }
		 
		 
		 searchBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				ArrayList<User> userslist;
				
				switch(userType){
				
				case "Manager":
					userslist = (ArrayList<User>) FileManager.readFile("Manager.txt");
					break;
				case "Technician":
					userslist = (ArrayList<User>) FileManager.readFile("Technician.txt");
					break;
				case "Customer":
					userslist = (ArrayList<User>) FileManager.readFile("Customer.txt");
					break;
				default:
					userslist = (ArrayList<User>) FileManager.readFile("Manager.txt");
				}
				
				boolean found = false;
				
				for(int i = 0; i < userslist.size(); i++) {
					if(userslist.get(i).getUserName().equals(searchTxtFld.getText())) {
						myTab.setRowSelectionInterval(i, i);
					}
				}
				
			}
			 
		 });
		 
		 searchPan.add(searchLbl);
		 searchPan.add(searchTxtFld);
		 searchPan.add(searchBtn);
		 
		 container.add(searchPan, BorderLayout.NORTH);
		 
		 
		 
		 
		 
	 }
	 
	 public class DeleteListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
            int[] selection = myTab.getSelectedRows();
            
            for(int i = selection.length - 1; i >= 0; i--){
            	
            	switch(userType) {
            	case "Manager":
            		myManagerModel.removeUser(selection[i]);
            		break;
            	case "Technician":
            		myTechnicianModel.removeUser(selection[i]);
            		break;
            	case "Customer":
            		myCustomerModel.removeUser(selection[i]);
            		break;
            	}
                
            }
			
		}		 
	 }
	 
	 public class SaveUpdateListener implements ActionListener{
		 
			@Override
			public void actionPerformed(ActionEvent e) {
				
				switch(userType)
				{
				case "Manager":
					ArrayList<Manager> newUsersList = myManagerModel.getUsersList();
					
					FileManager.updateManagerFile(newUsersList);
					break;
				case "Technician":
					ArrayList<Technician> TnewUsersList = myTechnicianModel.getUsersList();
					
					FileManager.updateTechnicianFile(TnewUsersList);
					break;
				case "Customer":
					ArrayList<Customer> CnewUsersList = myCustomerModel.getUsersList();
					
					FileManager.updateCustomerFile(CnewUsersList);
					break;
					
				}
				
				JOptionPane.showMessageDialog(null, "users has been updated !", "Information", JOptionPane.INFORMATION_MESSAGE);

				
			}
	 }
}

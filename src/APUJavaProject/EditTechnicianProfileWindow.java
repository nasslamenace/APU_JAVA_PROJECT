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

public class EditTechnicianProfileWindow extends JDialog {
	
	private MyPanel container = new MyPanel();
	

	private MyLabel title = new MyLabel("here is your informations, feel free to change what you need :");
	
	private MyButton saveUpdatesBtn = new MyButton("Save Updates");
	
	private JTable myTab;
	
	private Technician myTechnician, updatedTechnician;
	
	  public EditTechnicianProfileWindow(JFrame parent, String title, boolean modal, int technicianId){
		    super(parent, title, modal);
		    this.setSize(700, 200);
		    this.setLocationRelativeTo(null);
		    this.setResizable(false);
		    
		    this.myTechnician = FileManager.returnSpecificTechnician(technicianId);
		    
		    initComponent();
		    this.setContentPane(container);
		    this.setVisible(true);
		  }
	
	  private void initComponent() {
		  
		  
		  
		  
		  updatedTechnician = FileManager.returnSpecificTechnician(myTechnician.getUserName(), myTechnician.getUserPassword());

		  
		  String[] titles = {"user-name", "user-password", "phone", "email", "mailing"};
		  
		  Object[][] data = {{myTechnician.getUserName(), myTechnician.getUserPassword(), myTechnician.getPhoneNumber(), myTechnician.getEmailAdress(), myTechnician.getMailingAdress()}};
		  
		  
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
			
			myTechnician.setUserName((String) myTab.getModel().getValueAt(0, 0));
			myTechnician.setUserPassword((String) myTab.getModel().getValueAt(0, 1));
			myTechnician.setPhoneNumber((String) myTab.getModel().getValueAt(0, 2));
			myTechnician.setEmailAdress((String) myTab.getModel().getValueAt(0, 3));
			myTechnician.setMailingAdress((String) myTab.getModel().getValueAt(0, 4));
			
			
			
			
			ArrayList<Technician> usersList = (ArrayList<Technician>) FileManager.readFile("Technician.txt");
			
			for(int i = 0; i < usersList.size(); i++) {
				
				if(usersList.get(i).getIdentityCardNumber() == myTechnician.getIdentityCardNumber())
				{
					usersList.remove(i);
					i = usersList.size();
				}
				
			}
			usersList.add(myTechnician);
			FileManager.updateTechnicianFile(usersList);
			
			dispose();
			
		}
		  
	  }
	

}

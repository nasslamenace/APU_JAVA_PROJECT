package APUJavaProject;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class CustomerPan extends MyPanel {
	
	
	
	private MyLabel customerTitle = new MyLabel("CUSTOMER INTERFACE :");
	
	private MyButton editProfileBtn = new MyButton("Edit my profile");
	private MyButton historiesBtn = new MyButton("Service and payment histories");
	private MyButton requirementBtn = new MyButton("Give a requirement to improve our software");
	
	private Customer connectedCustomer;
	
	public CustomerPan() {
		
	      super();
	      
	      customerTitle.setFont(new Font("ChalkBoard", Font.PLAIN, 30));
		
		
		  MyPanel myCustomerPan = new MyPanel();
	  	  
		  editProfileBtn.addActionListener(new editListener());
		  
		  historiesBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				HistoriesWindow myHistories = new HistoriesWindow(null, "history", true, connectedCustomer.getIdentityCardNumber());
				
			}
			  
			  
			  
		  });
		  
		  requirementBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new RequirementWindow(null, "Give a requirement", true);
				
			}
			  
		  });
		  
		  myCustomerPan.setLayout(new BoxLayout(myCustomerPan, BoxLayout.PAGE_AXIS));
		  myCustomerPan.add(Box.createVerticalStrut(15));
		  myCustomerPan.add(customerTitle, BorderLayout.NORTH);
		  myCustomerPan.add(Box.createVerticalStrut(15));
		  myCustomerPan.add(editProfileBtn);
		  myCustomerPan.add(Box.createVerticalStrut(15));
		  myCustomerPan.add(historiesBtn);
		  myCustomerPan.add(Box.createVerticalStrut(15));
		  myCustomerPan.add(requirementBtn);
		  
		  this.setLayout(new BorderLayout());
		  
		  this.add(myCustomerPan, BorderLayout.CENTER);
		  
		
	}
	
	
	
	public class editListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			EditCustomerProfileWindow editMyProfile = new EditCustomerProfileWindow(null, "Editing my profile", true, connectedCustomer.getIdentityCardNumber());
			
		}
		
	}
	
	public class FeedbacksListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
		}
		
	}

	public Customer getConnectedCustomer() {
		return connectedCustomer;
	}

	public void setConnectedCustomer(Customer connectedCustomer) {
		this.connectedCustomer = connectedCustomer;
	}

}

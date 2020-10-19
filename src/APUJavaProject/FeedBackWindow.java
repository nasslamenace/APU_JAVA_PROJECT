package APUJavaProject;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

public class FeedBackWindow extends JDialog {
	
	private MyPanel container = new MyPanel();
	
	private MyLabel feedBackLbl = new MyLabel("Can you please give a feedback to your customer :");
	
	private JTextArea feedbackTxtArea = new JTextArea();
	
	private JScrollPane textAreaScroll = new JScrollPane(feedbackTxtArea);
	
	private MyButton confirmBtn = new MyButton("confirm");
	
	private LocalDateTime appointmentDate;
	
	private double cost;
	private String serviceType;
	
	private Customer currentCustomer;
	private int technicianId;
	
	  public FeedBackWindow(JFrame parent, String title, boolean modal, int customerId, int technicianId, LocalDateTime appointmentDate, double cost, String serviceType){
		    super(parent, title, modal);
		    this.setSize(470, 490 );
		    this.setLocationRelativeTo(null);
		    this.setResizable(false);
		    this.technicianId = technicianId;
		    currentCustomer = FileManager.returnSpecificCustomer(customerId);
		    this.appointmentDate = appointmentDate;
		    this.cost = cost;
		    this.serviceType = serviceType;
		    initComponent();
		    this.setContentPane(container);
		    this.setVisible(true);
		  }

	private void initComponent() {
		
		container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
		
		confirmBtn.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				
				currentCustomer.addFeedback(new Feedback(technicianId, feedbackTxtArea.getText(), appointmentDate, cost, serviceType));
				
				ArrayList<Customer> customers = (ArrayList<Customer>) FileManager.readFile("Customer.txt");
				
				for(int i = 0; i < customers.size(); i++) {
					
					if(customers.get(i).getIdentityCardNumber() == currentCustomer.getIdentityCardNumber())
						customers.remove(i);
				}
				
				customers.add(currentCustomer);
				
				FileManager.updateCustomerFile(customers);
				
				dispose();
	
			}
			
			
		});
		
		
		feedBackLbl.setVerticalTextPosition(SwingConstants.CENTER);
		
		
		textAreaScroll.setSize(50, 50);
		
		container.add(feedBackLbl, BorderLayout.NORTH);
		container.add(textAreaScroll, BorderLayout.CENTER);
		container.add(confirmBtn, BorderLayout.SOUTH);
	}

}

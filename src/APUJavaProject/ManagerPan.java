package APUJavaProject;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

public class ManagerPan extends MyPanel {

	private MyLabel managerTitle = new MyLabel("MANAGER INTERFACE :", SwingConstants.CENTER);

	private MyButton registerBtn = new MyButton("Register new end users");
	private MyButton manageBtn = new MyButton("Manage users's information");
	private MyButton setPriceBtn = new MyButton("Set the prices of services");
	private MyButton customerAssistanceBtn = new MyButton("Assist customer for booking service appointment");
	private MyButton generateReceiptBtn = new MyButton("Generate receipt of ended appointment");
	private MyButton cancelBookingBtn = new MyButton("cancel an appointment");
	private MyButton accessBalanceBtn = new MyButton("access company balance");
	private MyButton checkRequirementsBtn = new MyButton("check requirements");
	
	public ManagerPan() {

		super();

		managerTitle.setFont(new Font("ChalkBoard", Font.PLAIN, 30));

		registerBtn.addActionListener(new RegisterListener());
		manageBtn.addActionListener(new ManageListener());
		setPriceBtn.addActionListener(new SetPriceListener());
		customerAssistanceBtn.addActionListener(new CustomerAssistanceListener());

		generateReceiptBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				try {

					int customerId = Integer
							.parseInt(JOptionPane.showInputDialog("who needs a receipt ? (Input the customer id)"));

					if (customerId != -1) {
						if (FileManager.isCustomerExist(customerId))
							new GenerateReceiptWindow(null, "Receipt", true, customerId);
						else
							JOptionPane.showMessageDialog(null, "This customer does not exist !", "error",
									JOptionPane.ERROR_MESSAGE);
					}

				} catch (NumberFormatException parseException1) {
					JOptionPane.showMessageDialog(null, "You have to input a number !", "Erreur",
							JOptionPane.ERROR_MESSAGE);
				}

			}

		});

		cancelBookingBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int customerId = Integer.parseInt(JOptionPane
							.showInputDialog("who needs to cancel an appointment ? (Input the customer id)"));

					if (FileManager.isCustomerExist(customerId))
						new CancelBookingWindow(null, "cancel booking", true, customerId);
					else
						JOptionPane.showMessageDialog(null, "This customer does not exist !", "error",
								JOptionPane.ERROR_MESSAGE);

				} catch (NumberFormatException parseException1) {
					JOptionPane.showMessageDialog(null, "You have to input a number !", "Erreur",
							JOptionPane.ERROR_MESSAGE);
				}

			}

		});

		accessBalanceBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				JOptionPane.showMessageDialog(null,
						"The APU automotive center Balance worth $" + Manager.companyBalance, "Company balance",
						JOptionPane.INFORMATION_MESSAGE, new ImageIcon("logo.png"));
			}

		});
		
		checkRequirementsBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				new RequirementListWindow(null, "requirements", true);
				
			}
			
		});

		MyPanel myManagerPan = new MyPanel();

		myManagerPan.setLayout(new BoxLayout(myManagerPan, BoxLayout.Y_AXIS));
		myManagerPan.add(Box.createVerticalStrut(15));
		myManagerPan.add(registerBtn);
		myManagerPan.add(Box.createVerticalStrut(15));
		myManagerPan.add(manageBtn);
		myManagerPan.add(Box.createVerticalStrut(15));
		myManagerPan.add(setPriceBtn);
		myManagerPan.add(Box.createVerticalStrut(15));
		myManagerPan.add(customerAssistanceBtn);
		myManagerPan.add(Box.createVerticalStrut(15));
		myManagerPan.add(generateReceiptBtn);
		myManagerPan.add(Box.createVerticalStrut(15));
		myManagerPan.add(cancelBookingBtn);
		myManagerPan.add(Box.createVerticalStrut(15));
		myManagerPan.add(accessBalanceBtn);
		myManagerPan.add(Box.createVerticalStrut(15));
		myManagerPan.add(checkRequirementsBtn);
		
		
		this.setLayout(new BorderLayout());

		this.add(managerTitle, BorderLayout.NORTH);
		this.add(new JScrollPane(myManagerPan), BorderLayout.CENTER);

	}

	public void setConnectedManager(Manager connectedManager) {
	}

	public class RegisterListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			new RegisterWindow(null, "Register new end user", true);

		}

	}

	public class ManageListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String[] choice = { "Manager", "Technician", "Customer" };
			String userType = (String) JOptionPane.showInputDialog(null, "what kind of user do you need to manage ?", " ",
					JOptionPane.QUESTION_MESSAGE, null, choice, choice[2]);
			if(userType != null) 
				new ManageUsersWindow(null, "Manage " + userType, true, userType);
		}

	}

	public class SetPriceListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			new SetPriceWindow(null, "Set prices ", true);

		}
	}

	public class CustomerAssistanceListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			String[] choice = { "normal", "major" };

			String serviceType = (String) JOptionPane.showInputDialog(null, "what kind of service you want to book ?",
					" ", JOptionPane.QUESTION_MESSAGE, null, choice, choice[1]);

			if (serviceType != null)
				new BookAppointmentWindow(null, "Book an appointment ", true, serviceType);

		}

	}

}

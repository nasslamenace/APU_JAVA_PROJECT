package APUJavaProject;

import java.util.ArrayList;

public class Customer extends User implements Editable {
	
	private int identityCardNumber;
	private String phoneNumber;
	private String emailAdress;
	private String mailingAdress;
	
	static int customerNumber = 0;
	static ArrayList<Customer> customers = (ArrayList<Customer>) FileManager.readFile("Customer.txt");
	
	
	private ArrayList<Feedback> feedbacks = new ArrayList<Feedback>();
	
	public Customer() {
		
		super("John_Doe", "AZERTYUIOP");
		setPhoneNumber("0777494661");
		setEmailAdress("nassim@gmail.com");
		setMailingAdress("8 rue amédée simon villeneuve le roi 94290");
		setIdentityCardNumber(customerNumber);
		customerNumber++;
		
	}
	
	public Customer(String userName, String userPassword) {
		super(userName, userPassword);
		setIdentityCardNumber(customerNumber);
		customerNumber++;
	}
	
	public Customer(String userName, String userPassword, String phoneNumber, String emailAdress, String mailingAdress) {
		super(userName, userPassword);
		this.setPhoneNumber(phoneNumber);
		this.setEmailAdress(emailAdress);
		this.setMailingAdress(mailingAdress);
		setIdentityCardNumber(customerNumber);
		customerNumber++;
	}

	@Override
	public void edit() {
		
		
		
	}
	
	public void addFeedback(Feedback feedback) {this.feedbacks.add(feedback);}

	public int getIdentityCardNumber() {
		return identityCardNumber;
	}

	public void setIdentityCardNumber(int identityCardNumber) {
		this.identityCardNumber = identityCardNumber;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmailAdress() {
		return emailAdress;
	}

	public void setEmailAdress(String emailAdress) {
		this.emailAdress = emailAdress;
	}

	public String getMailingAdress() {
		return mailingAdress;
	}

	public void setMailingAdress(String mailingAdress) {
		this.mailingAdress = mailingAdress;
	}

	public ArrayList<Feedback> getFeedbacks() {
		return feedbacks;
	}

	public void setFeedbacks(ArrayList<Feedback> feedbacks) {
		this.feedbacks = feedbacks;
	}

}

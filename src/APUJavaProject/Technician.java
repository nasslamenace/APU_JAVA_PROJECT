package APUJavaProject;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Technician extends User implements Editable {
	
	static int technicianNumber = 0;
	static ArrayList<Technician> technicians = (ArrayList<Technician>) FileManager.readFile("Technician.txt");
	
	private int identityCardNumber;
	
	private String department;
	private String phoneNumber;
	private String emailAdress;
	private String mailingAdress;
	private ArrayList<Schedule> schedules = new ArrayList<Schedule>();
	private ArrayList<Comment> comments = new ArrayList<Comment>();
	
	
	
	public Technician() {
		
		super("John_Doe","AZERTYUIOP" );
		department = "Technician";
		phoneNumber = "0777494661";
		emailAdress = "nassim@gmail.com";
		mailingAdress = "8 rue amédée simon villeneuve le roi 94290";
		identityCardNumber = technicianNumber;
		
		technicianNumber++;
		
	}
	
	public Technician(String userName, String userPassword) {
		super(userName, userPassword);
		identityCardNumber = technicianNumber;
		technicianNumber++;
	}
	
	public Technician(String userName, String userPassword, String department, String phoneNumber, String emailAdress, String mailingAdress) {
		
		super(userName, userPassword);
		this.department = department;
		this.phoneNumber = phoneNumber;
		this.emailAdress = emailAdress;
		this.mailingAdress = mailingAdress;
		identityCardNumber = technicianNumber;
		System.out.print("azertgytrez" + Integer.toString(identityCardNumber));
		technicianNumber++;
	}

	@Override
	public void edit() {
		// TODO Auto-generated method stub
		
	}
	
	
	//----------------------GETTER AND SETTER ---------------------------------------------

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

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

	public ArrayList<Schedule> getSchedules() {
		return schedules;
	}

	public void setSchedules(ArrayList<Schedule> schedules) {
		this.schedules = schedules;
	}
	
	public void setComments(ArrayList<Comment> comments){this.comments = comments;}
	
	public ArrayList<Comment> getComments() {return comments;}
	//------------------------------------------------------------------------------------
	
	
	public void addSchedule(Schedule schedule) {this.schedules.add(schedule);}
	
	public void addComment(Comment comment) {this.comments.add(comment);}
	
	public boolean isAvailableAt(LocalDateTime time) {
		
		for(int i = 0; i < this.schedules.size(); i++) {
			
			if(schedules.get(i).getEnd().isAfter(time)  && schedules.get(i).getStart().isBefore(time))
				return false;
		}
		
		return true;
		
	}

}

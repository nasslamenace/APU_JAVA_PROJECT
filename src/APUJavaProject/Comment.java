package APUJavaProject;

import java.time.LocalDateTime;

public class Comment {
	
	private int customerId;
	private String comment;
	private LocalDateTime appointmentDate;
	private Rate rate = Rate.NORATED;
	private int  Mon$nom = 5;
	
	public Comment(int customerId, String comment, LocalDateTime appointmentDate, Rate rate) {
		
		this.setCustomerId(customerId);
		this.setComment(comment);
		this.setAppointmentDate(appointmentDate);
		this.setRate(rate);
		
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public LocalDateTime getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(LocalDateTime appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	public Rate getRate() {
		return rate;
	}

	public void setRate(Rate rate) {
		this.rate = rate;
	}

}

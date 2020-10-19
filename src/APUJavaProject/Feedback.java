package APUJavaProject;

import java.time.LocalDateTime;

public class Feedback {
	
	private int technicianId;
	private String feedback;
	private LocalDateTime appointmentDate;
	private String serviceType;
	private double cost;
	
	
	public Feedback(int technicianId, String feedback, LocalDateTime appointmentDate, double cost, String serviceType) {
		
		this.setTechnicianId(technicianId);
		this.setFeedback(feedback);
		this.setAppointmentDate(appointmentDate);
		this.setCost(cost);
		this.setServiceType(serviceType);
	}

	public int getTechnicianId() {
		return technicianId;
	}

	public void setTechnicianId(int technicianId) {
		this.technicianId = technicianId;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	public LocalDateTime getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(LocalDateTime appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}
}

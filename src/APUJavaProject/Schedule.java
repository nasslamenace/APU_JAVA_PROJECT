package APUJavaProject;

import java.time.LocalDateTime;

public class Schedule {
	
	private LocalDateTime start;
	private LocalDateTime end;
	
	private int customerId;
	
	public Schedule() {
		
		setStart(LocalDateTime.now());
		setEnd(LocalDateTime.now());
		
	}
	
	public Schedule(LocalDateTime start, LocalDateTime end, int customerId) {
		this.setStart(start);
		this.setEnd(end);
		this.setCustomerId(customerId);
	}

	public LocalDateTime getStart() {
		return start;
	}

	public void setStart(LocalDateTime start) {
		this.start = start;
	}

	public LocalDateTime getEnd() {
		return end;
	}

	public void setEnd(LocalDateTime end) {
		this.end = end;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	

	
	

}

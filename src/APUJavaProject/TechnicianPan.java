package APUJavaProject;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;

public class TechnicianPan extends MyPanel {

	private MyLabel technicianTitle = new MyLabel("TECHNICIAN INTERFACE :");
	private MyButton editBtn = new MyButton("Edit my profile");
	private MyButton checkAppointmentBtn = new MyButton("Check my assigned appointments");
	private MyButton accessCommentsBtn = new MyButton("Comments from my customers");
	private MyButton requirementBtn = new MyButton("Give a requirement to improve our software");

	private Technician connectedTechnician;

	public TechnicianPan() {
		super();

		MyPanel myTechnicianPan = new MyPanel();

		technicianTitle.setFont(new Font("ChalkBoard", Font.PLAIN, 30));

		myTechnicianPan.setLayout(new BoxLayout(myTechnicianPan, BoxLayout.PAGE_AXIS));

		myTechnicianPan.add(Box.createVerticalStrut(15));
		myTechnicianPan.add(technicianTitle);
		myTechnicianPan.add(Box.createVerticalStrut(45));
		myTechnicianPan.add(Box.createVerticalStrut(15));
		myTechnicianPan.add(editBtn);
		myTechnicianPan.add(Box.createVerticalStrut(15));
		myTechnicianPan.add(checkAppointmentBtn);
		myTechnicianPan.add(Box.createVerticalStrut(15));
		myTechnicianPan.add(accessCommentsBtn);
		myTechnicianPan.add(Box.createVerticalStrut(15));
		myTechnicianPan.add(requirementBtn);

		editBtn.addActionListener(new EditListener());
		checkAppointmentBtn.addActionListener(new CheckAppointmentListener());

		accessCommentsBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				new CheckCommentsWindow(null, "comments from my customers", true,
						connectedTechnician.getIdentityCardNumber());

			}
		});

		requirementBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new RequirementWindow(null, "Give a requirement", true);

			}

		});

		this.setLayout(new BorderLayout());

		this.add(myTechnicianPan, BorderLayout.CENTER);

	}

	public class EditListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			new EditTechnicianProfileWindow(null, "Editing my profile", true,
					connectedTechnician.getIdentityCardNumber());
		}

	}

	public class CheckAppointmentListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			new CheckAssignedAppointmentWindow(null, "Check assigned appointment", true,
					connectedTechnician.getIdentityCardNumber());
		}

	}

	public Technician getConnectedTechnician() {
		return connectedTechnician;
	}

	public void setConnectedTechnician(Technician connectedTechnician) {
		this.connectedTechnician = connectedTechnician;
	}

}

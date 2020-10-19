package APUJavaProject;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

public class CommentWindow extends JDialog {
	
	private MyPanel container = new MyPanel();
	private MyPanel ratePanel = new MyPanel();
	
	private MyLabel CommentLbl = new MyLabel("Can you please give a Comment to your Technician :");
	
	private JTextArea CommentTxtArea = new JTextArea();
	
	private JScrollPane textAreaScroll = new JScrollPane(CommentTxtArea);
	
	private ImageIcon veryBadIcon = new ImageIcon("veryBad.png");
	private ImageIcon badIcon = new ImageIcon("bad.png");
	private ImageIcon neutralIcon = new ImageIcon("neutral.png");
	private ImageIcon goodIcon = new ImageIcon("good.png");
	private ImageIcon veryGoodIcon = new ImageIcon("veryGood.png");
	
	private MyButton confirmBtn = new MyButton("confirm");
	private MyButton veryBadBtn = new MyButton(veryBadIcon);
	private MyButton badBtn = new MyButton(badIcon);
	private MyButton neutralBtn = new MyButton(neutralIcon);
	private MyButton goodBtn = new MyButton(goodIcon);
	private MyButton veryGoodBtn = new MyButton(veryGoodIcon)
			;
	
	
	private LocalDateTime appointmentDate;
	
	private Technician currentTechnician;
	private int technicianId;
	
	  public CommentWindow(JFrame parent, String title, boolean modal, int technicianId, int customerId, LocalDateTime appointmentDate){
		    super(parent, title, modal);
		    this.setSize(470, 490 );
		    this.setLocationRelativeTo(null);
		    this.setResizable(false);
		    this.technicianId = technicianId;
		    currentTechnician = FileManager.returnSpecificTechnician(customerId);
		    this.appointmentDate = appointmentDate;
		    initComponent();
		    this.setContentPane(container);
		    this.setVisible(true);
		  }

	private void initComponent() {
		
		container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
		
		confirmBtn.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				
				Rate rate = Rate.NORATED;
				
				for(int i = 0; i < ratePanel.getComponentCount(); i++) {
					
					if((!ratePanel.getComponent(i).isEnabled()) && ratePanel.getComponent(i) == badBtn) {
						rate = Rate.BAD;
					}
					else if((!ratePanel.getComponent(i).isEnabled()) && ratePanel.getComponent(i) == veryBadBtn) {
						rate = Rate.VERYBAD;
					}
					else if((!ratePanel.getComponent(i).isEnabled()) && ratePanel.getComponent(i) == neutralBtn) {
						rate = Rate.NEUTRAL;
					}
					else if((!ratePanel.getComponent(i).isEnabled()) && ratePanel.getComponent(i) == goodBtn) {
						rate = Rate.GOOD;
					}
					else if((!ratePanel.getComponent(i).isEnabled()) && ratePanel.getComponent(i) == veryGoodBtn) {
						rate = Rate.VERYGOOD;
					}
						
				}
				
				ArrayList<Technician> technicians = (ArrayList<Technician>) FileManager.readFile("Technician.txt");
				
				for(int i = 0; i < technicians.size(); i++) {
					
					if(technicians.get(i).getIdentityCardNumber() == currentTechnician.getIdentityCardNumber())
						technicians.get(i).addComment(new Comment(technicianId, CommentTxtArea.getText(), appointmentDate, rate));
				}
				
				FileManager.updateTechnicianFile(technicians);
				
				dispose();
	
			}
			
			
		});
		
		ratePanel.setLayout(new BoxLayout(ratePanel, BoxLayout.X_AXIS));
		
		RateListener myListener = new RateListener();
		veryBadBtn.addActionListener(myListener);
		badBtn.addActionListener(myListener);
		neutralBtn.addActionListener(myListener);
		goodBtn.addActionListener(myListener);
		veryGoodBtn.addActionListener(myListener);
		
		ratePanel.add(veryBadBtn);
		ratePanel.add(badBtn);
		ratePanel.add(neutralBtn);
		ratePanel.add(goodBtn);
		ratePanel.add(veryGoodBtn);
		
		
		CommentLbl.setVerticalTextPosition(SwingConstants.CENTER);
		
		
		textAreaScroll.setSize(50, 50);
		
		MyPanel layoutPan = new MyPanel(); 
		
		layoutPan.setLayout(new BoxLayout(layoutPan, BoxLayout.Y_AXIS));
		
		layoutPan.add(ratePanel);
		layoutPan.add(Box.createVerticalStrut(15));  
		layoutPan.add(confirmBtn);
		
		container.add(CommentLbl, BorderLayout.NORTH);
		container.add(textAreaScroll, BorderLayout.CENTER);
		container.add(layoutPan, BorderLayout.SOUTH);
	}
	
	public class RateListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			if(e.getSource() == veryBadBtn) {
				veryBadBtn.setEnabled(false);
				badBtn.setEnabled(true);
				neutralBtn.setEnabled(true);
				goodBtn.setEnabled(true);
				veryGoodBtn.setEnabled(true);
			}
			else if(e.getSource() == badBtn) {
				veryBadBtn.setEnabled(true);
				badBtn.setEnabled(false);
				
				neutralBtn.setEnabled(true);
				goodBtn.setEnabled(true);
				veryGoodBtn.setEnabled(true);
			}
			else if(e.getSource() == neutralBtn) {
				veryBadBtn.setEnabled(true);
				badBtn.setEnabled(true);
				neutralBtn.setEnabled(false);
				goodBtn.setEnabled(true);
				veryGoodBtn.setEnabled(true);
			}
			else if(e.getSource() == goodBtn) {
				veryBadBtn.setEnabled(true);
				badBtn.setEnabled(true);
				neutralBtn.setEnabled(true);
				goodBtn.setEnabled(false);
				veryGoodBtn.setEnabled(true);
			}
			else if(e.getSource() == veryGoodBtn) {
				veryBadBtn.setEnabled(true);
				badBtn.setEnabled(true);
				neutralBtn.setEnabled(true);
				goodBtn.setEnabled(true);
				veryGoodBtn.setEnabled(false);
			}
			
		}
		
	}

}

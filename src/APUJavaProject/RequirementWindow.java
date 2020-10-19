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

public class RequirementWindow extends JDialog {
	
	private MyPanel container = new MyPanel();
	
	private MyLabel requirementLbl = new MyLabel("Can you please give a Requirement to improve our software :");
	
	private JTextArea requirementTxtArea = new JTextArea();
	
	private JScrollPane textAreaScroll = new JScrollPane(requirementTxtArea);
	
	private MyButton confirmBtn = new MyButton("confirm");
	
	  public RequirementWindow(JFrame parent, String title, boolean modal){
		    super(parent, title, modal);
		    this.setSize(470, 490 );
		    this.setLocationRelativeTo(null);
		    this.setResizable(false);
		    initComponent();
		    this.setContentPane(container);
		    this.setVisible(true);
		  }

	private void initComponent() {
		
		container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
		
		confirmBtn.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				
				Manager.requirements.add(requirementTxtArea.getText());
				FileManager.updateManagerFile((ArrayList<Manager>) FileManager.readFile("Manager.txt"));
				dispose();
	
			}
			
			
		});
		
		
		requirementLbl.setVerticalTextPosition(SwingConstants.CENTER);
		
		
		textAreaScroll.setSize(50, 50);
		
		container.add(requirementLbl, BorderLayout.NORTH);
		container.add(textAreaScroll, BorderLayout.CENTER);
		container.add(confirmBtn, BorderLayout.SOUTH);
	}

}

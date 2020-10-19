package APUJavaProject;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class SetPriceWindow extends JDialog implements ActionListener {
	
	private MyPanel container = new MyPanel();
	
	private MyLabel title = new MyLabel("SET YOUR PRICES :");
	
	private MyLabel normalPriceLbl = new MyLabel("Input the new normal price service :");
	private JTextField normalPriceTf = new JTextField(Double.toString(Manager.normalServicePrice));
	private MyButton updateNormalPriceBtn = new MyButton("update normal price");
	
	private MyLabel majorPriceLbl = new MyLabel("Input the new major price service :");
	private JTextField majorPriceTf = new JTextField(Double.toString(Manager.majorServicePrice));
	private MyButton updateMajorPriceBtn = new MyButton("update major price");
	
	
	private MyButton closeBtn = new MyButton("ok");
	
	
	
	
	  public SetPriceWindow(JFrame parent, String title, boolean modal){
		    super(parent, title, modal);
		    this.setSize(260, 280 );
		    this.setLocationRelativeTo(null);
		    this.setResizable(false);
		    
		    initComponent();
		    this.setContentPane(container);
		    this.setVisible(true);
		  }
	  
	  private void initComponent() {
		  
		  
		  container.setLayout(new BorderLayout());
		  container.add(title, BorderLayout.NORTH);
		  
		  MyPanel centerPan = new MyPanel();
		  
		  centerPan.setLayout(new BoxLayout(centerPan, BoxLayout.PAGE_AXIS));
		  
		  centerPan.add(normalPriceLbl);
		  centerPan.add(normalPriceTf);
		  updateNormalPriceBtn.addActionListener(new updateListener());
		  centerPan.add(updateNormalPriceBtn);
		  
		  centerPan.add(majorPriceLbl);
		  centerPan.add(majorPriceTf);
		  updateMajorPriceBtn.addActionListener(new updateListener());
		  centerPan.add(updateMajorPriceBtn);
		  
		  closeBtn.addActionListener(this);
		  
		  
		  container.add(centerPan, BorderLayout.CENTER);
		  container.add(closeBtn, BorderLayout.SOUTH);
		
	}
	  
	  
	  
	  public class updateListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == updateNormalPriceBtn) {
				
				try {
					
					if(Double.parseDouble(normalPriceTf.getText()) < 0)
						JOptionPane.showMessageDialog(null, "You have to input a positive number !", "Erreur", JOptionPane.ERROR_MESSAGE);
					else
						Manager.normalServicePrice = Double.parseDouble(normalPriceTf.getText());
				    
				    FileManager.updateManagerFile((ArrayList<Manager>) FileManager.readFile("Manager.txt"));
				} catch (NumberFormatException parseException1) {
					parseException1.printStackTrace();
				    
				    JOptionPane.showMessageDialog(null, "You have to input a positive number !", "Erreur", JOptionPane.ERROR_MESSAGE);
				    
				    
				}
				
				
			}
			else if(e.getSource() == updateMajorPriceBtn ) {
				
				try {
					if(Double.parseDouble(majorPriceTf.getText()) < 0)
						JOptionPane.showMessageDialog(null, "You have to input a positive number !", "Erreur", JOptionPane.ERROR_MESSAGE);
					else
						Manager.majorServicePrice = Double.parseDouble(majorPriceTf.getText());
				    FileManager.updateManagerFile((ArrayList<Manager>) FileManager.readFile("Manager.txt"));
				} catch (NumberFormatException parseException2) {
					parseException2.printStackTrace();
					JOptionPane.showMessageDialog(null, "You have to input a number !", "Erreur", JOptionPane.ERROR_MESSAGE);
				}
				
			}
			
		}
		  
	  }



	@Override
	public void actionPerformed(ActionEvent e) {
		dispose();
		
	}


}

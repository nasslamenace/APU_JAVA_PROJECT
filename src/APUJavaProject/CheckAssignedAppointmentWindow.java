package APUJavaProject;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;


public class CheckAssignedAppointmentWindow extends JDialog {
	
	private Technician currentTechnician;
	
	private MyPanel container = new MyPanel();
	private JScrollPane scrollPane;
	
	private MyButton cancelBtn = new MyButton("cancel");
	
	  public CheckAssignedAppointmentWindow(JFrame parent, String title, boolean modal, int currentTechnicianId){
		    super(parent, title, modal);
		    
		    this.currentTechnician = FileManager.returnSpecificTechnician(currentTechnicianId);
		    
		    this.setSize(470, 490 );
		    this.setLocationRelativeTo(null);
		    this.setResizable(false);
		    
		    initComponent();
			this.setContentPane(container);
		    this.setVisible(true);
		  }

	private void initComponent() {
		
		for(int i = 0; i < currentTechnician.getSchedules().size(); i++) {
			
			if(currentTechnician.getSchedules().get(i).getEnd().isBefore(LocalDateTime.now())) {
				currentTechnician.getSchedules().remove(i);
				
				ArrayList<Technician> technicians = (ArrayList<Technician>) FileManager.readFile("Technician.txt");
				
				for(int j = 0; j < technicians.size(); j++) {
					
					if(technicians.get(j).getIdentityCardNumber() == currentTechnician.getIdentityCardNumber()) {
						technicians.remove(j);
						technicians.add(currentTechnician);
						
						j = technicians.size();
					}
				}
				FileManager.updateTechnicianFile(technicians);
			}
		}
		
		
        JTable mytable = new JTable(new AppointmentsModel(currentTechnician.getSchedules())); 
        scrollPane = new JScrollPane(mytable);
		mytable.setFillsViewportHeight(true);	
		MyTable.applyDesign(mytable);
		
		TableComponent buttonRenderer = new TableComponent();
		mytable.getColumn("finished").setCellRenderer(buttonRenderer);
		mytable.addMouseListener(new JTableButtonMouseListener(mytable));
		
		cancelBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				
			}});
		container.setLayout(new BorderLayout());
		container.add(scrollPane, BorderLayout.CENTER);
		container.add(cancelBtn, BorderLayout.SOUTH);
		
	}
	
	
	public class TableComponent extends DefaultTableCellRenderer {

		  public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		    //Si la valeur de la cellule est un JButton, on transtype cette valeur
		    if (value instanceof JButton)
		      return (JButton) value;
		    else
		      return this;
		  }
		}
	
	
	  //Classe modèle personnalisée
	  public class AppointmentsModel extends AbstractTableModel{
	    private String[] titles = {"Customer Id", "start time", "end time", "finished"};
		private ArrayList<Schedule> appointments;

	    //Constructeur
	    public AppointmentsModel(ArrayList<Schedule> appointments){
	    	
			super();
			
			this.appointments = appointments;
	    }
	    
		@Override
		 public Object getValueAt(int rowIndex, int columnIndex) {
	        switch(columnIndex){
	            case 0:
	                return appointments.get(rowIndex).getCustomerId();
	            case 1:
	                return appointments.get(rowIndex).getStart();
	            case 2:
	                return appointments.get(rowIndex).getEnd();
				case 3: 
					final JButton button = new JButton("finished");
					button.addActionListener(new ActionListener() {
														public void actionPerformed(ActionEvent arg0) {
															
															double cost = 0;
															String serviceType;
															
															if(currentTechnician.getSchedules().get(rowIndex).getEnd().getHour() - currentTechnician.getSchedules().get(rowIndex).getStart().getHour() < 3)
															{	cost = Manager.normalServicePrice;
																serviceType = "normal";
															}
															else
															{
																cost = Manager.majorServicePrice;
																serviceType = "major";
															}
															
															Manager.companyBalance += cost;
															FileManager.updateManagerFile((ArrayList<Manager>) FileManager.readFile("Manager.txt"));

															FeedBackWindow myFeedBackWindow = new FeedBackWindow(null, "give a feedback", true, currentTechnician.getSchedules().get(rowIndex).getCustomerId(), 
																	currentTechnician.getIdentityCardNumber(), currentTechnician.getSchedules().get(rowIndex).getStart(), cost, serviceType);
															currentTechnician.getSchedules().remove(rowIndex);
															
															ArrayList<Technician> technicians = (ArrayList<Technician>) FileManager.readFile("Technician.txt");
															
															for(int i = 0; i < technicians.size(); i++) {
																
																if(technicians.get(i).getIdentityCardNumber() == currentTechnician.getIdentityCardNumber()) {
																	technicians.remove(i);
																	technicians.add(currentTechnician);
																	
																	i = technicians.size();
																}
															}
															FileManager.updateTechnicianFile(technicians);
															
															dispose();
															
															
														}
				});
				return button;
	            default:
	                return null; //should never happen
	        }
	    }
		
		@Override
		public int getRowCount() {
			return appointments.size();
		}

		@Override
		public int getColumnCount() {
			
			return titles.length;
		}
	    
	    public String getColumnName(int col) {
	    	  return this.titles[col];
	    	}
	    
	  //Retourne la classe de la donnée de la colonne
	    public Class getColumnClass(int col){
	    	
	      if(col != 3)
	    	  return MyLabel.class;
	      else
	    	  return JButton.class;
	    }
	    
	  }
	  
	  public class JTableButtonMouseListener extends MouseAdapter {
		  private final JTable table;
				
		  public JTableButtonMouseListener(JTable table) {
		    this.table = table;
		  }

		  @Override public void mouseClicked(MouseEvent e) {
		    int column = table.getColumnModel().getColumnIndexAtX(e.getX());
		    int row    = e.getY()/table.getRowHeight(); 

		    if (row < table.getRowCount() && row >= 0 && column < table.getColumnCount() && column >= 0) {
		      Object value = table.getValueAt(row, column);
		      if (value instanceof JButton) {
		        ((JButton)value).doClick();
		      }
		    }
		  }
		}
	

}

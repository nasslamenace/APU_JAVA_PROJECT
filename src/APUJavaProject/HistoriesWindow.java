package APUJavaProject;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;


public class HistoriesWindow extends JDialog {
	
	private MyPanel container = new MyPanel();
	private JScrollPane scrollPane;
	
	private Customer currentCustomer;
	
	  public HistoriesWindow(JFrame parent, String title, boolean modal, int customerId){
		    super(parent, title, modal);
		    this.setSize(600, 490 );
		    this.setLocationRelativeTo(null);
		    this.setResizable(false);
		    currentCustomer = FileManager.returnSpecificCustomer(customerId);
		    
		    initComponent();
			this.setContentPane(container);
		    this.setVisible(true);
		  }

	private void initComponent() {
		
		container.setLayout(new BorderLayout());
		
		JTable mytable = new JTable(new HistoriesModel(currentCustomer.getFeedbacks())); 
		
		
		MyTable.applyDesign(mytable);
		
		
		mytable.getColumn("feedbacks").setCellRenderer(new JTableComponent());
		mytable.setRowHeight(30);
		mytable.getColumn("leave a comment").setCellRenderer(new JTableComponent());
		mytable.addMouseListener(new JTableButtonMouseListener(mytable));
		
        scrollPane = new JScrollPane(mytable);
        
		
		container.add(scrollPane, BorderLayout.CENTER);	
		
	}
	
	
	public class JTableComponent extends DefaultTableCellRenderer {

		  public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		    //Si la valeur de la cellule est un MyButton, on transtype cette valeur
		    if (value instanceof MyButton)
		      return (MyButton) value;
		    else
		      return this;
		  }
		}
	
	
	  //Classe modèle personnalisée
	  public class HistoriesModel extends AbstractTableModel{
	    private String[] titles = {"Technician Id", "appointment date", "feedbacks", "cost", "leave a comment"};
		private ArrayList<Feedback> histories;

	    //Constructeur
	    public HistoriesModel(ArrayList<Feedback> Histories){
	    	
			super();
			
			this.histories = Histories;
	    }
	    
		@Override
		 public Object getValueAt(int rowIndex, int columnIndex) {
	        switch(columnIndex){
	            case 0:
	                return histories.get(rowIndex).getTechnicianId();
	            case 1:
	                return histories.get(rowIndex).getAppointmentDate();
	            case 2:
	            	final MyButton feedbackBtn = new MyButton("access feedback");
	            	
	            	feedbackBtn.setBackground(Color.white);
	            	feedbackBtn.setForeground(new Color(61, 97, 166));
	            	
					feedbackBtn.addActionListener(new ActionListener() {
														public void actionPerformed(ActionEvent arg0) {
			
															JOptionPane.showMessageDialog(null, histories.get(rowIndex).getFeedback() ,"feedback" , JOptionPane.INFORMATION_MESSAGE, new ImageIcon("logo.png"));
														}
				});
				return feedbackBtn;
	            case 3:
	            	return histories.get(rowIndex).getCost() + "$";
	            case 4:
	            	JLabel text = new JLabel(new ImageIcon("commentBtn.png"));
	            	text.setOpaque(true);
	            	text.setBackground(new Color(61, 97, 166));
	            	
	            	text.setForeground(Color.white);
	            	final MyButton commentBtn = new MyButton("comment");
	            	
	            	commentBtn.setOpaque(true);
	            	commentBtn.setBackground(Color.white);
	            	commentBtn.setForeground(new Color(61, 97, 166));
					commentBtn.addActionListener(new ActionListener() {
														public void actionPerformed(ActionEvent arg0) {
															CommentWindow myWindow = new CommentWindow(null, "provide a comment", true, histories.get(rowIndex).getTechnicianId(),
																	   currentCustomer.getIdentityCardNumber(), histories.get(rowIndex).getAppointmentDate());

														}
				});
				return commentBtn;
	            default:
	                return null; //should never happen
	        }
	    }
		
		@Override
		public int getRowCount() {
			return histories.size();
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
	    	
	      if(col == 2 || col == 4)
	    	  return MyButton.class;
	      else
	    	  return JLabel.class;
	    }
	    
	  }
	  
	  public class JTableButtonMouseListener extends MouseAdapter {
		  private final JTable table;
				
		  public JTableButtonMouseListener(JTable mytable) {
		    this.table = mytable;
		  }

		  @Override public void mouseClicked(MouseEvent e) {
		    int column = table.getColumnModel().getColumnIndexAtX(e.getX());
		    int row    = e.getY()/table.getRowHeight(); 

		    if (row < table.getRowCount() && row >= 0 && column < table.getColumnCount() && column >= 0) {
		      Object value = table.getValueAt(row, column);
		      if (value instanceof MyButton) {
		        ((MyButton)value).doClick();
		      }
		    }
		  }
		}
	


}

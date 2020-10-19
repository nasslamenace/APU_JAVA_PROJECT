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

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;



public class CheckCommentsWindow extends JDialog {
	
	private MyPanel container = new MyPanel();
	private JScrollPane scrollPane;
	private MyButton okBtn = new MyButton("ok");
	
	private Technician currentTechnician;
	
	  public CheckCommentsWindow(JFrame parent, String title, boolean modal, int technicianId){
		    super(parent, title, modal);
		    this.setSize(470, 490 );
		    this.setLocationRelativeTo(null);
		    this.setResizable(false);
		    
		    currentTechnician = FileManager.returnSpecificTechnician(technicianId);
		    
		    initComponent();
			this.setContentPane(container);
		    this.setVisible(true);
		  }

	private void initComponent() {
		
		
		JTable mytable = new JTable(new commentsModel(currentTechnician.getComments())); 
		MyTable.applyDesign(mytable);
		mytable.setRowHeight(40);
		mytable.setFillsViewportHeight(true);	
		
		TableComponent buttonRenderer = new TableComponent();
		mytable.getColumn("Comments").setCellRenderer(buttonRenderer);
		mytable.getColumn("rate").setCellRenderer(buttonRenderer);
		mytable.addMouseListener(new JTableButtonMouseListener(mytable));
		
        scrollPane = new JScrollPane(mytable);
        
        okBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				
			}
        	
        });

		container.setLayout(new BorderLayout());
		container.add(scrollPane, BorderLayout.CENTER);
		container.add(okBtn, BorderLayout.SOUTH);
		
		
	}
	
	
	public class TableComponent extends DefaultTableCellRenderer {

		  public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		    //Si la valeur de la cellule est un JButton, on transtype cette valeur
		    if (value instanceof JButton)
		      return (JButton) value;
		    else if(value instanceof MyLabel)
		      return (MyLabel) value;
		    else
		      return this;
		  }
		  

	}
	
	
	  //Classe modèle personnalisée
	  public class commentsModel extends AbstractTableModel{
	    private String[] titles = {"Customer Id", "appointment date", "Comments", "rate"};
		private ArrayList<Comment> comments;

	    //Constructeur
	    public commentsModel(ArrayList<Comment> comments){
	    	
			super();
			
			this.comments = comments;
	    }
	    
		@Override
		 public Object getValueAt(int rowIndex, int columnIndex) {
	        switch(columnIndex){
	            case 0:
	                return comments.get(rowIndex).getCustomerId();
	            case 1:
	                return comments.get(rowIndex).getAppointmentDate();
	            case 2:
	            	final JButton CommentBtn = new JButton("access Comment");
					CommentBtn.addActionListener(new ActionListener() {
														public void actionPerformed(ActionEvent arg0) {
															
															JOptionPane.showMessageDialog(null, comments.get(rowIndex).getComment() ,"comment" , JOptionPane.INFORMATION_MESSAGE, new ImageIcon(comments.get(rowIndex).getRate() + ".png"));
															
														}
				});
				return CommentBtn;
	            case 3:
	            	switch(comments.get(rowIndex).getRate()) {
	            		case VERYBAD:
	            			MyLabel lblVb = new MyLabel();
	            			
	            			
	            			lblVb.setIcon(new ImageIcon("veryBad.png"));
	            			
	            			return lblVb;
	            		case BAD:
	            			MyLabel lblB = new MyLabel();
	            			
	            			lblB.setIcon(new ImageIcon("bad.png"));
	            			
	            			return lblB;
	            		case NEUTRAL:
	            			MyLabel lblN = new MyLabel();
	            			
	            			lblN.setIcon(new ImageIcon("neutral.png"));
	            			
	            			return lblN;
	            		case GOOD:
	            			MyLabel lblG = new MyLabel();
	            			
	            			lblG.setIcon(new ImageIcon("good.png"));
	            			
	            			return lblG;
	            		case VERYGOOD:
	            			MyLabel lbl = new MyLabel();
	            			
	            			lbl.setIcon(new ImageIcon("veryGood.png"));
	            			
	            			return lbl;
	            		default:
	            			return new MyLabel("No rate");
	            	}
	            	
	            default:
	                return null; //should never happen
	        }
	    }
		
		@Override
		public int getRowCount() {
			return comments.size();
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

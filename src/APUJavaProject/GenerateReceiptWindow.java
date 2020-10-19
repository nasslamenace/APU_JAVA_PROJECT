package APUJavaProject;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;


public class GenerateReceiptWindow extends JDialog {
	private MyPanel container = new MyPanel();
	private JScrollPane scrollPane;
	
	int customerId;

	
	  public GenerateReceiptWindow(JFrame parent, String title, boolean modal, int customerId){
		    super(parent, title, modal);
		    this.setSize(600, 500 );
		    this.setLocationRelativeTo(null);
		    this.setResizable(false);
		    this.customerId = customerId;
		    initComponent();
			this.setContentPane(container);
		    this.setVisible(true);
		  }

	private void initComponent() {
		
		
		JTable mytable = new JTable(new HistoriesModel(FileManager.returnSpecificCustomer(customerId).getFeedbacks())); 
		mytable.setFillsViewportHeight(true);	
		MyTable.applyDesign(mytable);
		
		TableComponent buttonRenderer = new TableComponent();
		mytable.getColumn("generate a receipt").setCellRenderer(buttonRenderer);
		mytable.addMouseListener(new JTableButtonMouseListener(mytable));
		
        scrollPane = new JScrollPane(mytable);

        container.setLayout(new BorderLayout());
		
		container.add(scrollPane, BorderLayout.CENTER);
		
		
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
	  public class HistoriesModel extends AbstractTableModel{
	    private String[] titles = {"Technician Id", "appointment date", "cost", "generate a receipt"};
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
	            	return histories.get(rowIndex).getCost() + "$";
	            case 3:
	            	final JButton commentBtn = new JButton("generate a receipt");
					commentBtn.addActionListener(new ActionListener() {
														public void actionPerformed(ActionEvent arg0) {
															
															
															
															String receipt = "<HTML><BODY> <div id=\"invoice-POS\">\n" + 
																	"    \n" + 
																	"    <center id=\"top\">\n" + 
																	"      <div class=\"logo\"></div>\n" + 
																	"      <div class=\"info\"> \n" + 
																	"        <h2>APU Automotive center &cie</h2>\n" + 
																	"      </div><!--End Info-->\n" + 
																	"    </center><!--End InvoiceTop-->\n" + 
																	"    \n" + 
																	"    <div id=\"mid\">\n" + 
																	"      <div class=\"info\">\n" + 
																	"        <h2>Contact Info</h2>\n" + 
																	"        <p> \n" + 
																	"            Address : Jalan Teknologi 5, Taman Teknologi Malaysia, 57000 Kuala Lumpur, Wilayah Persekutuan Kuala Lumpur</br>\n" + 
																	"            <br/>Email   : JohnDoe@gmail.com\n" + 
																	"            <br/>Phone   : 03-8996 1000</br>\n" + 
																	"        </p>\n" + 
																	"      </div>\n" + 
																	"    </div><!--End Invoice Mid-->\n" + 
																	"    \n" + 
																	"    <div id=\"bot\">\n" + 
																	"\n" + 
																	"					<div id=\"table\">\n" + 
																	"						<table>\n" + 
																	"							<tr class=\"tabletitle\">\n" + 
																	"								<td class=\"item\"><h2>Item</h2></td>\n" + 
																	"								<td class=\"Hours\"><h2>Qty</h2></td>\n" + 
																	"								<td class=\"Rate\"><h2>Sub Total</h2></td>\n" + 
																	"							</tr>\n" + 
																	"							<tr class=\"service\">\n" + 
																	"								<td class=\"tableitem\"><p class=\"itemtext\">" + histories.get(rowIndex).getServiceType() +" Service </p></td>\n" + 
																	"								<td class=\"tableitem\"><p class=\"itemtext\">  1</p></td>\n" + 
																	"								<td class=\"tableitem\"><p class=\"itemtext\"> $" + histories.get(rowIndex).getCost() + "</p></td>\n" + 
																	"							</tr>\n" +
																	"\n" + 
																	"							<tr class=\"tabletitle\">\n" + 
																	"								<td></td>\n" + 
																	"								<td class=\"Rate\"><h2>Total</h2></td>\n" + 
																	"								<td class=\"payment\"><h2>$" + histories.get(rowIndex).getCost() + "</h2></td>\n" + 
																	"							</tr>\n" + 
																	"\n" + 
																	"						</table>\n" + 
																	"					</div><!--End Table-->\n" + 
																	"\n" + 
																	"					<div id=\"legalcopy\">\n" + 
																	"						<p class=\"legal\"><strong>Thank you for coming to our Automotive center !</strong> \n" + 
																	"						</p>\n" + 
																	"					</div>\n" + 
																	"\n" + 
																	"				</div><!--End InvoiceBot-->\n" + 
																	"  </div><!--End Invoice-->\n" + 
																	"</BODY></HTML>";
															
															JOptionPane.showMessageDialog(null, new JLabel(receipt) ,"receipt" , JOptionPane.INFORMATION_MESSAGE, new ImageIcon("logo.png"));
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

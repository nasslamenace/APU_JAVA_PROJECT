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

public class CancelBookingWindow extends JDialog {
	private MyPanel container = new MyPanel();
	private JScrollPane scrollPane;
	
	private MyButton okBtn = new MyButton("ok");
	
	private ArrayList<Technician> technicians;
	

	int customerId;

	public CancelBookingWindow(JFrame parent, String title, boolean modal, int customerId) {
		super(parent, title, modal);
		this.setSize(600, 500);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.customerId = customerId;
		initComponent();
		this.setContentPane(container);
		this.setVisible(true);
	}

	private void initComponent() {
		
		technicians = (ArrayList<Technician>) FileManager.readFile("Technician.txt");

		ArrayList<Schedule> schedules = new ArrayList<Schedule>();

		for (int i = 0; i < technicians.size(); i++) {

			for (int j = 0; j < technicians.get(i).getSchedules().size(); j++) {

				if (technicians.get(i).getSchedules().get(j).getCustomerId() == this.customerId)
					schedules.add(technicians.get(i).getSchedules().get(j));

			}

		}

		JTable mytable = new JTable(
				new appointmentsModel(schedules));
		mytable.setFillsViewportHeight(true);
		MyTable.applyDesign(mytable);

		TableComponent buttonRenderer = new TableComponent();
		mytable.getColumn("cancel").setCellRenderer(buttonRenderer);
		mytable.addMouseListener(new JTableButtonMouseListener(mytable));

		scrollPane = new JScrollPane(mytable);

		container.setLayout(new BorderLayout());

		container.add(scrollPane, BorderLayout.CENTER);
		
		okBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				
			}
			
		});
		
		container.add(okBtn, BorderLayout.SOUTH);

	}

	public class TableComponent extends DefaultTableCellRenderer {

		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			// Si la valeur de la cellule est un JButton, on transtype cette valeur
			if (value instanceof JButton)
				return (JButton) value;
			else
				return this;
		}
	}

	// Classe modèle personnalisée
	public class appointmentsModel extends AbstractTableModel {
		private String[] titles = { "Appointment start", "appointment end time", "cancel" };
		private ArrayList<Schedule> appointments;

		// Constructeur
		public appointmentsModel(ArrayList<Schedule> appointments) {

			super();

			this.appointments = appointments;
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			switch (columnIndex) {
			case 0:
				return appointments.get(rowIndex).getStart();
			case 1:
				return appointments.get(rowIndex).getEnd();
			case 2:
				final JButton cancelBtn = new JButton("cancel");
				cancelBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						for(int i = 0; i < technicians.size(); i++) {
							
							for(int j = 0; j < technicians.get(i).getSchedules().size(); j++) {
								
								if(technicians.get(i).getSchedules().get(j).equals(appointments.get(rowIndex)))
									technicians.get(i).getSchedules().remove(j);
								
							}
							
						}
						
						FileManager.updateTechnicianFile(technicians);
						
						dispose();
						

					}
				});
				return cancelBtn;
			default:
				return null; // should never happen
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

		// Retourne la classe de la donnée de la colonne
		public Class getColumnClass(int col) {

			if (col != 3)
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

		@Override
		public void mouseClicked(MouseEvent e) {
			int column = table.getColumnModel().getColumnIndexAtX(e.getX());
			int row = e.getY() / table.getRowHeight();

			if (row < table.getRowCount() && row >= 0 && column < table.getColumnCount() && column >= 0) {
				Object value = table.getValueAt(row, column);
				if (value instanceof JButton) {
					((JButton) value).doClick();
				}
			}
		}
	}

}

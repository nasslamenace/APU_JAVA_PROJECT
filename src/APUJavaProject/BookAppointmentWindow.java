package APUJavaProject;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.chrono.ChronoLocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;

public class BookAppointmentWindow extends JDialog {

	private int hoursAmount;
	private int currentCustomerId;
	private ArrayList<Technician> availableTechnicians = new ArrayList<Technician>();

	private MyPanel container = new MyPanel();
	private MyPanel chooseAppointmentPanel = new MyPanel();
	private MyPanel chooseTechnicianPanel = new MyPanel();

	private CardLayout cardLayout = new CardLayout();

	private MyPanel datePan = new MyPanel();
	private MyPanel timePanel = new MyPanel();
	JScrollPane scrollPane;

	private MyLabel titleLbl = new MyLabel("Booking Appointment :");

	private MyLabel choseDateLbl = new MyLabel("please chose a Date for this month : ");
	private JComboBox<LocalDate> dateBox = new JComboBox<LocalDate>();
	private MyButton validDateBtn = new MyButton("valid date");
	private MyButton changeDateBtn = new MyButton("Change date");
	private MyButton cancelChoosingBtn = new MyButton("Cancel");

	private MyLabel choseTimeLbl = new MyLabel("please choose an available hour here : ");
	DefaultComboBoxModel model = new DefaultComboBoxModel();
	private JComboBox timeBox = new JComboBox(model);
	private MyButton validTimeBtn = new MyButton("valid Time");

	private MyLabel techniciansAmount;

	public BookAppointmentWindow(JFrame parent, String title, boolean modal, String serviceType) {
		super(parent, title, modal);
		this.setSize(470, 490);
		this.setLocationRelativeTo(null);
		this.setResizable(false);

		if (serviceType.equals("normal"))
			hoursAmount = 1;
		else
			hoursAmount = 3;

		initComponent();
		this.setContentPane(container);
		this.setVisible(true);
	}

	// this method inits all the components
	private void initComponent() {


		// this loop allows us to ask the manager to choose a customer, it will continue
		// to ask until
		// the input is a number or the customer does not exist
		boolean exist = false;
		do {

			try {
				currentCustomerId = Integer.parseInt(JOptionPane.showInputDialog(null, "Please input the customer ID",
						"Assign the customer", JOptionPane.QUESTION_MESSAGE));
				if (!FileManager.isCustomerExist(currentCustomerId))
					JOptionPane.showMessageDialog(null, "This customer does not exist !", "Error",
							JOptionPane.ERROR_MESSAGE);
				else
					exist = true;
			} catch (NumberFormatException parseException1) {
				JOptionPane.showMessageDialog(null, "You have to input a number !", "Error", JOptionPane.ERROR_MESSAGE);
			}

		} while (!exist);


		// we add to the date combo box the next 30 days including today
		for (int i = 0; LocalDate.now().plusDays(i).isBefore(LocalDate.now().plusMonths(1)); i++)
			dateBox.addItem(LocalDate.now().plusDays(i));

		validDateBtn.addActionListener(new ValidDateListener());
		changeDateBtn.addActionListener(new ChangeDateListener());
		validTimeBtn.addActionListener(new ValidTimeListener());
		cancelChoosingBtn.addActionListener(new CancelChoosingListener());

		datePan.add(choseDateLbl);
		datePan.add(dateBox);
		datePan.add(validDateBtn);

		chooseAppointmentPanel.setLayout(new BoxLayout(chooseAppointmentPanel, BoxLayout.PAGE_AXIS));
		chooseAppointmentPanel.add(titleLbl, BorderLayout.NORTH);
		chooseAppointmentPanel.add(datePan);

		container.setLayout(cardLayout);
		chooseTechnicianPanel.setLayout(new BorderLayout());

		container.add(chooseAppointmentPanel, "chooseTime");
		container.add(chooseTechnicianPanel, "chooseTechnician");

	}

	
	public class ValidDateListener implements ActionListener {

		//when the user push the valid date button
		@Override
		public void actionPerformed(ActionEvent e) {

			//initiate a technician arraylist using the file data (thanks to the file manager class)
			ArrayList<Technician> techniciansList = (ArrayList<Technician>) FileManager.readFile("Technician.txt");

			//first of all, we calculate what hours the technician works 
			for (int i = 0; i < techniciansList.size(); i++) {

				Vector<Integer> workHours = new Vector<Integer>();
				for (int j = 0; j < techniciansList.get(i).getSchedules().size(); j++) {
					LocalDateTime start = techniciansList.get(i).getSchedules().get(j).getStart();
					LocalDateTime end = techniciansList.get(i).getSchedules().get(j).getEnd();

					if (LocalDate.of(start.getYear(), start.getMonth(), start.getDayOfMonth())
							.isEqual((LocalDate) dateBox.getSelectedItem())) {
						for (int k = start.getHour(); k <= end.getHour(); k++)
							workHours.add(k);
					}
				}
				//then we verify if the technician is available to provide the asked service for the chosen date
				if (isAvailable(workHours))
					availableTechnicians.add(techniciansList.get(i));

			}
			
			
			
			
			//if there is no available technician this day, we display an error dialog 
			if (availableTechnicians.isEmpty() || timeBox.countComponents() == 0)
				JOptionPane.showMessageDialog(null, "There is no technician available at this date", "Erreur",
						JOptionPane.ERROR_MESSAGE);
			//else, we display the amount of available technicians and fill the time box
			//with the availables hours for the chosen date
			else {

				techniciansAmount = new MyLabel(
						"there is " + availableTechnicians.size() + " technicians available this day");

				//here, we fill the workHours vector with the availables hours for the chosen date
				for (int i = 0; i < availableTechnicians.size(); i++) {

					Vector<Integer> workHours = new Vector<Integer>();

					for (int j = 0; j < availableTechnicians.get(i).getSchedules().size(); j++) {

						LocalDateTime start = availableTechnicians.get(i).getSchedules().get(j).getStart();
						LocalDateTime end = availableTechnicians.get(i).getSchedules().get(j).getEnd();

						if (LocalDate.of(start.getYear(), start.getMonth(), start.getDayOfMonth())
								.isEqual((LocalDate) dateBox.getSelectedItem())) {
							for (int k = start.getHour(); k < end.getHour(); k++) {
								if (!workHours.contains(k))
									workHours.add(k);
							}
						}
					}

					
					//here, we add these hours into the time box
					for (int k = 8; k <= 19; k++) {

						
						if (workHours.isEmpty() && (!boxContains(timeBox, k))) {
							//if the current time of the current day is after the current "k" time, we do not add it to the time box 
							//because this time already happened
							if (LocalDateTime.now().isBefore(LocalDateTime.of((LocalDate) dateBox.getSelectedItem(), LocalTime.of(k, 0))))
								timeBox.addItem(LocalTime.of(k, 0));
						} else {
							for (int l = k; l <= k + hoursAmount; l++) {

								if (!workHours.contains(k) && !workHours.contains(k + hoursAmount)
										&& !boxContains(timeBox, k)) {
									//if the current time of the current day is after the current "k" time, we do not add it to the time box 
									//because this time already happened
									if (LocalDateTime.now().isBefore(LocalDateTime.of((LocalDate) dateBox.getSelectedItem(), LocalTime.of(k, 0))))
										timeBox.addItem(LocalTime.of(k, 0));
								}

							}
						}
					}
				}
				if(timeBox.getItemCount() > 0) {
					
					//we disable the datebox to use the chosen information with no interference from the user
					dateBox.setEnabled(false);
					datePan.remove(validDateBtn);
					//if he need to chznge the date and restart the process, he will have to push the "change" button
					datePan.add(changeDateBtn, BorderLayout.SOUTH);
	
					timePanel.add(techniciansAmount, BorderLayout.NORTH);
					timePanel.add(choseTimeLbl);
					timePanel.add(timeBox);
					timePanel.add(validTimeBtn);
	
					chooseAppointmentPanel.add(timePanel);
	
					chooseAppointmentPanel.revalidate();
					chooseAppointmentPanel.repaint();
				}
				else {
					JOptionPane.showMessageDialog(null, "There is no technician available at this date", "Erreur",
							JOptionPane.ERROR_MESSAGE);
				}

			}
		}

	}

	//return true if the comboBox already contains the time "hour" 
	public boolean boxContains(JComboBox box, int hour) {

		for (int i = 0; i < box.getItemCount(); i++) {

			if (((LocalTime) box.getItemAt(i)).getHour() == hour)
				return true;
		}

		return false;

	}

	//return true if there is the technician is available during *hoursAmount hours
	public boolean isAvailable(Vector takenHours) {

		Collections.sort(takenHours);

		if (takenHours.isEmpty())
			return true;

		for (int i = 0; i < takenHours.size() - 1; i++) {

			if (((int) takenHours.get(i + 1) - (int) takenHours.get(i)) > this.hoursAmount)
				return true;
		}

		//apu automotive center is opened 11 hours a day
		if (11 - takenHours.size() >= hoursAmount)
			return true;

		return false;
	}

	//return true if the array arr contains the element key
	public static boolean contains(final int[] arr, final int key) {
		return Arrays.stream(arr).anyMatch(i -> i == key);
	}

	
	public class ChangeDateListener implements ActionListener {

		//when the change button is pushed
		@Override
		public void actionPerformed(ActionEvent e) {

			timeBox.removeAllItems();

			availableTechnicians.clear();

			dateBox.setEnabled(true);

			datePan.remove(changeDateBtn);
			datePan.add(validDateBtn);

			timePanel.remove(techniciansAmount);
			timePanel.remove(choseTimeLbl);
			timePanel.remove(timeBox);
			timePanel.remove(validTimeBtn);

			chooseAppointmentPanel.revalidate();
			chooseAppointmentPanel.repaint();

		}

	}

	public class ValidTimeListener implements ActionListener {

		//when the valid time button is pushed
		@Override
		public void actionPerformed(ActionEvent e) {

			//here, we remove the technician that are not available int the chosen time (reminder: there is at least one technician available
			//for each hour
			for (int i = 0; i < availableTechnicians.size(); i++) {

				for (int j = 0; j < ((availableTechnicians.size() > 0)
						? availableTechnicians.get(((i < 0) ? 0 : i)).getSchedules().size()
						: 0); j++) {

					LocalDateTime start = availableTechnicians.get(((i < 0) ? 0 : i)).getSchedules().get(j).getStart();
					LocalDateTime end = availableTechnicians.get(((i < 0) ? 0 : i)).getSchedules().get(j).getEnd();

					//if the technicians schedule date is equal to the chosen date
					if (((LocalDate) dateBox.getSelectedItem())
							.isEqual(LocalDate.of(start.getYear(), start.getMonth(), start.getDayOfMonth()))) {

						LocalDateTime time = LocalDateTime.of(
								LocalDate.ofYearDay(start.getYear(), start.getDayOfYear()),
								(LocalTime) timeBox.getSelectedItem());

						//if the chosen time occurs during the work our of the current technician...
						if ((time.isAfter(start) && time.isBefore(end)) || time.isEqual(start)) {

							j = availableTechnicians.get(i).getSchedules().size();
							//we remove the technician
							availableTechnicians.remove(i);
							i--;
						}
					}
				}
			}

			//we instantiate a table with the available technicians arraylist
			JTable mytable = new JTable(new AvailableTechniciansModel(availableTechnicians));
			scrollPane = new JScrollPane(mytable);
			mytable.setFillsViewportHeight(true);
			MyTable.applyDesign(mytable);

			TableComponent buttonRenderer = new TableComponent();
			mytable.getColumn("Choose").setCellRenderer(buttonRenderer);
			mytable.addMouseListener(new JTableButtonMouseListener(mytable));

			chooseTechnicianPanel.add(scrollPane, BorderLayout.CENTER);

			chooseTechnicianPanel.add(cancelChoosingBtn, BorderLayout.SOUTH);

			chooseTechnicianPanel.revalidate();
			chooseTechnicianPanel.repaint();

			container.revalidate();
			container.repaint();

			//we show the table to the manager so he can choose one
			cardLayout.show(container, "chooseTechnician");

		}

	}

	public class CancelChoosingListener implements ActionListener {

		//allow the manager to restart the process by pushing the cancel button  
		@Override
		public void actionPerformed(ActionEvent e) {

			availableTechnicians.clear();

			dateBox.setEnabled(true);

			datePan.remove(changeDateBtn);
			datePan.add(validDateBtn);

			timePanel.remove(techniciansAmount);
			timePanel.remove(choseTimeLbl);
			timePanel.remove(timeBox);
			timePanel.remove(validTimeBtn);

			chooseAppointmentPanel.revalidate();
			chooseAppointmentPanel.repaint();

			chooseTechnicianPanel.remove(scrollPane);
			chooseTechnicianPanel.revalidate();
			chooseTechnicianPanel.repaint();

			cardLayout.show(container, "chooseTime");
		}
	}

	public class TableComponent extends DefaultTableCellRenderer {

		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			// Si la valeur de la cellule est un MyButton, on transtype cette valeur
			if (value instanceof JButton)
				return (JButton) value;
			else
				return this;
		}
	}

	// Classe modèle personnalisée
	public class AvailableTechniciansModel extends AbstractTableModel {
		private String[] titles = { "Id", "Name", "Choose" };
		private ArrayList<Technician> technicians;

		// Constructeur
		public AvailableTechniciansModel(ArrayList<Technician> technicians) {

			super();

			this.technicians = technicians;
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			switch (columnIndex) {
			case 0:
				return technicians.get(rowIndex).getIdentityCardNumber();
			case 1:
				return technicians.get(rowIndex).getUserName();
			case 2:
				final JButton button = new JButton("choose");

				button.setOpaque(true);
				button.setForeground(new Color(61, 97, 166));

				button.addActionListener(new ActionListener() {
					//when the manager push the whoose button...
					public void actionPerformed(ActionEvent arg0) {

						LocalTime time = (LocalTime) timeBox.getSelectedItem();
						LocalDate date = (LocalDate) dateBox.getSelectedItem();

						LocalDateTime start = LocalDateTime.of(date, time);

						LocalDateTime end = start.plusHours(hoursAmount);

						Schedule newSchedule = new Schedule(start, end, currentCustomerId);
						
						//...we add the new schedule to the customer timetable
						FileManager.addSchedule(newSchedule, technicians.get(rowIndex).getIdentityCardNumber());

						disposeWindow();

						JOptionPane.showMessageDialog(null, "Your appointment has been booked", "success",
								JOptionPane.INFORMATION_MESSAGE);

					}
				});
				return button;
			default:
				return null; // should never happen
			}
		}

		@Override
		public int getRowCount() {
			return technicians.size();
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

			if (col != 2)
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

	private void disposeWindow() {
		this.dispose();
	}

}

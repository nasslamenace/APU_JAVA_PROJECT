package APUJavaProject;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/*This class allows me to access data from file and convert it to arrayLists, it also allows me to update the files.
 * Indeed, it convert arraylists to string data into files.
 * I only use Static function so i can use it all my files.
 * */

public class FileManager {

	/*
	 * this method allows me to add one manager in my managers. I use the readfile
	 * static function which return all managers into an arraylist, i add myManager
	 * into this array list and i update the file thanks to updateManagerFile
	 * staticfunction
	 */
	public static void addManager(Manager myManager) {

		ArrayList<Manager> usersList = (ArrayList<Manager>) FileManager.readFile("Manager.txt");

		usersList.add(myManager);

		FileManager.updateManagerFile(usersList);

	}

	// same but with technicians

	public static void addTechnician(Technician myTechnician) {

		ArrayList<Technician> usersList = (ArrayList<Technician>) FileManager.readFile("Technician.txt");

		usersList.add(myTechnician);

		FileManager.updateTechnicianFile(usersList);

	}

	// same but with customers

	public static void addCustomer(Customer myCustomer) {

		ArrayList<Customer> usersList = (ArrayList<Customer>) FileManager.readFile("Customer.txt");

		usersList.add(myCustomer);

		FileManager.updateCustomerFile(usersList);
	}

	public static ArrayList<String> returnRequirements() {

		ArrayList<String> requirements = new ArrayList<String>();

		BufferedReader reader;

		String buffer = "";

		try {

			reader = new BufferedReader(new FileReader("Manager.txt"));
			
			do {
				 buffer = reader.readLine();
			}while(!buffer.equals("requirements"));

			while (!buffer.equals("end_requirements")) {
				
				String requirement = "";
				
				buffer = reader.readLine();
				
				while (!buffer.equals("end_requirements")) {
					
					while(!buffer.equals("end")){
						requirement += buffer + "\n";
						buffer = reader.readLine();
					}
					buffer = reader.readLine();
					if(!(requirement.isBlank() || requirement.isEmpty())) {
						requirements.add(requirement);
					requirement = "";
				}
				}
				
			}

			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return requirements;

	}

	/*
	 * In this function, i write my managers data into the file Manager.txt. I use a
	 * buffered writer to write into the text file.
	 */

	public static void updateManagerFile(ArrayList<Manager> usersList) {

		OutputStreamWriter osw = null;
		FileOutputStream is = null;

		try {
			File file = new File("Manager.txt");
			is = new FileOutputStream(file);
			osw = new OutputStreamWriter(is);
			Writer w = new BufferedWriter(osw);

			w.write("Major\n");
			w.write(Double.toString(Manager.majorServicePrice) + "\n");
			w.write("Minor\n");
			w.write(Double.toString(Manager.normalServicePrice) + "\n");

			w.write("balance\n");
			w.write(Double.toString(Manager.companyBalance) + "\n");

			w.write("requirements\n");
			


			for (int i = 0; i < Manager.requirements.size(); i++)
				w.write(Manager.requirements.get(i) + "\nend\n");

			w.write("end_requirements\n");

			w.write("Managers\n");

			for (int i = 0; i < usersList.size(); i++) {

				w.write(usersList.get(i).getUserName());
				w.write("\n");
				w.write(usersList.get(i).getUserPassword());

				w.write("\n");
				if (i < usersList.size() - 1)
					w.write("---------------------------\n");
			}

			w.close();
		} catch (IOException e) {
			System.err.println("Problem writing to the file Manager.txt");
		}

		/*
		 * DataOutputStream dos;
		 * 
		 * try { dos = new DataOutputStream( new BufferedOutputStream( new
		 * FileOutputStream( new File("Manager.txt"))));
		 * 
		 * dos.writeChars("Managers"); dos.writeChars("\n");
		 * 
		 * for(int i = 0; i < usersList.size(); i++) {
		 * 
		 * dos.writeChars(usersList.get(i).getUserName()); dos.writeChars("\n");
		 * dos.writeChars(usersList.get(i).getUserPassword());
		 * 
		 * 
		 * dos.writeChars("\n"); if(i < usersList.size() - 1)
		 * dos.writeChars("---------------------------\n");
		 * 
		 * }
		 * 
		 * dos.close(); } catch (FileNotFoundException e) { e.printStackTrace(); } catch
		 * (IOException e) { e.printStackTrace(); }
		 * 
		 * 
		 * /*File file = new File("Manager.txt");
		 * 
		 * FileOutputStream fos = null; ObjectOutputStream oos = null; try { fos = new
		 * FileOutputStream(file); oos = new ObjectOutputStream(fos);
		 * oos.writeObject(usersList); } catch (FileNotFoundException fnfe) {
		 * System.out.println("Could not find file"); fnfe.printStackTrace(); } catch
		 * (IOException ioe) {
		 * System.out.println("I/O Exception while writing to file");
		 * ioe.printStackTrace(); } finally { if (fos != null) { safeClose(fos); } }
		 */
	}

	// same but with technician

	public static void updateTechnicianFile(ArrayList<Technician> usersList) {

		OutputStreamWriter osw = null;
		FileOutputStream is = null;

		try {

			File file = new File("Technician.txt");
			is = new FileOutputStream(file);
			osw = new OutputStreamWriter(is);
			Writer w = new BufferedWriter(osw);

			if (!usersList.isEmpty())
				w.write("Technicians\n");

			for (int i = 0; i < usersList.size(); i++) {

				w.write(usersList.get(i).getUserName());
				w.write("\n");

				w.write(usersList.get(i).getUserPassword());
				w.write("\n");

				w.write(Integer.toString(usersList.get(i).getIdentityCardNumber()));
				w.write("\n");

				w.write(usersList.get(i).getDepartment());
				w.write("\n");

				w.write(usersList.get(i).getPhoneNumber());
				w.write("\n");

				w.write(usersList.get(i).getEmailAdress());
				w.write("\n");

				w.write(usersList.get(i).getMailingAdress());
				w.write("\n");

				w.write("schedule\n");
				for (int j = 0; j < usersList.get(i).getSchedules().size(); j++) {

					w.write(usersList.get(i).getSchedules().get(j).getStart()
							.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
					w.write("\n");

					w.write(usersList.get(i).getSchedules().get(j).getEnd()
							.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
					w.write("\n");

					w.write(Integer.toString(usersList.get(i).getSchedules().get(j).getCustomerId()));
					w.write("\n");

				}
				w.write("end_Schedule\n");

				w.write("comments");
				w.write("\n");

				for (int j = 0; j < usersList.get(i).getComments().size(); j++) {

					w.write(Integer.toString(usersList.get(i).getComments().get(j).getCustomerId()));
					w.write("\n");

					w.write(usersList.get(i).getComments().get(j).getRate().toString());
					w.write("\n");

					w.write(usersList.get(i).getComments().get(j).getAppointmentDate().toString());
					w.write("\n");

					w.write(usersList.get(i).getComments().get(j).getComment());
					w.write("\n");

					w.write("end\n");
				}

				w.write("end_comments");

				w.write("\n");
				if (i < usersList.size() - 1)
					w.write("---------------------------\n");

			}
			w.close();
		} catch (IOException e) {
			System.err.println("Problem writing to the file Manager.txt");
		}

		/*
		 * File file = new File("Technician.txt");
		 * 
		 * FileOutputStream fos = null; ObjectOutputStream oos = null; try { fos = new
		 * FileOutputStream(file); oos = new ObjectOutputStream(fos);
		 * oos.writeObject(usersList); } catch (FileNotFoundException fnfe) {
		 * System.out.println("Could not find file"); fnfe.printStackTrace(); } catch
		 * (IOException ioe) {
		 * System.out.println("I/O Exception while writing to file");
		 * ioe.printStackTrace(); } finally { if (fos != null) { safeClose(fos); } }
		 */
	}

	// same but with customers

	public static void updateCustomerFile(ArrayList<Customer> usersList) {

		OutputStreamWriter osw = null;
		FileOutputStream is = null;

		try {
			// Whatever the file path is.
			File file = new File("Customer.txt");
			is = new FileOutputStream(file);
			osw = new OutputStreamWriter(is);
			Writer w = new BufferedWriter(osw);

			w.write("Customers\n");

			for (int i = 0; i < usersList.size(); i++) {

				w.write(usersList.get(i).getUserName());
				w.write("\n");

				w.write(usersList.get(i).getUserPassword());
				w.write("\n");

				w.write(Integer.toString(usersList.get(i).getIdentityCardNumber()));
				w.write("\n");

				w.write(usersList.get(i).getPhoneNumber());
				w.write("\n");

				w.write(usersList.get(i).getEmailAdress());
				w.write("\n");

				w.write(usersList.get(i).getMailingAdress());
				w.write("\n");

				w.write("feedbacks");
				w.write("\n");

				for (int j = 0; j < usersList.get(i).getFeedbacks().size(); j++) {

					w.write(Integer.toString(usersList.get(i).getFeedbacks().get(j).getTechnicianId()));
					w.write("\n");

					w.write(usersList.get(i).getFeedbacks().get(j).getServiceType() + "\n");

					w.write(Double.toString(usersList.get(i).getFeedbacks().get(j).getCost()));
					w.write("\n");

					w.write(usersList.get(i).getFeedbacks().get(j).getAppointmentDate().toString() + "\n");

					w.write(usersList.get(i).getFeedbacks().get(j).getFeedback());
					w.write("\n");

					w.write("end\n");
				}

				w.write("end_feedbacks");

				w.write("\n");
				if (i < usersList.size() - 1)
					w.write("---------------------------\n");

			}

			w.close();
		} catch (IOException e) {
			System.err.println("Problem writing to the file Manager.txt");
		}
		/*
		 * File file = new File("Customer.txt");
		 * 
		 * FileOutputStream fos = null; ObjectOutputStream oos = null; try { fos = new
		 * FileOutputStream(file); oos = new ObjectOutputStream(fos);
		 * oos.writeObject(usersList); } catch (FileNotFoundException fnfe) {
		 * System.out.println("Could not find file"); fnfe.printStackTrace(); } catch
		 * (IOException ioe) {
		 * System.out.println("I/O Exception while writing to file");
		 * ioe.printStackTrace(); } finally { if (fos != null) { safeClose(fos); } }
		 */
	}

	/*
	 * In this static function, i read into the manager file. indeed, in my text
	 * file, i write the major and minor price at the beginning of the file, if i
	 * don't find this data into the file, it means that managers did'nt set the
	 * price yet so it return 0.
	 */

	public static double returnMajorPrice() {

		BufferedReader reader;
		String buffer = "";

		try {

			reader = new BufferedReader(new FileReader("Manager.txt"));

			buffer = reader.readLine();

			if (buffer.equals("Major")) {
				buffer = reader.readLine();
				return Double.parseDouble(buffer);
			}

			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 0;
	}

	// same but with minor price

	public static double returnMinorPrice() {

		BufferedReader reader;
		String buffer = "";

		try {

			reader = new BufferedReader(new FileReader("Manager.txt"));

			buffer = reader.readLine();

			while (!buffer.equals("Minor")) {
				if (buffer.equals("Managers"))
					return 0;
				buffer = reader.readLine();

			}

			buffer = reader.readLine();

			reader.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		return Double.parseDouble(buffer);
	}

	/*
	 * this static function will read the manager text file. Indeed, we write the
	 * company balance into this file
	 */

	public static double returnCompanyBalance() {

		BufferedReader reader;
		String buffer = "";

		try {

			reader = new BufferedReader(new FileReader("Manager.txt"));

			buffer = reader.readLine();

			while (!buffer.equals("balance")) {
				if (buffer.equals("Managers"))
					return 0;
				buffer = reader.readLine();

			}

			buffer = reader.readLine();

			reader.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		return Double.parseDouble(buffer);

	}

	/*
	 * this method return an arraylist that contain either managers, technician or
	 * customers. It depend on the file that is passed as the file paramater.
	 */
	public static ArrayList<?> readFile(String file) {

		// Nous déclarons nos objets en dehors du bloc try/catch
		BufferedReader reader;

		String buffer = "";

		switch (file) {
		case "Manager.txt":

			ArrayList<Manager> usersList = new ArrayList<Manager>();

			try {

				reader = new BufferedReader(new FileReader("Manager.txt"));

				buffer = reader.readLine();
				while (!buffer.equals("Managers")) {
					buffer = reader.readLine();
					if (buffer == null) {
						reader.close();
						return usersList;
					}
				}

				while (buffer != null) {

					Manager currentManager = new Manager();

					buffer = reader.readLine();
					currentManager.setUserName(buffer);

					buffer = reader.readLine();
					currentManager.setUserPassword(buffer);
					usersList.add(currentManager);

					buffer = reader.readLine();

				}

				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

			return usersList;

		/*
		 * ArrayList<Manager> usersList = new ArrayList<Manager>();
		 * 
		 * try { fis = new FileInputStream(file); ois = new ObjectInputStream(fis);
		 * 
		 * usersList = (ArrayList<Manager>) ois.readObject();
		 * 
		 * } catch (FileNotFoundException fnfe) {
		 * System.out.println("Could not find file"); fnfe.printStackTrace(); } catch
		 * (ClassNotFoundException cnfe) {
		 * System.out.println("File format is wrong :("); cnfe.printStackTrace(); }
		 * catch (IOException ioe) {
		 * System.out.println("I/O Exception while reading file");
		 * ioe.printStackTrace(); } finally { if (fis != null) { safeClose(fis); } }
		 * return usersList;
		 */

		case "Technician.txt":

			ArrayList<Technician> TusersList = new ArrayList<Technician>();

			try {

				reader = new BufferedReader(new FileReader("Technician.txt"));

				buffer = reader.readLine();

				while (buffer != null) {

					Technician currentTechnician = new Technician();

					buffer = reader.readLine();
					currentTechnician.setUserName(buffer);

					buffer = reader.readLine();
					currentTechnician.setUserPassword(buffer);

					buffer = reader.readLine();
					currentTechnician.setIdentityCardNumber(Integer.parseInt(buffer));

					buffer = reader.readLine();
					currentTechnician.setDepartment(buffer);

					buffer = reader.readLine();
					currentTechnician.setPhoneNumber(buffer);

					buffer = reader.readLine();
					currentTechnician.setEmailAdress(buffer);

					buffer = reader.readLine();
					currentTechnician.setMailingAdress(buffer);

					buffer = reader.readLine();
					buffer = reader.readLine();

					ArrayList<Schedule> scheduleList = new ArrayList<Schedule>();

					while (!buffer.equals("end_Schedule")) {

						Schedule schedule = new Schedule();

						schedule.setStart(LocalDateTime.parse(buffer, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));

						buffer = reader.readLine();

						schedule.setEnd(LocalDateTime.parse(buffer, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));

						scheduleList.add(schedule);

						buffer = reader.readLine();

						schedule.setCustomerId(Integer.parseInt(buffer));

						buffer = reader.readLine();

					}

					currentTechnician.setSchedules(scheduleList);

					buffer = reader.readLine();
					buffer = reader.readLine();

					while (!buffer.equals("end_comments")) {

						String comment = "";

						int customerId = Integer.parseInt(buffer);

						buffer = reader.readLine();

						Rate rate = Rate.valueOf(buffer);

						buffer = reader.readLine();

						LocalDateTime appointmentDate = LocalDateTime.parse(buffer);

						buffer = reader.readLine();

						while (!buffer.equals("end")) {
							comment += buffer + "\n";
							buffer = reader.readLine();
						}

						currentTechnician.getComments().add(new Comment(customerId, comment, appointmentDate, rate));

						buffer = reader.readLine();
					}

					TusersList.add(currentTechnician);

					buffer = reader.readLine();

				}

				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

			return TusersList;

		/*
		 * ArrayList<Technician> TusersList = new ArrayList<Technician>();
		 * 
		 * 
		 * try { fis = new FileInputStream(file); ois = new ObjectInputStream(fis);
		 * 
		 * TusersList = (ArrayList<Technician>) ois.readObject();
		 * 
		 * } catch (FileNotFoundException fnfe) {
		 * System.out.println("Could not find file"); fnfe.printStackTrace(); } catch
		 * (ClassNotFoundException cnfe) {
		 * System.out.println("File format is wrong :("); cnfe.printStackTrace(); }
		 * catch (IOException ioe) {
		 * System.out.println("I/O Exception while reading file");
		 * ioe.printStackTrace(); } finally { if (fis != null) { safeClose(fis); } }
		 * return TusersList;
		 */

		case "Customer.txt":

			ArrayList<Customer> CusersList = new ArrayList<Customer>();

			try {

				reader = new BufferedReader(new FileReader("Customer.txt"));

				buffer = reader.readLine();

				while (buffer != null) {

					Customer currentCustomer = new Customer();

					buffer = reader.readLine();
					currentCustomer.setUserName(buffer);

					buffer = reader.readLine();
					currentCustomer.setUserPassword(buffer);

					buffer = reader.readLine();
					currentCustomer.setIdentityCardNumber(Integer.parseInt(buffer));

					buffer = reader.readLine();
					currentCustomer.setPhoneNumber(buffer);

					buffer = reader.readLine();
					currentCustomer.setEmailAdress(buffer);

					buffer = reader.readLine();
					currentCustomer.setMailingAdress(buffer);

					buffer = reader.readLine();
					buffer = reader.readLine();

					while (!buffer.equals("end_feedbacks")) {

						String feedback = "";

						int technicianId = Integer.parseInt(buffer);

						buffer = reader.readLine();

						String serviceType = buffer;

						buffer = reader.readLine();

						double cost = Double.parseDouble(buffer);

						buffer = reader.readLine();

						LocalDateTime appointmentDate = LocalDateTime.parse(buffer);

						buffer = reader.readLine();

						while (!buffer.equals("end")) {
							feedback += buffer + "\n";
							buffer = reader.readLine();
						}

						currentCustomer.getFeedbacks()
								.add(new Feedback(technicianId, feedback, appointmentDate, cost, serviceType));

						buffer = reader.readLine();
					}

					CusersList.add(currentCustomer);

					buffer = reader.readLine();

				}

				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

			return CusersList;

		/*
		 * ArrayList<Customer> CusersList = new ArrayList<Customer>();
		 * 
		 * 
		 * 
		 * try { fis = new FileInputStream(file); ois = new ObjectInputStream(fis);
		 * 
		 * CusersList = (ArrayList<Customer>) ois.readObject();
		 * 
		 * } catch (FileNotFoundException fnfe) {
		 * System.out.println("Could not find file"); fnfe.printStackTrace(); } catch
		 * (ClassNotFoundException cnfe) {
		 * System.out.println("File format is wrong :("); cnfe.printStackTrace(); }
		 * catch (IOException ioe) {
		 * System.out.println("I/O Exception while reading file");
		 * ioe.printStackTrace(); } finally { if (fis != null) { safeClose(fis); } }
		 * 
		 * return CusersList;
		 */
		default:
			return null; // should not happen

		}

	}

	// this function return a customer according to its username and password

	public static Customer returnSpecificCustomer(String userName, String userPassword) {

		ArrayList<Customer> usersList = (ArrayList<Customer>) FileManager.readFile("Customer.txt");

		for (int i = 0; i < usersList.size(); i++) {

			if (userName.equals(usersList.get(i).getUserName())
					&& userPassword.equals(usersList.get(i).getUserPassword())) {
				return usersList.get(i);
			}

		}

		return new Customer("neant", "neant");

	}

	// same but with technician

	public static Technician returnSpecificTechnician(String userName, String userPassword) {

		ArrayList<Technician> usersList = (ArrayList<Technician>) FileManager.readFile("Technician.txt");

		for (int i = 0; i < usersList.size(); i++) {

			if (userName.equals(usersList.get(i).getUserName())
					&& userPassword.equals(usersList.get(i).getUserPassword()))
				return usersList.get(i);

		}

		return new Technician("neant", "neant");

	}

	// return a technician according to its id

	public static Technician returnSpecificTechnician(int id) {

		ArrayList<Technician> usersList = (ArrayList<Technician>) FileManager.readFile("Technician.txt");

		for (int i = 0; i < usersList.size(); i++) {

			if (id == usersList.get(i).getIdentityCardNumber())
				return usersList.get(i);

		}

		return new Technician("neant", "neant");
	}

	// same but with customer
	public static Customer returnSpecificCustomer(int id) {

		ArrayList<Customer> usersList = (ArrayList<Customer>) FileManager.readFile("Customer.txt");

		for (int i = 0; i < usersList.size(); i++) {

			if (id == usersList.get(i).getIdentityCardNumber())
				return usersList.get(i);

		}

		return new Customer("neant", "neant");
	}

	// return true if the technician exist in the technician text file

	public static boolean isTechnicianExist(int idNumber) {

		ArrayList<Technician> usersList = (ArrayList<Technician>) FileManager.readFile("Technician.txt");

		for (int i = 0; i < usersList.size(); i++) {
			if (idNumber == usersList.get(i).getIdentityCardNumber())
				return true;
		}
		return false;
	}

	// same but with customer
	public static boolean isCustomerExist(int idNumber) {

		ArrayList<Customer> usersList = (ArrayList<Customer>) FileManager.readFile("Customer.txt");

		for (int i = 0; i < usersList.size(); i++) {
			if (idNumber == usersList.get(i).getIdentityCardNumber())
				return true;
		}
		return false;
	}

	// add a schedule to the technician that has as an id the id passed on the
	// parameter

	public static void addSchedule(Schedule schedule, int idNumber) {

		ArrayList<Technician> usersList = (ArrayList<Technician>) FileManager.readFile("Technician.txt");

		for (int i = 0; i < usersList.size(); i++) {
			if (idNumber == usersList.get(i).getIdentityCardNumber()) {
				usersList.get(i).addSchedule(schedule);
				i = usersList.size();
			}
		}

		FileManager.updateTechnicianFile(usersList);

	}

	/*
	 * public static void safeClose(Closeable closeable) { try { closeable.close();
	 * } catch (IOException ioe) { throw new RuntimeException(ioe); } }
	 */

}

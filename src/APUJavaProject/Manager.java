package APUJavaProject;

import java.util.ArrayList;

public class Manager extends User {
	
	static double normalServicePrice = FileManager.returnMinorPrice();
	static double majorServicePrice = FileManager.returnMajorPrice();
	static double companyBalance = FileManager.returnCompanyBalance();
	static ArrayList<String> requirements = FileManager.returnRequirements();
	//static ArrayList<Manager> managers = (ArrayList<Manager>) FileManager.readFile("Manager.txt")
		
	
	public static void affiche() {System.out.println("affiche manager");}
	public void affichemethod() {System.out.println("affiche instance  manager");}
	public Manager() {
		super("John_Doe","AZERTYUIOP");
	}
	
	public Manager(String userName, String userPassword) {
		super(userName, userPassword);
	}
	
	public void register() {}

	public void updateInformation() {}
	
	public void setPrice(double normalService, double majorService) {
		Manager.normalServicePrice = normalService;
		Manager.majorServicePrice = majorService;
	}

	public double getNormalServicePrice() {
		return normalServicePrice;
	}

	public void setNormalServicePrice(double normalServicePrice) {
		this.normalServicePrice = normalServicePrice;
	}

	public double getMajorServicePrice() {
		return majorServicePrice;
	}

	public void setMajorServicePrice(double majorServicePrice) {
		this.majorServicePrice = majorServicePrice;
	}
	
}

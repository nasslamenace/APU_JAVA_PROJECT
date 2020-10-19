package APUJavaProject;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class CustomerTableModel extends AbstractTableModel {
	
	private ArrayList <Customer> usersList;
	
	private String titles[] = {"User-name", "User-password", "id", "phone number", "email adress", "mailing adress"};
	
	public CustomerTableModel(ArrayList<Customer> usersList) {
		
		super();
		

  	  
		
		this.usersList = usersList;
	}
	
	public ArrayList<Customer> getUsersList(){return usersList;}
	
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		
		if(columnIndex == 2)
			return false;
		
	    return true; 
	}
	
	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
	    if(aValue != null){
	        Customer myCustomer = usersList.get(rowIndex);
	 
	        switch(columnIndex){
	            case 0:
	                myCustomer.setUserName((String)aValue);
	                break;
	            case 1:
	            	myCustomer.setUserPassword((String)aValue);
	                break;
	            case 2:
	            	myCustomer.setIdentityCardNumber((int)aValue);
	                break;
	            case 3:
	            	myCustomer.setPhoneNumber((String)aValue);
	                break;
	            case 4:
	            	myCustomer.setEmailAdress((String)aValue);
	                break;
	            case 5:
	            	myCustomer.setMailingAdress((String)aValue);
	                break;
	        }
	    }
	}

	@Override
	public int getRowCount() {
		return usersList.size();
	}

	@Override
	public int getColumnCount() {
		
		return titles.length;
	}
	
    public String getColumnName(int columnIndex) {
        return titles[columnIndex];
    }

	@Override
	 public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex){
            case 0:
                return usersList.get(rowIndex).getUserName();
            case 1:
                return usersList.get(rowIndex).getUserPassword();
            case 2:
            	return usersList.get(rowIndex).getIdentityCardNumber();
            case 3:
            	return usersList.get(rowIndex).getPhoneNumber();
            case 4:
            	return usersList.get(rowIndex).getEmailAdress();
            case 5:
            	return usersList.get(rowIndex).getMailingAdress();
            default:
                return null; //should never happen
        }
    }
	
	public void addUser(Customer user) {
        usersList.add(user);
        fireTableRowsInserted(usersList.size() -1, usersList.size() -1);
    }
 
    public void removeUser(int rowIndex) {
    	
    	ArrayList<Technician> technicians = (ArrayList<Technician>) FileManager.readFile("Technician.txt");
    	
    	for(int i = 0; i < technicians.size(); i++) {
    		for(int j = 0; j < technicians.get(i).getSchedules().size(); j++) {
    			
    			if(technicians.get(i).getSchedules().get(j).getCustomerId() == usersList.get(rowIndex).getIdentityCardNumber())
    				technicians.get(i).getSchedules().remove(j);
    			
    		}
    	}
    	
    	FileManager.updateTechnicianFile(technicians);
    	
        usersList.remove(rowIndex);
        
        
 
        fireTableRowsDeleted(rowIndex, rowIndex);
    }

}

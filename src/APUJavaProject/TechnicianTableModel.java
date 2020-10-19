package APUJavaProject;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class TechnicianTableModel extends AbstractTableModel {
	
	private ArrayList <Technician> usersList;
	
	private String titles[] = {"User-name", "User-password", "id" , "department", "phone number", "email adress", "mailing adress"};
	
	public TechnicianTableModel(ArrayList<Technician> usersList) {
		
		super();
		
		

  	  
		
		this.usersList = usersList;
	}
	
	public ArrayList<Technician> getUsersList(){return usersList;}
	
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		
		if(columnIndex == 2)
			return false;
		
	    return true; 
	}
	
	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
	    if(aValue != null){
	    	
	    	System.out.println("nassssss");
	        Technician myTechnician = usersList.get(rowIndex);
	 
	        switch(columnIndex){
	            case 0:
	                myTechnician.setUserName((String)aValue);
	                break;
	            case 1:
	            	myTechnician.setUserPassword((String)aValue);
	                break;
	            case 2:
	            	myTechnician.setIdentityCardNumber((int)aValue);
	                break;
	            case 3:
	            	myTechnician.setDepartment((String)aValue);
	                break;
	            case 4:
	            	myTechnician.setPhoneNumber((String)aValue);
	                break;
	            case 5:
	            	myTechnician.setEmailAdress((String)aValue);
	                break;
	            case 6:
	            	myTechnician.setEmailAdress((String)aValue);
	                break;
	            case 7:
	            	myTechnician.setMailingAdress((String)aValue);
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
            	return usersList.get(rowIndex).getDepartment();
            case 4:
            	return usersList.get(rowIndex).getPhoneNumber();
            case 5:
            	return usersList.get(rowIndex).getEmailAdress();
            case 6:
            	return usersList.get(rowIndex).getMailingAdress();
            	
            default:
                return null; //should never happen
        }
    }
	
	public void addUser(Technician user) {
        usersList.add(user);
        fireTableRowsInserted(usersList.size() -1, usersList.size() -1);
    }
 
    public void removeUser(int rowIndex) {
    	
        usersList.remove(rowIndex);
        
        
 
        fireTableRowsDeleted(rowIndex, rowIndex);
    }

}

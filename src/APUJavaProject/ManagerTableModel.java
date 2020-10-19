package APUJavaProject;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class ManagerTableModel extends AbstractTableModel {
	
	private ArrayList <Manager> usersList;
	
	private String titles[] = {"User-name", "User-password"};
	
	public ManagerTableModel(ArrayList<Manager> usersList) {
		
		super();
		

  	  
		
		this.usersList = usersList;
	}
	
	public ArrayList<Manager> getUsersList(){return usersList;}
	
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		
	    return true;
	}
	
	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
	    if(aValue != null){
	        Manager myManager = usersList.get(rowIndex);
	 
	        switch(columnIndex){
	            case 0:
	                myManager.setUserName((String)aValue);
	                break;
	            case 1:
	            	myManager.setUserPassword((String)aValue);
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
            default:
                return null; //should never happen
        }
    }
	
	public void addUser(Manager user) {
        usersList.add(user);
 
        fireTableRowsInserted(usersList.size() -1, usersList.size() -1);
    }
 
    public void removeUser(int rowIndex) {
        usersList.remove(rowIndex);
        
        
 
        fireTableRowsDeleted(rowIndex, rowIndex);
    }

}

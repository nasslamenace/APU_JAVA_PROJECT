package APUJavaProject;

import java.io.Serializable;

public  abstract class User implements Serializable {
	
	public User() {}
	protected String userName;
	protected String userPassword;
	
	public static void affiche() {System.out.println("affiche user");}
	public void affichemethod() {System.out.println("affiche instance  user");}
	
	public User(String userName, String userPassword) {
		this.setUserName(userName);
		this.setUserPassword(userPassword);
	}
	
	public String getUserName() {return userName;}
	public String getUserPassword() {return userPassword;}
	
	public void setUserName(String userName) {this.userName = userName;}
	public void setUserPassword(String userPassword) {this.userPassword = userPassword;}
}

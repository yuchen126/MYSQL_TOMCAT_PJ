package application.model;

import javafx.scene.control.CheckBox;

public class Employee {
	private String id;
	private String name;
	private String ssn;
	private String address;
	private String department;
	public Employee(String u, String e, String c, String s, String t){
		id = u;
		name = e;
		ssn = c;
		address = s;
		department = t;
	}
	
	public String getId(){
		return id;
	}
	public String getName(){
		return name;
	}
	public String getSsn(){
		return ssn;
	}
	public String getAddress(){
		return address;
	}
	public String getDepartment(){
		return department;
	}
}

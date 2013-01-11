package edu.iiitb.timesheet.model;

public class EmployeeBean {
	
	public EmployeeBean(String projectid, String projectname) {
		super();
				
	}
	public EmployeeBean() {
		// TODO Auto-generated constructor stub
	}	
	
	String empId;
	String empName;
	String empPassword;
	
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getEmpPassword() {
		return empPassword;
	}
	public void setEmpPassword(String empPassword) {
		this.empPassword = empPassword;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	String emailId;
	String phoneNumber;
		
	
}

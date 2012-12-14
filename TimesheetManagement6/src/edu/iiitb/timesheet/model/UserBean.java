package edu.iiitb.timesheet.model;

public class UserBean {
	
	
	public UserBean(){}
	public UserBean(String userid, String username) {
		super();
		this.userid = userid;
		this.username = username;
	}
	String userid;
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	String username;

}

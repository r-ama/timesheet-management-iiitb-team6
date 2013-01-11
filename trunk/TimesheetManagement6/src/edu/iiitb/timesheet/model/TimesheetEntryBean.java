package edu.iiitb.timesheet.model;

import java.util.ArrayList;

public class TimesheetEntryBean {
	
	String projectid;
	String projectName;
	public ArrayList<String> day=new ArrayList<String>();
	String total;
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
	public ArrayList<Boolean> approvalFlag=new ArrayList<Boolean>();
	
	public ArrayList<Boolean> getApprovalFlag() {
		return approvalFlag;
	}
	public void setApprovalFlag(ArrayList<Boolean> approvalFlag) {
		this.approvalFlag = approvalFlag;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	public ArrayList<String> getDay() {
		return day;
	}
	public void setDay(ArrayList<String> day) {
		this.day = day;
	}
	public ArrayList<String> getDate() {
		return date;
	}
	public void setDate(ArrayList<String> date) {
		this.date = date;
	}
	public ArrayList<String> date=new ArrayList<String>();
	
	public String getProjectid() {
		return projectid;
	}
	public void setProjectid(String projectid) {
		this.projectid = projectid;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	
}

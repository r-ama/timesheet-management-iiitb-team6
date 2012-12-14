package edu.iiitb.timesheet.model;

import java.util.ArrayList;

public class ProjectAllocationBean {
	
	boolean checkId;
	String allocationId;
	public String getAllocationId() {
		return allocationId;
	}
	public void setAllocationId(String allocationId) {
		this.allocationId = allocationId;
	}
	String allocatedFlag;
	public String getAllocatedFlag() {
		return allocatedFlag;
	}
	public void setAllocatedFlag(String allocatedFlag) {
		this.allocatedFlag = allocatedFlag;
	}
	public boolean isCheckId() {
		return checkId;
	}
	public void setCheckId(boolean checkId) {
		this.checkId = checkId;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getProjectid() {
		return projectid;
	}
	public void setProjectid(String projectid) {
		this.projectid = projectid;
	}
	public String getAllocationStartDate() {
		return allocationStartDate;
	}
	public void setAllocationStartDate(String allocationStartDate) {
		this.allocationStartDate = allocationStartDate;
	}
	public String getAllocationEndDate() {
		return allocationEndDate;
	}
	public void setAllocationEndDate(String allocationEndDate) {
		this.allocationEndDate = allocationEndDate;
	}
	String userid;
	String userName;
	String roleid;
	String supervisorId;
	public String getSupervisorId() {
		return supervisorId;
	}
	public void setSupervisorId(String supervisorId) {
		this.supervisorId = supervisorId;
	}
	public String getRoleid() {
		return roleid;
	}
	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}
	String projectid;
	String allocationStartDate;
	String allocationEndDate;
	
	

}

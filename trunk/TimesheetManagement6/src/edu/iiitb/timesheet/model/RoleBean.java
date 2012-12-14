package edu.iiitb.timesheet.model;

public class RoleBean {

	String roleId;
	String roleName;
	public RoleBean() {
		// TODO Auto-generated constructor stub
	}
	
	
	public RoleBean(String roleId, String roleName) {
		super();
		this.roleId = roleId;
		this.roleName = roleName;
	}


	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	
	
	
}

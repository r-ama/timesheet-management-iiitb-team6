package edu.iiitb.timesheet.action;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Map;

import com.mast.util.DB;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import edu.iiitb.timesheet.model.ProjectAllocationBean;
import edu.iiitb.timesheet.model.ProjectBean;
import edu.iiitb.timesheet.model.RoleBean;
import edu.iiitb.timesheet.model.UserBean;

public class CreateAllocationAction extends ActionSupport {

	
	ArrayList<RoleBean> roleList = new ArrayList<RoleBean>();
	ArrayList<ProjectAllocationBean> allocList =new ArrayList<ProjectAllocationBean>();
	ArrayList<UserBean> supervisorList = new ArrayList<UserBean>();
	public ArrayList<UserBean> getSupervisorList() {
		return supervisorList;
	}

	public void setSupervisorList(ArrayList<UserBean> supervisorList) {
		this.supervisorList = supervisorList;
	}

	String commandButton="";
	public String getCommandButton() {
		return commandButton;
	}

	public void setCommandButton(String commandButton) {
		this.commandButton = commandButton;
	}

	public ArrayList<ProjectAllocationBean> getAllocList() {
		return allocList;
	}

	public void setAllocList(ArrayList<ProjectAllocationBean> allocList) {
		this.allocList = allocList;
	}

	String userid;
	String username;
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

	public ArrayList<RoleBean> getRoleList() {
		return roleList;
	}

	public void setRoleList(ArrayList<RoleBean> roleList) {
		this.roleList = roleList;
	}

	

	public ArrayList<ProjectBean> getProjList() {
		return projList;
	}

	public void setProjList(ArrayList<ProjectBean> projList) {
		this.projList = projList;
	}

	ArrayList<ProjectBean> projList = new ArrayList<ProjectBean>();
	ArrayList<UserBean> userlist = new ArrayList<UserBean>();
	
	public ArrayList<UserBean> getUserlist() {
		return userlist;
	}

	public void setUserlist(ArrayList<UserBean> userlist) {
		this.userlist = userlist;
	}

	public String execute()
	{
		//Get all allocation 
		Map session = (Map) ActionContext.getContext().get("session");
		if(session.get("userid")==null)
			return "initial_entry";
		
		
			username = session.get("username")+"";
			userid = session.get("userid")+"";
		
		
		
			
		//Get all users
		try
		{
		String query = "select empId,empName from employee";
		ResultSet rs=DB.readFromBmtcDB(query);
		
		
		
		while(rs.next())
		{
			UserBean ub=new UserBean();
			ub.setUserid(rs.getString(1));
			ub.setUsername(rs.getString(2));
			
			userlist.add(ub);
			
		}
		
		//Get all active projects
		query = "select p.projectid,p.projectname from project p where (p.actual_end_date is null or p.actual_end_date >NOW())";
		
		int roleId=(Integer)session.get("roleId");
		
		if(roleId==3)
		{
			
			query+= " and p.projectid in(select distinct p1.projectId from employee u1,allocation a1,project p1";
			query+=    " where p1.projectId = a1.projectId";
			query+=		" and u1.empId = a1.empId";
			query+=     " and a1.roleId = 3";
			query+=		" and u1.empId ="+userid+ ")";
		}
		
		rs=DB.readFromBmtcDB(query);
		projList.add(new ProjectBean("0","--Select--"));
		while(rs.next())
		{
            ProjectBean ub=new ProjectBean();
			ub.setProjectid(rs.getString(1));
			ub.setProjectname(rs.getString(2));
			
			projList.add(ub);
			
		}
		
		//Get all roles
		
		query = "select roleid,rolename from rolemst";
		roleList.add(new RoleBean("0","Select"));
		rs=DB.readFromBmtcDB(query);
		while(rs.next())
		{
            RoleBean ub=new RoleBean();
			ub.setRoleId(rs.getString(1));
			ub.setRoleName(rs.getString(2));
			
			roleList.add(ub);
			
		}
		
		if(allocList.size()>0)
		{
		    query = "select e.empId,e.empName from employee e,allocation a where e.empId=a.empId";
			query+=" and a.projectid="+allocList.get(0).getProjectid();
			
			rs=DB.readFromBmtcDB(query);
			
			while(rs.next())
			{
				UserBean ub=new UserBean();
				ub.setUserid(rs.getString(1));
				ub.setUsername(rs.getString(2));
				
				supervisorList.add(ub);
				
			}
			
		}
		
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
		if(allocList.size()==0)
		{	
			ProjectAllocationBean pb=new ProjectAllocationBean();
			pb.setCheckId(true);
			pb.setRoleid("0");
			pb.setUserid("0");
			pb.setSupervisorId("0");
			pb.setProjectid("0");
			pb.setAllocationId("0");
			allocList.add(pb);
		}
		
		
		return "success";
	}
	
	
}

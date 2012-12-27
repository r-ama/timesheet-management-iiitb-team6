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

public class ProjectAllocationAction extends ActionSupport {

	ArrayList<ProjectAllocationBean> allocList =new ArrayList<ProjectAllocationBean>();
	ArrayList<RoleBean> roleList = new ArrayList<RoleBean>();
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

	public ArrayList<ProjectAllocationBean> getAllocList() {
		return allocList;
	}

	public void setAllocList(ArrayList<ProjectAllocationBean> allocList) {
		this.allocList = allocList;
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
		
		String query = "select u.empId,u.empName,a.supervisorId,a.allocation_start_date,a.allocation_end_date,a.projectid,a.roleid,a.allocationId from employee u,allocation a where u.empId = a.empId";
		
		int roleId=(Integer)session.get("roleId");
		
		if(roleId==3)
		{
		
		query+= " and a.projectid in(select distinct p1.projectId from employee u1,allocation a1,project p1";
		query+=    " where p1.projectId = a1.projectId";
		query+=		" and u1.empId = a1.empId";
		query+=     " and a1.roleId = 3";
		query+=		" and u1.empId ="+userid+ ")";
		}
		ResultSet rs=DB.readFromBmtcDB(query);
		SimpleDateFormat sdf=new SimpleDateFormat("dd-MMM-yyyy");
		try
		{
		while(rs.next())
		{
			ProjectAllocationBean projectAllocationBean = new ProjectAllocationBean();
			projectAllocationBean.setUserid(rs.getString(1));
			projectAllocationBean.setUserName(rs.getString(2));
			projectAllocationBean.setSupervisorId(rs.getString(3));
			if(rs.getString(4)!=null)
				projectAllocationBean.setAllocationStartDate(sdf.format(rs.getDate(4)));
			else
				projectAllocationBean.setAllocationStartDate("dd-MMM-YYYY");
			
			if(rs.getString(5)!=null)
				projectAllocationBean.setAllocationEndDate(sdf.format(rs.getDate(5)));
			else
				projectAllocationBean.setAllocationEndDate("dd-MMM-YYYY");
			if(rs.getString(6)!=null)
			{	
				projectAllocationBean.setProjectid(rs.getString(6));
				projectAllocationBean.setAllocatedFlag("Yes");
			}	
			else
			{	
				projectAllocationBean.setProjectid("0");
				projectAllocationBean.setAllocatedFlag("No");
			}	
			
			if(rs.getString(7)!=null)
				projectAllocationBean.setRoleid(rs.getString(7));
			else
				projectAllocationBean.setRoleid("0");
			
			projectAllocationBean.setCheckId(false);
			projectAllocationBean.setAllocationId(rs.getString(8));
			
			allocList.add(projectAllocationBean);
			
		}
		//Get all users
		
		query = "select empId,empName from employee";
		rs=DB.readFromBmtcDB(query);
		userlist.add(new UserBean("0","Select a Supervisor"));
		while(rs.next())
		{
			UserBean ub=new UserBean();
			ub.setUserid(rs.getString(1));
			ub.setUsername(rs.getString(2));
			
			userlist.add(ub);
			
		}
		
		//Get all active projects
		query = "select p.projectid,p.projectname from project p where (p.actual_end_date is null or p.actual_end_date >NOW())";
		
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
		
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
		
		
		return "success";
	}
	
	
}

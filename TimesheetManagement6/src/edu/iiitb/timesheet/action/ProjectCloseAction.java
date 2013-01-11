package edu.iiitb.timesheet.action;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Map;

import com.mast.util.DB;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import edu.iiitb.timesheet.model.ProjectBean;

public class ProjectCloseAction extends ActionSupport {
	
	ArrayList<ProjectBean> projectList;
	String userid;
	String username; 
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public ArrayList<ProjectBean> getProjectList() {
		return projectList;
	}

	public void setProjectList(ArrayList<ProjectBean> projectList) {
		this.projectList = projectList;
	}

	public ArrayList<String> getHeaderlist() {
		return headerlist;
	}

	public void setHeaderlist(ArrayList<String> headerlist) {
		this.headerlist = headerlist;
	}

	ArrayList<String> headerlist;
	
	public String execute()
	{
		
		projectList = new ArrayList<ProjectBean>();
		Map session = (Map) ActionContext.getContext().get("session");
		if(session.get("userid")==null)
			return "initial_entry";
		userid = session.get("userid")+"";
		username = session.get("username")+"";
		headerlist = new ArrayList<String>();
		headerlist.add("Select");
		headerlist.add("Project ID");
		headerlist.add("Project Name ");
		headerlist.add("Planned Start Date ");
		headerlist.add("Planned End Date ");
		headerlist.add("Actual Start Date ");
		headerlist.add("Actual End Date ");
		headerlist.add("Planned Effort ");
		headerlist.add("Actual Effort ");
		headerlist.add("Client Name");
		
		System.out.println("Role id:"+session.get("roleId"));
		int roleid= Integer.parseInt(session.get("roleId")+"");
		String query = " select p.projectId,p.projectName,p.planned_start_date,p.planned_end_date,p.actual_start_date,p.actual_end_date, p.planned_effort,p.actual_effort, c.clientName"
			+ " from project p,clientMst c"
			+ " where p.clientId = c.clientId ";
		
	if(roleid==3)
	{
		query+= " and p.projectid in(select distinct p1.projectId from employee u1,allocation a1,project p1";
		query+=    " where p1.projectId = a1.projectId";
		query+=		" and u1.empId = a1.empId";
		query+=		" and u1.empId ="+userid+ ")";
		
	}	
	
	
		
	
  
	ResultSet rs = DB.readFromBmtcDB(query);
	try
	{
	while (rs.next()) {
		ProjectBean pb = new ProjectBean();
		pb.setProjectid(rs.getString(1));
		pb.setProjectname(rs.getString(2));
		pb.setPlanned_start_date(rs.getString(3));
		pb.setPlanned_end_date(rs.getString(4));
		pb.setActual_start_date(rs.getString(5));
		pb.setActual_end_date(rs.getString(6));
		pb.setPlanned_effort(rs.getString(7));
		pb.setActual_effort(rs.getString(8));
		pb.setClientname(rs.getString(9));
        pb.setCheck(false);
		projectList.add(pb);

	}
	
	}
	catch(Exception ex)
	{
		ex.printStackTrace();
		return "error";
		
	}
		
	return "success";
		
	}

}

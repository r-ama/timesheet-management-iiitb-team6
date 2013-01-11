package edu.iiitb.timesheet.action;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Map;

import com.mast.util.DB;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import edu.iiitb.timesheet.model.ClientBean;
import edu.iiitb.timesheet.model.RoleBean;
import edu.iiitb.timesheet.model.UserBean;


public class CreateProjectAction extends ActionSupport {
	
	String userid;
	String username;
	ArrayList<String> headerlist;
	String projectname;
	String planned_start_date;
	String planned_end_date;
	String planned_effort;	
	ArrayList<ClientBean> clientList = new ArrayList<ClientBean>();
	
	public String getProjectname() {
		return projectname;
	}

	public ArrayList<ClientBean> getClientList() {
		return clientList;
	}

	public void setClientList(ArrayList<ClientBean> clientList) {
		this.clientList = clientList;
	}

	public void setProjectname(String projectname) {
		this.projectname = projectname;
	}

	public String getPlanned_start_date() {
		return planned_start_date;
	}

	public void setPlanned_start_date(String planned_start_date) {
		this.planned_start_date = planned_start_date;
	}

	public String getPlanned_end_date() {
		return planned_end_date;
	}

	public void setPlanned_end_date(String planned_end_date) {
		this.planned_end_date = planned_end_date;
	}	

	public String getPlanned_effort() {
		return planned_effort;
	}

	public void setPlanned_effort(String planned_effort) {
		this.planned_effort = planned_effort;
	}

	public ArrayList<String> getHeaderlist() {
		return headerlist;
	}

	public void setHeaderlist(ArrayList<String> headerlist) {
		this.headerlist = headerlist;
	}

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

	public String execute()
	{
		Map session = (Map) ActionContext.getContext().get("session");
		if(session.get("err_proj").equals("true"))
		{
			addActionError(getText("Project Name cannot be Empty! Please Enter"));
			session.put("err_proj", "false");
		}
		
		if(session.get("err_date").equals("true"))
		{
			addActionError(getText("End Date cannot be earlier than Start Date."));
			session.put("err_date", "false");
		}
		
		if(session.get("date_null").equals("true"))
		{
			addActionError(getText("Please enter Planned Start Date and Planned End Date."));
			session.put("date_null", "false");
		}
		
		if(session.get("client_null").equals("true"))
		{
			addActionError(getText("Please Select Client Name."));
			session.put("client_null", "false");
		}
		
		if(session.get("err_effort").equals("true"))
		{
			addActionError(getText("Planned Effort can contain only numeric values."));
			session.put("err_effort", "false");
		}
		
		headerlist=new ArrayList<String>();
		headerlist.add("Project Name ");
		headerlist.add("Planned Start Date ");
		headerlist.add("Planned End Date ");
		headerlist.add("Planned Effort ");
		headerlist.add("Client Name");
				
//		Map session = (Map) ActionContext.getContext().get("session");
		if(session.get("userid")==null)
			return "initial_entry";
		
		
		username = session.get("username")+"";
		userid = session.get("userid")+"";
		
		try
		{
		String query = "select clientId,clientName from clientMst";
		ResultSet rs=DB.readFromBmtcDB(query);	
		clientList.add(new ClientBean("0","Select"));
		while(rs.next())
		{
			ClientBean cb=new ClientBean();
			cb.setClientId(rs.getString(1));
			cb.setClientName(rs.getString(2));
			
			clientList.add(cb);
			
		}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
		return "success";
	}

}



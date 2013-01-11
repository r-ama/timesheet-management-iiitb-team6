package edu.iiitb.timesheet.action;


import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Map;

import com.mast.util.DB;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import edu.iiitb.timesheet.model.EmployeeBean;

public class CreateClientAction extends ActionSupport {
	
	String userid;
	String username;
	ArrayList<String> headerlist;
	String clientName;
	String clientPassword;
	String clientLocation;
		
	
	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getClientPassword() {
		return clientPassword;
	}

	public void setClientPassword(String clientPassword) {
		this.clientPassword = clientPassword;
	}

	public String getClientLocation() {
		return clientLocation;
	}

	public void setClientLocation(String clientLocation) {
		this.clientLocation = clientLocation;
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
		if(session.get("err_client").equals("true"))
		{
			addActionError(getText("Client Name cannot be Empty! Please Enter"));
			session.put("err_client", "false");
		}
		
		if(session.get("err_clientpass").equals("true"))
		{
			addActionError(getText("Client Password cannot be Empty! Please Enter"));
			session.put("err_clientpass", "false");
		}
		
		if(session.get("userid")==null)
			return "initial_entry";
		
		headerlist=new ArrayList<String>();
		headerlist.add("Client Name ");
		headerlist.add("Client Password ");
		headerlist.add("Client Location ");
		
		username = session.get("username")+"";
		userid = session.get("userid")+"";		
			
		return "success";
	}

}



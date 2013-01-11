package edu.iiitb.timesheet.action;


import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Map;

import com.mast.util.DB;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class PasswordEntryAction extends ActionSupport {
	
	String userid;
	String username;
	String oldpassword;
	String newpassword;
	String confirmpassword;
	ArrayList<String> headerlist;
		
		
	public ArrayList<String> getHeaderlist() {
		return headerlist;
	}

	public void setHeaderlist(ArrayList<String> headerlist) {
		this.headerlist = headerlist;
	}

	public String getOldpassword() {
		return oldpassword;
	}

	public void setOldpassword(String oldpassword) {
		this.oldpassword = oldpassword;
	}

	public String getNewpassword() {
		return newpassword;
	}

	public void setNewpassword(String newpassword) {
		this.newpassword = newpassword;
	}

	public String getConfirmpassword() {
		return confirmpassword;
	}

	public void setConfirmpassword(String confirmpassword) {
		this.confirmpassword = confirmpassword;
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
		System.out.println("inside password entry action");
		headerlist=new ArrayList<String>();
		headerlist.add("Old Password ");
		headerlist.add("New Password");
		headerlist.add("Confirm Password ");
		System.out.println("header list size:"+headerlist.size());
		Map session = (Map) ActionContext.getContext().get("session");
		username = session.get("username")+"";
		userid = session.get("userid")+"";
							
		
		if(session.get("userid")==null)
			return "initial_entry";
		
		System.out.println("header list size:"+headerlist.size());
		
					
		return "success";
	}

}



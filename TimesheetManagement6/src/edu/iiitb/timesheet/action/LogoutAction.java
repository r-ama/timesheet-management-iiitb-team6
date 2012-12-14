package edu.iiitb.timesheet.action;


import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class LogoutAction extends ActionSupport {
	
	public String execute()
	{
		Map session = (Map) ActionContext.getContext().get("session");
		if(session.get("userid")==null)
			return "initial_entry";
		
		session.remove("userid");
		session.remove("username");
		session.remove("curdate");
		
		return "success";
	}

}

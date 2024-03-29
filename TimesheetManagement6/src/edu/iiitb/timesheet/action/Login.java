package edu.iiitb.timesheet.action;

/*  
 * url: http://  /MyTest/test1

 * 
 * Welcome, dear Student
 *      Multiplication Quiz
 *      What is the product of 5 X 7?
 *      Answer:  ____
 *      <Submit button>
 *      Correct!
 *      Incorrect!
 *  
 */

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Map;

import com.mast.util.MyLog;
import com.mast.util.DB;

// /**
// * Servlet implementation class Login
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class Login extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String commandButton = "";

	public String getCommandButton() {
		return commandButton;
	}

	public void setCommandButton(String commandButton) {
		this.commandButton = commandButton;
	}

	private String username = "";
	private String password = "";

	public String execute() {
		Map session = (Map) ActionContext.getContext().get("session");
		session.put("err_date", "false");
		session.put("err_proj", "false");
		session.put("date_null", "false");
		session.put("client_null", "false");
		session.put("err_effort", "false");
		session.put("err_client", "false");
		session.put("err_clientpass", "false");
		session.put("err_timesheetentry", "false");
		
		System.out.println("inside login");
		MyLog.log("in Login.execute() with commandButton = "
				+ this.commandButton);
		System.out.println("in Login.execute() with commandButton = "
				+ this.commandButton);
		if (this.username.isEmpty()) {
			// first time screen
			return "initial_entry";
		}
		String query = "select empId,logCount from employee where empName = '"
				+ username + "' and empPassword='" + password + "'";
		System.out.println("query is:" + query);
		ResultSet rs = DB.readFromBmtcDB(query);
		try {
			if (rs.next()) {
				// Map session = (Map)
				// ActionContext.getContext().get("session");
				if(rs.getInt(2)==0)
				{
					
					
					session.put("userid", rs.getInt(1) + "");
					session.put("username", username);
					session.put("clientFlag", "N");
				return "passwordentry";
					
				}
				else
				{	
				    	
				    	String updateQuery = "update employee set logCount="+(rs.getInt(2)+1);
				    	updateQuery+=" where empId="+rs.getInt(1);
				    	
				    	int rows=DB.update(updateQuery);
				    	
					
					session.put("userid", rs.getInt(1) + "");
					session.put("username", username);
					session.put("clientFlag", "N");
				return "success";
				}
			}

			query = "select clientId from clientmst where clientName = '"
					+ username + "' and clientPassword='" + password + "'";
			rs = DB.readFromBmtcDB(query);
			if (rs.next()) {
				// Map session = (Map)
				// ActionContext.getContext().get("session");
				session.put("userid", rs.getInt(1) + "");
				session.put("username", username + "(Client)");
				session.put("clientFlag", "Y");
				return "success";
			}

			addActionError(getText("Incorrect user id and / or password!"));
			return "error";
		} catch (Exception ex) {
			ex.printStackTrace();
			return "error";
		}
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}

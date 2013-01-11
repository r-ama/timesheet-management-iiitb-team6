package edu.iiitb.timesheet.action;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Map;

import com.mast.util.DB;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class PasswordSaveAction extends ActionSupport {

	private static final String NULL = null;
	String oldpassword;
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

	String newpassword;
	String confirmpassword;
	ArrayList<String> headerlist;
	String username;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	String userid = "";

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String execute() {
		Map session = (Map) ActionContext.getContext().get("session");
		if (session.get("userid") == null)
			return "initial_entry";

		username = session.get("username") + "";
		userid = session.get("userid") + "";
		headerlist = new ArrayList<String>();
		System.out.println("Password save action:"+oldpassword+" "+newpassword);
		
		if (oldpassword.equals("")) {
			addActionError(getText("Please Enter Old Password first "));
			headerlist.add("Old Password ");
			headerlist.add("New Password ");
			headerlist.add("Confirm Password ");
			return "error";
		}

		if (newpassword.equals("")) {
			addActionError(getText("New Password Cannot be Empty! Please Enter"));
			headerlist.add("Old Password ");
			headerlist.add("New Password ");
			headerlist.add("Confirm Password ");
			return "error";
		}

		if (newpassword.equals("")) {
			addActionError(getText("Confirm Password and New Password should be same! Please Re-Enter"));
			headerlist.add("Old Password ");
			headerlist.add("New Password ");
			headerlist.add("Confirm Password ");
			return "error";
		}
			

		headerlist.add("Old Password ");
		headerlist.add("New Password ");
		headerlist.add("Confirm Password ");

		try {
			String insertQuery = "";

			insertQuery = "update employee set empPassword='"+newpassword+"'"+",logcount=1 where empId ='"+userid+"'";


			System.out.println("Update query:" + insertQuery);
			int rows = DB.update(insertQuery);
			System.out.println("rows:" + rows);
		
			
		} catch (Exception ex) {
			ex.printStackTrace();
			return "error";
		}

		return "success";

	}

	public ArrayList<String> getHeaderlist() {
		return headerlist;
	}

	public void setHeaderlist(ArrayList<String> headerlist) {
		this.headerlist = headerlist;
	}

}

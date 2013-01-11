package edu.iiitb.timesheet.action;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Map;

import com.mast.util.DB;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import edu.iiitb.timesheet.model.WorkListBean;

public class WorklistAction extends ActionSupport {
	
	String userid;
	String username;
	ArrayList<WorkListBean> worklist=new ArrayList<WorkListBean>();
	String action;
	
	

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public ArrayList<WorkListBean> getWorklist() {
		return worklist;
	}

	public void setWorklist(ArrayList<WorkListBean> worklist) {
		this.worklist = worklist;
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
		if(session.get("userid")==null)
			return "initial_entry";
		String clientFlag="";
		String query="";
			username = session.get("username")+"";
			userid = session.get("userid")+"";
		
		query = "select text,action from worklist where userid="+userid;
		
		ResultSet rs = DB.readFromBmtcDB(query);
		try
		{
		while(rs.next())
		{
			worklist.add(new WorkListBean(rs.getString(1),rs.getString(2)));
			
			
		}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			
		}
		return "success";
		
	}


}

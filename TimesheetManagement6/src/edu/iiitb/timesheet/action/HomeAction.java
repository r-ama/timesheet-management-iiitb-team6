package edu.iiitb.timesheet.action;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Map;
import edu.iiitb.timesheet.model.*;

import com.mast.util.DB;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class HomeAction extends ActionSupport {
	
	private String username;
	private String userid;
	private ArrayList<UserAllocation> userinfo;
	
	public ArrayList<UserAllocation> getUserinfo() {
		return userinfo;
	}

	public void setUserinfo(ArrayList<UserAllocation> userinfo) {
		this.userinfo = userinfo;
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
		
		else
		{
			username = session.get("username")+"";
			userid = session.get("userid")+"";
			String query ="select u.empId,u.empName,p.projectid,p.projectname,a.allocation_start_date,a.allocation_end_date,r.rolename from employee u,project p,allocation a,rolemst r where u.empId ="+userid;
            query+=" and u.empId = a.empId";
            query+=" AND p.projectid = a.projectid";
            query+=" and a.roleid = r.roleid";
            query+=" and a.allocation_end_date >SYSDATE()";
            System.out.println(query);
            ResultSet rs= DB.readFromBmtcDB(query);
            try
            {
            	userinfo = new ArrayList<UserAllocation>();	
            while(rs.next())
            {
            	
            	UserAllocation ua=new UserAllocation();
            	ua.setUserid(rs.getString(1));
            	ua.setUsername(rs.getString(2));
            	ua.setProjectid(rs.getString(3));
            	ua.setProjectname(rs.getString(4));
            	ua.setStartDate(rs.getDate(5)+"");
            	ua.setEndDate(rs.getDate(6)+"");
            	ua.setRole(rs.getString(7));
            	userinfo.add(ua);
            	
            	
            }
            }
            catch(Exception ex)
            {
            	return "error";
            	
            }
            
            return "success";
			
		}
			
		
		
	}

}

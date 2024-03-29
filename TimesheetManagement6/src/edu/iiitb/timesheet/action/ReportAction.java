package edu.iiitb.timesheet.action;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Map;

import com.mast.util.DB;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ReportAction extends ActionSupport {
	
	String userid;
	String username;
	ArrayList<ReportBean> reportList=new ArrayList<ReportBean>();
	
	public ArrayList<ReportBean> getReportList() {
		return reportList;
	}

	public void setReportList(ArrayList<ReportBean> reportList) {
		this.reportList = reportList;
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
		String roleId = session.get("roleId")+"";	
		
		if (session.get("clientFlag") != null)
			clientFlag = session.get("clientFlag") + "";
		
		if(clientFlag.equals("N"))
		{	
			query="select r1.reportId,r1.ReportName from Report r1,rolemst r";
			query+=" where r1.Role = r.roleFlag";
			query+=" and r.roleId = "+roleId;
		}	
		
		else
		{
			query="select r1.reportId,r1.ReportName from Report r1";
			query+=" where r1.Role = 'Client'";
			
			
		}	
		
		ResultSet rs= DB.readFromBmtcDB(query);
		try
		{
		while(rs.next())
		{	
		
		reportList.add(new ReportBean(rs.getString(1),rs.getString(2)));
		
		
		}
		}
		catch(Exception ex)
		{
			
			return "initial_entry";
		}
		
		return "success";
		
	}

}

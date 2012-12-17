package edu.iiitb.timesheet.action;

import java.util.ArrayList;
import java.util.Map;

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
		
		
			username = session.get("username")+"";
			userid = session.get("userid")+"";
			
		reportList.add(new ReportBean("1","Effort related report"));
		reportList.add(new ReportBean("2","Active team members report"));
		reportList.add(new ReportBean("3","Project performance report"));
		
		
		
		return "success";
		
	}

}

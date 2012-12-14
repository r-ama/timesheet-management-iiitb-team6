package edu.iiitb.timesheet.action;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Map;

import com.mast.util.DB;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import edu.iiitb.timesheet.model.TimesheetEntryBean;

public class TimesheetSaveAction extends ActionSupport {
	
	ArrayList<TimesheetEntryBean> timesheet=new ArrayList<TimesheetEntryBean>();
	String userid="";
	String projectid="";
	String projectname="";

	public String getProjectid() {
		return projectid;
	}

	public void setProjectid(String projectid) {
		this.projectid = projectid;
	}

	public String getProjectname() {
		return projectname;
	}

	public void setProjectname(String projectname) {
		this.projectname = projectname;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public ArrayList<TimesheetEntryBean> getTimesheet() {
		return timesheet;
	}

	public void setTimesheet(ArrayList<TimesheetEntryBean> timesheet) {
		this.timesheet = timesheet;
	}
	
	public String execute()
	{
		System.out.println("size:"+timesheet.size());
		for(int i=0;i<timesheet.size();i++)
		{
			Map session = (Map) ActionContext.getContext().get("session");
			if(session.get("userid")==null)
				return "initial_entry";
			
			userid = session.get("userid")+"";
		    projectid = timesheet.get(i).getProjectid();
		    projectname = timesheet.get(i).getProjectName();
			ArrayList<String> day=timesheet.get(i).getDay();
			System.out.println(day.size()+" "+projectid+" "+day.get(0));
			ArrayList<String> date=timesheet.get(i).getDate();
			try
			{
			for(int count=0;count<day.size();count++)
			{
				String query = "select 1 from timesheetentry t where t.empId ="+userid;
				query+=" and t.projectid ="+projectid;
				query+=" and t.date_of_entry ='"+date.get(count)+"'";
				
				ResultSet rs=DB.readFromBmtcDB(query);
				
				String updateQuery="";
				
				if(rs.next())
				{
					updateQuery+="update timesheetentry t set t.hours_worked='"+day.get(count)+"'";
					updateQuery+=" where t.empId ="+userid;
					updateQuery+=" and t.projectid ="+projectid;
					updateQuery+=" and t.date_of_entry ='"+date.get(count)+"'";
					
				}
				
				else
				{
					updateQuery+="insert into timesheetentry(date_of_entry,empId,projectid,hours_worked) values('"+date.get(count)+"',"+userid+","+projectid+",'"+day.get(count)+"')";
					
				}
				
				System.out.println("update query:"+updateQuery);
				int rows=DB.update(updateQuery);
				System.out.println("rows:"+rows);
				
			}
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				 return "error";
			}
			
			
			
		}
		if(timesheet.size()>1)
		{	
			projectid="";
			projectname="";
		}	
		
		return "success";
		
	}

}

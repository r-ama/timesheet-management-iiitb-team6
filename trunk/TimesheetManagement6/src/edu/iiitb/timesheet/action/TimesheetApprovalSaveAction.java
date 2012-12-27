package edu.iiitb.timesheet.action;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Map;

import com.mast.util.DB;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import edu.iiitb.timesheet.model.TimesheetEntryBean;

public class TimesheetApprovalSaveAction extends ActionSupport {
	
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
			
			userid = timesheet.get(i).getUserid();
		    projectid = timesheet.get(i).getProjectid();
		    projectname = timesheet.get(i).getProjectName();
			ArrayList<Boolean> approveFlag= timesheet.get(i).getApprovalFlag();
			System.out.println("flag size:"+i+" "+approveFlag.size());
			
			ArrayList<String> date=timesheet.get(i).getDate();
			System.out.println(date.size());
			try
			{
			for(int count=0;count<date.size();count++)
			{
				String query = "select 1 from timesheetentry t where t.empId ="+userid;
				query+=" and t.projectid ="+projectid;
				query+=" and t.date_of_entry ='"+date.get(count)+"'";
				
				ResultSet rs=DB.readFromBmtcDB(query);
				
				String updateQuery="";
				
				if(rs.next())
				{
					
					String apFlag = "";
					if(approveFlag.get(0))
						apFlag = "Y";
					else
						apFlag ="N"; 
					
					if(apFlag.equals("Y"))
					{	
					updateQuery+="update timesheetentry t set t.approval_flag='"+apFlag+"'";
					updateQuery+=" where t.empId ="+userid;
					updateQuery+=" and t.projectid ="+projectid;
					updateQuery+=" and t.date_of_entry ='"+date.get(count)+"'";
					
				
					
				
				
				System.out.println("update query:"+updateQuery);
				int rows=DB.update(updateQuery);
				System.out.println("rows:"+rows);
				}
				}
			}
			String actual_start_date="",actual_effort="";
			
			String query1 = "select te.date_of_entry from  timesheetentry te";
			query1+=" where te.projectId = " + projectid;
			query1+=" and te.approval_flag='Y'";
			query1+=" order by te.date_of_entry";
			
			ResultSet rs=DB.readFromBmtcDB(query1);
			
			if(rs.next())
			{
				actual_start_date = rs.getString(1);
				
			}
			
			query1="select sum(te.hours_worked) from  timesheetentry te";
			query1+="	where te.projectId ="+projectid;
			query1+=" and te.approval_flag='Y'";
			
			rs=DB.readFromBmtcDB(query1);
			
			if(rs.next())
			{
				actual_effort = rs.getString(1);
				
			}
			
			String updateQuery = "update project p set p.actual_start_date='"+actual_start_date+"',";
			updateQuery += "p.actual_effort="+actual_effort;
			updateQuery += " where p.projectId="+projectid;
			
			int rows=DB.update(updateQuery);
			System.out.println("Update query:"+updateQuery);
			System.out.println(rows);
			
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

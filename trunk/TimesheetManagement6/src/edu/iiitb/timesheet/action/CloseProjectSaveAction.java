package edu.iiitb.timesheet.action;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Map;

import com.mast.util.DB;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import edu.iiitb.timesheet.model.ProjectBean;

public class CloseProjectSaveAction extends ActionSupport
{
	ArrayList<ProjectBean> projectList;
	public ArrayList<ProjectBean> getProjectList() {
		return projectList;
	}
	public void setProjectList(ArrayList<ProjectBean> projectList) {
		this.projectList = projectList;
	}
	public String execute()
	{
		 	
		
		for(int i=0;i<projectList.size();i++)
		{
			ProjectBean projectBean = projectList.get(i);
			String actual_end_date="",actual_effor=""; 
			if(projectBean.isCheck())
			{
				String query="select max(te.date_of_entry )from timesheetentry te";
				query+=" where te.projectId = "+projectBean.getProjectid();
				
				
				
				
				ResultSet rs= DB.readFromBmtcDB(query);
				
				try
				{
					if(rs.next())
					{
						actual_end_date = rs.getDate(1)+"";	  
					    
					}
					
					
					query="select sum(te.hours_worked) from  timesheetentry te";
					query+="	where te.projectId ="+projectBean.getProjectid();
					query+=" and te.approval_flag='Y'";
					
					rs= DB.readFromBmtcDB(query);
					
					if(rs.next())
					{
						actual_effor = rs.getString(1);
						
					}
					
					System.out.println("Actual end date:"+actual_end_date);
					
					String updateQuery = "update project set actual_end_date='"+actual_end_date+"'";
					updateQuery+=",actual_effort="+actual_effor;
					updateQuery+=" where projectId="+projectBean.getProjectid();
					int rows=DB.update(updateQuery);
					
					query="select p.clientId  from project p where p.projectId = "+projectBean.getProjectid();
					rs = DB.readFromBmtcDB(query);
					
					if(rs.next())
					{
						updateQuery="insert into worklist(userid,text,action) values("+rs.getInt(1)+",'Project closed please check:"+projectBean.getProjectid()+",check Reports','report.action')";
						System.out.println("Update Query:"+updateQuery);
						DB.update(updateQuery);
						
					}
					
					
					
					
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
					
				}
				
			}
			
			
		}
		
		return "success";
		
	}
	
}


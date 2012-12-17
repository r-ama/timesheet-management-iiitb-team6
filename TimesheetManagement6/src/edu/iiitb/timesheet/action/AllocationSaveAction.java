package edu.iiitb.timesheet.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.mast.util.DB;
import com.opensymphony.xwork2.ActionSupport;

import edu.iiitb.timesheet.model.ProjectAllocationBean;

public class AllocationSaveAction extends ActionSupport {
	
	ArrayList<ProjectAllocationBean> allocList =new ArrayList<ProjectAllocationBean>();

	public ArrayList<ProjectAllocationBean> getAllocList() {
		return allocList;
	}

	public void setAllocList(ArrayList<ProjectAllocationBean> allocList) {
		this.allocList = allocList;
	}

	public String execute()
	{
		System.out.println("alloc size:"+allocList.size());
		SimpleDateFormat format1 = new SimpleDateFormat("dd-MMM-yyyy");
		SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
		
		for(int count=0;count<allocList.size();count++)
		{
			ProjectAllocationBean projectAllocationBean=allocList.get(count);
			String updateQuery="";
			String startDate="";
			String endDate="";
			System.out.println(projectAllocationBean.getAllocationId()+" "+projectAllocationBean.isCheckId());
			if(projectAllocationBean.isCheckId())
			{
				
				try
				{
					Date date=format1.parse(projectAllocationBean.getAllocationStartDate());
					startDate = format2.format(date);
					
				  date=format1.parse(projectAllocationBean.getAllocationEndDate());
					endDate = format2.format(date);
					
					System.out.println("enddate:"+endDate+"startDate:"+startDate);
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
					
				}
				if(projectAllocationBean.getAllocationId().equals("0"))
				{
					updateQuery = "insert into allocation(empId,projectId,allocation_start_date,allocation_end_date,roleId,superVisorId) values("+projectAllocationBean.getUserid()+","+projectAllocationBean.getProjectid()+",'"+startDate+"','"+endDate+"',"+projectAllocationBean.getRoleid()+","+projectAllocationBean.getSupervisorId()+")";
					
				}
				
				else
				{
					updateQuery = "update allocation set projectId ="+ projectAllocationBean.getProjectid()+",";
					updateQuery += "allocation_start_date ='"+startDate+"',";
					updateQuery += "allocation_end_date = '"+endDate+"',";
					updateQuery += "roleId="+projectAllocationBean.getRoleid()+",";
					updateQuery += "supervisorId="+projectAllocationBean.getSupervisorId();
					updateQuery +=" where allocationId="+projectAllocationBean.getAllocationId();
					
					
					
				}
				System.out.println("update query:"+updateQuery);
				
				int rows =  DB.update(updateQuery);
				 
				
				
				
			}
			
		}
		
		
		return "success";
		
	}
	
	
}

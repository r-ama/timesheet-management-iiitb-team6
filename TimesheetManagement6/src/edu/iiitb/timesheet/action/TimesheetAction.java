package edu.iiitb.timesheet.action;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import com.mast.util.DB;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import edu.iiitb.timesheet.model.TimesheetEntryBean;

public class TimesheetAction extends ActionSupport {
	
	String projectid="";
	String projectname="";
	ArrayList<String> headerlist;
	ArrayList<TimesheetEntryBean> timesheet;
	String username;
	String userid;
	String commandButton="";
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getCommandButton() {
		return commandButton;
	}
	public void setCommandButton(String commandButton) {
		this.commandButton = commandButton;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public ArrayList<TimesheetEntryBean> getTimesheet() {
		return timesheet;
	}
	public void setTimesheet(ArrayList<TimesheetEntryBean> timesheet) {
		this.timesheet = timesheet;
	}
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
	public ArrayList<String> getHeaderlist() {
		return headerlist;
	}
	public void setHeaderlist(ArrayList<String> headerlist) {
		this.headerlist = headerlist;
	}
	public String execute()
	{
	   
	
		
		headerlist=new ArrayList<String>();
	   	
	   	Map session = (Map) ActionContext.getContext().get("session");
	   	
	   	if(session.get("userid")==null)
			return "initial_entry";
		
	   	
	   	if(session.get("err_timesheetentry").equals("true"))
	   	{
	   	addActionError(getText("Wrong Timesheet Entry! Please Re-Enter"));
	   	session.put("err_timesheetentry", "false");
	   	}
		
			username = session.get("username")+"";
			userid = session.get("userid")+"";
			
	
			
			
	   	Date refDate;
	   	if(session.get("curdate")==null)
	   	{
	   		refDate=new Date();
	   	}
	   	else
	   	{
	   		refDate=(Date)session.get("curdate");
	   		
	   	}
	   	
	   	Calendar cal = Calendar.getInstance();  
	   	cal.setTime(refDate);
	   	System.out.println("button:"+commandButton);
	   	if(this.commandButton.equals("Prev"))
	   	{	
	   		cal.add(Calendar.DATE, -7); // add 10 days
	   		this.commandButton="";
	   	}
	   	else if(this.commandButton.equals("Next"))
	   	{
	   		Calendar now=Calendar.getInstance();
	   		cal.add(Calendar.DATE, 7); // add 10 days
	   		if(cal.after(now))
	   			cal=now;
	   		this.commandButton="";
	   	}
	   	  
	   	refDate = cal.getTime(); 
	   	
	   	session.put("curdate", refDate);
		
			
		try
		{
			 timesheet = new ArrayList<TimesheetEntryBean>();
			 ArrayList<String> projectidlist=new ArrayList<String>();
			 ArrayList<String> projectnamelist=new ArrayList<String>();
	    if(projectid==null || projectid.equals(""))
	    {
	    	    
	    	 Date[] day1 = getDaysOfWeek(refDate, Calendar.MONDAY);
	    	 SimpleDateFormat sdf1=new SimpleDateFormat("yyyy-MM-dd");
	    	 System.out.println("last sunday:"+sdf1.format(day1[6]));
	    	
	    	
	    	    String query = "select distinct p.projectid,p.projectname from project p,employee u,allocation a";
	    		query+=" where p.projectid = a.projectid";
	    		query+=" and a.empId ="+userid;
	    		query+=" and a.allocation_end_date >=NOW()";
	    		query+=" and a.allocation_start_date <='"+sdf1.format(day1[6])+"'";
	    		
	    	ResultSet rs= DB.readFromBmtcDB(query);
	    	
	    	while(rs.next())
	    	{
	    		projectidlist.add(rs.getString(1));
	    		projectnamelist.add(rs.getString(2));
	    		
	    		
	    	}
	    	
	    }
	    
	    else
	    {
	    	
	    	 Date[] day1 = getDaysOfWeek(refDate, Calendar.MONDAY);
	    	 SimpleDateFormat sdf1=new SimpleDateFormat("yyyy-MM-dd");
	    	 System.out.println("last sunday:"+sdf1.format(day1[6]));
	    	
	    	
	    	    String query = "select distinct p.projectid,p.projectname from project p,employee u,allocation a";
	    		query+=" where p.projectid = a.projectid";
	    		query+=" and p.projectid="+projectid;
	    		query+=" and a.empId ="+userid;
	    		query+=" and a.allocation_end_date >=NOW()";
	    		query+=" and a.allocation_start_date <='"+sdf1.format(day1[6])+"'";
	    		
	    	ResultSet rs= DB.readFromBmtcDB(query);
	    	
	    	while(rs.next())
	    	{
	    		projectidlist.add(rs.getString(1));
	    		projectnamelist.add(rs.getString(2));
	    		
	    		
	    	}
	    }
		if(projectidlist.size()>0)
		{
			headerlist.add("Project Id");
		   	headerlist.add("Project Name");
		}
	    
	    for(int i=0;i<projectidlist.size();i++)
	    {	
	    
		
	    
	    
	    TimesheetEntryBean tm=new TimesheetEntryBean();
	    tm.setProjectid(projectidlist.get(i));
	    tm.setProjectName(projectnamelist.get(i));
	    
        Date[] days = getDaysOfWeek(refDate, Calendar.MONDAY);
        SimpleDateFormat sdf=new SimpleDateFormat("E MMM dd yyyy");
        SimpleDateFormat sdf1=new SimpleDateFormat("yyyy-MM-dd");
        int sum=0;
        for (Date day : days) {
        	if(i==0)
        		headerlist.add(sdf.format(day));
            System.out.println("date:"+sdf1.format(day));
            String query = "select hours_worked,approval_flag from timesheetentry ty where ty.empId ="+userid;
    	    query+=" and ty.projectid ="+projectidlist.get(i);
            query+= " and ty.date_of_entry='"+sdf1.format(day)+"'";
            tm.date.add(sdf1.format(day));
            ResultSet rs=DB.readFromBmtcDB(query);
           
            if(rs.next())
            {	
            	tm.day.add(rs.getString(1));
            	if(rs.getString(2).equals("Y"))
            		tm.approvalFlag.add(true);
            	else
            		tm.approvalFlag.add(false);
            	 sum+=Integer.parseInt(rs.getString(1));
            }	
            else
            {
            	tm.day.add("0");
            	tm.approvalFlag.add(false);
            }	
        }
        
        tm.setTotal(sum+"");
        if(i==0)
        	headerlist.add("Total");
        
        
        timesheet.add(tm);
	    }
        System.out.println("timesheet size:"+timesheet.size());
        return "success";
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return "error";
			
		}
	   	
	   	
		
	}
	
	
	private Date[] getDaysOfWeek(Date refDate, int firstDayOfWeek) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(refDate);
        calendar.set(Calendar.DAY_OF_WEEK, firstDayOfWeek);
        Date[] daysOfWeek = new Date[7];
        for (int i = 0; i < 7; i++) {
            daysOfWeek[i] = calendar.getTime();
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }
        return daysOfWeek;
    }

}

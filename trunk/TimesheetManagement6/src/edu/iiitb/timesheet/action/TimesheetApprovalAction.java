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

public class TimesheetApprovalAction extends ActionSupport {

	ArrayList<String> headerlist;
	ArrayList<TimesheetEntryBean> timesheet;
	String username;
	String userid;
	String commandButton = "";

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

	public ArrayList<String> getHeaderlist() {
		return headerlist;
	}

	public void setHeaderlist(ArrayList<String> headerlist) {
		this.headerlist = headerlist;
	}

	public String execute() {

		headerlist = new ArrayList<String>();
		headerlist.add("Employee Name");
		headerlist.add("Projectid");
		headerlist.add("ProjectName");
		Map session = (Map) ActionContext.getContext().get("session");
		Date refDate;
		if (session.get("curdate") == null) {
			refDate = new Date();
		} else {
			refDate = (Date) session.get("curdate");

		}

		Calendar cal = Calendar.getInstance();
		cal.setTime(refDate);
		System.out.println("button:" + commandButton);
		if (this.commandButton.equals("Prev")) {
			cal.add(Calendar.DATE, -7); // add 10 days
			this.commandButton = "";
		} else if (this.commandButton.equals("Next")) {
			Calendar now = Calendar.getInstance();
			cal.add(Calendar.DATE, 7); // add 10 days
			if (cal.after(now))
				cal = now;
			this.commandButton = "";
		}

		refDate = cal.getTime();

		session.put("curdate", refDate);
		if (session.get("userid") == null)
			return "initial_entry";

		username = session.get("username") + "";
		userid = session.get("userid") + "";
		
		Date[] days = getDaysOfWeek(refDate, Calendar.MONDAY);
		SimpleDateFormat sdf = new SimpleDateFormat("E MMM dd yyyy");
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd"); 

		try {
			timesheet = new ArrayList<TimesheetEntryBean>();
			ArrayList<String> userList = new ArrayList<String>();
			ArrayList<String> usernameList = new ArrayList<String>();
			String query = "select distinct u.empId,u.empName from employee u,allocation a where u.empId=a.empId";
			query += " and a.supervisorId= " + userid;
			ResultSet rs = DB.readFromBmtcDB(query);
			while (rs.next()) {
				userList.add(rs.getString(1));
				usernameList.add(rs.getString(2));
			}
			

			for (int j = 0; j < userList.size(); j++) {
				ArrayList<String> projectidlist = new ArrayList<String>();
				ArrayList<String> projectnamelist = new ArrayList<String>();
				query = "select distinct p.projectid,p.projectname from project p,employee u,allocation a";
				query += " where p.projectid = a.projectid";
				query += " and a.empId =" + userList.get(j);
				query += " and a.supervisorId=" + userid;
				query += " and a.allocation_end_date >NOW()";

				rs = DB.readFromBmtcDB(query);

				while (rs.next()) {
					projectidlist.add(rs.getString(1));
					projectnamelist.add(rs.getString(2));

				}

				for (int i = 0; i < projectidlist.size(); i++) {

					TimesheetEntryBean tm = new TimesheetEntryBean();
					tm.setProjectid(projectidlist.get(i));
					tm.setProjectName(projectnamelist.get(i));
					tm.setUserid(userList.get(j));
					tm.setUsername(usernameList.get(j));

					
					int sum = 0;
					for (Date day : days) {
						
						System.out.println("date:" + sdf1.format(day));
						query = "select hours_worked,approval_flag from timesheetentry ty where ty.empId ="
								+ userList.get(j);
						query += " and ty.projectid =" + projectidlist.get(i);
						query += " and ty.date_of_entry='" + sdf1.format(day)
								+ "'";
						tm.date.add(sdf1.format(day));
						rs = DB.readFromBmtcDB(query);

						if (rs.next()) {
							tm.day.add(rs.getString(1));
							if (rs.getString(2).equals("Y"))
								tm.approvalFlag.add(true);
							else
								tm.approvalFlag.add(false);
							sum += Integer.parseInt(rs.getString(1));
						} else {
							tm.day.add("0");
							tm.approvalFlag.add(false);
						}
					}

					tm.setTotal(sum + "");
				

                   	   	

					timesheet.add(tm);
				}
			}
			System.out.println("timesheet size:" + timesheet.size());
			
			for(Date day:days)
			{
				headerlist.add(sdf.format(day));
				
			}
			
			headerlist.add("Total");
			headerlist.add("Approve");
			
			if(timesheet.size()==0)
			{
				System.out.println("returning no timesheet");
				return "noTimesheet";
			}
			else
			{
				return "success";				
			}
			
		} catch (Exception ex) {
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

package edu.iiitb.timesheet.action;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Map;

import com.mast.util.DB;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import edu.iiitb.timesheet.model.TimesheetEntryBean;

public class TimesheetSaveAction extends ActionSupport {

	ArrayList<TimesheetEntryBean> timesheet = new ArrayList<TimesheetEntryBean>();
	String userid = "";
	String projectid = "";
	String projectname = "";
	String username = "";

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public String execute() {
		System.out.println("size:" + timesheet.size());
		for (int i = 0; i < timesheet.size(); i++) {
			Map session = (Map) ActionContext.getContext().get("session");
			if (session.get("userid") == null)
				return "initial_entry";

			userid = session.get("userid") + "";
			username = session.get("username") + "";
			projectid = timesheet.get(i).getProjectid();
			projectname = timesheet.get(i).getProjectName();
			ArrayList<String> day = timesheet.get(i).getDay();
			boolean updateFlag = false;
			ArrayList<String> date = timesheet.get(i).getDate();
			try {

				for (int k = 0; k < day.size(); k++) {
					int temp = Integer.parseInt(day.get(k));
					if (temp > 24 || temp < 0) {
						session.put("err_timesheetentry", "true");
						return "success";
					}
				}

				for (int count = 0; count < date.size(); count++) {
					String query = "select 1 from timesheetentry t where t.empId ="
							+ userid;
					query += " and t.projectid =" + projectid;
					query += " and t.date_of_entry ='" + date.get(count) + "'";

					ResultSet rs = DB.readFromBmtcDB(query);

					String updateQuery = "";

					if (rs.next()) {
						updateQuery += "update timesheetentry t set t.hours_worked='"
								+ day.get(count) + "'";
						updateQuery += " where t.empId =" + userid;
						updateQuery += " and t.projectid =" + projectid;
						updateQuery += " and t.date_of_entry ='"
								+ date.get(count) + "'";

					}

					else {
						updateQuery += "insert into timesheetentry(date_of_entry,empId,projectid,hours_worked) values('"
								+ date.get(count)
								+ "',"
								+ userid
								+ ","
								+ projectid + ",'" + day.get(count) + "')";
						updateFlag = true;
					}

					System.out.println("update query:" + updateQuery);
					int rows = DB.update(updateQuery);
					System.out.println("rows:" + rows);

				}

				if (updateFlag) {

					String query = "select a.supervisorId from  allocation a";
					query += " where a.empId =" + userid;
					query += " and a.projectId = " + projectid;

					ResultSet rs = DB.readFromBmtcDB(query);

					if (rs.next()) {
						String supId = rs.getString(1);
						String updateQuery = "insert into worklist(userid,empId,text,action) values("
								+ supId+","+userid
								+ ",'Time sheet approval pending for employee:"
								+ username + "','timesheetapprove.action')";
						System.out.println("Update Query:" + updateQuery);
						int rows = DB.update(updateQuery);

					}
				}

			} catch (Exception ex) {
				ex.printStackTrace();
				return "error";
			}

		}
		if (timesheet.size() > 1) {
			projectid = "";
			projectname = "";
		}

		return "success";

	}

}

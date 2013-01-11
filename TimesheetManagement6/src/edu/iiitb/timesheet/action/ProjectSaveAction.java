package edu.iiitb.timesheet.action;

import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import com.mast.util.DB;
import com.mast.util.EmailValidator;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import edu.iiitb.timesheet.model.ProjectBean;
import edu.iiitb.timesheet.model.TimesheetEntryBean;

public class ProjectSaveAction extends ActionSupport {

	String projectname;
	String planned_start_date;
	String planned_end_date;
	String planned_effort;
	String clientid;
	String username;
	ArrayList<ProjectBean> projectList;
	ArrayList<String> headerlist;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getClientid() {
		return clientid;
	}

	public void setClientid(String clientid) {
		this.clientid = clientid;
	}

	public String getProjectname() {
		return projectname;
	}

	public void setProjectname(String projectname) {
		this.projectname = projectname;
	}

	public String getPlanned_start_date() {
		return planned_start_date;
	}

	public void setPlanned_start_date(String planned_start_date) {
		this.planned_start_date = planned_start_date;
	}

	public String getPlanned_end_date() {
		return planned_end_date;
	}

	public void setPlanned_end_date(String planned_end_date) {
		this.planned_end_date = planned_end_date;
	}

	public String getPlanned_effort() {
		return planned_effort;
	}

	public void setPlanned_effort(String planned_effort) {
		this.planned_effort = planned_effort;
	}

	String userid = "";

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String execute() {
		Map session = (Map) ActionContext.getContext().get("session");
		if (session.get("userid") == null)
			return "initial_entry";
		username = session.get("username") + "";
		headerlist = new ArrayList<String>();

		if (projectname.equals("")) {

			session.put("err_proj", "true");
			return "error";
		}

		SimpleDateFormat format1 = new SimpleDateFormat("dd-MMM-yyyy");
		SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
		String startDate = "";
		String endDate = "";
		Date sd = null;
		Date ed = null;

		if (planned_start_date.equals("") || planned_end_date.equals("")) {
			session.put("date_null", "true");
			return "error";
		}

		else {

			try {
				sd = format1.parse(planned_start_date);
				startDate = format2.format(sd);

				ed = format1.parse(planned_end_date);
				endDate = format2.format(ed);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// if(ed.equals("")||sd.equals(""))
			// {
			// session.put("date_null", "true");
			// return "error";
			// }

			if (ed.before(sd)) {
				// addActionError(getText("End Date cannot be earlier than Start Date."));
				session.put("err_date", "true");				
				return "error";
			}
		}
		
		if (planned_effort.equals("")) {
			planned_effort = "0";
		}
			
		EmailValidator ev = new EmailValidator();
		if (!(ev.validatenumber(planned_effort))) {
			session.put("err_effort", "true");
			return "error";
		}
		
		if(clientid.equals("0"))
		{
			session.put("client_null", "true");				
			return "error";
		}

		headerlist.add("Project ID");
		headerlist.add("Project Name ");
		headerlist.add("Planned Start Date ");
		headerlist.add("Planned End Date ");
		headerlist.add("Actual Start Date ");
		headerlist.add("Actual End Date ");
		headerlist.add("Planned Effort ");
		headerlist.add("Actual Effort ");
		headerlist.add("Client Name");

		// SimpleDateFormat format1 = new SimpleDateFormat("dd-MMM-yyyy");
		// SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
		//
		// String startDate="";
		// String endDate="";

		userid = session.get("userid") + "";

		try {

			String insertQuery = "";

			// Date date=format1.parse(planned_start_date);
			// startDate = format2.format(date);
			//
			// date=format1.parse(planned_end_date);
			// endDate = format2.format(date);
			System.out.println("client id = " + clientid);
			insertQuery = "insert into project(projectName,planned_start_date,planned_end_date,planned_effort,clientId) values('"
					+ projectname
					+ "','"
					+ startDate
					+ "','"
					+ endDate
					+ "',"
					+ planned_effort + "," + clientid + ")";

			System.out.println("Insert query:" + insertQuery);
			int rows = DB.update(insertQuery);
			System.out.println("rows:" + rows);

			projectList = new ArrayList<ProjectBean>();
			String query = " select p.projectId,p.projectName,p.planned_start_date,p.planned_end_date,p.actual_start_date,p.actual_end_date, p.planned_effort,p.actual_effort, c.clientName"
					+ " from project p,clientMst c"
					+ " where p.clientId = c.clientId ";

			ResultSet rs = DB.readFromBmtcDB(query);			
			while (rs.next()) {
				ProjectBean pb = new ProjectBean();
				pb.setProjectid(rs.getString(1));
				pb.setProjectname(rs.getString(2));
				pb.setPlanned_start_date(rs.getString(3));
				pb.setPlanned_end_date(rs.getString(4));
				pb.setActual_start_date(rs.getString(5));
				pb.setActual_end_date(rs.getString(6));
				pb.setPlanned_effort(rs.getString(7));
				pb.setActual_effort(rs.getString(8));
				pb.setClientname(rs.getString(9));

				projectList.add(pb);

			}

		} catch (Exception ex) {
			ex.printStackTrace();
			return "error";
		}

		return "success";

	}

	public ArrayList<String> getHeaderlist() {
		return headerlist;
	}

	public void setHeaderlist(ArrayList<String> headerlist) {
		this.headerlist = headerlist;
	}

	public ArrayList<ProjectBean> getProjectList() {
		return projectList;
	}

	public void setProjectList(ArrayList<ProjectBean> projectList) {
		this.projectList = projectList;
	}

}

package edu.iiitb.timesheet.model;

public class ProjectBean {

	public ProjectBean(String projectid, String projectname) {
		super();
		this.projectid = projectid;
		this.projectname = projectname;
		
	}
	public ProjectBean() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	String projectid;
	
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
	public String getActual_start_date() {
		return actual_start_date;
	}
	public void setActual_start_date(String actual_start_date) {
		this.actual_start_date = actual_start_date;
	}
	public String getActual_end_date() {
		return actual_end_date;
	}
	public void setActual_end_date(String actual_end_date) {
		this.actual_end_date = actual_end_date;
	}
	public String getPlanned_effort() {
		return planned_effort;
	}
	public void setPlanned_effort(String planned_effort) {
		this.planned_effort = planned_effort;
	}
	public String getActual_effort() {
		return actual_effort;
	}
	public void setActual_effort(String actual_effort) {
		this.actual_effort = actual_effort;
	}
	String projectname;
	String planned_start_date;
	String planned_end_date;
	String actual_start_date;
	String actual_end_date;
	String planned_effort;
	String actual_effort;
	
}

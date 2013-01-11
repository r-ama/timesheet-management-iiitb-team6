package edu.iiitb.timesheet.model;

public class WorkListBean {
	
	public WorkListBean(String text, String action) {
		super();
		this.text = text;
		this.action = action;
	}
	String text;
	String action;
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	} 

}

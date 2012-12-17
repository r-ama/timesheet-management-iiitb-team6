package edu.iiitb.timesheet.action;

public class ReportBean {
	
	
	public ReportBean(String reportId, String reportName) {
		super();
		this.reportId = reportId;
		this.reportName = reportName;
	}
	String reportId;
	public String getReportId() {
		return reportId;
	}
	public void setReportId(String reportId) {
		this.reportId = reportId;
	}
	public String getReportName() {
		return reportName;
	}
	public void setReportName(String reportName) {
		this.reportName = reportName;
	}
	String reportName;

}

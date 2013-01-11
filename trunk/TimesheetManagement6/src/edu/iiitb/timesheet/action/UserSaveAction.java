package edu.iiitb.timesheet.action;

import com.mast.util.EmailValidator;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Map;

import com.mast.util.DB;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import edu.iiitb.timesheet.model.EmployeeBean;

public class UserSaveAction extends ActionSupport {

	private static final String NULL = null;
	String empname;
	String emppassword;
	String emailid;
	String phonenumber;
	ArrayList<EmployeeBean> employeeList;
	ArrayList<String> headerlist;
	String username;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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
		EmailValidator ev = new EmailValidator();

		if (empname.equals("")) {
			addActionError(getText("Employee Name cannot be Empty! Please Enter"));
			headerlist.add("Employee Name ");
			headerlist.add("Password ");
			headerlist.add("Email ID ");
			headerlist.add("Phone Number ");
			return "error";
		}

		if (emppassword.equals("")) {
			addActionError(getText("Employee Password cannot be Empty! Please Enter"));
			headerlist.add("Employee Name ");
			headerlist.add("Password ");
			headerlist.add("Email ID ");
			headerlist.add("Phone Number ");
			return "error";
		}

		if (!(ev.validatephone(phonenumber)) && !(ev.validate(emailid))) {
			addActionError(getText("Incorrect Email Id and Phone Number! Please Re-Enter"));
			headerlist.add("Employee Name ");
			headerlist.add("Password ");
			headerlist.add("Email ID ");
			headerlist.add("Phone Number ");
			phonenumber = null;
			emailid = null;
			return "error";
		}

		if (!(ev.validate(emailid))) {
			addActionError(getText("Incorrect Email Id! Please Re-Enter"));
			headerlist.add("Employee Name ");
			headerlist.add("Password ");
			headerlist.add("Email ID ");
			headerlist.add("Phone Number ");
			emailid = null;
			return "error";
		}

		if (!(ev.validatephone(phonenumber))) {
			addActionError(getText("Incorrect Phone Number! Please Re-Enter"));
			headerlist.add("Employee Name ");
			headerlist.add("Password ");
			headerlist.add("Email ID ");
			headerlist.add("Phone Number ");
			phonenumber = null;
			return "error";
		}

		headerlist.add("Employee ID");
		headerlist.add("Employee Name ");
		headerlist.add("Password ");
		headerlist.add("Email ID ");
		headerlist.add("Phone Number ");

		try {
			String insertQuery = "";

			insertQuery = "insert into employee(empName,empPassword,emailId,phoneNumb) values('"
					+ empname
					+ "','"
					+ emppassword
					+ "','"
					+ emailid
					+ "',"
					+ phonenumber + ")";

			System.out.println("Insert query:" + insertQuery);
			int rows = DB.update(insertQuery);
			System.out.println("rows:" + rows);

			employeeList = new ArrayList<EmployeeBean>();
			String query = " select e.empId,e.empName,e.empPassword,e.emailId,e.phoneNumb"
					+ " from employee e";

			ResultSet rs = DB.readFromBmtcDB(query);

			while (rs.next()) {
				EmployeeBean eb = new EmployeeBean();

				eb.setEmpId(rs.getString(1));
				eb.setEmpName(rs.getString(2));
				eb.setEmpPassword(rs.getString(3));
				eb.setEmailId(rs.getString(4));
				eb.setPhoneNumber(rs.getString(5));

				employeeList.add(eb);

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

	public String getEmpname() {
		return empname;
	}

	public void setEmpname(String empname) {
		this.empname = empname;
	}

	public String getEmppassword() {
		return emppassword;
	}

	public void setEmppassword(String emppassword) {
		this.emppassword = emppassword;
	}

	public String getEmailid() {
		return emailid;
	}

	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	public ArrayList<EmployeeBean> getEmployeeList() {
		return employeeList;
	}

	public void setEmployeeList(ArrayList<EmployeeBean> employeeList) {
		this.employeeList = employeeList;
	}

}

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

import edu.iiitb.timesheet.model.ClientBean;

public class ClientSaveAction extends ActionSupport {

	String clientName;
	String clientPassword;
	String clientLocation;
	
	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getClientPassword() {
		return clientPassword;
	}

	public void setClientPassword(String clientPassword) {
		this.clientPassword = clientPassword;
	}

	public String getClientLocation() {
		return clientLocation;
	}

	public void setClientLocation(String clientLocation) {
		this.clientLocation = clientLocation;
	}

	public ArrayList<ClientBean> getClientList() {
		return clientList;
	}

	public void setClientList(ArrayList<ClientBean> clientList) {
		this.clientList = clientList;
	}

	String username;
	ArrayList<String> headerlist;
	ArrayList<ClientBean> clientList;

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

		if (clientName.equals("")) {
			session.put("err_client", "true");
			return "error";
		}
		
		if (clientPassword.equals("")) {
			session.put("err_clientpass", "true");
			return "error";
		}
		
		headerlist.add("Client ID");
		headerlist.add("Client Name ");
		headerlist.add("Client Password ");
		headerlist.add("Client Location ");
		
		userid = session.get("userid") + "";

		try {

			String insertQuery = "";

			insertQuery = "insert into clientMst(clientName,clientPassword,clientLoc) values('"
					+ clientName
					+ "','"
					+ clientPassword
					+ "','"
					+ clientLocation
					+ "')";

			System.out.println("Insert query:" + insertQuery);
			int rows = DB.update(insertQuery);
			System.out.println("rows:" + rows);

			clientList = new ArrayList<ClientBean>();
			String query = " select clientId,clientName,clientPassword,clientLoc "
					+ " from clientMst";
					
			
			ResultSet rs = DB.readFromBmtcDB(query);			
			while (rs.next()) {
				ClientBean cb = new ClientBean();
				cb.setClientId(rs.getString(1));
				cb.setClientName(rs.getString(2));
				cb.setClientPassword(rs.getString(3));
				cb.setClientLocation(rs.getString(4));
				
				clientList.add(cb);

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
	

}

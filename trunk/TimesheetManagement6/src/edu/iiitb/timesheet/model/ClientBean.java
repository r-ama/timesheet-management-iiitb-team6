package edu.iiitb.timesheet.model;

public class ClientBean {
	
	public ClientBean(String clientId, String clientName) {
		super();
		this.clientId = clientId;
		this.clientName = clientName;
	}
	
	public ClientBean()
	{}
	private String clientId;
	private String clientName;
	private String clientPassword;
	private String clientLocation;
	
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

	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	
	

}

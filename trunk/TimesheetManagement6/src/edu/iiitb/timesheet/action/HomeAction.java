package edu.iiitb.timesheet.action;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Map;
import edu.iiitb.timesheet.model.*;

import com.mast.util.DB;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class HomeAction extends ActionSupport {
	
	private String username;
	private String userid;
	private String clientFlag;
	private ArrayList<MenuBean> menuList = new ArrayList<MenuBean>();
	

	public ArrayList<MenuBean> getMenuList() {
		return menuList;
	}

	public void setMenuList(ArrayList<MenuBean> menuList) {
		this.menuList = menuList;
	}

	public String getCleintFlag() {
		return clientFlag;
	}

	public void setCleintFlag(String cleintFlag) {
		this.clientFlag = cleintFlag;
	}

	private ArrayList<UserAllocation> userinfo;
	private ArrayList<ClientAllocation> cleintinfo;
	
	public String getClientFlag() {
		return clientFlag;
	}

	public void setClientFlag(String clientFlag) {
		this.clientFlag = clientFlag;
	}

	public ArrayList<ClientAllocation> getCleintinfo() {
		return cleintinfo;
	}

	public void setCleintinfo(ArrayList<ClientAllocation> cleintinfo) {
		this.cleintinfo = cleintinfo;
	}

	public ArrayList<UserAllocation> getUserinfo() {
		return userinfo;
	}

	public void setUserinfo(ArrayList<UserAllocation> userinfo) {
		this.userinfo = userinfo;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String execute()
	{
		Map session = (Map) ActionContext.getContext().get("session");
		if(session.get("userid")==null)
			return "initial_entry";
		
		else
		{
			if(session.get("clientFlag")!=null)
				clientFlag = session.get("clientFlag")+"";
			
			
			username = session.get("username")+"";
			userid = session.get("userid")+"";
			int roleId=0;
			
			if(clientFlag.equals("N"))
			{	
			String query ="select u.empId,u.empName,p.projectid,p.projectname,a.allocation_start_date,a.allocation_end_date,r.rolename,(select e1.empName from employee e1 where e1.empId=a.supervisorId) supervisor,r.roleId from employee u,project p,allocation a,rolemst r where u.empId ="+userid;
            query+=" and u.empId = a.empId";
            query+=" AND p.projectid = a.projectid";
            query+=" and a.roleid = r.roleid";
            query+=" and a.allocation_end_date >SYSDATE()";
            System.out.println(query);
            ResultSet rs= DB.readFromBmtcDB(query);
            try
            {
            	userinfo = new ArrayList<UserAllocation>();	
            while(rs.next())
            {
            	
            	UserAllocation ua=new UserAllocation();
            	ua.setUserid(rs.getString(1));
            	ua.setUsername(rs.getString(2));
            	ua.setProjectid(rs.getString(3));
            	ua.setProjectname(rs.getString(4));
            	ua.setStartDate(new SimpleDateFormat("dd-MMM-yyyy").format(rs.getDate(5)));
            	ua.setEndDate(new SimpleDateFormat("dd-MMM-yyyy").format(rs.getDate(6)));
            	ua.setRole(rs.getString(7));
            	ua.setSupervisor(rs.getString(8));
            	if(rs.getInt(9)>roleId)
            		 roleId=rs.getInt(9);
            	
            	userinfo.add(ua);
            	
            	
            }
            
            query = "select m.Name,m.action from menu m,rolemst r";
            query+=  " where m.Role = r.roleFlag";
            query+=  " and r.roleId ="+roleId;
            rs= DB.readFromBmtcDB(query);
            
            while(rs.next())
            {
            	
            	MenuBean mb = new MenuBean();
            	
            	mb.setName(rs.getString(1));
            	mb.setAction(rs.getString(2));
            	
            	menuList.add(mb);
            	
            }
            
            
            session.put("menuList", menuList);
            session.put("roleId", roleId);
            
            }
            catch(Exception ex)
            {
            	return "error";
            	
            }
            
            return "success";
			}
			
			else
			{
				String query = "select c.clientId,clientName,c.clientLoc,p.projectId,p.projectName,p.planned_start_date,p.planned_end_date from clientmst c,project p";
				query+=        " where c.clientId = p.clientId";
				query+=		   " and c.clientId ="+userid;
				
				ResultSet rs = DB.readFromBmtcDB(query);
				try
				{
					cleintinfo = new ArrayList<ClientAllocation>();		
				while(rs.next())
				{
					ClientAllocation ca=new ClientAllocation();
					ca.setClientId(rs.getString(1));
					ca.setClientname(rs.getString(2));
					ca.setClientloc(rs.getString(3));
					ca.setProjectid(rs.getString(4));
					ca.setProjectname(rs.getString(5));
					ca.setStartDate(new SimpleDateFormat("dd-MMM-yyyy").format(rs.getDate(6)));
					ca.setEndDate(new SimpleDateFormat("dd-MMM-yyyy").format(rs.getDate(7)));
					cleintinfo.add(ca);
					
					
				}
				
				
				query = "select m.Name,m.action from menu m";
	            query+=  " where m.Role = 'Client'";
	            
	            rs= DB.readFromBmtcDB(query);
	            
	            while(rs.next())
	            {
	            	
	            	MenuBean mb = new MenuBean();
	            	
	            	mb.setName(rs.getString(1));
	            	mb.setAction(rs.getString(2));
	            	
	            	menuList.add(mb);
	            	
	            }
	            
	            
	            session.put("menuList", menuList);
				
				return "client_home";
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
					return "error";
					
				}
				
			}
			
			
			
		}
			
		
		
	}

}

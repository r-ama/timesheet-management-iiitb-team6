<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="edu.iiitb.timesheet.model.MenuBean" %>
<%@ page import="java.util.ArrayList" %>
<div id='cssmenu'>
<ul>

<%
   ArrayList<MenuBean> menuList = (ArrayList<MenuBean>)session.getAttribute("menuList");
   for(int count=0;count<menuList.size();count++)
   {
	 MenuBean mb = (MenuBean)menuList.get(count);  
  %>

   <li><a href=<%=mb.getAction()%>><span><%=mb.getName()%></span></a></li>
  
    
 <%} %> 
   
</ul>
<div style="width: 300px; line-height: 30px; font-size: 15px; text-align: left;">
   &nbsp;<font color="black" face="cornerstone"><b>Welcome
   <s:property value="username" /></b></font>
   </div>
</div>
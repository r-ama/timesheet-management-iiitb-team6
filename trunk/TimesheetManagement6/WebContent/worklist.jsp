<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="java.util.ArrayList"  %>
<html>
<head>

<link rel="stylesheet" href="table_new.css">
<link rel="stylesheet" href="mystyle.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<s:form id="myform" method="GET">
		

		<table class="imagetable" align="center">
			<tr>
				<th colspan="1"><h2>Worklist or Notifications</h2></th>
			</tr>
			<tr>
				<th width="200"><h2>Item</h2></th>
				<th width="200"><h2>Action</h2></th>

			</tr>

            <%
			   ArrayList<String> worklist=(ArrayList<String>)(request.getAttribute("worklist"));
			   if(worklist.size()==0)
			   {   
			%>
			
			<tr>
			<td height="20" width="200"><font size="3" color="green">No worklist pending</font></td>
			    
			</tr>
			
			<%} %>

			<s:iterator value="worklist" status="stat">
				<tr>

					<td width="200"><h3>
							<s:property value="text" />
						</h3>
				
					<td width="200"><a
						href="<s:property value="action"/>">Go</a>
					</td>

				</tr>
			</s:iterator>
			
		</table>
	</s:form>
</body>
</html>
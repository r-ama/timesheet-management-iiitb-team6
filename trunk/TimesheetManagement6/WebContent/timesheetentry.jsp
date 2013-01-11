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
<title>Timesheet Entry</title>
</head>
<body>

<s:if test="hasActionErrors()">
<div class="errors">
<s:actionerror />
</div>
</s:if>
	<table>
		<tr>
		<td width="100"></td>
			<td><s:form action="timesheet" method="GET">
					<input type="submit" value="Prev" name="commandButton" size="10"
						style="width: 50px" id="mysubmit" />&nbsp;
			<input type="submit" value="Next" name="commandButton"
				size="10" style="width: 50px" id="mysubmit" />
				
		   <s:hidden name="projectid" value="%{projectid}" />
		   <s:hidden name="projectname" value="%{projectname}" />
			
		 </s:form>
				</td>
		</tr>
	</table>
	<s:form action="timesheetsave" method="GET">
		<table class="imagetable" align="center">
			<tr>
				<th colspan="2">
					<h1 align="center">Time Sheet Entry</h1>
				</th>
			</tr>
			<%
			   ArrayList<String> headerlist=(ArrayList<String>)(request.getAttribute("headerlist"));
			   if(headerlist.size()==0)
			   {   
			%>
			
			<tr>
			<td height="20" width="200"><font size="3" color="red">No records Found</font></td>
			    
			</tr>
			
			<%} %>
			<tr>
				<s:iterator value="headerlist">
					<th width="110"><h2>
							<s:property />
						</h2></th>
				</s:iterator>
			</tr>
			
			<s:iterator value="timesheet" var="timesheet" status="outerstat">
				<tr>
					<td><h3>
							<s:property value="projectid" />
						</h3></td>
					<td><h3>
							<s:property value="projectName" />
						</h3></td>
					<s:iterator value="day" status="mystat" var="day">
						<td><s:textfield
								name="timesheet[%{#outerstat.index}].day[%{#mystat.index}]"
								value="%{day[#mystat.index]}"
								disabled="%{approvalFlag[#mystat.index]}" size="2"
								cssClass="label" theme="simple" /></td>
						<s:if test="%{approvalFlag[#mystat.index]}">
							<s:hidden
								name="timesheet[%{#outerstat.index}].day[%{#mystat.index}]"
								value="%{day[#mystat.index]}" />
						</s:if>
						<s:hidden
							name="timesheet[%{#outerstat.index}].date[%{#mystat.index}]"
							value="%{date[#mystat.index]}" />
					</s:iterator>
					<td width="50"><h3>
							<s:property value="total" />
						</h3></td>
					<s:hidden name="timesheet[%{#outerstat.index}].projectid"
						value="%{projectid}" />
					<s:hidden name="timesheet[%{#outerstat.index}].projectName"
						value="%{projectName}" />
				</tr>
			</s:iterator>
		</table>
		<s:hidden property="projectid" />
		<s:hidden property="projectname" />
		<s:hidden property="timesheet" />
		<table align="center">
			<tr>
				<td height="10"></td>
			</tr>
			<tr>
				<td><input type="submit" value="Save" size="10"
					style="width: 100px" id="mysubmit" /></td>
			</tr>
		</table>
	</s:form>
</body>
</html>
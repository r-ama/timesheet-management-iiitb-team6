<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<script language="javascript" type="text/javascript"
	src="datetimepicker.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="table_new.css">
<link rel="stylesheet" href="mystyle.css">
<title>Create New Project</title>
</head>
<body>


	<s:if test="hasActionErrors()">
		<div class="errors">
			<s:actionerror />
		</div>
	</s:if>

	<s:form action="projectsave" method="POST">

		<table class="imagetable" align="center">
			<tr>
				<th colspan="2">
					<h1 align="center">Add New Project</h1>
				</th>
			</tr>
			<tr>
				<s:iterator value="headerlist">
					<th><h2>
							<s:property />
						</h2></th>
				</s:iterator>
			</tr>

			<tr>
				<td><s:textfield name="projectname" size="25" cssClass="label"
						theme="simple"></s:textfield></td>
				<td><h3>
						<s:textfield id="planStartDate" name="planned_start_date"
							value="%{planned_start_date}" cssClass="label" theme="simple" />
						<a href="javascript:NewCal('planStartDate','ddmmmyyyy',false,24)"><img
							src="cal.gif" width="16" height="16" border="0" alt="Pick a date"></a>
					</h3></td>
				<td><h3>
						<s:textfield id="planEndDate" name="planned_end_date"
							value="%{planned_end_date}" cssClass="label" theme="simple" />
						<a href="javascript:NewCal('planEndDate','ddmmmyyyy',false,24)"><img
							src="cal.gif" width="16" height="16" border="0" alt="Pick a date"></a>
					</h3></td>
				<td><s:textfield name="planned_effort" size="14"
						cssClass="label" theme="simple"></s:textfield></td>
				<td><h3>
						<s:select list="clientList" listKey="clientId"
							listValue="clientName" name="clientid" cssClass="label"
							theme="simple" />
					</h3></td>

			</tr>
		</table>

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

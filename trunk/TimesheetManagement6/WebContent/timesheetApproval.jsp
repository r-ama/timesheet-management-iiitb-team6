<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<link rel="stylesheet" href="table_new.css">
<link rel="stylesheet" href="mystyle.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Timesheet Approval</title>
</head>
<body>
	<table>
		<tr>
			<td width="48"></td>
			<td><s:form action="timesheetapprove" method="POST">

					<input type="submit" value="Prev" name="commandButton" size="10"
						style="width: 50px" id="mysubmit" />&nbsp;
					<input type="submit" value="Next" name="commandButton" size="10"
						style="width: 50px" id="mysubmit" />
				</s:form></td>
		</tr>
	</table>
	<s:form action="timesheetapsave" method="GET">


		<table class="imagetable" align="center">
			<tr>
				<th colspan="2">
					<h1>Time sheet Approval</h1>
				</th>
			</tr>
			<tr>
				<s:iterator value="headerlist">
					<th width="100"><h2>
							<s:property />
						</h2></th>

				</s:iterator>
			</tr>


			<s:iterator value="timesheet" var="timesheet" status="outerstat">
				<tr>
					<td><h3>
							<s:property value="username" />
						</h3></td>
					<td><h3>
							<s:property value="projectid" />
						</h3></td>
					<td><h3>
							<s:property value="projectName" />
						</h3></td>
					<s:iterator value="day" status="mystat" var="day">
						<td><s:textfield
								name="timesheet[%{#outerstat.index}].day[%{#mystat.index}]"
								value="%{day[#mystat.index]}" disabled="true" size="2"
								cssClass="label" theme="simple" /></td>

						<s:hidden
							name="timesheet[%{#outerstat.index}].date[%{#mystat.index}]"
							value="%{date[#mystat.index]}" />
					</s:iterator>
					<td><h3>
							<s:property value="total" />
						</h3></td>
					<td><s:checkbox
							name="timesheet[%{#outerstat.index}].approvalFlag[0]"
							value="%{approvalFlag[0]}" cssClass="label" theme="simple" /></td>

					<s:hidden name="timesheet[%{#outerstat.index}].projectid"
						value="%{projectid}" />
					<s:hidden name="timesheet[%{#outerstat.index}].projectName"
						value="%{projectName}" />
					<s:hidden name="timesheet[%{#outerstat.index}].userid"
						value="%{userid}" />

				</tr>
			</s:iterator>


			<s:actionerror />



			<tr>
			</tr>
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
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
<title>Add New User</title>
</head>
<body>

	<s:if test="hasActionErrors()">
		<div class="errors">
			<s:actionerror />
		</div>
	</s:if>

	<s:form action="usersave" method="POST">

		<table class="imagetable" align="center">
			<tr>
				<th colspan="2">
					<h1>Add New User</h1>
				</th>
			</tr>
			<tr>
				<s:iterator value="headerlist">
					<th width="180"><h2>
							<s:property />
						</h2></th>
				</s:iterator>
			</tr>

			<tr>
				<td height="40"><s:textfield name="empname" size="25" cssClass="label"
						theme="simple"></s:textfield></td>
				<td><s:textfield name="emppassword" size="25" cssClass="label"
						theme="simple"></s:textfield></td>
				<td><s:textfield name="emailid" size="30" cssClass="label"
						theme="simple"></s:textfield></td>
				<td><s:textfield name="phonenumber" size="25" cssClass="label"
						theme="simple"></s:textfield></td>

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

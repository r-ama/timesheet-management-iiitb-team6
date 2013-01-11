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
<title>Change Password</title>
</head>
<body>

	<s:form action="passwordsave" method="POST">

		<table class="imagetable" align="center">
			<tr>
				<th colspan="2">
					<h1>Change Password</h1>
				</th>
			</tr>
			
			<tr>
				
					<th width="180"><h2>
							Old Password
						</h2></th>
						
						<th width="180"><h2>
							New Password
						</h2></th>
						
						<th width="180"><h2>
							Confirm Password
						</h2></th>
				
			</tr>
			
			<tr>
				<td height="40"><s:password name="oldpassword" size="30" cssClass="label"
						theme="simple"></s:password></td>
				<td><s:password name="newpassword" size="30" cssClass="label"
						theme="simple"></s:password></td>
				<td><s:password name="confirmpassword" size="30" cssClass="label"
						theme="simple"></s:password></td>				
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

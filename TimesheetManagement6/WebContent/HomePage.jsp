<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<link rel="stylesheet" href="table_new.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<s:form action="" method="POST">
		<table class="imagetable" align="center">
			<tr>
				<th colspan="2">
					<h1>Project Allocation List</h1>
				</th>
			</tr>
			<tr>

				<th width="170"><h2>Project</h2></th>
				<th width="170"><h2>Allocation Start Date</h2></th>
				<th width="170"><h2>Allocation End Date</h2></th>
				<th width="170"><h2>Role</h2></th>
				<th width="170"><h2>Supervisor Name</h2></th>
				<th width="170"><h2>Timesheet</h2></th>
			</tr>


			<s:iterator value="userinfo">
				<tr>
					<td><h3>
							<s:property value="projectname" />
						</h3></td>
					<td><h3>
							<s:property value="startDate" />
						</h3></td>
					<td><h3>
							<s:property value="endDate" />
						</h3></td>
					<td><h3>
							<s:property value="role" />
						</h3></td>
					<td><h3>
							<s:property value="supervisor" />
						</h3></td>
					<td><h3>
							<a
								href="timesheet?projectid=<s:property value="projectid"/>&projectname=<s:property value="projectname"/>">View/Edit
								Timesheet</a>
						</h3></td>
				</tr>

			</s:iterator>
			<s:actionerror name="cart.error" />
		</table>
	</s:form>
</body>
</html>
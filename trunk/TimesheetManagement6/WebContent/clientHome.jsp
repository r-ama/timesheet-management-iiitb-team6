<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<link rel="stylesheet" href="table_new.css">
<link rel="stylesheet" href="mystyle.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<s:form action="" method="POST">
		<table class="imagetable" align="center">
			<tr>
				<th>
					<h1>Project List</h1>
				</th>
			</tr>
			<tr>
				<th width="150"><h2>Client Name</h2></th>
				<th width="150"><h2>Client Location</h2></th>
				<th width="150"><h2>Project Name</h2></th>
				<th width="150"><h2>Planned Start Date</h2></th>
				<th width="150"><h2>Planned End Date</h2></th>

				<th width="150"><h2>Reports</h2>
			</tr>


			<s:iterator value="cleintinfo">
				<tr>
					<td><h3>
							<s:property value="clientname" />
						</h3></td>
					<td><h3>
							<s:property value="clientloc" />
						</h3></td>
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
							<a href="report.action">Reports</a>
						</h3></td>

				</tr>
			</s:iterator>
			<s:actionerror name="cart.error" />


		</table>
	</s:form>
</body>
</html>
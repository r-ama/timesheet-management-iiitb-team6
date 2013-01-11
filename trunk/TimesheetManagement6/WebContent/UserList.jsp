<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<script language="javascript" type="text/javascript"
	src="datetimepicker.js"></script>
<link rel="stylesheet" href="table_new.css">
<link rel="stylesheet" href="mystyle.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Employee List</title>
<style type="text/css">
.pg-normal {
	color: black;
	font-weight: bold;
	text-decoration: none;
	cursor: pointer;
	font-size: 12px;
}

.pg-selected {
	color: black;
	font-weight: bold;
	text-decoration: underline;
	cursor: pointer;
	font-size: 13px;
}
</style>
<script type="text/javascript" src="paging.js"></script>
</head>
<body>
	<table>
		<tr>
			<td height="70"></td>
		</tr>
	</table>
	<table class="imagetable" align="center" id="results">
		<tr>
			<th colspan="2">
				<h1>Employee List</h1>
			</th>
		</tr>
		<tr>
			<s:iterator value="headerlist">
				<th width="180"><h2>
						<s:property />
					</h2></th>
			</s:iterator>
		</tr>

		<s:iterator value="employeeList" status="stat">
			<tr>
				<td><h3>
						<s:property value="empId" />
					</h3></td>
				<td><h3>
						<s:property value="empName" />
					</h3></td>
				<td><h3>
						<s:property value="empPassword" />
					</h3></td>
				<td><h3>
						<s:property value="emailId" />
					</h3></td>
				<td><h3>
						<s:property value="phoneNumber" />
					</h3></td>

			</tr>
		</s:iterator>


	</table>
	<table class="pager" align="center">
		<tr>
			<th height="30" width="912"><div id="pageNavPosition" align="center"></div></th>
		</tr>
	</table>
	
	<script type="text/javascript">
	<!--
		var pager = new Pager('results', 10);
		pager.init();
		pager.showPageNav('pager', 'pageNavPosition');
		pager.showPage(1);
	//-->
	</script>
	<br/>
	<br/>
</body>
</html>

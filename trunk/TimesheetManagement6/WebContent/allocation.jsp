<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<script language="javascript" type="text/javascript"
	src="datetimepicker.js"></script>

<script type="text/javascript">
	function create() {

		var url = "createallocation";
		window
				.open(url, "_blank",
						"directories=no, status=yes,width=500, height=500,top=100,left=100");
	}
</script>
<link rel="stylesheet" href="table_new.css">
<link rel="stylesheet" href="mystyle.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
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
	<s:form action="allocationsave" method="POST" id="myform">
		<table>
			<tr>
				<td height="70"></td>
			</tr>
		</table>
		<table class="imagetable" align="center" id="results">
			<tr>
				<th colspan="2">
					<h1>Allocated List</h1>
				</th>
				<th colspan="2"><a href="createallocation.action"><font
						size="2">Create new Allocation</font></a></th>
			</tr>
			<tr>
				<th width="50"><h2>Select</h2></th>
				<th width="120"><h2>Username</h2></th>
				<th width="120"><h2>Supervisor</h2></th>
				<th width="180"><h2>Allocation Start Date</h2></th>
				<th width="180"><h2>Allocation End Date</h2></th>
				<th width="120"><h2>Project</h2></th>
				<th width="150"><h2>Role</h2>
			</tr>


			<s:iterator value="allocList" status="stat">
				<tr>
					<td><h3>
							<s:checkbox value="%{checkId}"
								name="allocList[%{#stat.index}].checkId" cssClass="label"
								theme="simple">
							</s:checkbox>
						</h3></td>
					<td><h3>
							<s:property value="userName" />
						</h3></td>

					<td><h3>
							<s:select list="userlist" listKey="userid" listValue="username"
								name="allocList[%{#stat.index}].supervisorId" cssClass="label"
								theme="simple" />
						</h3></td>
					<td><h3>
							<s:textfield id="start%{#stat.index}"
								name="allocList[%{#stat.index}].allocationStartDate"
								value="%{allocationStartDate}" cssClass="label" theme="simple" />
							<a
								href="javascript:NewCal('start'+'<s:property value="#stat.index"/>','ddmmmyyyy',false,24)"><img
								src="cal.gif" width="16" height="16" border="0"
								alt="Pick a date"></a>
						</h3></td>
					<td><h3>
							<s:textfield id="end%{#stat.index}"
								name="allocList[%{#stat.index}].allocationEndDate"
								value="%{allocationEndDate}" cssClass="label" theme="simple" />
							<a
								href="javascript:NewCal('end'+'<s:property value="#stat.index"/>','ddmmmyyyy',false,24)"><img
								src="cal.gif" width="16" height="16" border="0"
								alt="Pick a date"></a>
						</h3></td>
					<td><h3>
							<s:select list="projList" listKey="projectid"
								listValue="projectname"
								name="allocList[%{#stat.index}].projectid" cssClass="label"
								theme="simple" />
						</h3></td>
					<td><h3>
							<s:select list="roleList" listKey="roleId" listValue="roleName"
								name="allocList[%{#stat.index}].roleid" cssClass="label"
								theme="simple" />
						</h3></td>
					<s:hidden name="allocList[%{#stat.index}].userid" value="%{userid}" />
					<s:hidden name="allocList[%{#stat.index}].allocationId"
						value="%{allocationId}" />
				</tr>
			</s:iterator>

		</table>
		<table class="pager" align="center">
			<tr>
				<th height="30" width="1015"><div id="pageNavPosition"
						align="center"></div></th>
			</tr>
		</table>
		<table align="center">
			<tr>
				<td height="10"></td>
			</tr>
			<tr>
				<td><input type="submit" value="Update" size="10"
					style="width: 100px" id="mysubmit" /></td>

			</tr>
		</table>


		<script type="text/javascript">
		<!--
			var pager = new Pager('results', 5);
			pager.init();
			pager.showPageNav('pager', 'pageNavPosition');
			pager.showPage(1);
		//-->
		</script>

	</s:form>

</body>
</html>
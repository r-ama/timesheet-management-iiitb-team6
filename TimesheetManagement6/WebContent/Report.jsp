<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<script language="javascript" type="text/javascript"
	src="datetimepicker.js"></script>
<script language="javascript" type="text/javascript">
	function download(reportId) {

		var start = document.getElementById("start");
		var end = document.getElementById("end");
		document.getElementById("report").value = reportId;
		var link = "fileDownload.action?a=a&reportType=" + reportId
				+ "&startDate=" + start.value + "&endDate=" + end.value;
		document.getElementById("myform").action = link;
		document.getElementById("myform").method = "GET";
		document.getElementById("myform").submit();

	}
</script>
<link rel="stylesheet" href="table_new.css">
<link rel="stylesheet" href="mystyle.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<s:form id="myform" method="GET">
		<table class="norm">
			<tr>
				<td><h2>Start Date:</h2></td>

				<td><h3>
						<s:textfield id="start" name="startDate" value="%{startDate}"
							cssClass="label" theme="simple" />
						<a href="javascript:NewCal('start','ddmmmyyyy',false,24)"><img
							src="cal.gif" width="16" height="16" border="0" alt="Pick a date"></a>
					</h3></td>
			</tr>
			<tr>
				<td><h2>End Date:</h2></td>
				<td><h3>
						<s:textfield id="end" name="endDate" value="%{endDate}"
							cssClass="label" theme="simple" />
						<a href="javascript:NewCal('end','ddmmmyyyy',false,24)"><img
							src="cal.gif" width="16" height="16" border="0" alt="Pick a date"></a>
					</h3></td>
			</tr>
		</table>

		<table class="imagetable" align="center">
			<tr>
				<th colspan="1"><h2>Reports</h2></th>
			</tr>
			<tr>
				<th width="120"><h2>Report ID</h2></th>
				<th width="250"><h2>Report Name</h2></th>
				<th width="120"><h2>Download</h2></th>

			</tr>

			<s:iterator value="reportList" status="stat">
				<tr>

					<td><h3>
							<s:property value="reportId" />
						</h3>
					<td><h3>
							<s:property value="reportName" />
						</h3>
					<td><a
						href="javascript:download('<s:property value="reportId"/>')">Download</a>
					</td>

				</tr>
			</s:iterator>
			<s:hidden name="reportType" id="report"></s:hidden>
		</table>
	</s:form>
</body>
</html>
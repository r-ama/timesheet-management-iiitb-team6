<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<script language="javascript" type="text/javascript" src="datetimepicker.js"></script>
<script language="javascript" type="text/javascript">
 function refresh()
 {
	 
	 document.getElementById("myform").action="createallocation";
	 document.getElementById("myform").submit();
	 
 }
</script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h3 align="left" >Welcome,<s:property value="username"/></h3>

<s:form id="myform" action="allocationsave" method="GET"> 
<table class="pretty">
<tr>
 <td>
   Select Employee
 </td>
 <td>
    <h3><s:select list="userlist" listKey="userid" listValue="username" headerKey="0" headerValue="--Select Employee--" name="allocList[0].userid" cssClass="label" theme="simple"/></h3>
 </td>
</tr>

<tr>
<td>
  Select Project
</td>
<td>
    <h3><s:select list="projList" listKey="projectid" listValue="projectname" name="allocList[0].projectid" cssClass="label" theme="simple" onchange="javascript:refresh();"/></h3>
</td>
</tr>

<tr>
<td>
  Select Supervisor
</td>
<td>
    <h3><s:select list="supervisorList" listKey="userid" listValue="username" name="allocList[0].supervisorId" headerKey="0" headerValue="--Select Supervisor--" cssClass="label" theme="simple"/></h3>
</td>
</tr>

<tr>

  <td>
     Enter Start Date(dd-MMM-yyyy)
  </td>
  
  <td>
     <s:textfield type="text" id="startDate" value="%{allocList[0].allocationStartDate}" name="allocList[0].allocationStartDate" cssClass="label" theme="simple" />
     <a href="javascript:NewCal('startDate','ddmmmyyyy',false,24)"><img src="cal.gif" width="16" height="16" border="0" alt="Pick a date"></a>
  </td>
  
  
</tr>

<tr>

  <td>
     Enter End Date(dd-MMM-yyyy)
  </td>
  
  <td>
     <s:textfield type="text" id="endDate" value="%{allocList[0].allocationEndDate}" name="allocList[0].allocationEndDate" cssClass="label" theme="simple" />
     <a href="javascript:NewCal('endDate','ddmmmyyyy',false,24)"><img src="cal.gif" width="16" height="16" border="0" alt="Pick a date"></a>
  </td>
  
  
</tr>


<tr>

  <td>
    Select Role
  </td>
  <td>
    <s:select list="roleList" listKey="roleId" listValue="roleName" name="allocList[0].roleid" cssClass="label" theme="simple"/>
  </td>
</tr>

  <s:hidden name="allocList[0].allocationId" value="%{allocList[0].allocationId}"/>
</table>
<table align="center"><tr>
 <td>
 <input type="submit" value="Save" name="commandButton" size="10" style="width:100px"/>
 </td>

 </tr></table>

<s:hidden name="allocList[0].checkId" value="%{allocList[0].checkId}"/>
</s:form>

</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<script language="javascript" type="text/javascript" src="datetimepicker.js"></script>

<script type="text/javascript">

function create(){
	  
	  var url="createallocation";
	  window.open(url,"_blank","directories=no, status=yes,width=500, height=500,top=100,left=100");
	}

</script>
<style type="text/css">
table.pretty {
  
  background: whitesmoke;
  border-collapse: collapse;
  width=100%;
  align="center";
}
table.pretty th, table.pretty td {
  border: 1px silver solid;
  padding: 0.2em;
}
table.pretty th {
  background: gainsboro;
  text-align: left;
}
table.pretty caption {
  margin-left: inherit;
  margin-right: inherit;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h3 align="left" >Welcome,<s:property value="username"/></h3>

<s:form action="allocationsave" method="POST" id="myform"> 
<table class="pretty">
<tr>
<th colspan="3"> <h1>Allocated List</h1></th>
<th colspan="2"><a href="createallocation.action">Create new Allocation</a> </th>
</tr>
<tr> 
    <th><h2>Select</h2></th>
    <th><h2>Username</h2></th>
   
    <th><h2>Supervisor</h2></th>
    <th><h2>Allocation Start Date</h2></th>
    <th><h2>Allocation End Date</h2></th>
    <th><h2>Project</h2></th>
    <th><h2>Role</h2>
</tr>


<s:iterator value="allocList" status="stat">
<tr>
<td><h3><s:checkbox value="%{checkId}" name="allocList[%{#stat.index}].checkId" cssClass="label" theme="simple"> </s:checkbox> </h3></td>
<td><h3> <s:property value="userName"/> </h3></td>

<td><h3> <s:select list="userlist" listKey="userid" listValue="username" name="allocList[%{#stat.index}].supervisorId" cssClass="label" theme="simple"/></h3> </td>
<td><h3> <s:textfield  id="start%{#stat.index}" name="allocList[%{#stat.index}].allocationStartDate" value="%{allocationStartDate}" cssClass="label" theme="simple"/><a href="javascript:NewCal('start'+'<s:property value="#stat.index"/>','ddmmmyyyy',false,24)"><img src="cal.gif" width="16" height="16" border="0" alt="Pick a date"></a></h3></td>
<td><h3> <s:textfield  id="end%{#stat.index}" name="allocList[%{#stat.index}].allocationEndDate" value="%{allocationEndDate}" cssClass="label" theme="simple"/><a href="javascript:NewCal('end'+'<s:property value="#stat.index"/>','ddmmmyyyy',false,24)"><img src="cal.gif" width="16" height="16" border="0" alt="Pick a date"></a></h3></td>
<td><h3> <s:select list="projList" listKey="projectid" listValue="projectname" name="allocList[%{#stat.index}].projectid" cssClass="label" theme="simple"/></h3> </td>
<td><h3> <s:select list="roleList" listKey="roleId" listValue="roleName" name="allocList[%{#stat.index}].roleid" cssClass="label" theme="simple"/></h3> </td>
 <s:hidden name="allocList[%{#stat.index}].userid" value="%{userid}"/>
  <s:hidden name="allocList[%{#stat.index}].allocationId" value="%{allocationId}"/>
</tr>
</s:iterator>
 
</table>
<table align="center"><tr>
 <td>
 <input type="submit" value="Update" size="10" style="width:100px"/>
 </td>

 </tr></table>

</s:form>

</body>
</html>
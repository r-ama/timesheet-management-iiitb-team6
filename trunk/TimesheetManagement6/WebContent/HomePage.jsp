<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<style type="text/css">
table.pretty {
  margin: 10em 10em 10em 10em;
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

<s:form action="" method="POST"> 
<table class="pretty">
<tr>
<th> <h1>Project Allocation List</h1></th>
</tr>
<tr> 
   
    <th><h2>Project</h2></th>
    <th><h2>Allocation Start Date</h2></th>
    <th><h2>Allocation End Date</h2></th>
    <th><h2>Role</h2></th>
     <th><h2>Supervisor Name</h2></th>
    <th><h2>Timesheet</h2>
</tr>


<s:iterator value="userinfo">
<tr>
<td><h3> <s:property value="projectname"/> </h3></td>
<td><h3> <s:property value="startDate"/></h3> </td>
<td><h3> <s:property value="endDate"/></h3></td>
<td><h3> <s:property value="role"/></h3></td>
<td><h3> <s:property value="supervisor"/></h3></td>
<td><h3> <a href="timesheet?projectid=<s:property value="projectid"/>&projectname=<s:property value="projectname"/>">View/Edit Timesheet</a></h3> </td>
 
</tr>
</s:iterator>
 <tr>
 <td>

      <s:actionerror name="cart.error"/>
   
</td>
</tr>

<tr>  </tr>
</table>
</s:form>
</body>
</html>
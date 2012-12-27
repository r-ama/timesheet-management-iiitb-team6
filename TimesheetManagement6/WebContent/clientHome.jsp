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
<th> <h1>Project List</h1></th>
</tr>
<tr> 
     <th><h2>Client Name</h2></th>
     <th><h2>Client Location</h2></th>
    <th><h2>Project Name</h2></th>
    <th><h2>Planned Start Date</h2></th>
    <th><h2>Planned End Date</h2></th>
   
    <th><h2>Reports</h2>
</tr>


<s:iterator value="cleintinfo">
<tr>
<td><h3> <s:property value="clientname"/> </h3></td>
<td><h3> <s:property value="clientloc"/></h3> </td>
<td><h3> <s:property value="projectname"/></h3></td>
<td><h3> <s:property value="startDate"/></h3></td>
<td><h3> <s:property value="endDate"/></h3></td>
<td><h3> <a href="report.action">Reports</a></h3> </td>
 
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
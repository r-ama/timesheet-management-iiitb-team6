<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<link rel="button.css" type="text/css">
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
<title>Timesheet Entry</title>
</head>
<body>
<h3 align="left" >Welcome,<s:property value="username"/></h3>

<s:form action="timesheet" method="GET">

  <input type="submit" value="Prev" name="commandButton" size="10" style="width:50px" />
  <input type="submit" value="Next" name="commandButton" size="10" style="width:50px"/>
</s:form>

<s:form action="timesheetsave" method="GET"> 

 
<table class="pretty">
<tr>
<th colspan="3"> <h1>Time sheet entry</h1></th>
</tr>
<tr> 
   <s:iterator value="headerlist">
    <th><h2><s:property /></h2></th>
   
    </s:iterator>
</tr>


<s:iterator value="timesheet" var="timesheet" status="outerstat" >
<tr>
<td><h3> <s:property value="projectid"/> </h3></td>
<td><h3> <s:property value="projectName"/></h3> </td>
<s:iterator value="day" status="mystat" var="day">
 <td><s:textfield name="timesheet[%{#outerstat.index}].day[%{#mystat.index}]" value="%{day[#mystat.index]}" disabled="%{approvalFlag[#mystat.index]}" size="2" cssClass="label" theme="simple"/></td>
    <s:if test="%{approvalFlag[#mystat.index]}">
    
     <s:hidden name="timesheet[%{#outerstat.index}].day[%{#mystat.index}]" value="%{day[#mystat.index]}"/>
    </s:if> 
     
 	<s:hidden name="timesheet[%{#outerstat.index}].date[%{#mystat.index}]" value="%{date[#mystat.index]}"/>
</s:iterator> 
<td><h3> <s:property value="total"/></h3> </td>
<s:hidden name="timesheet[%{#outerstat.index}].projectid" value="%{projectid}"/>
 <s:hidden name="timesheet[%{#outerstat.index}].projectName" value="%{projectName}"/>
 
</tr>
</s:iterator>
 
</table>

<s:hidden property="projectid"/>
<s:hidden property="projectname"/>
<s:hidden property="timesheet"/>
 <table align="center"><tr>
 <td>
 <input type="submit" value="Save" size="10" style="width:100px" class="classname"/>
 </td>
 </tr></table>
</s:form>
</body>
</html>
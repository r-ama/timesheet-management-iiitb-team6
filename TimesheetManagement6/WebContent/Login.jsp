<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<link rel="stylesheet" href="mystyle.css">
<title>Login</title>
</head>
 
<body>

<s:if test="hasActionErrors()">
   <div class="errors">
      <s:actionerror/>
   </div>
</s:if>
<center>
<s:form action="admin.action" method="post">
    <s:textfield name="username" label = "User ID" id="myinput" 
      size="20" />
    <s:password name="password" label="Password" id="myinput"
      size="20" />
    <s:submit name = "commandButton" id="mysubmit" value="Login"
      align="center" />
</s:form>
</center>
</body>
</html>

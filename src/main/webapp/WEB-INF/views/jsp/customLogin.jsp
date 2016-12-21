<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
    <%@ page isELIgnored="false" %> 
        <title>Content Management System</title>
    </head>
    <body>
       <h3>ADMIN LOGIN</h3>
        <font color="red">
		   ${SPRING_SECURITY_LAST_EXCEPTION.message}
        </font>
	<form action="<%=request.getContextPath()%>/appLogin" method="POST">
		Enter UserName:	<input type="text" name="app_username"/><br/><br/>
		Enter Password: <input type="password" name="app_password"/> <br/><br/>			
		<input type="submit" value="Login"/>
		<a href="<%=request.getContextPath()%>/">Back</a>
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>			
	</form>
    <body>
</html>   
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
 "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ page isELIgnored="false"%>
<meta http-equiv="Content-Type" content="text/html;
charset=UTF-8">
<title>CMS</title>
<style>
.error {
	color: red;
	font-weight: bold;
}
</style>
</head>
<body>
	<div align="center">
		<h2>Content Management System</h2>
		<table border="0" width="90%">
			<form:form action="userLogin.html" commandName="userForm">
				<tr>
					<td align="left" width="20%"></td>
					<td align="right" width="40%">Guest User Name: <form:input path="userName"
							size="30" /></td>
					<td align="left"><form:errors path="userName" cssClass="error" /></td>
				</tr>
				<tr>
					<td align="left" width="20%"></td>
					<td align="right" width="40%"><input type="submit" value="Login as Guest" /></td>
					<td align="left"></td>
				</tr>
			</form:form>
		</table>
		<table border="0" width="90%">
			<form:form action="customLogin" method="GET">
				<tr>
					<td align="left" width="20%"></td>
					<td align="right" width="40%"><input type="submit" value="Admin" /></td>
					<td></td>
				</tr>
			</form:form>
		</table>
	</div>
</body>
</html>
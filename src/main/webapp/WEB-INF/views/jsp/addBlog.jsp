<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<%@ page isELIgnored="false"%>
<head>
<title>Content Management System</title>
<style>
.error {
	color: red;
	font-weight: bold;
}
</style>
</head>
<body>
	<div align="center">
		<h3>ADD BLOG</h3>

		<form:form action="result" method="POST" commandName="blog"
			enctype="multipart/form-data">
			<table border="0" width="70%">
				<tr>
					<td align="right" width="20%">Add Blog Tittle:</td>
					<td align="left" width="60%"><form:input path="tittle"
							width="100%" /></td>
					<td align="left" width="20%"><form:errors path="tittle"
							cssClass="error" /></td>
				</tr>
				<tr>
					<td align="right" width="20%">File to upload:</td>
					<td align="left" width="60%"><form:input type="file"
							path="file" /></td>
					<td align="left" width="20%" style="color: red;"><c:if test="${fileError!=null}">
                 					 <b>${fileError}</b>
                 					</c:if></td>
				</tr>
				<tr>
					<td align="right" width="20%">Blog Content:</td>
					<td align="left" width="60%"><form:textarea rows="15"
							cols="75" path="content" /></td>
					<td align="left" width="20%"><form:errors path="content"
							cssClass="error" /></td>
				</tr>
				<tr>
					<td colspan="3" align="center"><input type="submit"
						value="ADD BLOG"></td>
				</tr>
			</table>
		</form:form>
		</div>
		<form action="<%=request.getContextPath()%>/secure/home" method="POST">
			<table border="0" width="100%">
				<tr>
					<td align="center" colspan="3"><input type="submit" value="Back" /></td>
				</tr>
			</table>
		</form>
</body>
</html>
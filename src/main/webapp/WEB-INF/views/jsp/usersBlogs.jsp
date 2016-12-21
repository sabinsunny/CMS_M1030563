<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
 "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ page isELIgnored="false"%>
<meta http-equiv="Content-Type" content="text/html;
charset=UTF-8">
<title>Welcome</title>
</head>
<body>
	<div align="center">
		<h2>Welcome ${userForm.userName}!</h2>
		<a href="<%=request.getContextPath()%>/">Logout</a> <br>
		<br>
		<table border="1" width="600px">
			<form:form method="post" action="viewBlog" modelAttribute="blogs">
				<c:if test="${empty blogs}">No Blogs Available</c:if>
				<c:if test="${!empty blogs}">
					<c:forEach var="blog" items="${blogs}">
						<tr bordercolor="green" height="200px">
							<td align="center">${blog.tittle}</td>
							<td align="center"><img
								src="data:image/jpg;base64,${blog.fileBase64}" alt="No image"
								width="200px" height="200px"></td>
							<td align="center"><a
								href="<%=request.getContextPath()%>/openBlog?value=${blog.blogId}&userName=${userForm.userName}">ViewBlog</a></td>
						</tr>
					</c:forEach>
				</c:if>
			</form:form>
		</table>
	</div>
</body>
</html>
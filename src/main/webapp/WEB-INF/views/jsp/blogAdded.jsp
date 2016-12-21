<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
    <%@ page isELIgnored="false" %> 
        <title>Blog Added</title>
    </head>
    <body>
          <h3>Bolog Added</h3>
          Blog Name:  ${blogName}
	  <form action="<%=request.getContextPath()%>/appLogout" method="POST">
	     <input type="submit" value="Logout"/>
	     <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>		
	  </form><br>
	  <form action="<%=request.getContextPath()%>/secure/addBlog" method="POST">
	     <input type="submit" value="Add Again"/>	
	  </form><br>
	  <form action="<%=request.getContextPath()%>/secure/home" method="POST">
	     <input type="submit" value="Back"/>	
	  </form>
    </body>
</html>  
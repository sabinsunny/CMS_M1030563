<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
 "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<%@ page isELIgnored="false"%>
<script src="http://code.jquery.com/jquery-1.11.1.js"></script>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Content Management System</title>
</head>
<body>
	<div style="width: 100%;">
		<div style="float: left; width: 80%;">
			<div>
				<table width="100%">
					<tr>
						<td width="20%" align="left"><a
							href="<%=request.getContextPath()%>/backToMain?userName=${userName}">Go
								Back</a></td>

						<td width="60%" align="center"><h2>${blog.tittle}</h2></td>
						<td width="20%"></td>
					</tr>
					<tr height="40px">
						<td width="20%" align="left"></td>
						<td width="60%" align="center"><img
							src="data:image/jpg;base64,${blog.fileBase64}" alt="No image"
							width="200" height="200"></td>
						<td width="20%"></td>
					</tr>
				</table>
			</div>
			<div>
				<table width="100%">
					<tr>
						<td width="20%" align="left"></td>

						<td width="60%" align="center">${blog.content}<br /> <br /></td>
						<td width="20%"></td>
					</tr>
				</table>
			</div>
			<div id="userCommentOnload" style='text-align: center;'>
				<h2 style='color: #8B0000;'>User Comments About This :</h2>
				<c:forEach var="comment" items="${blog.comments}">
					<b style='font-size: large; color: #8B0000;'>${comment.userName}:</b>${comment.comment}<br /></br>
				</c:forEach>
			</div>
			<div align="center" id="userComment"></div>
			<div style="text-align: center;">
				<form:form action="/addBlogComment" modelAttribute="blogComment"
					method="post" id="comments-form">

					<table align="center">
						<tr>
							<td style="color: #8B0000">Add your Comments:</td>
							<td><form:textarea id="comment" path="comment" /> <input
								type="hidden" value="${userName}" /></td>
						</tr>
						<tr>
							<td colspan="2" style="text-align: center;"><input
								type="submit" value="Post Comment" /></td>
						</tr>
					</table>
				</form:form>
			</div>
		</div>
		<div style="float: right; width: 20%;">
			<div style="text-align: center; color: green; font-size: xx-large;">Tweets
				- About ${blog.tittle}</div>
			<br>

			<div id="tweets" style="display: inline-block; overflow: hidden;"></div>

		</div>
	</div>
</body>
</html>
<script>
	jQuery(document)
			.ready(
					function($) {
						$
								.ajax({
									type : "GET",
									url : '/twitter/home',
									dataType : 'json',
									data : {
										'title' : '${blog.tittle}'
									},
									success : function(json) {
										var $el = $("#tweets");
										var htmlContent = '<div class="row"  style="display: inline-block;overflow: hidden;">';
										$el.empty();
										$
												.each(
														json,
														function(value, key) {
															htmlContent += '<marquee  scrolldelay="40px" direction="up">'
																	+ key.fromUser
																	+ '</br>';
															htmlContent += key.text
																	+ '</marquee>'

														});
										htmlContent += '</div>';
										$el.html(htmlContent);
									}
								});
						refreshData(event);
						$("#comments-form").submit(function(event) {
							refreshData(event);
						});
						function refreshData(event) {
							event.preventDefault();
							var data = {
								"comment" : $("#comment").val(),
								"userName" : '${userName}',
								"blogId" : '${blog.blogId}'
							}
							$
									.ajax({
										type : "POST",
										contentType : "application/json",
										url : "/addBlogComment",
										data : JSON.stringify(data),
										dataType : 'json',
										success : function(data) {
											console.log("SUCCESS: ", data);
											var htmlCnt = "<div style='text-align: center;'><h2 style='color: #8B0000;'>User Comments About This :</h2>";
											$
													.each(
															data,
															function(index,
																	element) {
																htmlCnt += "<b style='font-size: large;color: #8B0000;'>"
																		+ element.userName
																		+ ":</b>";
																htmlCnt += element.comment
																		+ "<br /></br></div>";
															});
											$("#userComment").html(htmlCnt);
											$("#comment").val("");
											$("#userCommentOnload").html(
													"<br /></br>");
										},
										error : function(e) {
											console.log("ERROR: ", e);
										},
										done : function(e) {
										}
									});
						}

					})
</script>
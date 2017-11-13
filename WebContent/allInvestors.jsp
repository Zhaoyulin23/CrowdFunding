<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="crowdFunding.model.*, java.util.*, crowdFunding.tool.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Invests</title>
<link rel="stylesheet" href="CSS/bootstrap.css"/>
<link rel="stylesheet" href="CSS/profile.css"/>
</head>
<body>
	<jsp:include page="/Component/navbar.jsp"/>
	<%
	List<User> users = new ArrayList();
	UserDao dao = new UserDao();
	users = dao.findAllUsers();
	%>
	<div class="container">
		<div class="heading">
				<h4>Users</h4>
			</div>		
			<div class="project">
			<% if (users != null) { 
					for (User user : users) { %>
				<div class="project-small">
					<div class="lead image">
						<% if(user.getImageUrl() == null || user.getImageUrl().equals("")) {
						Tool tool = new Tool();
						String hashText = tool.MD5(user.getEmail());
						String avatarUrl = "http://www.gravatar.com/avatar/" + hashText + "?d=retro";
				%>
					<img src=<%=avatarUrl%> height="180" width="180">
				<%} else { %>
				  <img src=<%= user.getImageUrl()%> width="180" height="180">
				  <% } %>
					</div>
					<hr>
					<div class="title lead"><a href="/CrowdFunding/ProfileAction?username=<%= user.getUsername()%>"><span><%=user.getFirstName()%></span><span> <%=user.getLastName() %></span></a></div>
				</div>
				<%} %>
			<% } else {%>
				<div><h1>No User has been created</h1></div>
			<% } %>
			</div>
	</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="crowdFunding.model.*, java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>All Projects</title>
<link rel="stylesheet" href="CSS/bootstrap.css"/>
<link rel="stylesheet" href="CSS/profile.css"/>
</head>
<body>
	<jsp:include page="/Component/navbar.jsp"/>
	<%
		ProjectDao dao = new ProjectDao();
		List<Project> projects = new ArrayList();
		projects = dao.findAllProject();
	%>
	<div class="container">
			<div class="heading">
				<h4>PROJECT</h4>
			</div>		
			<div class="project">
			<% if (projects != null) { 
					for (Project project : projects) { %>
				<div class="project-small">
					<div class="lead image"><img alt=<%=project.getName() %> src=<%= project.getImageUrl()%> width="160" height="160" ></div>
					<div class="title lead"><a href="/CrowdFunding/Project?projectId=<%=project.getId()%>"><%=project.getName() %></a></div>
					<div class="shortDes"><p><%=project.getShortDescription()%></p></div>
				</div>
				<%} %>
			<% } else {%>
				<div><h1>No project has been published</h1></div>
			<% } %>
			</div>
	</div>
</body>
</html>
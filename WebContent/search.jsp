<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="crowdFunding.model.*, java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Search result</title>
<link rel="stylesheet" href="CSS/bootstrap.css"/>
<link rel="stylesheet" href="CSS/profile.css"/>
</head>
<body>
	<jsp:include page="/Component/navbar.jsp"/>
	<div class="container">
		<div class="heading">
			<h4>Search result</h4>
		</div>
		<div class="project">
		<%
			List<Project> projects = (List<Project>)request.getAttribute("projects");
			if(projects != null && !projects.isEmpty()){	
				for (Project project : projects) { %>
				<div class="project-small">
					<div class="lead image"><img alt=<%=project.getName() %> src=<%= project.getImageUrl()%> width="160" height="160" ></div>
					<div class="title lead"><a href="/CrowdFunding/Project?projectId=<%=project.getId()%>"><%=project.getName() %></a></div>
					<div class="shortDes"><p><%=project.getShortDescription()%></p></div>
				</div>
				<%} %>
			<% } else {%>
				<div><h1>No matched project</h1></div>
			<% } %>
		</div>
	</div>	
</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="crowdFunding.model.*, java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Message</title>
<link rel="stylesheet" href="CSS/bootstrap.css"/>
<link rel="stylesheet" href="CSS/profile.css"/>
</head>
<body>
	<jsp:include page="/Component/navbar.jsp"/>
	<%
		List<Message> msgs = (List<Message>)request.getAttribute("msgs");
	%>
	<div class=container>
		<h1>Message</h1>
	<% if(!msgs.isEmpty()) { %>
		<% for(Message msg : msgs) {%>
			<div class="message">
			<a href="/CrowdFunding/profileAction?username=<%= msg.getSender().getUsername()%>"><%=msg.getSender().getUsername() %></a>
			<span> funded your Project: </span><a href="/CrowdFunding/Project?projectId=<%= msg.getProject().getId()%>"><%=msg.getProject().getName() %></a>
			<span>: $<%= msg.getAmount() %></span>
			</div>	
		<%} %>
	<% }else{%>
		<div class="message">
			<span>No messages</span>
		</div>
	<% } %>
	
</body>
</html>
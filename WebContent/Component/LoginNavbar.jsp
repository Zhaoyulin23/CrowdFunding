<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<nav class="navbar navbar-inverse" role="navigation">
		<div class="container">
			<div class="navbar-header">
				<a class="navbar-brand" href="/CrowdFunding/index.jsp">CrowdFunding</a>
			</div>
			<ul class="nav navbar-nav">
				<li><a href="allProjects.jsp">Project</a></li>
				<li><a href="allInvestors.jsp">Investor</a></li>
			</ul>
			<form class="navbar-form navbar-left" role="search" action="/CrowdFunding/Search" method="GET">
				<div class="form-group">
					<input type="text" class="form-control" placeholder="search" name="pattern">
				</div>
				<button type="submit" class="btn btn-default">Submit</button>
			</form>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="/CrowdFunding/ProfileAction?username=<%=session.getAttribute("username")%>"><%=session.getAttribute("username") %></a></li>
				<li><a href="/CrowdFunding/Message?username=<%=session.getAttribute("username")%>">Message</a></li>
				<li><a href="/CrowdFunding/LogoutAction">Logout</a></li>
			</ul>
		</div>
	</nav>
</body>
</html>
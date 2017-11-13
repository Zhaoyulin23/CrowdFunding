<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<!--The navigation bar show in the page when user is NOT login  -->
	<nav class="navbar navbar-inverse" role="navigation">
		<div class="container">
			<div class="navbar-header">
				<a class="navbar-brand" href="/CrowdFunding/index.jsp">CrowdFunding</a>
			</div>
			<ul class="nav navbar-nav">
				<li><a href="#">Project</a></li>
				<li><a href="#">Investor</a></li>
			</ul>
			<form class="navbar-form navbar-left" role="search" action="/CrowdFunding/Search" method="GET">
				<div class="form-group">
					<input type="text" class="form-control" placeholder="search" name="pattern">
				</div>
				<button type="submit" class="btn btn-default">Submit</button>
			</form>
			<ul class="nav navbar-nav navbar-right">
			<!--To remember the current page, After login, it will redirect back to this current page  -->
				<li><a href="/CrowdFunding/login.jsp?from=${pageContext.request.requestURI}">Login</a></li>
				<li><a href="/CrowdFunding/register.jsp">Join Now</a></li>
			</ul>
		</div>
	</nav>
</body>
</html>
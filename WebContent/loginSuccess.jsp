<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login Success</title>
<link rel="stylesheet" href="CSS/bootstrap.css"/>
<link rel="stylesheet" href="CSS/profile.css"/>
</head>
<body>
	<jsp:include page="/Component/navbar.jsp"/>
<%
	String username = null;
	String myCookie = null;
	String sessionId = null;
	if(session.getAttribute("username") == null){
		response.sendRedirect("login.jsp");
	}else{
		username = (String) session.getAttribute("username");
		sessionId = session.getId();
		Cookie[] cookies = request.getCookies();
		if(cookies != null){
			for(Cookie cookie : cookies){
				if(cookie.getName().equals("username"))
					myCookie = cookie.getValue();
			}
		}
		
	}
%>
	<h1>Hello <%=username %></h1>
	<p>Cookie = <%=myCookie %></p>
	<p>Session = <%=sessionId %></p>
	<form action="<%=response.encodeURL("LogoutAction") %>" method = "post">
		<button class = "btn btn-warning btn-block">Logout</button>
	</form>
</body>
</html>
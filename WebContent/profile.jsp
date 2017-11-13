<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="crowdFunding.model.*, crowdFunding.tool.*, java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Profile</title>
<link rel="stylesheet" href="CSS/bootstrap.css"/>
<link rel="stylesheet" href="CSS/profile.css"/>
<script src="Javascript/jquery-1.11.1.min.js"></script>
</head>
<body>
	<jsp:include page="/Component/navbar.jsp"/>
<%
	User user = (User)request.getAttribute("user");
	ProjectDao projectDao = new ProjectDao();
	InvestDao investDao = new InvestDao();
	List<Project> projects = new ArrayList();
	projects = projectDao.findProjectByOwner(user);
	List<Invest> invests = new ArrayList();
	invests = investDao.findInvestByInvestor(user);
	FollowProjectDao dao = new FollowProjectDao();
	List<FollowProject> follows = new ArrayList();
	follows = dao.findAllFollow(user);
	
	/*To check whether user is followed by current login user  */
	User master = (User)session.getAttribute("user");
	FollowUserDao followUserDao = new FollowUserDao();
	FollowUser followUser = followUserDao.findFollowByMaster(master, user); 
	/*get the user's follow users  */
	List<FollowUser> users = followUserDao.findAllFollow(user);
	
%>
	<div class="container">
		<div class="content-block">
			<div class="header">
				<div class="avatar">
				<!--Set the user's avatar, if user does not provide avatar url, it will get the avatar from gravatar.com  -->
				<% if(user.getImageUrl() == null || user.getImageUrl().equals("")) {
						Tool tool = new Tool();
						String hashText = tool.MD5(user.getEmail());
						String avatarUrl = "http://www.gravatar.com/avatar/" + hashText + "?d=retro";
				%>
					<img src=<%=avatarUrl%> height="140" width="140">
				<%} else { %>
				  <img src=<%= user.getImageUrl()%> width="140" height="140">
				  <% } %>
				</div>
				<div class="info">
					<h2><%=user.getFirstName() %> <%=user.getLastName() %></h2>
					<p><%=user.getEmail() %></p>
				</div>
				<% if(session.getAttribute("username") != null){ %>
					<%	if(session.getAttribute("username").equals(user.getUsername())) { %>
					<a class="btn btn-primary btn-lg right" href="createProject.jsp">Publish a Project</a>
					<a class="btn btn-success btn-lg right" href="/CrowdFunding/editProfile.jsp">Edit Profile</a>
					<% } else { %>
						<% if(followUser != null && !followUser.equals("")) { %>
						<a class="btn btn-danger btn-lg right unfollow" href="javascript:;" data-id="<%= user.getUsername()%>">Un-Follow</a>
						<% } else{ %>
						<a class="btn btn-primary btn-lg right follow" href="javascript:;" data-id="<%=user.getUsername() %>">Follow</a>
						<% } %>
					<% } %>
					<% } %>
			</div>
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
			<div class="heading">
				<h4>INVESTMENT</h4>
			</div>
			<div class="investment">
				<% if(invests != null) { %>
					<% for(Invest invest : invests) { %>
					<div class="project-small">
						<div class="lead image">
							<img src=<%= invest.getProject().getImageUrl() %> width="160" height="160">
						</div>
						<div class="title lead">
							<a href="/CrowdFunding/Project?projectId=<%=invest.getProject().getId()%>"><%= invest.getProject().getName() %></a>
						</div>
						<div class="shortDes">
							<p><%= invest.getProject().getShortDescription() %></p>
						</div>
					</div>
					<% } %>
				<% } else { %>
				<div><h1>No invest</h1></div>
				<% } %>
			</div>
			<div class="heading">
				<h4>Followed Projects</h4>
			</div>
			<div class="project">
				<% if(follows != null) { %>
					<% for (FollowProject follow : follows) { %>
						<div class="project-small">
							<div class="lead image">
								<img src=<%= follow.getFollowedProject().getImageUrl() %> width="160" height="160">
							</div>
							<div class="title lead">
								<a href="/CrowdFunding/Project?projectId=<%=follow.getFollowedProject().getId()%>"><%= follow.getFollowedProject().getName() %></a>
							</div>
							<div class="shortDes">
								<p><%= follow.getFollowedProject().getShortDescription() %></p>
							</div>
						</div>
					<% } %>
				<% }else { %>
					<div><h1>No collection</h1></div>
				<% } %>
			</div>
		</div>
		<div class="heading">
				<h4>Followed Users</h4>
			</div>		
			<div class="project">
			<% if (users != null) { 
					for (FollowUser followedUser : users) { %>
				<div class="project-small">
					<div class="lead image">
						<% if(followedUser.getFollowedUser().getImageUrl() == null || followedUser.getFollowedUser().getImageUrl().equals("")) {
						Tool tool = new Tool();
						String hashText = tool.MD5(user.getEmail());
						String avatarUrl = "http://www.gravatar.com/avatar/" + hashText + "?d=retro";
				%>
					<img src=<%=avatarUrl%> height="180" width="180">
				<%} else { %>
				  <img src=<%= followedUser.getFollowedUser().getImageUrl()%> width="180" height="180">
				  <% } %>
					</div>
					<hr>
					<div class="title lead"><a href="/CrowdFunding/ProfileAction?username=<%= followedUser.getFollowedUser().getUsername()%>"><span><%=followedUser.getFollowedUser().getFirstName()%></span><span> <%=followedUser.getFollowedUser().getLastName() %></span></a></div>
				</div>
				<%} %>
			<% } else {%>
				<div><h1>No followed users</h1></div>
			<% } %>
			</div>
	</div>
	<script>
	$(document).ready(function (){
		$('.follow').click(function () {
			var username = $(this).data('id');
			if(username){
				$.post('/CrowdFunding/FollowUserAction?username=' + username, function (result) {
					location.reload();
				});
			}
			return false;
		});
		
		$('.unfollow').click(function () {
			var username = $(this).data('id');
			if(username){
				$.ajax({
					url:'/CrowdFunding/FollowUserAction?username=' + username, 
					type:'PUT',
					success: function (result) {
						location.reload();
						}
				});
			}
			return false;
		});
		
		
	});
	</script>
</body>
</html>
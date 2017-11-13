<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="crowdFunding.model.*, crowdFunding.tool.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Project</title>
<link rel="stylesheet" href="CSS/bootstrap.css"/>
<link rel="stylesheet" href="CSS/profile.css"/>
<script src="Javascript/jquery-1.11.1.min.js"></script>
</head>
<body>
<%
	Project project = (Project)request.getAttribute("project");
	User user = (User)session.getAttribute("user");
	FollowProjectDao dao = new FollowProjectDao();
	FollowProject follow = dao.findFollowByMaster(user, project);
%>

	<jsp:include page="/Component/navbar.jsp"/>
	<div class="container">
		<div class="header">
			<div class="avatar">
				<img alt="<%=project.getName() %>" src="<%= project.getImageUrl()%>"
					height="140" width="140">
			</div>
			<div class="info">
				<h2><%=project.getName() %></h2>
				<p><%=project.getShortDescription() %></p>
			</div>
			<%if(session.getAttribute("username") != null && !session.getAttribute("username").equals("")){ %>
			<% 	if(session.getAttribute("username").equals(project.getOwner().getUsername())) { %>
				<a class="btn btn-primary btn-lg right" href="/CrowdFunding/editProject.jsp?projectId=<%=project.getId()%>">Edit</a> 
					<%} %>
			<%} %>
			<%if(session.getAttribute("username") != null && !session.getAttribute("username").equals("")){ %>
			<% 	if(!session.getAttribute("username").equals(project.getOwner().getUsername())) { %>
				<% if(follow != null && !follow.equals("")) {%>
					<a href="javascript:;" data-id="<%=project.getId()%>" class="btn btn-info btn-lg right unfollow">Un-Follow</a>
				<% } else {%>
					<a href="javascript:;" data-id="<%=project.getId()%>" class="btn btn-info btn-lg right follow">Follow</a>
					<%} %>
					<%} %>
				<%} %>
		</div>
		<div class="heading">
			<h4>Description</h4>
		</div>
		<div class="block_model">
			<div class="left_block">
				<div class="description">
					<div class="lead">
						<img alt="<%=project.getName() %>"
							src="<%=project.getImageUrl()%>" height="300" width="300">
					</div>
					<div>
						<p><%=project.getDescription() %></p>
					</div>

				</div>
			</div>
			<div class="right_block">
				<div class="side-bar">
					<h4 class="lead">FUNDRAISING</h4>
					<h5 class="lead">Goal: $<%=project.getGoal() %></h5>
					<h5 class="lead">Raised: $<%=project.getRaised()%></h5>
				</div>
				<div class="side-bar">
					<h4 class="lead">Interested in this project?</h4>
					<p>
						<a class="btn btn-success btn-lg full" href="/CrowdFunding/InvestAction?projectId=<%=project.getId() %>">Invest</a>
					</p>
				</div>
			</div>
		</div>

		<div class="heading">
			<h4 class="col-md-6 space">Founder</h4>
		</div>
		<div class="block_model">
			<div class="left_block">
				<div class="founder">
					<div class="avatar">
						<!--Set the user's avatar, if user does not provide avatar url, it will get the avatar from gravatar.com  -->
						<% if(project.getOwner().getImageUrl() == null || project.getOwner().getImageUrl().equals("")) {
									Tool tool = new Tool();
									String hashText = tool.MD5(project.getOwner().getEmail());
									String avatarUrl = "http://www.gravatar.com/avatar/" + hashText +"?size=140";
							%><img alt="avatar" src="<%=avatarUrl%>">
						<%} else { %>
						<img alt="avatar" src="<%= project.getOwner().getImageUrl()%>">
						<% } %>
					</div>
					<div class="info">
						<a
							href="/CrowdFunding/ProfileAction?username=<%=project.getOwner().getUsername() %>"><h2><%=project.getOwner().getFirstName() %>
								<%=project.getOwner().getLastName() %></h2></a>
						<p><%=project.getOwner().getEmail() %></p>
					</div>
				</div>
			</div>
		</div>

	</div>
	<footer> <jsp:include page="/Component/footer.html" /> </footer>
	<script>
	$(document).ready(function (){
		$('.follow').click(function () {
			var projectId = $(this).data('id');
			console.log(projectId);
			if(projectId){
				$.post('/CrowdFunding/FollowAction?projectId=' + projectId, function (result) {
					location.reload();
				});
			}
			return false;
		});
		
		$('.unfollow').click(function () {
			var projectId = $(this).data('id');
			if(projectId){
				$.ajax({
					url:'/CrowdFunding/FollowAction?projectId=' + projectId, 
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
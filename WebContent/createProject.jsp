<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="crowdFunding.model.*, java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Create a project</title>
<link rel="stylesheet" href="CSS/bootstrap.css"/>
<link rel="stylesheet" href="CSS/project.css"/>
</head>
<body>
<%
	CategoryDao categoryDao = new CategoryDao();
	List<Category> categories = new ArrayList();
	categories = categoryDao.findAll();	
%>
	<jsp:include page="/Component/navbar.jsp"/>
	<div class="container">
		<div class="errorMessage"><p class="bg-danger">${errorMessage}</p></div>
		<h1>Create a project</h1>
		<form role="form" id="project" action="/CrowdFunding/Project" method="POST">
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-8">
					<label>	Project Name</label>
					<input type="text" name="name" placeholder="Project Name" class="form-control"/>
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-8">
					<label>Raise Goal</label>
					<input type="number" name="goal" placeholder="Raise Goal: Please input Number" class="form-control"/>
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-8">
					<label>Short Description</label>
					<textarea rows="5" class="form-control" name="shortDescription" placeholder="Short Description"></textarea>				
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-8">
					<label>Description</label>
					<textarea rows="10" class="form-control" name="description" placeholder="description"></textarea>				
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-8">
					<label>	Image</label>
					<input type="url" name="imageUrl" placeholder="Image" class="form-control"/>
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-8">
					<label>	Category</label>
					<select class="form-control" name="category" form="project">
						<% for(Category category: categories) {%>
						<option value=<%=category.getCategory() %> ><%=category.getCategory() %></option>
						<% } %>
					</select>
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-8">
					<button class="btn btn-success btn-lg">Submit</button>
				</div>
			</div>
		</form>
	</div>
</body>
</html>
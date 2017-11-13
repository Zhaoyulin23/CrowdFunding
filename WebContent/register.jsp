<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registration</title>
<link rel ="stylesheet" href="CSS/bootstrap.css"/>
</head>
<body>
<!--Navigation bar in the top of the page  -->
	<jsp:include page="/Component/LogoutNavbar.jsp"/>
	<div class = "container">
		<h1>Create an account</h1>
		<div class="errorMessage"><p class="bg-danger">${errorMessage}</p></div>
		<form class="form-horizontal" role="form" action="RegisterAction" method="POST">
			<div class="form-group">
				<label for="username" class="col-sm-2 control-label">Username</label>
				<div class="col-sm-8">
					<input type="text" id="username" name="username" placeholder="Username" class="form-control">
				</div>
			</div>
			<div class="form-group">
				<label for="firstName" class="col-sm-2 control-label">First Name</label>
				<div class="col-sm-8">
					<input type="text" id="firstName" name="firstName" placeholder="First Name" class="form-control">
				</div>
			</div>
			<div class="form-group">
				<label for="lastName" class="col-sm-2 control-label">Last Name</label>
				<div class="col-sm-8">
					<input type="text" id="lastName" name="lastName" placeholder="Last Name" class="form-control">
				</div>
			</div>
			<div class="form-group">
				<label for="email" class="col-sm-2 control-label">Email</label>
				<div class="col-sm-8">
					<input type="email" id="email" name="email" placeholder="Email" class="form-control">
				</div>
			</div>
			<div class="form-group">
				<label for="credit" class="col-sm-2 control-label">Credit</label>
				<div class="col-sm-8">
					<input type="text" id="credit" name="credit" placeholder="Credit" class="form-control">
				</div>
			</div>
			<div class="form-group">
				<label for="password" class="col-sm-2 control-label">Password</label>
				<div class="col-sm-8">
					<input type="password" id="password" name="password" placeholder="Password" class="form-control">
				</div>
			</div>
			<div class="form-group">
				<label for="imageUrl" class="col-sm-2 control-label">Image</label>
				<div class="col-sm-8">
					<input type="url" id="imageUrl" name="imageUrl" placeholder="Image" class="form-control">
				</div>
			</div>
			<div class="form-group">
    			<div class="col-sm-offset-2 col-sm-10">
      				<button type="submit" class="btn btn-default">Register</button>
    			</div>
    		</div>
		</form>
	</div>
</body>
</html>
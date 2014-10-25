<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Course Client Site</title>
<script type="text/javascript">
	function validateForm() {
		var uname = document.forms["loginForm"]["uname"].value;
		var pswd = document.forms["loginForm"]["pswd"].value;
		if (uname == null || uname == "" || pswd == null || pswd == "") {
			document.getElementById("userName").innerText = 'Either User Name or Password is Empty.';
			document.getElementById("Password").innerText = 'Either User Name or Password is Empty.';
			return false;
		}
	}
</script>
</head>
<body>

	<h2>Welcome To Course Web Site</h2>

	<br />
	<form name="loginForm"
		action="${pageContext.request.contextPath}/login"
		onsubmit="return validateForm()" method="post">
		<br />
		<p>
			User Name: <input type="text" name="uname" /> <label id="userName"></label>
		</p>
		<p>
			Password <input type="password" name="pswd" /><label id="Password"></label>
		</p>
		<input type="submit" name="SignIn" value="Sign In!" />



	</form>
</body>
</html>
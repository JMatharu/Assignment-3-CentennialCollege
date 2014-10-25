<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Main Page</title>
<script type="text/javascript">
	function validateForm() {
		var c = document.forms["COURSE"]["courseCode"].value;
		if (c == null || c == "") {
			document.getElementById("CourseCode").innerText = 'Please Enter Course code';
			return false;
		}
	}
</script>
</head>
<body>
	<%@ include file="banner.jsp"%>
	<form action="${pageContext.request.contextPath}/core" method="post" name="COURSE"
		onsubmit="return validateForm()" >
		<table>
			<tr>
				<td>Course Code: <input type="text" name="courseCode" />
				</td>
				<td><label id="CourseCode"></label></td>
			</tr>
		</table>
		<table>
			<tr>
				<td><input type="submit" name="action" value="Display Course" /></td>
			</tr>
		</table>

	</form>
	<form action="${pageContext.request.contextPath}/addcourse"
		method="post">
		<input type="submit" name="add" value="Add New Course" />
	</form>
	<form action="${pageContext.request.contextPath}/display" method="post">
		<input type="submit" name="back" value="Back" />
	</form>
</body>
</html>
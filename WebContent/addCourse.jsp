<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Course</title>
</head>
<body>
	<%@ include file="banner.jsp"%>
	<h3>Add Course</h3>
	<form action="${pageContext.request.contextPath}/core" method="post">
		<table>

			<tbody>
				<tr>
					<td>Course Code:</td>
					<td><input type="text" name="courseCode" /></td>
				</tr>
				<tr>
					<td>Course Title:</td>
					<td><input type="text" name="courseTitle" /></td>
				</tr>
				<tr>
					<td>Professor's Name:</td>
					<td>First Name: <input type="text" name="firstName" />Last
						Name: <input type="text" name="lastName" /></td>
				</tr>
				<tr></tr>
				<tr>
					<td>Professor Id:</td>
					<td><input type="text" name="professorId" /></td>
				</tr>
				<tr>
					<td><input type="submit" name="action" value="Add Course" /></td>
				</tr>
			</tbody>
		</table>
	</form>
	<form action="${pageContext.request.contextPath}/display" method="post">
		<input type="submit" name="back" value="Back" />
	</form>

</body>
</html>
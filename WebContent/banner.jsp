<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>


	<h3>
		Hello "
		<c:out value="${sessionScope.userName}"></c:out>
		" Welcome to College Website!
	</h3>


	<form action="${pageContext.request.contextPath}/login" method="get">
		<input type="submit" value="Logout" />



		<p>
			Total Number of Courses :


			<c:out value="${applicationScope.NumOfCourses}"></c:out>
		</p>

		<p>
			Last Update :
			<c:out value="${applicationScope.lastUpdate}"></c:out>
		</p>
	</form>
</body>
</html>
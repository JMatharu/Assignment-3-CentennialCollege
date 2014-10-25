<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Error Page</title>
</head>
<body>
	<h3>Sorry! Empty fields can't be Deleted or Edited. Please retry
		again with valid Course Code</h3>
	<form action="${pageContext.request.contextPath}/display" method="post">
		<input type="submit" name="back" value="Back" />
	</form>
</body>
</html>
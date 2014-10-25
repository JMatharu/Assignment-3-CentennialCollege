<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Display Course</title>
<script type="text/javascript">
	function validateForm() {
		document.getElementById("Submit").onclick = function() {
			var date = new Date();
			document.getElementById("texts").value = date.toString();
		};
		function noUpdate() {
			var cC = document.forms["COURSEEMPTY"]["courseCode"].value;
			var cT = document.forms["COURSEEMPTY"]["courseTitle"].value;
			var fn = document.forms["COURSEEMPTY"]["firstName"].value;
			var lN = document.forms["COURSEEMPTY"]["lastName"].value;
			var p = document.forms["COURSEEMPTY"]["professorId"].value;
			if (cC == null || cC == "" || cT == null || cT == "" || fn == null
					|| fn == "" || lN == null || lN == "" || p == null
					|| p == "") {
				document.getElementById("NoUpdate").innerText = 'All Fields are empty! Enter correct/valid course.';
				return false;
			}
		}
	}
</script>

</head>
<body>
	<%@ include file="banner.jsp"%>
	<h2>
		<c:choose>
			<c:when test="${requestScope.CALLER=='Display Course'}">
		       Course Details!!!
		    </c:when>
			<c:when test="${requestScope.CALLER=='Add Course'}">
		       Course added successfully!!!
		    </c:when>
			<c:when test="${requestScope.CALLER=='Delete Course'}">
		       Course has been deleted!!!
		    </c:when>
			<c:when test="${requestScope.CALLER=='Update Course'}">
		       Course Has been Updated!!!
		    </c:when>

		</c:choose>

	</h2>

	<c:if test="${requestScope.CALLER=='Display Course'}">
		<form action="${pageContext.request.contextPath}/core" method="post"
			name="COURSEEMPTY" onsubmit="return noUpdate()">


			<table>
				<tbody>
					<tr>
						<td>Course Code:</td>
						<td><input type="text" name="courseCode"
							value="<c:out value="${requestScope.course.courseCode}"/>" /><label
							id="NoUpdate"></label></td>
					</tr>
					<tr>
						<td>Course Title:</td>
						<td><input type="text" name="courseTitleEmpty"
							value="${requestScope.course.courseTitle}" /><label
							id="NoUpdate"></label></td>
					</tr>
					<tr>
						<td>Professor:</td>
						<td><input type="text" name="firstName"
							value="<c:out value="${requestScope.course.professor.firstName}"/>" />
							<input type="text" name="lastName"
							value="<c:out value="${requestScope.course.professor.lastName}"/>" /><label
							id="NoUpdate"></label></td>
					</tr>
					<tr>
						<td>Professor Id:</td>
						<td><input type="text" name="professorId"
							value="<c:out value="${requestScope.course.professor.profId}"/>" /><label
							id="NoUpdate"></label></td>
					</tr>
				</tbody>
			</table>

			<c:if test="${requestScope.CALLER=='Display Course'}">
				<input type="submit" name="action" value="Delete Course" />
				<input type="submit" name="action" value="Update Course" />
			</c:if>
		</form>

	</c:if>
	<form action="${pageContext.request.contextPath}/display" method="post">
		<input type="submit" name="back" value="Back" />
	</form>


</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
	<style>
		body {
			font-family: Sans-Serif;
		}
		table, tr, th, td {
			border: 1px solid black;
			border-collapse: collapse;
			padding: 10px;
		}
		thead {
			background-color: silver;
		}
		
	</style>
	<meta charset="UTF-8">
	<title>Courses</title>
</head>
<body>
	<div class="container">
		<h1>Welcome, <c:out value="${currentUser.name}" /></h1>
		<div class="box">
			
				<h2>Courses</h2>
				<p>Click 'Add' to sign up for a course</p>
			
			<table>
				<thead>
					<tr>
						<th>Course ID#</th>
						<th>Course</th>
						<th>Instructor</th>
						<th>Signup capacity</th>
						<th>Action</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${allCourses}" var="c">
						<tr>
							<td><c:out value="${c.id}" /></td>
							<td><a href="/courses/${c.id}"><c:out value="${c.name }" /></a></td>
							<td><c:out value="${c.instructor}" /></td>
							<td><c:out value="${c.students.size() }"/>/<c:out value="${c.capacity}" /></td>
							<td>
								<c:choose>
									<c:when test="${c.students.contains(currentUser)}">
										<p>Already Added</p>
									</c:when>
									<c:when test="${c.students.size() >= c.capacity }">
										<p>Full</p>
									</c:when>
									<c:otherwise>	
										<form:form action="/add" method="POST" modelAttribute="midTable">
											<form:hidden path="student" value="${currentUser.id}"></form:hidden>										
											<form:hidden path="course" value="${c.id}"></form:hidden>
											<input type="submit" value="Add" />										
										</form:form>
									</c:otherwise>	
								</c:choose>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<div style="margin-top: 30px;">			
				<h4><a href="/courses/new">Add a Course</a></h4>
			</div>
		</div>
	
	</div>
</body>
</html>
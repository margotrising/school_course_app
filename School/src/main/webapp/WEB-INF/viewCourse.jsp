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
		div.container{
			margin: 0 auto;
			padding: 25px;
		}
		
		div.box {
			display: inline-block;
			vertical-align: top;
			padding-left: 50px;
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
	<title>View Course</title>
</head>
<body>
	<div class="container">
		<h1>Course name: <c:out value="${course.name}"/></h1>
		<h3>Instructor: <c:out value="${course.instructor}"/></h3>
		<h3>Signups: <c:out value="${course.students.size()}"/></h3>
		<table>
			<thead>
				<tr>
					<th>Student Name</th>
					<th>Sign up Date</th>
					<th>Action</th>
				</tr>
			</thead>
			<tbody>
					<c:forEach items="${course.students}" var="c">
				<tr>
						<td><c:out value="${c.name}"/></td>
						
						
						
						<td><c:out value="${c.createdAt}"/></td>
						<td>
							<c:choose>
								<c:when test="${c.id == currentUser.id}">
									<form action="/remove/${currentUser.id}/${course.id}" method="post" >
										<input type="hidden" name="_method" value="delete" />
										<input type="submit" value="Remove" />
									</form>
								</c:when>
							</c:choose>
						</td>			
				</tr>
					</c:forEach>
			</tbody>
		</table>
		
		<div style="margin-top: 50px;">
			<p style="margin-bottom: 25px;"><a href="/courses/${course.id }/edit">Edit</a></p>
			
			<form action="/delete/${course.id}" method="POST">
				<input type="hidden" name="_method" value="delete"/>
				<input type="submit" value="Delete" />
			</form>
		</div>
		
	
	
	</div>
</body>
</html>
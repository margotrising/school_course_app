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
		div.container {
			margin: 0 auto;
			padding: 25px;
		}
		
		div.box {
			display: inline-block;
			vertical-align: top;
			padding-left: 50px;
		}
	</style>

	<meta charset="UTF-8">
	<title>Insert title here</title>
</head>
<body>
	<div class="container">
		<p style="text-align: right;"><a href="/courses">Back to Courses</a></p>
		<h1>Create a new course</h1>
		
		<div class="box">
			<p style="color: red;"><form:errors path="newCourse.*"/></p>
		    
		    <form:form method="POST" action="/courses" modelAttribute="newCourse">
		        <p>
		            <form:label path="name">Name:</form:label>
		            <form:input type="text" path="name"/>
		        </p>
		        <p>
		            <form:label path="instructor">Instructor:</form:label>
		            <form:input path="instructor"/>
		        </p>
		        <p>
		            <form:label path="capacity">Capacity:</form:label>
		            <form:input path="capacity"/>
		        </p>
		        <input type="submit" value="Create"/>
		    </form:form>
		</div>
	</div>
</body>
</html>
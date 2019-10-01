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
		
	</style>
	<meta charset="UTF-8">
	<title>View Event</title>
</head>
<body>
	<h1>Edit event: <c:out value="${courseObj.name}" /> </h1>
	<form:form action="/courses/${courseObj.id}/edit" method="POST" modelAttribute="courseObj">
	<input type="hidden" name="_method" value="put"/>
		<p>
	            <form:label path="name">Name:</form:label>
	            <form:errors path="name"/>
	            <form:input type="text" path="name"/>
	           
	           
	        </p>
	        <p>
	           <form:label path="instructor">Instructor:</form:label>
	            <form:errors path="instructor"/>
		          <form:input path="instructor"/>
	        </p>
	        <p>
	            <form:label path="capacity">Capacity:</form:label>
	            <form:errors path="capacity"/>
		           <form:input path="capacity"/>
	            
	        </p>
	    
	        <input type="submit" value="Update"/>
	</form:form>
	
	
</body>
</html>
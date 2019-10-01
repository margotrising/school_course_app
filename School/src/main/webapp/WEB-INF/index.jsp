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
		input {
			margin: 5px 0;
		}
		
	</style>
	<meta charset="UTF-8">
	<title>Register/Login</title>
</head>
<body>
	<div class="container">
	    <div class="box">
		<h1>Register</h1>
		    <p style="color: red;"><form:errors path="newStudent.*"/></p>
		    
		    <form:form method="POST" action="/registration" modelAttribute="newStudent">
		        <p>
		            <form:label path="name">Name:</form:label>
		            <form:input type="text" path="name"/>
		        </p>
		        <p>
		            <form:label path="email">Email:</form:label>
		            <form:input type="email" path="email"/>
		        </p>
		        <p>
		            <form:label path="password">Password:</form:label>
		            <form:password path="password"/>
		        </p>
		        <p>
		            <form:label path="passwordConfirmation">Password Confirmation:</form:label>
		            <form:password path="passwordConfirmation"/>
		        </p>
		        <input type="submit" value="Register"/>
		    </form:form>
	    </div>
	    
	    <div class="box">
		    <h1 style="margin-top: 75px">Login</h1>
		    <p style="color: red;"><c:out value="${error}" /></p>
		    <form method="post" action="/login">
		        <p>
		            <label for="email">Email</label>
		            <input type="text" id="email" name="email"/>
		        </p>
		        <p>
		            <label for="password">Password</label>
		            <input type="password" id="password" name="password"/>
		        </p>
		        <input type="submit" value="Login"/>
		    </form>
	    </div>
    
	</div>
</body>
</html>
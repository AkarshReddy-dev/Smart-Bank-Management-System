<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>Customer Login</h1>
<form action="login" method="post">
Account Number : <input type="text" name="accountNumber">
Password       : <input type="password" name="password">
<input type="submit" value="Login">
</form>
<% if (request.getAttribute("msg") != null) { %>

    <p> 
        ${msg}
    </p>

<% } %>

<% if (request.getAttribute("status") != null) { %>

    <p> 
        ${status}
    </p>

<% } %>


${msg}
</body>
</html>
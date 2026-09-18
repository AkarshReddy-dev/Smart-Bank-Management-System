<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Create Cutsomer</title>
</head>
<body>
<h1>Create Customer</h1>
<form action="createCustomer" method="post">
Full Name : <input type="text" name="name"><br><br>
Email     : <input type="email" name="email"><br><br>
Mobile    : <input type="number" name="mobileNumber"><br><br>
<input type="submit" value="Create Customer">
<br><br>
</form>
<% if (request.getAttribute("msg") != null) { %>

    <p> 
        ${msg}, Account number: ${accountNumber} and Temporary Password: ${TPassword} 
    </p>

<% } %>
</body>
</html>
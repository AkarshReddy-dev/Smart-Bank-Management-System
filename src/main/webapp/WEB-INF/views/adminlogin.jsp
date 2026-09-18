<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin login</title>
</head>
<body>
<h1>ADMIN LOGIN</h1>
<form action="login" method="post">
Username       : <input type="text" name="number"><br><br>
Password       : <input type="password" name="password"><br><br>
<input type="submit" value="Create Account">
</form>
${msg}
</body>
</html>
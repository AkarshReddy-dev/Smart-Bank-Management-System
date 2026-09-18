<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>FUND TRANSFER</h1>	
<form action="fundTransfer" method ="post">
TO Account Number:-<br><br>
<input type="text" name="accountNumber">
Amount:-
<input type="number" name="amount">
<input type="submit" value="Transfer">
<br><br>
${msg}
</form>
</body>
</html>
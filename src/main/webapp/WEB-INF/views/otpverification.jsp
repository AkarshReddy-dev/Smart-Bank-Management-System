<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>OTP Verification</h1>
<form action="otp-verification" method="post">
Enter OTP : <input type="text" name="enteredOTP">
<input type="submit" value="confirm" >
</form>
${msg}
</body>
</html>
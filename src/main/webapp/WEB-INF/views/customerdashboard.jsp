<%@page import="com.model.Customer"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<% Customer customer=(Customer)session.getAttribute("customer"); %>
Welcome <%=customer.getName() %>
<br><br>
Account number :<%=customer.getAccountNumber() %>
Balance :<%=customer.getBalance() %>
<a href="fundTransfer">Fund Transfer</a><br><br>
<a href="miniStatement">Mini Statement</a><br><br>
<a href="applyLoan">Apply Loan</a><br><br>
<a href="viewLoanStatus">View Loan Status</a>
<br><br>
<a href="logout">Logout</a>
</body>
</html>
<%@page import="java.util.List"%>
<%@ page import="com.model.Loan" language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>Pending Loan Applications</h1>
<table>
<tr>
	<th>LoanID</th>
	<th>Account</th>
	<th>Type</th>
	<th>Amount</th>
	<th>Tenure</th>
	<th>Intrest</th>
	<th>Action</th>
</tr>
<%
List<Loan> loans=(List<Loan>)request.getAttribute("loans");

for(Loan tempLoan:loans) {
	
%>
	
<tr>
	<td><%=tempLoan.getLoanId()%></td>
	<td><%=tempLoan.getCustomer().getAccountNumber() %></td>
	<td><%=tempLoan.getLoanType()%></td>
	<td><%=tempLoan.getAmount() %></td>
	<td><%=tempLoan.getTenure() %></td>
	<td><%=tempLoan.getInterest() %></td>
	<td>
	<form action="updateLoan" method="post">
	<input type="hidden" name="loanId" value="<%=tempLoan.getLoanId()%>">
	<input type="hidden" name="status" value="ACCEPTED">
	<input type="submit" value="Accept">
	</form>
	<form action="updateLoan" method="post">
	<input type="hidden" name="loanId" value="<%=tempLoan.getLoanId()%>">
	<input type="hidden" name="status" value="REJECTED">
	<input type="submit" value="Reject">
	</form>
	</td>
</tr>
	

	<% } %>
	
</table>
</body>
</html>
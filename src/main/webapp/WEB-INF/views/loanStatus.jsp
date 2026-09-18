<%@page import="java.util.List"%>
<%@page import="com.model.Loan"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>CUSTOMER MANAGEMENT</h1>
<table>
	<tr>
		<th>TYPE</th>
		<th>Amount</th>
		<th>Tenure</th>
		<th>Intrest</th>
		<th>Status</th>
	</tr>
	
	<% List<Loan> loans=(List<Loan>)request.getAttribute("loans");
	
	for(Loan allLoans:loans){
		
	
	
	%>
	
	<tr>
		<td><%=allLoans.getLoanType() %></td>
		<td><%=allLoans.getAmount() %></td>
		<td><%=allLoans.getTenure()%></td>
		<td><%=allLoans.getInterest() %></td>
		<td><%=allLoans.getLoanStatus() %></td>
	</tr>
	
	<%
	}
	%>
</table>
</body>
</html>
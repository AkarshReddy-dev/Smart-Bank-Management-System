<%@page import="com.model.Transaction"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>Transaction Reports</h1>

<%
List<Transaction> transactions=(List<Transaction>)request.getAttribute("transactions");
%>

<form action=generateReports method="post">
Report Type:<br>
<select name="reportType">
<option value="">--Select --</option>
<option value="Monthly">Monthly</option>
<option value="Annual">Annual</option>
</select>

Monthly<br>
<input type="month" name="month">

Year:<br>
<input type="number" name="year">

Customer:<br>
<select>
<option value="">--Select --</option>
<option value="All Customers">All Customers</option>
</select>
<input type="submit" value="Generate Report">
</form>

<%
if(transactions!=null){
	
	if(transactions.isEmpty()){
%>		
		<p>NO TRANSACTIONS FOUND</p>
<%
	}
	else {
%>

<table>
	<tr>
		<th>Date</th>
		<th>Type</th>
		<th>From</th>
		<th>To</th>
		<th>Amount</th>		
	</tr>
	
<%
for(Transaction tempTransaction:transactions){
	
%>
	<tr>
		<td><%=tempTransaction.getDate() %></td>
		<td><%=tempTransaction.getType() %></td>
		<td><%=tempTransaction.getSenderAccountNumber() %></td>
		<td><%=tempTransaction.getReceiverAccountNumber() %></td>
		<td><%=tempTransaction.getAmount() %></td>
	</tr>
<%
}
%>
</table>

<%
}
%>

<%
}
%>
</body>
</html>
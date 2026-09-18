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
<h1>TRANSACTION STATEMENT</h1>
<table>
	<tr>
		<th>Date</th>
		<th>Type</th>
		<th>From</th>
		<th>To</th>
		<th>Amount</th>
	</tr>
<%
List<Transaction> transactions=(List<Transaction>)request.getAttribute("transactions");

for(Transaction allTransaction:transactions){
	

%>

	<tr>
		<td><%=allTransaction.getDate()%></td>
		<td><%=allTransaction.getType() %></td>
		<td><%=allTransaction.getSenderAccountNumber() %></td>
		<td><%=allTransaction.getReceiverAccountNumber() %></td>
		<td><%=allTransaction.getAmount() %></td>
	</tr>
	
<%
}
%>
</table>
</body>
</html>
<%@page import="java.util.List"%>
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
<h1>Customer Management</h1>
<table>
	<tr>
		<th>Account Number</th>
		<th>Name</th>
		<th>Email</th>
		<th>Mobile</th>
		<th>Status</th>
		<th>Action</th> 
	</tr>
<%
	List<Customer> customer =(List<Customer>)request.getAttribute("customers");

for(Customer tempCustomer:customer) {
	

%>
	<tr>
		<td><%=tempCustomer.getAccountNumber() %></td>
		<td><%=tempCustomer.getName() %></td>
		<td><%=tempCustomer.getEmail() %></td>
		<td><%=tempCustomer.getMobileNumber() %></td>
		<% if(tempCustomer.isStatus()){ %>
			
			<td>ACTIVE</td>
			
		<%
		}
		else{%>
			
			<td>DEACTIVE</td>
		<%
		}
		%>


		
		
		<% if(tempCustomer.isStatus()){ %>
		<td>	
		<form action="manageCustomer" method="post">
		<input type="hidden" name="customerId" value=<%=tempCustomer.getCustomerId() %>>
		<input type="hidden" name="status" value="false">
		<input type="submit" value="DEACTIVATE">
		</form>
		</td>
			
		<%
		}
		else{%>
			
		<td>	
		<form action="manageCustomer" method="post">
		<input type="hidden" name="customerId" value=<%=tempCustomer.getCustomerId() %>>
		<input type="hidden" name="status" value="true">
		<input type="submit" value="ACTIVATE">
		</form>
		</td>
		<%
		}
		%>
		
		
		
		
		
	</tr>
<%
}
%>
</table>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="applyLoan" method="post">
Loan Type:<br>
<select name="loanType">
<option value="">-- Select Loan Type --</option>
<option value="Home Loan">Home Loan</option>
<option value="Personal Loan">Personal Loan</option>
<option value="Education Loan">Education Loan</option>
</select>
Amount:<br>
<input type="number" name="amount">
Tenure(Months):
<input type="number" name="tenure">
<input type="submit" value="Apply">
</form>
<% if (request.getAttribute("msg") != null) { %>

    <p> 
        ${msg}
    </p>

<% } %>
</body>
</html>
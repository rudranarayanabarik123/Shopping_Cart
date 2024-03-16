<%@page import="com.jsp.shopping_cart.dto.Customer"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<% Customer c=(Customer)session.getAttribute("customerinfo");
if(c!=null) {
%>
<h1>${mesg}</h1>
<h1><a href="displayproducts">Display all products</a></h1>
<h1><a href="readbrandfromcustomer.jsp">Display product by brand</a></h1>
<%} else { %>
	<h1><a href="customerloginform.jsp">login first</a></h1>
<%} %>

</body>
</html>
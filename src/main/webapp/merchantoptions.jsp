<%@page import="com.jsp.shopping_cart.dto.Merchant"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<% Merchant m=(Merchant)session.getAttribute("merchantinfo"); %>
<% if(m!=null) {%>
<h1>${mesg}</h1>
<!--  <h1>Merchant options is pending</h1>-->
<h1><a href="addproduct">Add product</a></h1>

<h1><a href="viewallproducts.jsp">View all products</a></h1>
<%}
else {%>
<h1><a href="merchantloginform.jsp">Please login first</a></h1>
<%} %>
</body>
</html>
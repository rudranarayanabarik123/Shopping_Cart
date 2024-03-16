<%@page import="com.jsp.shopping_cart.dto.Orders"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
		<h1>${mesg}</h1>
	<% Orders o=(Orders)request.getAttribute("ordersobj"); %>
	<h1>The Total Price is : <%=o.getTotalprice()%></h1>
		
</body>
</html>
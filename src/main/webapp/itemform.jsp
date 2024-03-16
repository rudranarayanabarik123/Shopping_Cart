<%@page import="com.jsp.shopping_cart.dto.Product"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<% Product p=(Product)request.getAttribute("productobj"); %>

<form action="additemtocart">
<!-- we are using readonly here because customer can only view but can't able to change 
but we are not providei9ng for quantity because we want to take input of quantity from customer -->
id:<input type="hidden" name="id" value=<%=p.getId() %> readonly="true" ><br>
Brand:<input type="text" name="brand" value=<%=p.getBrand()%> readonly="true"><br>
model:<input type="text" name="model" value=<%=p.getModel() %> readonly="true" ><br>
category:<input type="text" name="category" value=<%=p.getCategory() %> readonly="true"><br>
price:<input type="number" name="price" value=<%=p.getPrice() %> readonly="true"><br>
Quantity:<input type="number" name="quantity"><br>
<input type="submit" value="Add to cart">
</form>

</body>
</html>
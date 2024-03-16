<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form:form action="savemerchant" modelAttribute="merchantobj">
Enter name: <form:input path="name"/>
Enter mobnum: <form:input path="mobnum"/>
Enter email: <form:input path="email"/>
Enter password: <form:input path="password"/>
<input type="submit">
</form:form>
</body>
</html>
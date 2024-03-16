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
<form action="loginvalidation"  method="post">
Enter email:<input type="email" name="email">
Enter password:<input type="password" name="password">
<input type="submit">
</form>

</body>
</html>
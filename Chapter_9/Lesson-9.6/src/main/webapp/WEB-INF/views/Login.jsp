<%--
  Created by IntelliJ IDEA.
  User: Andrey
  Date: 16.03.2018
  Time: 21:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<form action="${pageContext.servletContext.contextPath}/signin" method="post">
    <input type="hidden" name="login" value="${login}">
    Login: <input type="text" name="login">
    Password: <input type="password" name="password">
    <input type="submit" value="update">
</form>
</body>
</html>

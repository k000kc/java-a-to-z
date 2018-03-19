<%--
  Created by IntelliJ IDEA.
  User: Andrey
  Date: 16.03.2018
  Time: 21:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>AddUser</title>
</head>
<body>
<form action="${pageContext.servletContext.contextPath}/add" method="post">
    Login: <input type="text" name="login"></br>
    Password: <input type="password" name="password"></br>
    Name: <input type="text" name="name"></br>
    Email: <input type="text" name="email"></br>
    Role: <input type="text" name="role"></br>
    <input type="submit" value="add">
</form>
</body>
</html>

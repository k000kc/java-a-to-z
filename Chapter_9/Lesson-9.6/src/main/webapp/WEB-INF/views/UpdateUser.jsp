<%--
  Created by IntelliJ IDEA.
  User: Andrey
  Date: 16.03.2018
  Time: 21:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>UpdateUser</title>
</head>
<body>
<form action="${pageContext.servletContext.contextPath}/update" method="post">
    <input type="hidden" name="login" value="${login}">
    Name: <input type="text" name="name">
    </br>
    Email: <input type="text" name="email">
    </br>
    New password: <input type="password" name="password">
    </br>
    Role: <input type="text" name="role">
    </br>
    <input type="submit" value="update">
</form>
</body>
</html>

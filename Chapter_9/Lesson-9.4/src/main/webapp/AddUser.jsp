<%--
  Created by IntelliJ IDEA.
  User: Andrey
  Date: 01.03.2018
  Time: 20:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>AddUser</title>
</head>
<body>
    <form action="<%=request.getContextPath()%>/add" method="post">
        Login: <input type="text" name="login">
        Name: <input type="text" name="name">
        Email: <input type="text" name="email">
        <input type="submit" value="add">
    </form>
</body>
</html>

<%@ page import="ru.apetrov.model.User" %>
<%@ page import="ru.apetrov.storege.UserStore" %><%--
  Created by IntelliJ IDEA.
  User: Andrey
  Date: 01.03.2018
  Time: 19:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>UpdateUser</title>
</head>
<body>

Login: <%=request.getParameter("login")%>
<form action="<%=request.getContextPath()%>/update" method="post">
    <input type="text" hidden name="login" value="<%=request.getParameter("login")%>">
    Name: <input type="text" name="name">
    Email: <input type="text" name="email">
    <input type="submit" value="update">
</form>

</body>
</html>

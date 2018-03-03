<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Andrey
  Date: 02.03.2018
  Time: 19:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ShowUsers</title>
</head>
<body>
<table border="2">
    <caption>Users List</caption>
    <c:forEach items="${users}" var="user">
    <tr>
        <td><c:out value="${user.login}"></c:out></td>
        <td><c:out value="${user.name}"></c:out></td>
        <td><c:out value="${user.email}"></c:out></td>
        <td><c:out value="${user.createDate}"></c:out></td>
        <td>
            <form action="${pageContext.servletContext.contextPath}/update" method="get">
            <input type="submit" value="update">
            <input type="hidden" name="login" value="${user.login}">
            </form>
        </td>
        <td>
            <form action="${pageContext.servletContext.contextPath}/delete" method="post">
                <input type="submit" value="delete">
                <input type="hidden" name="login" value="${user.login}">
            </form>
        </td>
    </tr>
    </c:forEach>
</table>
    <form action="${pageContext.servletContext.contextPath}/add" method="get">
        <input type="submit" value="add">
    </form>
</body>
</html>

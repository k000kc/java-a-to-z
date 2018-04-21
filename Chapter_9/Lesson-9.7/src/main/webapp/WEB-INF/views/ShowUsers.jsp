<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Andrey
  Date: 16.04.2018
  Time: 18:49
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
        <td><c:out value="${user.login}"/></td>
        <td><c:out value="${user.name}"/></td>
        <td><c:out value="${user.email}"/></td>
        <td><c:out value="${user.createDate}"/></td>
        <td><c:out value="${user.role}"/></td>
        <td><c:out value="${user.city}"/></td>
        <td><c:out value="${user.country}"/></td>
        <c:if test="${role == 'admin' || login == user.login}">
            <td>
                <form action="${pageContext.servletContext.contextPath}/update" method="get">
                    <input type="submit" value="update">
                    <input type="hidden" name="login" value="${user.login}">
                </form>
            </td>
        </c:if>
        <c:if test="${role == 'admin'}">
            <td>
                <form action="${pageContext.servletContext.contextPath}/delete" method="post">
                    <input type="submit" value="delete">
                    <input type="hidden" name="login" value="${user.login}">
                </form>
            </td>
        </c:if>
        </tr>
    </c:forEach>
</table>
<c:if test="${role == 'admin'}">
    <form action="${pageContext.servletContext.contextPath}/add" method="get">
        <input type="submit" value="add">
    </form>
</c:if>
</body>
</html>

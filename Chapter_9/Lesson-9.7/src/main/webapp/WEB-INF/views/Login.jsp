<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Andrey
  Date: 16.04.2018
  Time: 18:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/style.css">
</head>
<body>
<c:if test="${error != ''}">
    <div style="background-color: red">
        <c:out value="${error}"/>
    </div>
</c:if>

<form action="${pageContext.servletContext.contextPath}/signin" method="post">
    <div class="main">
    <div class="field">
        <label>Login:</label>
        <input type="text" name="login" class="text">
    </div>
    <div class="field">
        <label>Password:</label>
        <input type="password" name="password" class="text">
    </div>
        <div class="field">
            <input type="submit" value="Submit" class="submit">
        </div>
    </div>
</form>
</body>
</html>

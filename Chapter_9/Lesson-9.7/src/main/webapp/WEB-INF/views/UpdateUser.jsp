<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <title>UpdateUser</title>
    <script type="text/javascript" src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="WEB-INF/js/citieslistajax.js"></script>
</head>
<body>
<form action="${pageContext.servletContext.contextPath}/update" method="post">
    <input type="hidden" name="login" value="${login}">
    Name: <input type="text" name="name"><br>
    Email: <input type="text" name="email"><br>
    New password: <input type="password" name="password"><br>
    Role: <select name="role">
    <option selected>user</option>
    <c:if test="${role == 'admin'}">
        <option>admin</option>
    </c:if>
    </select><br>
    <form action="" id="location">
        <label>Country:</label>
        <select id="countrylist">
            <option value="">Choose the country</option>
            <c:forEach items="${countries}" var="country">
                <option>${country}</option>>
            </c:forEach>
        </select></br>

        <label>City:</label>
        <select id="citylist" disabled="disabled">
            <option value="">Choose the city</option>
        </select></br>
    </form>
    <input type="submit" value="update">
</form>
</body>
</html>

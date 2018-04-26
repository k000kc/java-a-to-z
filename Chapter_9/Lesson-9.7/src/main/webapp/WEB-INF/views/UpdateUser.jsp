<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>UpdateUser</title>
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
    <script src="${pageContext.servletContext.contextPath}/js/loadcities.js"></script>
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
        <label>Country:</label>
        <select id="countrylist">
            <option>Choose the country</option>
            <c:forEach items="${countries}" var="country">
                <option>${country}</option>
            </c:forEach>
        </select></br>

        <label>City:</label>
        <select id="citylist" name="city" disabled="disabled">

        </select></br>
    <input type="submit" value="update">
</form>
</body>
</html>

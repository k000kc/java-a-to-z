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
    <title>AddUser</title>
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
    <script src="${pageContext.servletContext.contextPath}/js/loadcities.js"></script>
    <script src="${pageContext.servletContext.contextPath}/js/check-validate.js"></script>
</head>
<body>
<form action="${pageContext.servletContext.contextPath}/add" method="post">
    Login: <input type="text" name="login"></br>
    Password: <input type="password" name="password"></br>
    Name: <input type="text" name="name"></br>
    Email: <input type="text" name="email"></br>
    Role: <select name="role">
        <option selected>user</option>
        <option>admin</option>
    </select></br>
        <label>Country:</label>
        <select id="countrylist" name="country">
            <option>Choose the country</option>
            <c:forEach items="${countries}" var="country">
                <option>${country}</option>
            </c:forEach>
        </select></br>

        <label>City:</label>
        <select id="citylist" name="city" disabled="disabled">

        </select></br>
    <input type="submit" value="add" onclick="return validate(this.form)">
</form>
</form>
</body>
</html>

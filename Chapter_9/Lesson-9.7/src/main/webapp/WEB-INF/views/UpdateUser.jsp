<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>UpdateUser</title>
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
    <script src="${pageContext.servletContext.contextPath}/js/loadcities.js"></script>
    <script src="${pageContext.servletContext.contextPath}/js/check-validate.js"></script>
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/style.css">
</head>
<body>
<form action="${pageContext.servletContext.contextPath}/update" method="post">
    <div class="main">
        <input type="hidden" name="login" value="${login}">
        <div class="field">
            <label>New password: </label>
            <input type="password" name="password" class="text">
        </div>
        <div class="field">
            <label>Name: </label>
            <input type="text" name="name" class="text">
        </div>
        <div class="field">
            <label>Email:</label>
            <input type="text" name="email" class="text">
        </div>
        <div class="field">
            <label>Role:</label>
            <select name="role">
                <option selected>user</option>
                <c:if test="${role == 'admin'}">
                    <option>admin</option>
                </c:if>
            </select>
        </div>
        <div class="field">
            <label>Country:</label>
            <select id="countrylist" name="country">
                <option>Choose the country</option>
                <c:forEach items="${countries}" var="country">
                    <option>${country}</option>
                </c:forEach>
            </select>
        </div>
        <div class="field">
            <label>City:</label>
            <select id="citylist" name="city" disabled="disabled">
            </select>
        </div>
        <div class="field">
            <input type="submit" value="update" class="submit" onclick="return validate(this.form)">
        </div>
    </div>
</form>
</body>
</html>

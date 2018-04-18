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
    <form action="" id="location">
        <label>Country:</label>
        <select id="countrylist">
            <option value="">Choose the country</option>
        </select></br>

        <label>City:</label>
        <select id="citylist" disabled="disabled">
            <option value="">Choose the city</option>
        </select></br>
    </form>
    <input type="submit" value="add">
</form>
</body>
</html>

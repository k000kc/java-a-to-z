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
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.0/jquery.min.js"></script>
    <script>
        $(document).ready(function () {
            $("#countrylist").change(function () {
                $("#citylist").empty();
                var country = $("#countrylist option:selected").val();

                $.ajax({
                    url : 'ajax',
                    type : 'get',
                    data: {country : country},
                    complete: function (data) {
                        var result = "<option>Choose the city</option>";
                        var cities = JSON.parse(data.responseText);
                        for (var i = 0; i != cities.length; i++) {
                            result += "<option>" + cities[i] + "</option>"
                        }
                        var selectOptions = $("#citylist");
                        selectOptions.attr("disabled", false);
                        $(result).appendTo(selectOptions);
                    }
                })
            })
        })
    </script>

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
            <option value="">Choose the country</option>
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

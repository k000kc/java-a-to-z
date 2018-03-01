<%@ page import="ru.apetrov.model.User" %>
<%@ page import="ru.apetrov.storege.UserStore" %><%--
  Created by IntelliJ IDEA.
  User: Andrey
  Date: 27.02.2018
  Time: 20:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <table border="2">
        <caption>Users List</caption>
            <% for (User user : UserStore.getInstance().getAll()) {%>
            <tr>
                <td><%=user.getLogin()%></td>
                <td><%=user.getName()%></td>
                <td><%=user.getEmail()%></td>
                <td><%=user.getCreateDate()%></td>
                <td>
                    <form action="<%=request.getContextPath()%>/UpdateUser.jsp" method="get">
                        <input type="submit" value="update">
                        <input type="text" hidden name="login" value="<%=user.getLogin()%>">
                    </form>
                </td>
                <td>
                    <form action="<%=request.getContextPath()%>/delete" method="post">
                        <input type="submit" value="delete">
                        <input type="text" hidden name="login" value="<%=user.getLogin()%>">
                    </form>
                </td>
            </tr>
            <%}%>
    </table>
    <form action="<%=request.getContextPath()%>/AddUser.jsp" method="get">
        <input type="submit" value="add">
    </form>
</body>
</html>

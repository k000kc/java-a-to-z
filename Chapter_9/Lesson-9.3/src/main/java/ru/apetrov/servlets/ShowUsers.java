package ru.apetrov.servlets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.apetrov.model.User;
import ru.apetrov.storege.UserStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

/**
 * Created by Andrey on 15.02.2018.
 */
public class ShowUsers extends HttpServlet {

    private UserStore userStore = UserStore.getInstance();

    private static final Logger log = LoggerFactory.getLogger(ShowUsers.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());

        StringBuilder sb = new StringBuilder();
        sb.append("<table border='2'>");
        sb.append("<caption>Users List</caption>");
        for (User user : this.userStore.getAll()) {
            sb.append("<tr>");
            sb.append(String.format("<td>%s</td>", user.getLogin()));
            sb.append(String.format("<td>%s</td>", user.getName()));
            sb.append(String.format("<td>%s</td>", user.getEmail()));
            sb.append(String.format("<td>%s</td>", user.getCreateDate()));

            sb.append(String.format("<td><form action='%s/update' method='get'>", req.getContextPath()));
            sb.append("<input type='submit' value='update'>");
            sb.append(String.format("<input type='text' hidden name='login' value='%s'>", user.getLogin()));
            sb.append("</form></td>");

            sb.append(String.format("<td><form action='%s/delete' method='post'>", req.getContextPath()));
            sb.append("<input type='submit' value='delete'>");
            sb.append(String.format("<input type='text' hidden name='login' value='%s'>", user.getLogin()));
            sb.append("</form></td>");

            sb.append("</tr>");
        }
        sb.append("</table>");
        sb.append(String.format("<form action='%s/add' method='get'><input type='submit' value='add'></form>", req.getContextPath()));

        writer.append("<!DOCTYPE html>" +
                "<head>" +
                "    <title>Title</title>" +
                "</head>" +
                "<body>" +
                sb.toString() +
                "</body>" +
                "</html>");
        writer.flush();
    }

    @Override
    public void destroy() {
        try {
            this.userStore.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

package ru.apetrov.servlets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.apetrov.storege.UserStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Andrey on 20.02.2018.
 */
public class DeleteUser extends HttpServlet {

    private UserStore userStore = UserStore.getInstance();

    private static final Logger log = LoggerFactory.getLogger(ShowUsers.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        writer.append("<!DOCTYPE html>" +
                "<head>" +
                "    <title>Title</title>" +
                "</head>" +
                "<body>" +
                "<form action='" + req.getContextPath() + "/delete' method='post'>" +
                "Name: <input type='text' name='login'/>" +
                "<input type='submit' value='delete'/>" +
                "</form>" +
                "<br>" +
                "<form action='" + req.getContextPath() + "/show' method='get'>" +
                "<input type='submit' value='show users'/>" +
                "</form>" +
                "<br>" +
                "</body>" +
                "</html>");
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String login = req.getParameter("login");
        this.userStore.delete(login);
        this.doGet(req, resp);
    }
}

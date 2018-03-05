package ru.apetrov.controller;

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
import java.sql.Timestamp;

/**
 * Created by Andrey on 20.02.2018.
 */
public class UpdateUser extends HttpServlet {

    /**
     * logger.
     */
    private static final Logger log = LoggerFactory.getLogger(ShowUsers.class);

    /**
     * user store.
     */
    private UserStore userStore = UserStore.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();
        String login = req.getParameter("login");
        writer.append("<!DOCTYPE html>" +
                "<head>" +
                "    <title>Title</title>" +
                "</head>" +
                "<body>" +
                "<form action='" + req.getContextPath() + "/update' method='post'>" +
                "Login: " +
                login +
                "<br>" +
                "<input type='text' hidden name='login' value='" +
                login +
                "'/>" +
                "Name: <input type='text' name='name'/>" +
                "email: <input type='text' name='email'/>" +
                "<input type='submit' value='update'/>" +
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
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        this.userStore.update(new User(login, name, email, new Timestamp(System.currentTimeMillis())));
        this.doGet(req, resp);
    }

    @Override
    public void destroy() {
        try {
            this.userStore.close();
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
    }
}

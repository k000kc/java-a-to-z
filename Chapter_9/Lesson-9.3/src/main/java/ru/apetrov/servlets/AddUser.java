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
import java.sql.Timestamp;

/**
 * Created by Andrey on 20.02.2018.
 */
public class AddUser extends HttpServlet {


    private UserStore userStore = UserStore.getInstance();

    private static final Logger log = LoggerFactory.getLogger(ShowUsers.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();
        writer.append("<!DOCTYPE html>" +
                "<head>" +
                "    <title>Title</title>" +
                "</head>" +
                "<body>" +
                "<form action='" + req.getContextPath() + "/add' method='post'>" +
                "Login: <input type='text' name='login'/>" +
                "Name: <input type='text' name='name'/>" +
                "email: <input type='text' name='email'/>" +
                "<input type='submit' value='add'/>" +
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
        this.userStore.put(new User(login, name, email, new Timestamp(System.currentTimeMillis())));
        this.doGet(req, resp);
    }
}

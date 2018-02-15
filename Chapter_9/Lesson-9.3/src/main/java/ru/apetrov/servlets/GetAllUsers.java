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

/**
 * Created by Andrey on 15.02.2018.
 */
public class GetAllUsers extends HttpServlet {

    private UserStore userStore = UserStore.getInstance();

    private static final Logger log = LoggerFactory.getLogger(GetAllUsers.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append("<!DOCTYPE html>" +
                "<html lang=\"en\">" +
                "<head>" +
                "    <meta charset=\"UTF-8\">" +
                "    <title>Title</title>" +
                "</head>" +
                "<body>" +
                "<form action='" + req.getContextPath() + "/allusers' method='post'>" +
                "Name: <input type='text' name='login'/>" +
                "<input type='submit'/>" +
                "</form>" +
                "<br/>" +
                this.userStore.getAll().toString() +
                "</body>" +
                "</html>");
        writer.flush();
    }
}

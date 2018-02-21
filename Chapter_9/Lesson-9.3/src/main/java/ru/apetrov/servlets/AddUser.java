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
import java.sql.Timestamp;

/**
 * Created by Andrey on 20.02.2018.
 */
public class AddUser extends HttpServlet {


    private UserStore userStore = UserStore.getInstance();

    private static final Logger log = LoggerFactory.getLogger(ShowUsers.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String login = req.getParameter("login");
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        this.userStore.put(new User(login, name, email, new Timestamp(System.currentTimeMillis())));
    }
}

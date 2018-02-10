package ru.apetrov.servlets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.apetrov.Settings;
import ru.apetrov.model.User;
import ru.apetrov.storage.UserStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 * Created by Andrey on 05.02.2018.
 */
public class UsersServlet extends HttpServlet {

    /**
     * UserStore.
     */
    private final UserStore userStore = UserStore.getInstance();

    /**
     * logger.
     */
    private static final Logger log = LoggerFactory.getLogger(Settings.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();
        this.init();
        for (User user : this.userStore.getAll()) {
            writer.append(user.toString());
            writer.append(System.getProperty("line.separator"));
            writer.flush();
        }
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
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String login = req.getParameter("login");
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        this.userStore.put(new User(login, name, email, new Timestamp(System.currentTimeMillis())));
        this.doGet(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String login = req.getParameter("login");
        this.userStore.delete(login);
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

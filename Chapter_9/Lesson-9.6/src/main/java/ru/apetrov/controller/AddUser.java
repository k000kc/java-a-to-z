package ru.apetrov.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.apetrov.model.User;
import ru.apetrov.model.UserStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;

public class AddUser extends HttpServlet {

    /**
     * logger.
     */
    private static final Logger log = LoggerFactory.getLogger(UserStore.class);

    /**
     * User store.
     */
    private UserStore userStore = UserStore.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/AddUser.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            resp.setContentType("text/html");
            String login = req.getParameter("login");
            String password = req.getParameter("password");
            String name = req.getParameter("name");
            String email = req.getParameter("email");
            String role = req.getParameter("role");
            this.userStore.put(new User(login, password, name, email, new Timestamp(System.currentTimeMillis()), role));
            resp.sendRedirect(req.getContextPath());
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
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

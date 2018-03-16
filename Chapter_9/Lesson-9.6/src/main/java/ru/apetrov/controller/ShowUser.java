package ru.apetrov.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.apetrov.model.UserStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class ShowUser extends HttpServlet {

    /**
     * logger.
     */
    private static final Logger log = LoggerFactory.getLogger(UserStore.class);

    /**
     * user store.
     */
    private final UserStore userStore = UserStore.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.setAttribute("users", userStore.getAll());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        req.getRequestDispatcher("/WEB-INF/views/ShowUsers.jsp").forward(req, resp);
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

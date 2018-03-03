package ru.apetrov.controller;

import ru.apetrov.model.User;
import ru.apetrov.model.UserStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;

/**
 * update user.
 */
public class UpdateUser extends HttpServlet {

    /**
     * user store.
     */
    private final UserStore store = UserStore.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("login", req.getParameter("login"));
        req.getRequestDispatcher("/WEB-INF/views/UpdateUser.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String login = req.getParameter("login");
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        this.store.update(new User(login, name, email, new Timestamp(System.currentTimeMillis())));
        resp.sendRedirect(req.getContextPath());
    }
}

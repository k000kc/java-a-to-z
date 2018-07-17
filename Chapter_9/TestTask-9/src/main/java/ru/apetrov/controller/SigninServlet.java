package ru.apetrov.controller;

import ru.apetrov.models.User;
import ru.apetrov.repository.UserRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SigninServlet extends HttpServlet {

    private UserRepository repository = new UserRepository();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(String.format("%s/view/login.html", req.getContextPath())).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        User user = this.repository.getUser(login);
        if (user.getPassword().equals(password)) {
            HttpSession session = req.getSession();
            synchronized (session) {
                session.setAttribute("user", user);
            }
            resp.sendRedirect(String.format("%s/index", req.getContextPath()));
        } else {
            doGet(req, resp);
        }
    }
}

package ru.apetrov.controller;

import ru.apetrov.model.UserStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ShowUser extends HttpServlet {

    private final UserStore userStore = UserStore.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("users", userStore.getAll());
        req.getRequestDispatcher("/WEB-INF/views/ShowUsers.jsp").forward(req, resp);
    }
}

package ru.apetrov.controller;

import com.google.gson.Gson;
import ru.apetrov.models.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class ShowUsers extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/json");
        User user = (User) req.getSession().getAttribute("user");

        req.getRequestDispatcher(String.format("%s/view/index.html", req.getContextPath())).forward(req, resp);
    }
}

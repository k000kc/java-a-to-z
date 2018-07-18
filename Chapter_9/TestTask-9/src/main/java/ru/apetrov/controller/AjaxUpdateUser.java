package ru.apetrov.controller;

import com.google.gson.Gson;
import ru.apetrov.repository.UserRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class AjaxUpdateUser extends HttpServlet {

    private UserRepository repository = new UserRepository();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/view/update.html").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        PrintWriter writer = resp.getWriter();
        this.update(req, resp);
        String gson = new Gson().toJson(this.repository.findAll());
        writer.write(gson);
        writer.flush();
        writer.close();
    }

    private void update(HttpServletRequest req, HttpServletResponse resp) {
        String login = req.getParameter("login");
    }
}

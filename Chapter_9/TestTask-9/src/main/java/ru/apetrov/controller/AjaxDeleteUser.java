package ru.apetrov.controller;

import com.google.gson.Gson;
import ru.apetrov.repository.UserRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

public class AjaxDeleteUser extends HttpServlet {

    private UserRepository repository;

    @Override
    public void init() throws ServletException {
        this.repository = UserRepository.getInstance();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        PrintWriter writer = resp.getWriter();
        deleteUser(req, resp);
        String gson = new Gson().toJson(this.repository.findAll());
        writer.write(gson);
        writer.flush();
        writer.close();
    }

    private void deleteUser(HttpServletRequest req, HttpServletResponse resp) {
        String login = req.getParameter("login");
        try {
            this.repository.deleteUser(login);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void destroy() {
        this.repository.closeConection();
    }
}

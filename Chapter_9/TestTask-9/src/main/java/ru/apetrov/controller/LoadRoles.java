package ru.apetrov.controller;

import com.google.gson.Gson;
import ru.apetrov.dao.RoleImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class LoadRoles extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("application/json");
        PrintWriter writer = resp.getWriter();
        String gson = new Gson().toJson(new RoleImpl().getAll());
        System.out.println(gson);
        writer.write(gson);
        writer.flush();
        writer.close();
    }
}

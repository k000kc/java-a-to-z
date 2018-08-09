package ru.apetrov.controller;

import com.google.gson.Gson;
import ru.apetrov.dao.MusicTypeImpl;
import ru.apetrov.models.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class AjaxCurentRole extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        PrintWriter writer = resp.getWriter();
        User user = (User) req.getSession().getAttribute("user");
        String roleType = "[{\"currentRole\":\"" + user.getRole().getRoleType() + "\"}]";
        writer.write(roleType);
        writer.flush();
        writer.close();
    }
}

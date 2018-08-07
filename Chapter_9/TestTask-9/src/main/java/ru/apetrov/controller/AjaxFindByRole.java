package ru.apetrov.controller;

import com.google.gson.Gson;
import ru.apetrov.models.Role;
import ru.apetrov.models.User;
import ru.apetrov.repository.UserRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;

public class AjaxFindByRole extends HttpServlet {

    private UserRepository repository;

    @Override
    public void init() throws ServletException {
        this.repository = UserRepository.getInstance();
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        PrintWriter writer = resp.getWriter();
        String gson = new Gson().toJson(this.find(req, resp));
        writer.write(gson);
        writer.flush();
        writer.close();
    }

    private Set<User> find(HttpServletRequest req, HttpServletResponse resp) {
        Role role = new Role();
        role.setRoleType(req.getParameter("role"));
        return repository.findUserByRole(role);
    }


    @Override
    public void destroy() {
        this.repository.closeConection();
    }

}

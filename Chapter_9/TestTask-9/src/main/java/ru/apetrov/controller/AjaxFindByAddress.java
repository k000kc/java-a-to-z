package ru.apetrov.controller;

import com.google.gson.Gson;
import ru.apetrov.models.Address;
import ru.apetrov.models.User;
import ru.apetrov.repository.UserRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;

public class AjaxFindByAddress extends HttpServlet {

    private UserRepository repository;

    @Override
    public void init() throws ServletException {
        this.repository = UserRepository.getInstance();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        PrintWriter writer = resp.getWriter();
        String gson = new Gson().toJson(this.find(req, resp));
        writer.write(gson);
        writer.flush();
        writer.close();
    }

    private Set<User> find(HttpServletRequest req, HttpServletResponse resp) {
        Address address = new Address();
        address.setCountry(req.getParameter("country"));
        address.setCity(req.getParameter("city"));
        address.setStreet(req.getParameter("street"));
        address.setHouse(req.getParameter("house"));
        return this.repository.findUserByAddress(address);
    }

    @Override
    public void destroy() {
        this.repository.closeConection();
    }
}


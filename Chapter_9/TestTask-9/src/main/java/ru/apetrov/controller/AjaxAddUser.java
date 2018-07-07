package ru.apetrov.controller;

import com.google.gson.Gson;
import ru.apetrov.dao.MusicTypeImpl;
import ru.apetrov.dao.RoleImpl;
import ru.apetrov.models.Address;
import ru.apetrov.models.MusicType;
import ru.apetrov.models.Role;
import ru.apetrov.models.User;
import ru.apetrov.repository.UserRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AjaxAddUser extends HttpServlet {

    private UserRepository repository = UserRepository.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        PrintWriter writer = resp.getWriter();
        addUser(req, resp);
        String gson = new Gson().toJson(this.repository.findAll());
        writer.write(gson);
        writer.flush();
        writer.close();
    }

    private void addUser(HttpServletRequest req, HttpServletResponse resp) {
        User user = new User();
        user.setLogin(req.getParameter("login"));
        user.setPassword(req.getParameter("password"));
        user.setName(req.getParameter("name"));
        user.setEmail(req.getParameter("email"));

        Address address = new Address();
        address.setCountry(req.getParameter("country"));
        address.setCity(req.getParameter("city"));
        address.setStreet(req.getParameter("street"));
        address.setHouse(req.getParameter("house"));
        Integer roleId = Integer.valueOf(req.getParameter("role"));
        Role role = new RoleImpl().getById(roleId);

        this.repository.createUser(user, address, role);

        String musics = req.getParameter("musics");
        System.out.println(musics);
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(musics);
        while (matcher.find()) {
            Integer musicTypeId = Integer.valueOf(matcher.group());
            this.repository.putMusicTypeToUser(user, new MusicTypeImpl().getById(musicTypeId));
        }
    }
}

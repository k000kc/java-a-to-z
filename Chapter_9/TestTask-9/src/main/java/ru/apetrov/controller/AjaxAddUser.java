package ru.apetrov.controller;

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

    UserRepository repository = UserRepository.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        PrintWriter writer = resp.getWriter();
        addUser(req, resp);
    }

    private void addUser(HttpServletRequest req, HttpServletResponse resp) {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String country = req.getParameter("country");
        String city = req.getParameter("city");
        String street = req.getParameter("street");
        String house = req.getParameter("house");
        String role = req.getParameter("role");
        String musics = req.getParameter("musics");
        List<Integer> musicId = new ArrayList<>();
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(musics);
        while (matcher.find()) {
            musicId.add(Integer.valueOf(matcher.group()));
        }
        System.out.println(musicId.get(2));
    }
}

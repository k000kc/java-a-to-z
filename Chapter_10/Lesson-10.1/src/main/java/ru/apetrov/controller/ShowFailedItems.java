package ru.apetrov.controller;

import com.google.gson.Gson;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.apetrov.models.Item;
import ru.apetrov.repository.ItemStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class ShowFailedItems extends HttpServlet {

    private SessionFactory factory;
    private ItemStore store;

    @Override
    public void init() throws ServletException {
        this.factory = new Configuration().configure().buildSessionFactory();
        this.store = new ItemStore(factory);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        PrintWriter writer = resp.getWriter();
        List<Item> items = store.getFailedItems();
        String gson = new Gson().toJson(items);
        writer.write(gson);
        writer.flush();
        writer.close();
    }

    @Override
    public void destroy() {
        this.factory.close();
    }
}

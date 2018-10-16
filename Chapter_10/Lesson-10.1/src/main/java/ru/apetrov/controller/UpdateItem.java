package ru.apetrov.controller;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.apetrov.models.Item;
import ru.apetrov.repository.ItemStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;

public class UpdateItem extends HttpServlet {

    private SessionFactory factory;
    private ItemStore store;

    @Override
    public void init() throws ServletException {
        this.factory = new Configuration().configure().buildSessionFactory();
        this.store = new ItemStore(factory);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        System.out.println(req.getParameter("id"));
        Item item = this.store.getById(Integer.parseInt(req.getParameter("id")));
        if (item.isDone()) {
            item.setDone(false);
        } else {
            item.setDone(true);
        }
        this.store.update(item);
    }

    @Override
    public void destroy() {
        this.factory.close();
    }
}

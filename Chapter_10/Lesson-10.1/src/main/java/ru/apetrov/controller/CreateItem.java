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

public class CreateItem extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        Item item = new Item();
        item.setDesc(req.getParameter("description"));
        item.setCreated(new Timestamp(System.currentTimeMillis()));
        item.setDone(false);

        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        ItemStore store = new ItemStore(factory);
        store.create(item);
        factory.close();
    }
}

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
import java.io.PrintWriter;
import java.sql.Timestamp;

public class CreateItem extends HttpServlet {

    private ItemStore store;

    @Override
    public void init() throws ServletException {
        this.store = ItemStore.getInstance();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        Item item = new Item();
        item.setDesc(req.getParameter("description"));
        item.setCreated(new Timestamp(System.currentTimeMillis()));
        item.setDone(false);
        this.store.create(item);
        PrintWriter writer = resp.getWriter();
        writer.write("accept");
        writer.flush();
    }
}

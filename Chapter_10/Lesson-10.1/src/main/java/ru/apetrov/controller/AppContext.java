package ru.apetrov.controller;

import ru.apetrov.repository.ItemStore;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class AppContext implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("inti");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ItemStore.getInstance().getSessionfactory().close();
        System.out.println("destroy");
    }
}

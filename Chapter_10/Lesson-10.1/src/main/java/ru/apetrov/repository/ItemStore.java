package ru.apetrov.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.apetrov.models.Item;

import java.util.List;

public class ItemStore {

    private final SessionFactory factory;

    public ItemStore(SessionFactory factory) {
        this.factory = factory;
    }

    public void create(Item item) {
        Session session = this.factory.openSession();
        session.beginTransaction();
        session.save(item);
        session.getTransaction().commit();
        session.close();
    }

    public List<Item> getAll() {
        Session session = this.factory.openSession();
        session.beginTransaction();
        List<Item> result = session.createQuery("FROM Item").list();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    public List<Item> getFailedItems() {
        Session session = this.factory.openSession();
        session.beginTransaction();
        List<Item> result = session.createQuery("FROM Item I WHERE I.done = false").list();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    public void update(Item item) {
        Session session = null;
        session = this.factory.openSession();
        session.beginTransaction();
        session.update(item);
        session.getTransaction().commit();
        session.close();
    }
}

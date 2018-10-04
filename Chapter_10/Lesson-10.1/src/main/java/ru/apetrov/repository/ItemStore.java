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
    }

    public List<Item> getAll() {
        Session session = this.factory.openSession();
        session.getTransaction();
        List<Item> result = session.createQuery("from Item").list();
        return result;
    }
}

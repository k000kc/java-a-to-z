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
        session.getTransaction();
        List<Item> result = session.createQuery("FROM Item").list();
        return result;
    }

    public List<Item> getFailedItems() {
        Session session = this.factory.openSession();
        session.getTransaction();
        List<Item> result = session.createQuery("FROM Item I WHERE I.done = false").list();
        return result;
    }

    public void update(Item item) {
        Session session = this.factory.openSession();
        session.getTransaction();
        session.update(item);
        session.getTransaction().commit();
        session.close();
    }

    public Item getById(int id) {
        Session session = this.factory.openSession();
        session.getTransaction();
        Item item = (Item) session.load(Item.class, id);
        session.getTransaction().commit();
        session.close();
        return item;
    }
}

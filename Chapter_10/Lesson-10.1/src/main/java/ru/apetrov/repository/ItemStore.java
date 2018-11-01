package ru.apetrov.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.apetrov.models.Item;

import java.util.List;

public class ItemStore {

    private SessionFactory sessionfactory;
    private static ItemStore INSTANCE = null;

    private ItemStore() {
        this.sessionfactory = new Configuration().configure().buildSessionFactory();
    }

    public static ItemStore getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ItemStore();
        }
        return INSTANCE;
    }

    public SessionFactory getSessionfactory() {
        return sessionfactory;
    }

    public void create(Item item) {
        Session session = this.sessionfactory.openSession();
        session.beginTransaction();
        try {
            session.save(item);
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.getTransaction().commit();
            session.close();
        }
    }

    public List<Item> getAll() {
        Session session = this.sessionfactory.openSession();
        session.beginTransaction();
        try {
            return session.createQuery("FROM Item").list();
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.getTransaction().commit();
            session.close();
        }
    }

    public List<Item> getFailedItems() {
        Session session = this.sessionfactory.openSession();
        session.beginTransaction();
        try {
            return session.createQuery("FROM Item I WHERE I.done = false").list();
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.getTransaction().commit();
            session.close();
        }
    }

    public void update(Item item) {
        Session session = this.sessionfactory.openSession();
        session.beginTransaction();
        try {
            session.update(item);
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.getTransaction().commit();
            session.close();
        }
    }
}

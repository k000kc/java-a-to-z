package ru.apetrov.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.apetrov.models.Item;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class ItemStore {

    private SessionFactory sessionfactory;
    private static volatile ItemStore INSTANCE = null;

    private ItemStore() {
        this.sessionfactory = new Configuration().configure().buildSessionFactory();
    }

    public static ItemStore getInstance() {
        if (INSTANCE == null) {
            synchronized (ItemStore.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ItemStore();
                }
            }
        }
        return INSTANCE;
    }

    public SessionFactory getSessionfactory() {
        return sessionfactory;
    }

    private <T> T  getTx(Function<Session, T> command) {
        Session session = this.sessionfactory.openSession();
        session.beginTransaction();
        try {
            return command.apply(session);
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.getTransaction().commit();
            session.close();
        }
    }

    private void setTx(Consumer<Session> command) {
        Session session = this.sessionfactory.openSession();
        session.beginTransaction();
        try {
            command.accept(session);
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.getTransaction().commit();
            session.close();
        }
    }

    public List<Item> getAll() {
        return this.getTx(session -> session.createQuery("from Item").list());
    }

    public List<Item> getFailedItems() {
        return this.getTx(session -> session.createQuery("FROM Item AS i WHERE i.done = false").list());
    }

    public void create(Item item) {
        this.setTx(session -> session.save(item));
    }

    public void update(Item item) {
        this.setTx(session -> session.update(item));
    }
}

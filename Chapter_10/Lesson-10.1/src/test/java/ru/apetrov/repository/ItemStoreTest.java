package ru.apetrov.repository;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;
import ru.apetrov.models.Item;

import java.sql.Timestamp;
import java.util.List;

import static org.junit.Assert.*;

public class ItemStoreTest {

    @Test
    public void testadd() {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        ItemStore store = new ItemStore(factory);

        Item item = new Item();
        item.setDesc("test2");
        item.setCreated(new Timestamp(System.currentTimeMillis()));
        item.setDone(true);

        store.create(item);
        factory.close();
    }

    @Test
    public void testshow(){
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        ItemStore store = new ItemStore(factory);
        List<Item> items = store.getAll();
        for (Item i : items) {
            System.out.println(i.getDesc());
        }
        factory.close();
    }
}
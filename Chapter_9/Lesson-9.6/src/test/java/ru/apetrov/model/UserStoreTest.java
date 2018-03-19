package ru.apetrov.model;

import org.junit.Before;
import org.junit.Test;
import ru.apetrov.settings.ConnectionDB;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Objects;


public class UserStoreTest {

    private int count;
    private ConnectionDB connectionDB;
    private Connection connection;

    @Before
    public void init() {
        connectionDB = new ConnectionDB();
        connection = connectionDB.getConnection();
    }

    /**
     * tests db.
     */
    @Test
    public void testConnection() {
        this.delDB();
        this.addingDB();
    }

    @Test
    public void testDelete() {
        UserStore store = UserStore.getInstance();
        try {
            store.delete("login-5");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testUpdate() {
        UserStore store = UserStore.getInstance();
        try {
            store.update(new User("login-2", "123", "Andrey", "ham2188@mail.ru", new Timestamp(System.currentTimeMillis()),"user"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testShowUsers() {
        this.delDB();
        this.addingDB();
        UserStore store = UserStore.getInstance();
        try {
            for (User user : store.getAll()) {
                System.out.println(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testIsCredentional() {
        UserStore store = UserStore.getInstance();
        System.out.println(store.isCredentional("login-2", "123"));
    }

    public synchronized void addingDB() {
        while (count < 10) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    synchronized (UserStoreTest.class) {
                        UserStore store = UserStore.getInstance();
                        String login = String.format("login-%s", count);
                        String password = String.format("password=%s", count);
                        String name = String.format("name-%s", count);
                        String email = String.format("email@-%s", count);
                        User user = new User(login, password, name, email, new Timestamp(System.currentTimeMillis()), "admin");
                        System.out.println(user.getPassword());
                        try {
                            store.put(user);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        count++;
                    }
                }
            }).start();
        }
    }

    public synchronized void delDB() {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM users");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
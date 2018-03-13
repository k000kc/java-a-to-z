package ru.apetrov.model;

import org.junit.Before;
import org.junit.Test;
import ru.apetrov.settings.ConnectionDB;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;


public class UserStoreTest {

    private int count;

    @Before
    public void init() {
        ConnectionDB connectionDB = new ConnectionDB();
        Connection connection = connectionDB.getConnection();
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

    /**
     * tests db.
     */
    @Test
    public void testConnection() {
        while (count < 20) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    synchronized (UserStoreTest.class) {
                        UserStore store = UserStore.getInstance();
                        String login = String.format("login-%s", count);
                        String password = String.format("password=%s", count);
                        String name = String.format("name-%s", count);
                        String email = String.format("email@-%s", count);
                        store.put(new User(login, password, name, email, new Timestamp(System.currentTimeMillis())));
                        count++;
                    }
                }
            }).start();
        }
    }

}
package ru.apetrov.storege;

import org.junit.Before;
import org.junit.Test;
import ru.apetrov.ConnectionDB;
import ru.apetrov.model.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;


/**
 * Created by Andrey on 21.02.2018.
 */
public class UserStoreTest {

//    private int count;
//
//    @Before
//    public void init() {
//        ConnectionDB connectionDB = new ConnectionDB();
//        Connection connection = connectionDB.getConnection();
//        try {
//            Statement statement = connection.createStatement();
//            statement.executeUpdate("DELETE FROM users");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * tests db.
//     */
//    @Test
//    public void testConnection() {
//        while (count < 20) {
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    UserStore store = UserStore.getInstance();
//                    String login = String.format("login-%s", count);
//                    String name = String.format("name-%s", count);
//                    String email = String.format("email@-%s", count);
//                    count++;
//                    store.put(new User(login, name, email, new Timestamp(System.currentTimeMillis())));
//                }
//            }).start();
//        }
//    }
}
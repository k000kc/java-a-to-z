package ru.apetrov.storage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.apetrov.Settings;
import ru.apetrov.model.User;

import java.sql.*;

/**
 * Created by Andrey on 05.02.2018.
 */
public class UserStore {

    private static final UserStore instance = new UserStore();
    private Settings settings;
    private Connection connection;
    private static final Logger log = LoggerFactory.getLogger(Settings.class);

    private UserStore() {
        this.settings = new Settings();
        this.initConnection();
        try {
            Statement statement = this.connection.createStatement();
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS users(id serial PRIMARY KEY, user_name CHARACTER VARYING(50), login CHARACTER VARYING(30), email CHARACTER VARYING(50), create_date TIMESTAMP)");
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
    }

    public static UserStore getInstance() {
       return instance;
    }

    private void initConnection() {
        try {
            String url = this.settings.getValue("jdbc.url");
            String username = this.settings.getValue("jdbc.username");
            String password = this.settings.getValue("jdbc.password");
            this.connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
    }

    public void put(User user) {
        try {
            PreparedStatement statement = this.connection.prepareStatement("INSERT INTO users(user_name, login, email, create_date) VALUES(?, ?, ?, ?)");
            statement.setString(1, user.getName());
            statement.setString(2, user.getLogin());
            statement.setString(3, user.getEmail());
            statement.setTimestamp(4, user.getCreateDate());
            statement.execute();
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
    }
}

package ru.apetrov.storage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.apetrov.Settings;
import ru.apetrov.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrey on 05.02.2018.
 */
public class UserStore implements AutoCloseable {

    private static UserStore instance;
    private Settings settings;
    private Connection connection;
    private static final Logger log = LoggerFactory.getLogger(Settings.class);

    private UserStore() {
    }

    public static UserStore getInstance() {
        if (instance == null) {
            instance = new UserStore();
        }
       return instance;
    }

    public void initConnection() {
        try {
            this.settings = new Settings();
            String url = this.settings.getValue("jdbc.url");
            String username = this.settings.getValue("jdbc.username");
            String password = this.settings.getValue("jdbc.password");
            this.connection = DriverManager.getConnection(url, username, password);
            Statement statement = this.connection.createStatement();
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS users(login CHARACTER VARYING(30) UNIQUE PRIMARY KEY, user_name CHARACTER VARYING(50), email CHARACTER VARYING(50), create_date TIMESTAMP)");
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
    }

    public void put(User user) {
        try (PreparedStatement statement = this.connection.prepareStatement("INSERT INTO users(login, user_name, email, create_date) VALUES(?, ?, ?, ?)")) {
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getName());
            statement.setString(3, user.getEmail());
            statement.setTimestamp(4, user.getCreateDate());
            statement.execute();
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
    }

    public void delete(User user) {
        try (PreparedStatement statement = this.connection.prepareStatement("DELETE FROM users WHERE login = ?")) {
            statement.setString(1, user.getLogin());
            statement.execute();
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
    }

    public void update(User user) {
        try (PreparedStatement statement = this.connection.prepareStatement("UPDATE users SET user_name = ?, email = ?, create_date = ? WHERE login = ?")){
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setTimestamp(3, user.getCreateDate());
            statement.setString(4, user.getLogin());
            statement.execute();
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
    }

    public List<User> getAll() {
        List<User> result = new ArrayList<>();
        try (Statement statement = this.connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
            while (resultSet.next()) {
                User user = new User(resultSet.getString("login"), resultSet.getString("user_name"), resultSet.getString("email"), resultSet.getTimestamp("create_date"));
                result.add(user);
            }
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
        return result;
    }

    @Override
    public void close() throws Exception {
        this.connection.close();
    }
}

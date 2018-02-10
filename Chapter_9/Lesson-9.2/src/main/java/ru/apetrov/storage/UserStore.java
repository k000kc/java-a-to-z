package ru.apetrov.storage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.apetrov.ConnectionDB;
import ru.apetrov.Settings;
import ru.apetrov.model.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrey on 05.02.2018.
 */
public class UserStore implements AutoCloseable {

    /**
     * Storege by users.
     */
    private static UserStore instance;

    /**
     * database connection.
     */
    private Connection connection;

    /**
     * logger.
     */
    private static final Logger log = LoggerFactory.getLogger(Settings.class);

    /**
     * constructor.
     */
    private UserStore() {
    }

    /**
     * singleton instance UserStore.
     * @return UserStore.
     */
    public static UserStore getInstance() {
        if (instance == null) {
            instance = new UserStore();
            instance.initConnection();
        }
       return instance;
    }

    /**
     * initial database connection.
     */
    private void initConnection() {
        try {
            this.connection = ConnectionDB.getConnection();
            Statement statement = this.connection.createStatement();
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS users(login CHARACTER VARYING(30) UNIQUE PRIMARY KEY, user_name CHARACTER VARYING(50), email CHARACTER VARYING(50), create_date TIMESTAMP)");
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
    }

    /**
     * add User from datebase.
     * @param user user.
     */
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

    /**
     * delete User from datebase.
     * @param login user.
     */
    public void delete(String login) {
        try (PreparedStatement statement = this.connection.prepareStatement("DELETE FROM users WHERE login = ?")) {
            statement.setString(1, login);
            statement.execute();
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
    }

    /**
     * update User from datebase.
     * @param user user.
     */
    public void update(User user) {
        try (PreparedStatement statement = this.connection.prepareStatement("UPDATE users SET user_name = ?, email = ?, create_date = ? WHERE login = ?")) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setTimestamp(3, user.getCreateDate());
            statement.setString(4, user.getLogin());
            statement.execute();
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
    }

    /**
     * list all users from datebase.
     * @return list users.
     */
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

    /**
     * close datebase connection.
     * @throws SQLException exeption.
     */
    @Override
    public void close() throws SQLException {
        this.connection.close();
    }
}

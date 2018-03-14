package ru.apetrov.model;

import net.jcip.annotations.GuardedBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.apetrov.settings.ConnectionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserStore implements AutoCloseable {

    /**
     * logger.
     */
    private static final Logger log = LoggerFactory.getLogger(UserStore.class);

    /**
     * Storege by users.
     */
    @GuardedBy("this")
    private static UserStore instance;

    /**
     * database connection.
     */
    @GuardedBy("this")
    private Connection connection;


    /**
     * constructor.
     */
    private UserStore() {
    }

    /**
     * singleton instance UserStore.
     * @return UserStore.
     */
    public synchronized static UserStore getInstance() {
        if (instance == null) {
            instance = new UserStore();
            try {
                instance.initConnection();
            } catch (SQLException e) {
                log.error(e.getMessage(), e);
            }
        }
        return instance;
    }

    /**
     * initial database connection.
     */
    private synchronized void initConnection() throws SQLException {
        try {
            this.connection = new ConnectionDB().getConnection();
            this.connection.setAutoCommit(false);
            Statement statement = this.connection.createStatement();
            statement.addBatch("CREATE TABLE IF NOT EXISTS users(login CHARACTER VARYING(30) UNIQUE PRIMARY KEY, password CHARACTER VARYING(30), user_name CHARACTER VARYING(50), email CHARACTER VARYING(50), create_date TIMESTAMP)");
            statement.addBatch("CREATE TABLE IF NOT EXISTS roles(id INTEGER PRIMARY KEY UNIQUE , role CHARACTER VARYING(30) UNIQUE)");
            statement.addBatch("INSERT INTO roles(id, role) VALUES(1, 'admin'),(2, 'emploee')");
            statement.addBatch("CREATE TABLE IF NOT EXISTS users_roles(login CHARACTER VARYING(30) REFERENCES users(login), role_id INTEGER REFERENCES roles(id))");
            statement.executeBatch();
            this.connection.commit();
        } catch (SQLException e) {
            this.connection.rollback();
            log.error(e.getMessage(), e);
        } finally {
            this.connection.setAutoCommit(true);
        }
    }

    /**
     * add User from datebase.
     * @param user user.
     */
    public synchronized void put(User user) throws SQLException {
        this.connection.setAutoCommit(false);
        try (PreparedStatement statement = this.connection.prepareStatement("INSERT INTO users(login, password, user_name, email, create_date) VALUES(?, ?, ?, ?, ?)")) {
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getName());
            statement.setString(4, user.getEmail());
            statement.setTimestamp(5, user.getCreateDate());
            statement.addBatch();
            statement.executeBatch();
            this.connection.commit();
            this.putRole(user);
        } catch (SQLException e) {
            this.connection.rollback();
            log.error(e.getMessage(), e);
        }
    }

    private void putRole(User user) throws SQLException {
        try (PreparedStatement statement = this.connection.prepareStatement("INSERT INTO users_roles(login, role_id) VALUES(?, (SELECT id FROM roles WHERE role = ?))")){
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getRole());
            statement.addBatch();
            statement.executeBatch();
        } catch (SQLException e) {
            this.connection.rollback();
            log.error(e.getMessage(), e);
        } finally {
            this.connection.setAutoCommit(true);
        }
    }

    /**
     * delete User from datebase.
     * @param login user.
     */
    public synchronized void delete(String login) throws SQLException {
        this.connection.setAutoCommit(false);
        try (PreparedStatement statement = this.connection.prepareStatement("DELETE FROM users WHERE login = ?")) {
            statement.setString(1, login);
            statement.addBatch();
            statement.executeBatch();
            this.connection.commit();
        } catch (SQLException e) {
            this.connection.rollback();
            log.error(e.getMessage(), e);
        } finally {
            this.connection.setAutoCommit(true);
        }
    }

    /**
     * update User from datebase.
     * @param user user.
     */
    public synchronized void update(User user) throws SQLException {
        this.connection.setAutoCommit(false);
        try (PreparedStatement statement = this.connection.prepareStatement("UPDATE users SET user_name = ?, password = ?, email = ?, create_date = ? WHERE login = ?")) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getEmail());
            statement.setTimestamp(4, user.getCreateDate());
            statement.setString(5, user.getLogin());
            statement.addBatch();
            statement.executeBatch();
            this.connection.commit();
        } catch (SQLException e) {
            this.connection.rollback();
            log.error(e.getMessage(), e);
        } finally {
            this.connection.setAutoCommit(true);
        }
    }

    /**
     * list all users from datebase.
     * @return list users.
     */
    public synchronized List<User> getAll() throws SQLException {
        List<User> result = new ArrayList<>();
        try (Statement statement = this.connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
            while (resultSet.next()) {
                User user = new User(resultSet.getString("login"), resultSet.getString("password"), resultSet.getString("user_name"), resultSet.getString("email"), resultSet.getTimestamp("create_date"), resultSet.getString("role"));
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
    public synchronized void close() throws SQLException {
        this.connection.close();
    }
}

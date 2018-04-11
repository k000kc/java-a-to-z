package ru.apetrov.model;

import net.jcip.annotations.GuardedBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.apetrov.settings.ConnectionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
                instance.initDB();
            } catch (SQLException e) {
                log.error(e.getMessage(), e);
            }
        }
        return instance;
    }

    /**
     * initial database connection.
     */
    private synchronized void initDB() throws SQLException {
        try {
            this.connection = new ConnectionDB().getConnection();
            this.connection.setAutoCommit(false);
            Statement statement = this.connection.createStatement();
            statement.addBatch("CREATE TABLE IF NOT EXISTS roles(id INTEGER PRIMARY KEY UNIQUE , role CHARACTER VARYING(30) UNIQUE)");
            statement.addBatch("CREATE TABLE IF NOT EXISTS countries(id SERIAL PRIMARY KEY, country CHARACTER VARYING(50) UNIQUE)");
            statement.addBatch("CREATE TABLE IF NOT EXISTS cities(city CHARACTER VARYING(50) UNIQUE PRIMARY KEY, country_id INTEGER REFERENCES countries(id))");
            statement.addBatch("CREATE TABLE IF NOT EXISTS users(login CHARACTER VARYING(30) UNIQUE PRIMARY KEY, password CHARACTER VARYING(30), user_name CHARACTER VARYING(50), email CHARACTER VARYING(50), create_date TIMESTAMP, role_id INTEGER DEFAULT 2 REFERENCES roles(id), city CHARACTER  VARYING(50) REFERENCES cities(city))");
            statement.addBatch("INSERT INTO roles(id, role) VALUES(1, 'admin'),(2, 'user')");
            statement.addBatch("INSERT INTO countries(country) VALUES('Россия'), ('США')");
            statement.executeBatch();
            this.connection.commit();
        } catch (SQLException e) {
            this.connection.rollback();
            log.error(e.getMessage(), e);
        } finally {
            this.connection.setAutoCommit(true);
            this.addAdmin();
            this.addBaseCities();
        }
    }

    /**
     * adding admin account.
     * @throws SQLException
     */
    private synchronized void addAdmin() throws SQLException {
        User user = new User("root", "root", "root", "root@root.ru", new Timestamp(System.currentTimeMillis()),"admin");
        this.put(user);
    }

    private synchronized  void addBaseCities() throws SQLException {
        this.putCities("Россия", "Москва");
        this.putCities("Россия", "Санкт-Петербург");
        this.putCities("Россия", "Чебоксары");
        this.putCities("США", "Нью-Йорк");
        this.putCities("США", "Вашингтон");
        this.putCities("США", "Детройт");
    }

    public synchronized void putCountry(String country) {
        try (PreparedStatement statement = this.connection.prepareStatement("INSERT INTO countries(country) VALUES(?)")) {
            statement.setString(1, country);
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
    }

    public synchronized void putCities(String country, String city) throws SQLException {
        this.connection.setAutoCommit(false);
        try (PreparedStatement statement = this.connection.prepareStatement("INSERT INTO cities(city, country_id) VALUES(?, (SELECT id FROM countries WHERE country = ?))")) {
            statement.setString(1, city);
            statement.setString(2, country);
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
     * add User from datebase.
     * @param user user.
     */
    public synchronized void put(User user) throws SQLException {
        this.connection.setAutoCommit(false);
        try (PreparedStatement statement = this.connection.prepareStatement("INSERT INTO users(login, password, user_name, email, create_date, role_id) VALUES(?, ?, ?, ?, ?, (SELECT id FROM roles WHERE role = ?))")) {
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getName());
            statement.setString(4, user.getEmail());
            statement.setTimestamp(5, user.getCreateDate());
            statement.setString(6, user.getRole());
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
        try (PreparedStatement statement = this.connection.prepareStatement("UPDATE users SET user_name = ?, password = ?, email = ?, create_date = ?, role_id = (SELECT id FROM roles WHERE role = ?) WHERE login = ?")) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getEmail());
            statement.setTimestamp(4, user.getCreateDate());
            statement.setString(5, user.getRole());
            statement.setString(6, user.getLogin());
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

    public void assignCityOnUser(User user, String city) throws SQLException {
        user.setCity(city);
        this.connection.setAutoCommit(false);
        try (PreparedStatement statement = this.connection.prepareStatement("UPDATE users SET city = (SELECT city FROM cities WHERE city = ?) WHERE login = ?")) {
            statement.setString(1, user.getCity());
            statement.setString(2, user.getLogin());
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

    public List<String> showCities() {
        List<String> cities = new ArrayList<>();
        try (Statement statement = this.connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT city FROM cities");
            while (resultSet.next()) {
                cities.add(resultSet.getString("city"));
            }
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
        return cities;
    }

    /**
     * list all users from datebase.
     * @return list users.
     */
    public synchronized List<User> getAll() throws SQLException {
        List<User> result = new ArrayList<>();
        try (Statement statement = this.connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT login, password, user_name, email, create_date, role FROM users AS u INNER JOIN roles AS r ON u.role_id = r.id;");
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
     * account existence check.
     * @param login login.
     * @param password password.
     * @return true if account exist.
     */
    public boolean isCredentional(String login, String password) {
        boolean result = false;
        String passwordHash = String.valueOf(Objects.hash(login, password));
        System.out.println(passwordHash);
        try (PreparedStatement statement = this.connection.prepareStatement("SELECT login FROM users AS u WHERE u.login = ? AND u.password = ?")) {
            statement.setString(1, login);
            statement.setString(2, passwordHash);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                result = true;
            }
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
        return result;
    }

    /**
     * return the role by login.
     * @param login login.
     * @return role.
     */
    public String getRoleByLogin(String login) {
        String role = null;
        try (PreparedStatement statement = this.connection.prepareStatement("SELECT role FROM roles AS r INNER JOIN users AS u ON u.login = ? AND u.role_id = r.id")) {
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                role = resultSet.getString("role");
            }
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
        return role;
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

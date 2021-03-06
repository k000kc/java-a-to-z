package ru.apetrov;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Andrey on 09.02.2018.
 */
public enum  ConnectionDB {

    INSTANCE;

    /**
     * datebase connection.
     */
    private Connection connection;

    /**
     * logger.
     */
    private static final Logger log = LoggerFactory.getLogger(ConnectionDB.class);

    /**
     * get singleton connection instance.
     * @return connection.
     */
    public Connection getConnection() {
        if (connection == null) {
            Settings settings = new Settings();
            try {
                Class.forName("org.postgresql.Driver");
                String url = settings.getValue("jdbc.url");
                String username = settings.getValue("jdbc.username");
                String password = settings.getValue("jdbc.password");
                connection = DriverManager.getConnection(url, username, password);
            } catch (ClassNotFoundException e) {
                log.error(e.getMessage(), e);
            } catch (SQLException e) {
                log.error(e.getMessage(), e);
            }
        }
        return connection;
    }
}

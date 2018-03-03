package ru.apetrov.settings;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Connection pool to db.
 */
public class ConnectionDB {

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
        Settings settings = new Settings();
        PoolProperties poolProperties = new PoolProperties();
        DataSource source = new DataSource();
        poolProperties.setDriverClassName("org.postgresql.Driver");
        poolProperties.setUrl(settings.getValue("jdbc.url"));
        poolProperties.setUsername(settings.getValue("jdbc.username"));
        poolProperties.setPassword(settings.getValue("jdbc.password"));
        source.setPoolProperties(poolProperties);
        try {
            this.connection = source.getConnection();
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
        return connection;
    }
}

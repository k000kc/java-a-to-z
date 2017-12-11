package ru.apetrov;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * Created by Andrey on 11.12.2017.
 */
public class JDBCStorege {

    private Connection connection;

    public JDBCStorege() {
        this.initConnection();
        try {
            Statement statement = this.connection.createStatement();
            statement.executeUpdate("");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void initConnection() {
        Properties properties = new Properties();
        ClassLoader loader = JDBCStorege.class.getClassLoader();
        try (InputStream in = loader.getResourceAsStream("config.properties")){
            properties.load(in);
            String url = properties.getProperty("jdbc.url");
            String username = properties.getProperty("jdbc.username");
            String password = properties.getProperty("jdbc.password");
            this.connection = DriverManager.getConnection(url, username, password);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void closeConnection() {
        try {
            this.connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

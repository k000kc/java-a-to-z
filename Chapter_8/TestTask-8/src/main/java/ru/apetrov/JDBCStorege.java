package ru.apetrov;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
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
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS forumTable(id serial PRIMARY KEY, vacancy CHARACTER VARYING(50), author CHARACTER VARYING(30), createDate CHARACTER VARYING(20))");
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

    public void add(String vacancy, String author, String createDate) {
        if (!this.isDuplicate(vacancy, author)) {
            try (PreparedStatement statement = this.connection.prepareStatement("INSERT INTO forumTable(vacancy, author, createDate) VALUES(?, ?, ?)")) {
                statement.setString(1, vacancy);
                statement.setString(2, author);
                statement.setString(3, createDate);
                statement.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("duplicate");
        }
    }

    private boolean isDuplicate(String vacancy, String author) {
        boolean result = false;
        try(PreparedStatement statement = this.connection.prepareStatement("SELECT ft.id FROM forumTable AS ft WHERE ft.vacancy = ? AND ft.author = ?")) {
            statement.setString(1, vacancy);
            statement.setString(2, author);
            ResultSet set = statement.executeQuery();
            if (set.next()) {
                result = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void closeConnection() {
        try {
            this.connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

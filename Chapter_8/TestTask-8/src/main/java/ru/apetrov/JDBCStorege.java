package ru.apetrov;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.*;

/**
 * Created by Andrey on 11.12.2017.
 */
public class JDBCStorege implements AutoCloseable {

    private Connection connection;
    private DateManager manager;

    public JDBCStorege() {
        this.initConnection();
        this.manager = new DateManager();
        try {
            Statement statement = this.connection.createStatement();
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS forumTable(id serial PRIMARY KEY, vacancy CHARACTER VARYING(200), author CHARACTER VARYING(50), createDate TIMESTAMP)");
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

    public void add(Vacancy vacancy) {
        if (!this.isDuplicate(vacancy)) {
            try (PreparedStatement statement = this.connection.prepareStatement("INSERT INTO forumTable(vacancy, author, createDate) VALUES(?, ?, ?)")) {
                statement.setString(1, vacancy.getName());
                statement.setString(2, vacancy.getAuthor());
                statement.setTimestamp(3, vacancy.getCreateDate());
                statement.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("duplicate");
        }
    }

    private boolean isDuplicate(Vacancy vacancy) {
        boolean result = false;
        try(PreparedStatement statement = this.connection.prepareStatement("SELECT ft.id FROM forumTable AS ft WHERE ft.vacancy = ? AND ft.author = ?")) {
            statement.setString(1, vacancy.getName());
            statement.setString(2, vacancy.getAuthor());
            ResultSet set = statement.executeQuery();
            if (set.next()) {
                result = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public Vacancy getLastVacancy() {
        Vacancy vacancy = null;
        try (Statement statement = this.connection.createStatement()) {
            ResultSet result = statement.executeQuery("SELECT ft.vacancy, ft.author, ft.createDate FROM forumTable AS ft WHERE ft.createDate = (SELECT max(createDate) FROM forumTable)");
            if (result.next()) {
                String name = result.getString("vacancy");
                String author = result.getString("author");
                Timestamp createDate = result.getTimestamp("createDate");
                vacancy = new Vacancy(name, author, createDate);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vacancy;
    }

    @Override
    public void close() throws SQLException {
        this.connection.close();
    }
}

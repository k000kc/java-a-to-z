package ru.apetrov.dao;

import ru.apetrov.util.ConnectionDB;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Set;

public abstract class ModelBaseDAO<T, K> {

    private Connection connection;

    abstract void create(T t);
    abstract T getById(K k);
    abstract Set<T> getAll();
    abstract void update(T t);
    abstract void remove(K k);

    protected Connection getConnection() {
        this.connection = new ConnectionDB().getConnection();
        return this.connection;
    }

    protected void closeConnection() {
        try {
            this.connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

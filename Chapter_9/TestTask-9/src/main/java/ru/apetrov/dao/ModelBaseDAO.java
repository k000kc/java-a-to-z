package ru.apetrov.dao;

import ru.apetrov.util.ConnectionDB;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Set;

public abstract class ModelBaseDAO<T, K> {

    private Connection connection;

    public abstract void create(T t);
    public abstract T getById(K k);
    public abstract Set<T> getAll();
    public abstract void update(T t);
    public abstract void remove(K k);

    protected Connection getConnection() {
        this.connection = ConnectionDB.getInstance().getConnection();
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

package ru.apetrov.dao;

import java.util.List;

public interface EntityDAO<K, V> {

    void create(V value);
    List<V> getAll();
    V getById(K id);
    void update(K id);
    void remove(K id);
}

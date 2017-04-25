package ru.apetrov;

/**
 * Created by Andrey on 23.04.2017.
 * @param <T> key
 * @param <V> value
 */
public interface SimpleContainer<T, V> extends Iterable<V> {

    /**
     * insert element.
     * @param key key.
     * @param value value.
     * @return true - If you can insert an element.
     */
    boolean insert(T key, V value);

    /**
     * get.
     * @param key key.
     * @return value.
     */
    V get(T key);

    /**
     * delete.
     * @param key key.
     * @return true - If you can remove an element.
     */
    boolean delete(T key);
}

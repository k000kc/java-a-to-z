package ru.apetrov.generic;

/**
 * Created by Andrey on 07.03.2017.
 * @param <T> type.
 */
public interface Store<T extends Base> {

    /**
     * add.
     * @param value value.
     */
    void add(T value);

    /**
     * update.
     * @param id id
     * @param newValue new value.
     */
    void update(String id, T newValue);

    /**
     * delete.
     * @param id id.
     */
    void delete(String id);

    /**
     * get.
     * @param id id.
     * @return value.
     */
    T get(String id);
}

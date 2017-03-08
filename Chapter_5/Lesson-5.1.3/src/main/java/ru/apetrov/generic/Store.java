package ru.apetrov.generic;

/**
 * Created by Andrey on 08.03.2017.
 */
public interface Store<T extends Base> {

    void add(T value);

    void update(String id, T newValue);

    void delete(String id);
}

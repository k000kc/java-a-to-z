package ru.apetrov.list;

/**
 * Created by Andrey on 11.03.2017.
 */
public interface SimpleContainer<E> extends Iterable<E> {

    void add(E e);

    E get(int index);
}

package ru.apetrov.list;

/**
 * Created by Andrey on 11.03.2017.
 * @param <E> type.
 */
public interface SimpleContainer<E> extends Iterable<E> {

    /**
     * add.
     * @param e element.
     */
    void add(E e);

    /**
     * get.
     * @param index of element.
     * @return element.
     */
    E get(int index);
}

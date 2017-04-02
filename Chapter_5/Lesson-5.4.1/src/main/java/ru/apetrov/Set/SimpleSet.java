package ru.apetrov.Set;

/**
 * Created by Andrey on 27.03.2017.
 * @param <E> type.
 */
public interface SimpleSet<E> extends Iterable<E> {

    /**
     * add.
     * @param e element.
     * @return true - if element was added.
     */
    boolean add(E e);
}

package ru.apetrov.Set;

import java.util.Iterator;

/**
 * Created by Andrey on 27.03.2017.
 */
public interface SimpleSet<E> extends Iterable<E> {

    boolean add(E e);
}

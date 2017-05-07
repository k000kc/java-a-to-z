package ru.apetrov.MyTreeSet2;

/**
 * Created by Andrey on 03.05.2017.
 */
public interface SimpleTree<E extends Comparable<E>> extends Iterable<E> {

    boolean add(E parent, E child);
}

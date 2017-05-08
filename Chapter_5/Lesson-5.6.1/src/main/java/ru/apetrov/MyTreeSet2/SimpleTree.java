package ru.apetrov.MyTreeSet2;

/**
 * Created by Andrey on 03.05.2017.
 * @param <E> type.
 */
public interface SimpleTree<E extends Comparable<E>> extends Iterable<E> {

    /**
     * add.
     * @param parent parent.
     * @param child child.
     * @return true if you can add an child to parent.
     */
    boolean add(E parent, E child);
}

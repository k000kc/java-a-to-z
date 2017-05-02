package ru.apetrov.MyTreeSet;

import java.util.List;

/**
 * Created by Andrey on 30.04.2017.
 * @param <E> element type.
 */
public interface SimpleTree<E> {

    /**
     * add element.
     * @param e element.
     * @return true - If you can add an element.
     */
    boolean addChild(E e);

    /**
     * get list elements.
     * @return list.
     */
    List<E> getChildren();

    /**
     * get size.
     * @return size.
     */
    int getSize();

    /**
     * search to element.
     * @param e element.
     * @return element.
     */
    E search(E e);
}

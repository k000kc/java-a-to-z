package ru.apetrov.MyTreeSet;

import java.util.List;

/**
 * Created by Andrey on 30.04.2017.
 */
public interface SimpleTree<E> {

    boolean addChild(E e);

    List<E> getChildren(E e);

    int getSize();
}

package ru.apetrov.list;

import java.util.EmptyStackException;

/**
 * Created by Andrey on 16.03.2017.
 * @param <E> type.
 */
public class SimpleStack<E> extends SimpleLinkedList<E> {

    /**
     * add element and get this element.
     * @param e element.
     * @return element.
     */
    public E push(E e) {
        super.add(e);
        return e;
    }

    /**
     * get last element and remove this element.
     * @return element.
     */
    public E pop() {
        E obj;
        int len = super.getSize();
        obj = this.peek();
        super.remove(len - 1);
        return obj;
    }

    /**
     * get last element.
     * @return element.
     */
    public E peek() {
        int len = super.getSize();
        if (len == 0) {
            throw new EmptyStackException();
        }
        return super.get(len - 1);
    }

}

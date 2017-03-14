package ru.apetrov.list;

/**
 * Created by Andrey on 14.03.2017.
 */
public class SimpleQueue<E> extends SimpleLinkedList<E> {

    @Override
    public void add(E e) {
        super.add(e);
    }

    public E poll() {
        E result = this.get(0);
//        super.removFirst();
        return result;
    }
}

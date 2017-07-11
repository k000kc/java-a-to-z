package ru.apetrov.SynchronizedList;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;
import ru.apetrov.list.SimpleContainer;
import ru.apetrov.list.SimpleLinkedList;

import java.util.Iterator;

/**
 * Created by Andrey on 11.07.2017.
 */
@ThreadSafe
public class SynchronizedSimpleLinkedList<E> implements SimpleContainer<E> {

    /**
     * container.
     */
    @GuardedBy("SynchronizedSimpleLinkedList")
    private final SimpleContainer<E> container;

    /**
     * Constructor.
     */
    public SynchronizedSimpleLinkedList() {
        this.container = new SimpleLinkedList<E>();
    }

    /**
     * add element.
     * @param e element.
     */
    public synchronized void add(E e) {
        container.add(e);
    }

    /**
     * get element of index.
     * @param index of element.
     * @return
     */
    public synchronized E get(int index) {
        return container.get(index);
    }

    /**
     * iterator.
     * @return iterator.
     */
    public synchronized Iterator<E> iterator() {
        return container.iterator();
    }
}

package ru.apetrov.SynchronizedList;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;
import ru.apetrov.list.SimpleArray;
import ru.apetrov.list.SimpleContainer;

import java.util.Iterator;

/**
 * Created by Andrey on 11.07.2017.
 */
@ThreadSafe
public class SynchronizedSimpleArray<E> implements SimpleContainer<E> {

    /**
     * container.
     */
    @GuardedBy("SynchronizedSimpleArray")
    private final SimpleContainer<E> container;

    /**
     * Constructor.
     */
    public SynchronizedSimpleArray() {
        this.container = new SimpleArray<E>();
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
     * inerator.
     * @return iterator.
     */
    public synchronized Iterator<E> iterator() {
        return container.iterator();
    }
}

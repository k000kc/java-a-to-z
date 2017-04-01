package ru.apetrov.generic;

/**
 * Created by Andrey on 07.03.2017.
 * @param <E> type.
 */
public class SimpleArray<E> {

    /**
     * array of objects.
     */
    private Object[] objects;

    /**
     * index of array.
     */
    private int index = 0;

    /**
     * Constructor of class.
     * @param size size of array.
     */
    public SimpleArray(int size) {
        this.objects = new Object[size];
    }

    /**
     * add new value.
     * @param value value.
     */
    public void add(E value) {
        this.objects[index++] = value;
    }

    /**
     * get value by position.
     * @param position position.
     * @return value.
     */
    public E get(int position) {
        return (E) this.objects[position];
    }

    /**
     * delete value by position.
     * @param position position.
     */
    public void delete(int position) {
        this.objects[position] = null;
    }

    /**
     * Update value by position.
     * @param position position.
     * @param newValue new value.
     */
    public void update(int position, E newValue) {
        this.objects[position] = newValue;
    }
}

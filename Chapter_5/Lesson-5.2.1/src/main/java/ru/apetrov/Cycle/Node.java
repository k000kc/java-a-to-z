package ru.apetrov.Cycle;

/**
 * Created by Andrey on 17.03.2017.
 * @param <T> type.
 */
public class Node<T> {
    /**
     * value.
     */
    private T value;

    /**
     * flag.
     */
    private boolean isVisited = false;

    /**
     * next value.
     */
    private Node<T> next;

    /**
     * getter.
     * @return next value.
     */
    public Node<T> getNext() {
        return next;
    }

    /**
     * getter.
     * @return flag.
     */
    public boolean isVisited() {
        return isVisited;
    }

    /**
     * setter.
     * @param visited visited.
     */
    public void setVisited(boolean visited) {
        isVisited = visited;
    }
}

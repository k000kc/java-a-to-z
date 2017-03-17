package ru.apetrov.Cycle;

/**
 * Created by Andrey on 17.03.2017.
 */
public class Node<T> {
    T value;
    boolean isVisited = false;
    Node<T> next;
}

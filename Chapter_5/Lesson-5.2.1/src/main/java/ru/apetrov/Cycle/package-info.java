/**
 * Created by Andrey on 01.04.2017.
 * Задан связанный список.
 * class Node<T> {
 * T value;
 * Node<T> next;
 * }
 *
 * Node first = new Node(1);
 * Node two = new Node(2);
 * Node third = new Node(3);
 * Node four = new Node(4);
 *
 * first.next = two;
 * two.next = third;
 * third.next = four;
 * four.next = first;
 *
 * Написать алгоритм определяющий, что список содержит замыкания.
 *
 * boolean hasCycle(Node first);
 */
package ru.apetrov.Cycle;
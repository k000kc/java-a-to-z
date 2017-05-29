package ru.apetrov.OrderBook.Storege;

import ru.apetrov.OrderBook.Order;

import java.util.Map;

/**
 * Created by Andrey on 24.05.2017.
 */
public interface Storege {

    Map<Double, Order> getData();

    void addOrder(Order order);

    boolean removeOrder(Order order);
}

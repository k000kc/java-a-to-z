package ru.apetrov.OrderBook.Storage;

import ru.apetrov.OrderBook.Order;

import java.util.Map;

/**
 * Created by Andrey on 24.05.2017.
 * Storage class.
 */
public interface Storage {

    /**
     * get Storage Map.
     * @return Storage Map.
     */
    Map<Double, Order> getData();

    /**
     * add Order in Map.
     * @param order order.
     */
    void addOrder(Order order);
}

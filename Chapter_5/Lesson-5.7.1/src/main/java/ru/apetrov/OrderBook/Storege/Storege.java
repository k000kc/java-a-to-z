package main.java.ru.apetrov.OrderBook.Storege;

import main.java.ru.apetrov.OrderBook.Order;

import java.util.Map;
import java.util.Set;

/**
 * Created by Andrey on 24.05.2017.
 */
public interface Storege {

    Map<Double, Order> getData();

    void addOrder(Order order);

    boolean removeOrder(Order order);
}

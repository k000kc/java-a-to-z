package ru.apetrov.OrderBook.Storage;

import ru.apetrov.OrderBook.Order;

import java.util.*;

/**
 * Created by Andrey on 24.05.2017.
 */
public class BuyStorage implements Storage {

    private Map<Double, Order> data;

    public BuyStorage() {
        this.data = new TreeMap<>(new Comparator<Double>() {
            @Override
            public int compare(Double o1, Double o2) {
                return Double.compare(o2, o1);
            }
        });
    }

    @Override
    public Map<Double, Order> getData() {
        return data;
    }

    @Override
    public void addOrder(Order order) {
        Order tmpOrder = this.data.get(order.getPrice());
        if (tmpOrder != null) {
            this.data.put(tmpOrder.getPrice(), new Order(tmpOrder.getBookName(), tmpOrder.getOperation(), tmpOrder.getPrice(), tmpOrder.getVolume(), tmpOrder.getOrderId()));
        } else {
            this.data.put(order.getPrice(), order);
        }
    }

    @Override
    public boolean removeOrder(Order order) {
//        this.data.remove(order);
        return false;
    }

}

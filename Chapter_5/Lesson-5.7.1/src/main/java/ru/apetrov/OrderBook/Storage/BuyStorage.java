package ru.apetrov.OrderBook.Storage;

import ru.apetrov.OrderBook.Order;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;


/**
 * Created by Andrey on 24.05.2017.
 * Buy Storage class.
 */
public class BuyStorage implements Storage {

    /**
     * Storage Map of sorted buy orders.
     */
    private Map<Double, Order> data;

    /**
     * Constructor.
     */
    public BuyStorage() {
        this.data = new TreeMap<>(new Comparator<Double>() {
            @Override
            public int compare(Double o1, Double o2) {
                return Double.compare(o2, o1);
            }
        });
    }

    /**
     * Getter.
     * @return Storage Map.
     */
    @Override
    public Map<Double, Order> getData() {
        return data;
    }

    /**
     * Add in Storage buy orders.
     * @param order order.
     */
    @Override
    public void addOrder(Order order) {
        Order tmpOrder = this.data.get(order.getPrice());
        if (tmpOrder != null) {
            this.data.put(tmpOrder.getPrice(), new Order(tmpOrder.getBookName(), tmpOrder.getOperation(), tmpOrder.getPrice(), tmpOrder.getVolume() + order.getVolume(), tmpOrder.getOrderId()));
        } else {
            this.data.put(order.getPrice(), order);
        }
    }

}

package ru.apetrov.OrderBook.Storage;

import ru.apetrov.OrderBook.Order;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;


/**
 * Created by Andrey on 24.05.2017.
 * Sell Storage class.
 */
public class SellStorage implements Storage {

    /**
     * Storage Map of sorted sell orders.
     */
    private Map<Double, Order> data;

    /**
     * Constructor.
     */
    public SellStorage() {
        this.data = new TreeMap<>(new Comparator<Double>() {
            @Override
            public int compare(Double o1, Double o2) {
                return Double.compare(o1, o2);
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
     * Add in Storage sell orders.
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

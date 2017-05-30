package ru.apetrov.OrderBook;

/**
 * Created by Andrey on 23.05.2017.
 */
public class Order {

    /**
     * name of order.
     */
    private String bookName;

    /**
     * operation: sell or buy.
     */
    private String operation;

    /**
     * price of order.
     */
    private double price;

    /**
     * volume of order.
     */
    private int volume;

    /**
     * order id.
     */
    private int orderId;

    /**
     * Constructor.
     * @param bookName name of order.
     * @param operation operation: sell or buy.
     * @param price price of order.
     * @param volume volume of order.
     * @param orderId order id.
     */
    public Order(String bookName, String operation, double price, int volume, int orderId) {
        this.bookName = bookName;
        this.operation = operation;
        this.price = price;
        this.volume = volume;
        this.orderId = orderId;
    }

    /**
     * getter.
     * @return name of order.
     */
    public String getBookName() {
        return bookName;
    }

    /**
     * getter.
     * @return operation: sell or buy.
     */
    public String getOperation() {
        return operation;
    }

    /**
     * getter.
     * @return price of order.
     */
    public double getPrice() {
        return price;
    }

    /**
     * getter.
     * @return volume of order.
     */
    public int getVolume() {
        return volume;
    }

    /**
     * getter.
     * @return order id.
     */
    public int getOrderId() {
        return orderId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Order order = (Order) o;

        return orderId == order.orderId;
    }

    @Override
    public int hashCode() {
        return orderId;
    }
}

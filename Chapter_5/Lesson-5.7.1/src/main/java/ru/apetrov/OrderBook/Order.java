package main.java.ru.apetrov.OrderBook;

/**
 * Created by Andrey on 23.05.2017.
 */
public class Order {

    private String bookName;
    private String operation;
    private double price;
    private String volume;
    private int orderId;

    public Order(String bookName, String operation, double price, String volume, int orderId) {
        this.bookName = bookName;
        this.operation = operation;
        this.price = price;
        this.volume = volume;
        this.orderId = orderId;
    }

    public String getBookName() {
        return bookName;
    }

    public String getOperation() {
        return operation;
    }

    public double getPrice() {
        return price;
    }

    public String getVolume() {
        return volume;
    }

    public int getOrderId() {
        return orderId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        return orderId == order.orderId;
    }

    @Override
    public int hashCode() {
        return orderId;
    }
}

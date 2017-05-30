package ru.apetrov.OrderBook;

import ru.apetrov.OrderBook.Storage.BuyStorage;
import ru.apetrov.OrderBook.Storage.SellStorage;
import ru.apetrov.OrderBook.Storage.Storage;

import java.util.Iterator;
import java.util.Map;

/**
 * Created by Andrey on 23.05.2017.
 */
public class OrderBook {

    /**
     * Sell Storage.
     */
    private Storage sellStorage;

    /**
     * Buy Storage.
     */
    private Storage buyStorage;

    /**
     * Constructor.
     */
    public OrderBook() {
        this.sellStorage = new SellStorage();
        this.buyStorage = new BuyStorage();
    }

    /**
     * split up the Map from XML into a Map for the Sell and a Map for the Buy.
     * @param parserMap parser.
     */
    public void splitUp(Map<Integer, Order> parserMap) {
        for (Map.Entry<Integer, Order> tmp : parserMap.entrySet()) {
            if (tmp.getValue().getOperation().equals("SELL")) {
                this.sellStorage.addOrder(tmp.getValue());
            } else {
                this.buyStorage.addOrder(tmp.getValue());
            }
        }
    }

    /**
     * print book.
     */
    public void printBook() {
        Iterator<Order> sellItr = this.sellStorage.getData().values().iterator();
        Iterator<Order> buyItr = this.buyStorage.getData().values().iterator();
        System.out.println("Volume@Price  –  Volume@Price");
        while (sellItr.hasNext() && buyItr.hasNext()) {
            Order sell = sellItr.next();
            Order buy = buyItr.next();
            System.out.printf("%s @ %s\t\t", sell.getVolume(), sell.getPrice());
            System.out.printf("%s @ %s%n", buy.getVolume(), buy.getPrice());
            if (!sellItr.hasNext()) {
                while (buyItr.hasNext()) {
                    buy = buyItr.next();
                    System.out.printf("%s%s @ %s%n", "-----------", buy.getVolume(), buy.getPrice());
                }
            }
            if (!buyItr.hasNext()) {
                while (sellItr.hasNext()) {
                    sell = sellItr.next();
                    System.out.printf("%s @ %s\t\t%s%n", sell.getVolume(), sell.getPrice(), "-----------");
                }
            }
        }

    }

    /**
     * main.
     * @param args args.
     */
    public static void main(String[] args) {
        ParserStAX parserStAX = new ParserStAX("H:\\projects\\orders.xml");
        Map<Integer, Order> parserMap = parserStAX.startParser();

        OrderBook orderBook = new OrderBook();
        orderBook.splitUp(parserMap);
        orderBook.printBook();

    }
}

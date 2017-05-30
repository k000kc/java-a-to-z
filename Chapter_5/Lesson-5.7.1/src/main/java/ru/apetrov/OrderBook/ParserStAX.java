package ru.apetrov.OrderBook;

import ru.apetrov.OrderBook.Storage.BuyStorage;
import ru.apetrov.OrderBook.Storage.SellStorage;
import ru.apetrov.OrderBook.Storage.Storage;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Created by Andrey on 23.05.2017.
 */
public class ParserStAX {

    private String file;
    Storage sellStorage;
    Storage buyStorage;

    public Storage getSellStorage() {
        return sellStorage;
    }

    public Storage getBuyStorage() {
        return buyStorage;
    }

    public ParserStAX(String file) {
        this.file = file;
        this.sellStorage = new SellStorage();
        this.buyStorage = new BuyStorage();
    }

    public void add(Order order) {
        if (order.getOperation().equals("SELL")) {
            this.sellStorage.addOrder(order);
        } else {
            this.buyStorage.addOrder(order);
        }
    }

    public void startParser() {
        XMLInputFactory factory = XMLInputFactory.newInstance();
        try {
            XMLStreamReader parser = factory.createXMLStreamReader(new FileInputStream(this.file));
            while (parser.hasNext()) {
                int event = parser.next();
                if (event == XMLStreamConstants.START_ELEMENT) {
                    if (parser.getLocalName().equals("AddOrder")) {

                        String bookName = parser.getAttributeValue(0);
                        String operation = parser.getAttributeValue(1);
                        double price = Double.parseDouble(parser.getAttributeValue(2));
                        double volume = Double.parseDouble(parser.getAttributeValue(3));
                        int orderId = Integer.parseInt(parser.getAttributeValue(4));
                        add(new Order(bookName, operation, price, volume, orderId));

                    } else if (parser.getLocalName().equals("DeleteOrder")) {
                        String bookName = parser.getAttributeValue(0);
                        int orderId = Integer.parseInt(parser.getAttributeValue(1));

                    }
                }
            }
        } catch (XMLStreamException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}

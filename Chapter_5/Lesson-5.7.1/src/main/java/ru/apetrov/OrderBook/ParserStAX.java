package ru.apetrov.OrderBook;

import ru.apetrov.OrderBook.Storege.BuyStorege;
import ru.apetrov.OrderBook.Storege.SellStorege;
import ru.apetrov.OrderBook.Storege.Storege;

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
    Storege sellStorege;
    Storege buyStorege;

    public Storege getSellStorege() {
        return sellStorege;
    }

    public Storege getBuyStorege() {
        return buyStorege;
    }

    public ParserStAX(String file) {
        this.file = file;
        this.sellStorege = new SellStorege();
        this.buyStorege = new BuyStorege();
    }

    public void add(Order order) {
        if (order.getOperation().equals("SELL")) {
            this.sellStorege.addOrder(order);
        } else {
            this.buyStorege.addOrder(order);
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
                        String volume = parser.getAttributeValue(3);
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

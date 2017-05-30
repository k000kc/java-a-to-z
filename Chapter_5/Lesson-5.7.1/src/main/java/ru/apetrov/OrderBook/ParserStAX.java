package ru.apetrov.OrderBook;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Andrey on 23.05.2017.
 */
public class ParserStAX {

    /**
     * file of XML.
     */
    private String file;

    /**
     * Constructor.
     * @param file file of XML.
     */
    public ParserStAX(String file) {
        this.file = file;
    }

    /**
     * start parsing.
     * @return Map of parsing.
     */
    public Map<Integer, Order> startParser() {
        Map<Integer, Order> result = new HashMap<>();
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
                        int volume = Integer.parseInt(parser.getAttributeValue(3));
                        Integer orderId = Integer.parseInt(parser.getAttributeValue(4));
                        Order order = new Order(bookName, operation, price, volume, orderId);
                        result.put(orderId, order);
                    }
                    if (parser.getLocalName().equals("DeleteOrder")) {
                        String bookName = parser.getAttributeValue(0);
                        Integer orderId = Integer.parseInt(parser.getAttributeValue(1));
                        result.remove(orderId);
                    }
                }
            }
        } catch (XMLStreamException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return  result;
    }
}

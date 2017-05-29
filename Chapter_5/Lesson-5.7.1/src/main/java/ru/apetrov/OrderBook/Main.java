package ru.apetrov.OrderBook;

import java.util.Map;

/**
 * Created by Andrey on 23.05.2017.
 */
public class Main {
    public static void main(String[] args) {
        ParserStAX parserStAX = new ParserStAX("H:\\petr arsentev\\Часть 005. Коллекции\\Часть 005. Коллекции\\7. Контрольные вопросы\\orders.xml");
        parserStAX.startParser();

        for (Map.Entry<Double, Order> s : parserStAX.getBuyStorege().getData().entrySet()) {
            System.out.println(s.getValue().getBookName() + " " + s.getValue().getOperation() +
                    " " + s.getValue().getVolume() + " " + s.getValue().getPrice());
        }
    }
}

package ru.apetrov.DynamicFoodStorage.Products;

import java.util.Date;

/**
 * Created by Andrey on 14.01.2017.
 */
public class Fruit extends Food {

    /**
     * Конструктор.
     * @param name имя.
     * @param expaireDate срок годности.
     * @param createDate дата изготовления.
     * @param price цена.
     * @param disscount скидка.
     */
    public Fruit(String name, Date expaireDate, Date createDate, double price, double disscount) {
        super(name, expaireDate, createDate, price, disscount);
    }
}

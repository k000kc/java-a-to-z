package ru.apetrov.UpdateFoodStorage.Products;

import java.util.Date;

/**
 * Created by Andrey on 14.01.2017.
 */
public class Bread extends Food {

    /**
     * Конструктор.
     * @param name имя.
     * @param expaireDate срок годности.
     * @param createDate дата изготовления.
     * @param price цена.
     * @param disscount скидка.
     */
    public Bread(String name, Date expaireDate, Date createDate, double price, double disscount) {
        super(name, expaireDate, createDate, price, disscount);
    }
}

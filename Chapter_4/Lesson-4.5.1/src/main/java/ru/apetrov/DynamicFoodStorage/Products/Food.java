package ru.apetrov.DynamicFoodStorage.Products;

import java.util.Date;

/**
 * Created by Andrey on 14.01.2017.
 */
public class Food {

    /**
     * Имя продукта.
     */
    private String name;

    /**
     * Срок годности.
     */
    private Date expaireDate;

    /**
     * Дата изготовления.
     */
    private Date createDate;

    /**
     * Цена.
     */
    private double price;

    /**
     * Скидка.
     */
    private double disscount;

    /**
     * Конструктор.
     * @param name имя.
     * @param expaireDate срок годности.
     * @param createDate дата изготовления.
     * @param price цена.
     * @param disscount скидка.
     */
    public Food(String name, Date expaireDate, Date createDate, double price, double disscount) {
        this.name = name;
        this.expaireDate = expaireDate;
        this.createDate = createDate;
        this.price = price;
        this.disscount = disscount;
    }

    /**
     * Геттер.
     * @return цена.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Сеттер.
     * @param price цена.
     */
    public void setPrice(double price) {
        this.price = price - this.disscount;
    }

    /**
     * Расчет срока использования продукта (в процентах).
     * @param currentDate текущая дата.
     * @return срок использования (в процентах).
     */
    public int checkExpirationDate(Date currentDate) {
        int result = Math.round((float) (currentDate.getTime() - this.createDate.getTime())
                / (this.expaireDate.getTime() - this.createDate.getTime()) * 100);
        return result;
    }
}
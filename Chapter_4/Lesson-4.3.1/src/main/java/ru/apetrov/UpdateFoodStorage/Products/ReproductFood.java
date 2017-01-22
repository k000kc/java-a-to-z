package ru.apetrov.UpdateFoodStorage.Products;

import java.util.Date;

/**
 * Created by Andrey on 17.01.2017.
 */
public class ReproductFood extends Food {

    /**
     * Подлежит ли переработке.
     */
    private boolean canReproduct;

    /**
     * Геттер.
     * @return true если подлежит переработке.
     */
    public boolean isCanReproduct() {
        return canReproduct;
    }

    /**
     * Конструктор.
     *
     * @param name          имя.
     * @param expaireDate   срок годности.
     * @param createDate    дата изготовления.
     * @param price         цена.
     * @param disscount     скидка.
     * @param canReproduct  подлежит переработке?
     */
    public ReproductFood(String name, Date expaireDate, Date createDate, double price, double disscount, boolean canReproduct) {
        super(name, expaireDate, createDate, price, disscount);
        this.canReproduct = canReproduct;
    }
}

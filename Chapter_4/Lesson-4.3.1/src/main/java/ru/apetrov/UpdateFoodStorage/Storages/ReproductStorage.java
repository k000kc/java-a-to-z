package ru.apetrov.UpdateFoodStorage.Storages;

import ru.apetrov.UpdateFoodStorage.Products.ReproductFood;

import java.util.Date;

/**
 * Created by Andrey on 22.01.2017.
 */
public abstract class ReproductStorage extends BaseStorage {

    /**
     * Массив переработанных продуктов.
     */
    private ReproductFood[] reproductFoods = new ReproductFood[10];

    /**
     * позиция в массиве.
     */
    private int position = 0;

    /**
     * Добавление переработанных продуктов.
     * @param reproductFood переработанный продукт.
     */
    public void addReproductFood(ReproductFood reproductFood) {
        this.reproductFoods[position++] = reproductFood;
    }

    /**
     * Соответствует ли срок использования товара данному хранилищу.
     * @param reproductFood переработанный продукт.
     * @param currentDate текущая дата.
     * @return true/false.
     */
    public abstract boolean satisfiesConditions(ReproductFood reproductFood, Date currentDate);
}

package ru.apetrov.DynamicFoodStorage.Storages;

import ru.apetrov.DynamicFoodStorage.Products.ReproductFood;

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

    public ReproductFood[] getReproductFoods() {
        return reproductFoods;
    }

    public void setReproductFoods(ReproductFood[] reproductFoods) {
        this.reproductFoods = reproductFoods;
    }

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

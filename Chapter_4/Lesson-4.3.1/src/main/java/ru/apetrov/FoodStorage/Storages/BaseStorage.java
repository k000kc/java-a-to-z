package ru.apetrov.FoodStorage.Storages;

import ru.apetrov.FoodStorage.Products.Food;

import java.util.Date;

/**
 * Created by Andrey on 14.01.2017.
 */
public abstract class BaseStorage {

    /**
     * Массив продуктов.
     */
    private Food[] foods = new Food[10];

    /**
     * Позиция продукта.
     */
    private int position = 0;

    /**
     * Добавление продукта в массив.
     * @param food продукт.
     */
    public void addFood(Food food) {
        this.foods[this.position++] = food;
    }

    /**
     * Соответствует ли срок использования товара данному хранилищу.
     * @param food товар.
     * @param currentDate текущая дата.
     * @return true/false.
     */
    public abstract boolean satisfiesConditions(Food food, Date currentDate);
}

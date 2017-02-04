package ru.apetrov.DynamicFoodStorage.Storages;

import ru.apetrov.DynamicFoodStorage.Products.Food;

import java.util.Date;

/**
 * Created by Andrey on 14.01.2017.
 */
public class Trash extends BaseStorage {

    /**
     * Соответствует ли срок использования товара данному хранилищу.
     * @param food товар.
     * @param currentDate текущая дата.
     * @return true/false.
     */
    @Override
    public boolean satisfiesConditions(Food food, Date currentDate) {
        boolean result = false;

        if (food.checkExpirationDate(currentDate) > 100) {
            result = true;
        }

        return result;
    }
}
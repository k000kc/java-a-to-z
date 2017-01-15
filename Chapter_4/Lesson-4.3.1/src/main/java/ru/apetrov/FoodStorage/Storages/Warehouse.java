package ru.apetrov.FoodStorage.Storages;

import ru.apetrov.FoodStorage.Products.Food;

import java.util.Date;

/**
 * Created by Andrey on 14.01.2017.
 */
public class Warehouse extends BaseStorage {

    /**
     * Соответствует ли срок использования товара данному хранилищу.
     * @param food товар.
     * @param currentDate текущая дата.
     * @return true/false.
     */
    @Override
    public boolean satisfiesConditions(Food food, Date currentDate) {
        boolean result = false;

        if (food.checkExpirationDate(currentDate) < 25) {
            result = true;
        }

        return result;
    }
}

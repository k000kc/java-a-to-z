package ru.apetrov.DynamicFoodStorage.Storages;

import ru.apetrov.DynamicFoodStorage.Products.Food;
import ru.apetrov.DynamicFoodStorage.Products.ReproductFood;

import java.util.Date;

/**
 * Created by Andrey on 22.01.2017.
 */
public class DeepFreeze extends ReproductStorage {

    /**
     * Соответствует ли срок использования товара данному хранилищу.
     * @param reproductFood переработанный продукт.
     * @param currentDate текущая дата.
     * @return подлежит переработке?
     */
    public boolean satisfiesConditions(ReproductFood reproductFood, Date currentDate) {
        boolean result = false;
        if (reproductFood.isCanReproduct() && reproductFood.checkExpirationDate(currentDate) > 100) {
            result = true;
        }
        return result;
    }

    /**
     * Соответствует ли срок использования товара данному хранилищу.
     * @param food товар.
     * @param currentDate текущая дата.
     * @return не подлежит переработке.
     */
    public boolean satisfiesConditions(Food food, Date currentDate) {
        return false;
    }
}

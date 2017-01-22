package ru.apetrov.UpdateFoodStorage.Storages;

import ru.apetrov.UpdateFoodStorage.Products.Food;
import ru.apetrov.UpdateFoodStorage.Products.ReproductFood;

import java.util.Date;

/**
 * Created by Andrey on 17.01.2017.
 */
public class NewWarehouse extends Warehouse {

    /**
     * Соответствует ли срок использования товара данному хранилищу.
     * @param food товар.
     * @param currentDate текущая дата.
     * @return true/false.
     */
    public boolean satisfiesConditions(Food food, Date currentDate) {
        return super.satisfiesConditions(food, currentDate);
    }
}

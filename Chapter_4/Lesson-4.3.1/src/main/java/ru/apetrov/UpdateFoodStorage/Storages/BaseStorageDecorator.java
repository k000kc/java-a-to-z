package ru.apetrov.UpdateFoodStorage.Storages;

import ru.apetrov.UpdateFoodStorage.Products.Food;

import java.util.Date;

/**
 * Created by Andrey on 18.01.2017.
 */
public abstract class BaseStorageDecorator extends BaseStorage {

    private BaseStorage baseStorage;

    public BaseStorageDecorator(BaseStorage baseStorage) {
        this.baseStorage = baseStorage;
    }

    public boolean satisfiesConditions(Food food, Date currentDate) {
        return this.baseStorage.satisfiesConditions(food, currentDate);
    }
}

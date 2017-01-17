package ru.apetrov.UpdateFoodStorage.Storages;

import ru.apetrov.UpdateFoodStorage.Products.Food;

import java.util.Date;

/**
 * Created by Andrey on 17.01.2017.
 */
public abstract class BaseStorageDecorator extends BaseStorage {

    private BaseStorage storage;

    public BaseStorageDecorator(BaseStorage storage) {
        this.storage = storage;
    }

    @Override
    public boolean satisfiesConditions(Food food, Date currentDate) {
        return storage.satisfiesConditions(food, currentDate);
    }
}

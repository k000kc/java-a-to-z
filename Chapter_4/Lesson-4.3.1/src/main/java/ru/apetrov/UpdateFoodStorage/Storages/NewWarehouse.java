package ru.apetrov.UpdateFoodStorage.Storages;

import ru.apetrov.UpdateFoodStorage.Products.Food;

import java.util.Date;

/**
 * Created by Andrey on 17.01.2017.
 */
public class NewWarehouse extends BaseStorageDecorator {

    public NewWarehouse(BaseStorage storage) {
        super(storage);
    }

    @Override
    public boolean satisfiesConditions(Food food, Date currentDate) {
        return super.satisfiesConditions(food, currentDate);
    }
}

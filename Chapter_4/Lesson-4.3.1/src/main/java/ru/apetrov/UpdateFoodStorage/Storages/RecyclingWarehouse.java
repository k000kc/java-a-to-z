package ru.apetrov.UpdateFoodStorage.Storages;

import ru.apetrov.UpdateFoodStorage.Products.ReproductFood;

import java.util.Date;

/**
 * Created by Andrey on 18.01.2017.
 */
public class RecyclingWarehouse extends BaseStorageDecorator {

    public RecyclingWarehouse(BaseStorage baseStorage) {
        super(baseStorage);
    }

    public boolean satisfiesConditions(ReproductFood reproductFood, Date currentDate) {
        boolean result =false;

        if (reproductFood.isCanReproduct()) {
            result = reproductFood.checkExpirationDate(currentDate) > 100;
        }
        return result;
    }
}

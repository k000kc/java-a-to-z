package ru.apetrov.UpdateFoodStorage.Storages;

import ru.apetrov.UpdateFoodStorage.Products.Food;
import ru.apetrov.UpdateFoodStorage.Products.ReproductFood;

import java.util.Date;

/**
 * Created by Andrey on 18.01.2017.
 */
public class DeepFreeze extends BaseStorageDecorator {

    public DeepFreeze(BaseStorage baseStorage) {
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

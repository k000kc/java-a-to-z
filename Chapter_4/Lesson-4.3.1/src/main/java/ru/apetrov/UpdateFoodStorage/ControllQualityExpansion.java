package ru.apetrov.UpdateFoodStorage;

import ru.apetrov.UpdateFoodStorage.Products.ReproductFood;
import ru.apetrov.UpdateFoodStorage.Storages.*;

import java.util.Date;

/**
 * Created by Andrey on 17.01.2017.
 */
public class ControllQualityExpansion extends ControllQuality{

    private int position = 0;

    private ReproductStorage[] reproductStorages = new ReproductStorage[1];

    public ControllQualityExpansion(int position) {
        super(position);
    }

    public ReproductStorage[] getReproductStorages() {
        return reproductStorages;
    }

    @Override
    public void init() {
        this.getStorages()[0] = new Warehouse();
        this.getStorages()[1] = new Shop();
        this.getStorages()[2] = new Trash();
        this.getStorages()[3] = new NewWarehouse();
    }

    public void addStrorage(ReproductStorage storage) {
        this.reproductStorages[position++] = storage;
    }

    public ReproductStorage rellocateReproductFoods(ReproductFood reproductFood, Date currentDate) {
        ReproductStorage result = null;
        for (ReproductStorage storage : this.reproductStorages) {
            if (storage != null) {
                if (storage.satisfiesConditions(reproductFood, currentDate)) {
                    storage.addReproductFood(reproductFood);
                    result = storage;
                }
            }
        }
        return result;
    }
}

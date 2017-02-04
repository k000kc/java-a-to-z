package ru.apetrov.DynamicFoodStorage;

import ru.apetrov.DynamicFoodStorage.Products.ReproductFood;
import ru.apetrov.DynamicFoodStorage.Storages.NewWarehouse;
import ru.apetrov.DynamicFoodStorage.Storages.ReproductStorage;

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
        super.init();
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

    public void resort(Date currentDate) {
        ReproductFood[] temp = new ReproductFood[10];
        int position = 0;
        for (ReproductStorage storage : this.getReproductStorages()) {
            if (storage != null) {
                for (ReproductFood food : storage.getReproductFoods()) {
                    if (food != null) {
                        temp[position++] = food;
                    }
                }
                this.clean();
            }
        }
        for (ReproductFood food : temp) {
            if (food != null) {
                this.rellocateReproductFoods(food, currentDate);
            }
        }
    }

    public void clean() {
        for (ReproductStorage storage : this.getReproductStorages()) {
            if (storage != null) {
                storage.setReproductFoods(new ReproductFood[10]);
            }
        }
    }
}

package ru.apetrov.UpdateFoodStorage;

import ru.apetrov.UpdateFoodStorage.Storages.*;

/**
 * Created by Andrey on 17.01.2017.
 */
public class ControlQualityExpansion extends ControllQuality{

    /**
     * Конструктор.
     *
     * @param position Размерность массива (колличество продуктовых хранилищ).
     */
    public ControlQualityExpansion(int position) {
        super(position);
    }

    @Override
    public void init() {
        this.getStorages()[0] = new NewWarehouse(new Warehouse());
        this.getStorages()[1] = new Shop();
        this.getStorages()[2] = new Trash();
    }
}

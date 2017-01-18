package ru.apetrov.UpdateFoodStorage;

import ru.apetrov.UpdateFoodStorage.Storages.*;

/**
 * Created by Andrey on 17.01.2017.
 */
public class ControllQualityExpansion extends ControllQuality{

    /**
     * Конструктор.
     *
     * @param position Размерность массива (колличество продуктовых хранилищ).
     */
    public ControllQualityExpansion(int position) {
        super(position);
    }

    @Override
    public void init() {
        super.init();
        this.getStorages()[3] = new NewWarehouse();
        this.getStorages()[4] = new RecyclingWarehouse(new Trash());
    }
}

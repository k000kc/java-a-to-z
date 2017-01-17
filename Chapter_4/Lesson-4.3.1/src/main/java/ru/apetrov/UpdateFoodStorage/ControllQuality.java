package ru.apetrov.UpdateFoodStorage;

import ru.apetrov.UpdateFoodStorage.Products.Food;
import ru.apetrov.UpdateFoodStorage.Storages.BaseStorage;
import ru.apetrov.UpdateFoodStorage.Storages.Shop;
import ru.apetrov.UpdateFoodStorage.Storages.Trash;
import ru.apetrov.UpdateFoodStorage.Storages.Warehouse;

import java.util.Date;

/**
 * Created by Andrey on 14.01.2017.
 */
public class ControllQuality {

    /**
     * Размерность массива (колличество продуктовых хранилищ).
     */
    private int position;

    /**
     * Массив продуктовых хранилищ.
     */
    private BaseStorage[] storages;

    /**
     * Конструктор.
     * @param position Размерность массива (колличество продуктовых хранилищ).
     */
    public ControllQuality(int position) {
        this.position = position;
        this.storages = new BaseStorage[this.position];
        init();
    }

    /**
     * Геттер.
     * @return Массив продуктовых хранилищ.
     */
    public BaseStorage[] getStorages() {
        return storages;
    }

    /**
     * Инициализация массива продуктовых хранилищ.
     */
    public void init() {
        this.storages[0] = new Warehouse();
        this.storages[1] = new Shop();
        this.storages[2] = new Trash();
    }

    /**
     * Помещаем продукты в соответствующее хранилище.
     * @param food продукт.
     * @param currentDate текущая дата.
     * @return продуктовое хранилище.
     */
    public BaseStorage rellocateFoods(Food food, Date currentDate) {
        BaseStorage result = null;
        for (BaseStorage storage : this.storages) {
            if (storage != null) {
                if (storage.satisfiesConditions(food, currentDate)) {
                    storage.addFood(food);
                    result = storage;
                }
            }
        }
        return result;
    }
}

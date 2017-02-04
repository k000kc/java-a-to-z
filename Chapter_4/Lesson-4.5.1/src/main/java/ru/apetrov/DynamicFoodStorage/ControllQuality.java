package ru.apetrov.DynamicFoodStorage;

import ru.apetrov.DynamicFoodStorage.Products.Food;
import ru.apetrov.DynamicFoodStorage.Storages.BaseStorage;
import ru.apetrov.DynamicFoodStorage.Storages.Shop;
import ru.apetrov.DynamicFoodStorage.Storages.Trash;
import ru.apetrov.DynamicFoodStorage.Storages.Warehouse;

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
        this.storages = new BaseStorage[position];
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

    public void resort(Date currentDate) {
        Food[] temp = new Food[10];
        int position = 0;
        for (BaseStorage storage : this.getStorages()) {
            if (storage != null) {
                for (Food food : storage.getFoods()) {
                    if (food != null) {
                        temp[position++] = food;
                    }
                }
                this.clean();
            }
        }
        for (Food food : temp) {
            if (food != null) {
                this.rellocateFoods(food, currentDate);
            }
        }
    }

    public void clean() {
        for (BaseStorage storage: this.getStorages()) {
            if (storage != null) {
                storage.setFoods(new Food[10]);
            }
        }
    }
}

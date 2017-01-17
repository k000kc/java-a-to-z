package ru.apetrov.UpdateFoodStorageTest;

import org.junit.Before;
import org.junit.Test;
import ru.apetrov.UpdateFoodStorage.ControlQualityExpansion;
import ru.apetrov.UpdateFoodStorage.ControllQuality;
import ru.apetrov.UpdateFoodStorage.Products.Food;
import ru.apetrov.UpdateFoodStorage.Products.ReproductFood;
import ru.apetrov.UpdateFoodStorage.Storages.BaseStorage;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Andrey on 17.01.2017.
 */
public class ControlQualityExpansionTest {

    /**
     * Класс контроля качества (расширенный).
     */
    private ControlQualityExpansion controlQualityExpansion;

    /**
     * Класс контроля качества.
     */
    private ControllQuality controllQuality;

    /**
     * Срок годности.
     */
    private Date expaireDate;

    /**
     * Дата изготовления.
     */
    private Date createDate;

    /**
     * Текущая дата.
     */
    private Date currentDate;

    /**
     * Продукт.
     */
    private Food food;

    private ReproductFood reproductFood;

    /**
     * Инициализация.
     * @throws ParseException exeption.
     */
    @Before
    public void init() throws ParseException {
        controllQuality = new ControllQuality(3);
        controlQualityExpansion = new  ControlQualityExpansion(4);
        expaireDate = new SimpleDateFormat("yyyy-MM-dd").parse("2017-01-30");
        createDate = new SimpleDateFormat("yyyy-MM-dd").parse("2017-01-01");
        food = new Food("Apple", expaireDate, createDate, 65.5, 10.0);
    }

    /**
     * Ситуация когда продукты помещаются на склад.
     */
    @Test
    public void whenFoodsPlaceWarehouse() {
        try {
            currentDate = new SimpleDateFormat("yyyy-MM-dd").parse("2017-01-02");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        BaseStorage storage = controllQuality.rellocateFoods(food, currentDate);
        assertThat(storage, is(controllQuality.getStorages()[0]));
    }

    /**
     * Ситуация когда продукты помещаются на !!!НОВЫЙ!!! склад.
     */
    @Test
    public void whenFoodsPlaceNewWarehous() {
        reproductFood = new ReproductFood("Milk", expaireDate, createDate, 40.0, 10.0, false);
        try {
            currentDate = new SimpleDateFormat("yyyy-MM-dd").parse("2017-01-03");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        BaseStorage storage = controlQualityExpansion.rellocateFoods(reproductFood, currentDate);
        assertThat(storage, is(controlQualityExpansion.getStorages()[3]));
    }

    /**
     * Ситуация когда продукты помещаются в магазин.
     */
    @Test
    public void whenFoodsPlaceShop() {
        try {
            currentDate = new SimpleDateFormat("yyyy-MM-dd").parse("2017-01-15");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        BaseStorage storage = controllQuality.rellocateFoods(food, currentDate);
        assertThat(storage, is(controllQuality.getStorages()[1]));
    }

    /**
     * Ситуация когда продукты помещаются в мусорку.
     */
    @Test
    public void whenFoodsPlaceTrash() {
        try {
            currentDate = new SimpleDateFormat("yyyy-MM-dd").parse("2017-01-31");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        BaseStorage storage = controllQuality.rellocateFoods(food, currentDate);
        assertThat(storage, is(controllQuality.getStorages()[2]));
    }

    /**
     * Ситуация когда у продукта срок годности подходит к концу.
     */
    @Test
    public void whenExpirationDateEndsThenGiveDiscount() {
        try {
            currentDate = new SimpleDateFormat("yyyy-MM-dd").parse("2017-01-28");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        controllQuality.rellocateFoods(food, currentDate);
        assertThat(food.getPrice(), is(55.5));
    }
}

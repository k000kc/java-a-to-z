package ru.apetrov.DynamicFoodStorageTest;

import org.junit.Before;
import org.junit.Test;
import ru.apetrov.DynamicFoodStorage.ControllQuality;
import ru.apetrov.DynamicFoodStorage.Products.Food;
import ru.apetrov.DynamicFoodStorage.Storages.BaseStorage;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Andrey on 17.01.2017.
 */
public class ControllQualityTest {

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

    /**
     * Инициализация.
     * @throws ParseException exeption.
     */
    @Before
    public void init() throws ParseException {
        controllQuality = new ControllQuality(3);
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
        controllQuality.resort(currentDate);
        assertThat(storage, is(controllQuality.getStorages()[0]));
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
        controllQuality.resort(currentDate);
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
        controllQuality.resort(currentDate);
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
        controllQuality.resort(currentDate);
        assertThat(food.getPrice(), is(55.5));
    }
}
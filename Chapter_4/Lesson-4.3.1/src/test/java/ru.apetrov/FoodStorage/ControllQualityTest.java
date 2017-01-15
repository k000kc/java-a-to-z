package ru.apetrov.FoodStorage;

import org.junit.Before;
import org.junit.Test;
import ru.apetrov.FoodStorage.Products.Food;
import ru.apetrov.FoodStorage.Storages.BaseStorage;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Andrey on 14.01.2017.
 */
public class ControllQualityTest {

    ControllQuality controllQuality;
    Date expaireDate;
    Date createDate;
    Date currentDate;
    Food food;

    @Before
    public void init() throws ParseException {
        controllQuality = new ControllQuality(3);
        expaireDate = new SimpleDateFormat("yyyy-MM-dd").parse("2017-01-30");
        createDate = new SimpleDateFormat("yyyy-MM-dd").parse("2017-01-01");
        food = new Food("Apple", expaireDate, createDate,65.5, 10.0);
    }

    @Test
    public void whenFoodsPlaceWarehouse() {
        try {
            currentDate = new SimpleDateFormat("yyyy-MM-dd").parse("2017-01-02");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        BaseStorage storage = controllQuality.rellocateFoods(food,currentDate);
        assertThat(storage, is(controllQuality.getStorages()[0]));
    }

    @Test
    public void whenFoodsPlaceShop() {
        try {
            currentDate = new SimpleDateFormat("yyyy-MM-dd").parse("2017-01-15");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        BaseStorage storage = controllQuality.rellocateFoods(food,currentDate);
        assertThat(storage, is(controllQuality.getStorages()[1]));
    }

    @Test
    public void whenFoodsPlaceTrash() {
        try {
            currentDate = new SimpleDateFormat("yyyy-MM-dd").parse("2017-01-31");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        BaseStorage storage = controllQuality.rellocateFoods(food,currentDate);
        assertThat(storage, is(controllQuality.getStorages()[2]));
    }

    @Test
    public void whenExpirationDateEndsThenGiveDiscount() {
        try {
            currentDate = new SimpleDateFormat("yyyy-MM-dd").parse("2017-01-28");
        } catch (ParseException e) {
            e.printStackTrace();
        }
       controllQuality.rellocateFoods(food,currentDate);
        assertThat(food.getPrice(), is(55.5));
    }
}

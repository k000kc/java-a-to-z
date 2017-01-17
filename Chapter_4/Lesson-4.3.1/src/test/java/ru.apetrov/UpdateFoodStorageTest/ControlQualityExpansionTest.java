package ru.apetrov.UpdateFoodStorageTest;

import org.junit.Before;
import org.junit.Test;
import ru.apetrov.UpdateFoodStorage.Products.Food;
import ru.apetrov.UpdateFoodStorage.Storages.BaseStorage;
import ru.apetrov.UpdateFoodStorage.ControlQualityExpansion;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Andrey on 17.01.2017.
 */
public class ControlQualityExpansionTest {

    private ControlQualityExpansion controlQualityExpansion;

    private Date expaireDate;

    private Date createDate;

    private Date currentDate;

    private Food food;

    @Before
    public void init() throws ParseException {
        controlQualityExpansion = new ControlQualityExpansion(3);
        expaireDate = new SimpleDateFormat("yyyy-MM-dd").parse("2017-01-30");
        createDate = new SimpleDateFormat("yyyy-MM-dd").parse("2017-01-01");
        food = new Food("Apple", expaireDate, createDate, 65.5, 10.0);
    }

    @Test
    public void whenFoodsPlaceWarehouse() {
        try {
            currentDate = new SimpleDateFormat("yyyy-MM-dd").parse("2017-01-02");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        BaseStorage storage = controlQualityExpansion.rellocateFoods(food, currentDate);
        assertThat(storage, is(controlQualityExpansion.getStorages()[0]));
    }
}

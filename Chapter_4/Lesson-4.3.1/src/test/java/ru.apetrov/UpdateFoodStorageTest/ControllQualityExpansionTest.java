package ru.apetrov.UpdateFoodStorageTest;

import org.junit.Before;
import org.junit.Test;
import ru.apetrov.UpdateFoodStorage.ControllQualityExpansion;
import ru.apetrov.UpdateFoodStorage.Products.ReproductFood;
import ru.apetrov.UpdateFoodStorage.Storages.BaseStorage;
import ru.apetrov.UpdateFoodStorage.Storages.DeepFreeze;
import ru.apetrov.UpdateFoodStorage.Storages.ReproductStorage;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Andrey on 18.01.2017.
 */
public class ControllQualityExpansionTest {

    /**
     * Класс контроля качества (расширенный).
     */
    private ControllQualityExpansion controlQualityExpansion;

    /**
     * Продукт.
     */
    private ReproductFood reproductFood;

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
     * Инициализация.
     * @throws ParseException exeption.
     */
    @Before
    public void init() throws ParseException {
        controlQualityExpansion = new ControllQualityExpansion(4);
        expaireDate = new SimpleDateFormat("yyyy-MM-dd").parse("2017-01-30");
        createDate = new SimpleDateFormat("yyyy-MM-dd").parse("2017-01-01");
        controlQualityExpansion.addStrorage(new DeepFreeze());
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
     * Ситуация когда продукты помещаются в морозильный склад.
     */
    @Test
    public void whenFoodsPlaceDeepFreeze() {
        reproductFood = new ReproductFood("Milk", expaireDate, createDate, 40.0, 10.0, true);
        try {
            currentDate = new SimpleDateFormat("yyyy-MM-dd").parse("2017-01-31");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        ReproductStorage storage = controlQualityExpansion.rellocateReproductFoods(reproductFood, currentDate);
        assertThat(storage, is(controlQualityExpansion.getReproductStorages()[0]));
    }
}

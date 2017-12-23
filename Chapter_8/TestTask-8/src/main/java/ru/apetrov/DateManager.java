package ru.apetrov;

import java.io.*;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by Andrey on 19.12.2017.
 */
public class DateManager {

    /**
     * карта месяцев, для преобразования строки (имя месяца) в число(номер месяца).
     */
    private final Map<String, Integer> months;

    /**
     * один день в мс.
     */
    private final long DAY = 1000 * 60 * 60 * 24;

    /**
     * Проверка в первый ли раз запускается программа.
     */
    private boolean isFirstStart;

    /**
     * для работы с конфигурацией.
     */
    private Properties properties;

    /**
     * Класс настроек.
     */
    private Settings settings;

    /**
     * Конструктор.
     */
    public DateManager() {
        this.settings = new Settings();
        this.properties = new Properties();
        this.initConnection();
        this.months = new HashMap<>();
        this.initMonths();
    }

    /**
     * Проверка в первый ли раз запускается программа.
     * @return true - если программа запущенна впервые.
     */
    public boolean isFirstStart() {
        return isFirstStart;
    }

    /**
     * читаем из конфигурационного файла значение по ключу first_start,
     * если true - значит программа запускается впервые. После изменяем значение по кючу first_start на false.
     */
    private void initConnection() {
        this.isFirstStart = Boolean.valueOf(this.settings.getValue("first_start"));
        if (this.isFirstStart) {
            this.settings.savePropertiesForSecondStart("first_start","false");
        }
    }

    /**
     * инициализируем карту месяцев.
     */
    private void initMonths() {
        this.months.put("янв", 1);
        this.months.put("фев", 2);
        this.months.put("мар", 3);
        this.months.put("апр", 4);
        this.months.put("май", 5);
        this.months.put("июн", 6);
        this.months.put("июл", 7);
        this.months.put("авг", 8);
        this.months.put("сен", 9);
        this.months.put("окт", 10);
        this.months.put("ноя", 11);
        this.months.put("дек", 12);
    }

    /**
     * выбираем месяц по ключу.
     * @param month месяц.
     * @return число месяца.
     */
    private int selectMonth(String month) {
        return this.months.get(month);
    }

    /**
     * Из строчного представления даты, получаем дату типа Timestamp.
     * @param string дата формата (сегодня, 18:46, вчера, 21:13, 20 дек 17, 12:16).
     * @return дата типа Timestamp.
     */
    public Timestamp getCreateDate(String string) {
        String[] date = string.split("\\s|\\,");
        StringBuilder builder = new StringBuilder();
        Date parseDate = null;
        SimpleDateFormat result = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (date[0].equals("сегодня")){
            parseDate = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String str = dateFormat.format(parseDate);
            builder.append(String.format("%s %s:00.0", str, date[2]));
        } else if (date[0].equals("вчера")){
            parseDate = new Date();
            parseDate.setTime(parseDate.getTime() - DAY);
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String str = dateFormat.format(parseDate);
            builder.append(String.format("%s %s:00.0", str, date[2]));
        } else {
            builder.append(String.format("20%s-%s-%s %s:00.0", date[2], this.selectMonth(date[1]), date[0], date[4]));
        }
        try {
            parseDate = result.parse(builder.toString());

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Timestamp(parseDate.getTime());
    }
}

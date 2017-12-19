package ru.apetrov;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Andrey on 19.12.2017.
 */
public class DateManager {

    private final Map<String, Integer> months;
    private final long DAY = 1000 * 60 * 60 * 24;

    public DateManager() {
        this.months = new HashMap<>();
        this.initMonths();
    }

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

    private int selectMonth(String month) {
        return this.months.get(month);
    }

    public Timestamp getCreateDate(String string) {
        String[] date = string.split("\\s|\\,");
        StringBuilder builder = new StringBuilder();
        Date parseDate = null;
        SimpleDateFormat result = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        if (date[0].equals("сегодня")){
            parseDate = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
            String str = dateFormat.format(parseDate);
            builder.append(String.format("%s %s:00", str, date[2]));
        } else if (date[0].equals("вчера")){
            parseDate = new Date();
            parseDate.setTime(parseDate.getTime() - DAY);
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
            String str = dateFormat.format(parseDate);
            builder.append(String.format("%s %s:00", str, date[2]));
        } else {
            builder.append(String.format("20%s.%s.%s %s:00", date[2], this.selectMonth(date[1]), date[0], date[4]));
        }
        try {
            parseDate = result.parse(builder.toString());

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Timestamp(parseDate.getTime());
    }
}

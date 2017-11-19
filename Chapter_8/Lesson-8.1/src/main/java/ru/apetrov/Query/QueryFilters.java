package ru.apetrov.Query;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrey on 31.10.2017.
 */
public class QueryFilters {

    /**
     * Фильтры.
     */
    private final List<Filter> filters;

    /**
     * Билдер.
     */
    private StringBuilder builder;

    /**
     * поля.
     */
    private String fields;

    /**
     * таблицы.
     */
    private String table;


    /**
     * Конструктор.
     * @param fields поля.
     * @param table таблицы.
     */
    public QueryFilters(String fields, String table) {
        this.filters = new ArrayList<>();
        this.fillFilters();
        this.builder = new StringBuilder();
        this.fields = fields;
        this.table = table;
        this.builder.append(String.format("SELECT %s FROM %s AS %s ", fields, table, table.charAt(0)));
    }

    /**
     * заполнение фильтров.
     */
    private void fillFilters() {
        filters.add(new Equally());
        filters.add(new Larger());
        filters.add(new Smaller());
        filters.add(new Contains());
    }

    /**
     * выбор фильтра.
     * @param key ключ фильтра.
     * @return фильтр.
     */
    public Filter select(int key) {
        return filters.get(key);
    }

    /**
     * Фильтр "Равно".
     */
    private class Equally implements Filter {

        @Override
        public int key() {
            return 0;
        }

        @Override
        public String execute(String field, String criterion) {
            builder.append(String.format("WHERE %s.%s = %s;", table.charAt(0), field, criterion));
            return builder.toString();
        }
    }

    /**
     * Фильтр "Больше".
     */
    private class Larger implements Filter {

        @Override
        public int key() {
            return 1;
        }

        @Override
        public String execute(String field, String criterion) {
            builder.append(String.format("WHERE %s.%s > %s;", table.charAt(0), field, criterion));
            return builder.toString();
        }

    }

    /**
     * Фильтр "Меньше".
     */
    private class Smaller implements Filter {

        @Override
        public int key() {
            return 2;
        }

        @Override
        public String execute(String field, String criterion) {
            builder.append(String.format("WHERE %s.%s < %s;", table.charAt(0), field, criterion));
            return builder.toString();
        }

    }

    /**
     * Фильтр "Содержит".
     */
    private class Contains implements Filter {

        @Override
        public int key() {
            return 0;
        }

        @Override
        public String execute(String field, String criterion) {
            builder.append(String.format("WHERE %s.%s LIKE '%%%s%%';", table.charAt(0), field, criterion));
            return builder.toString();
        }
    }
}

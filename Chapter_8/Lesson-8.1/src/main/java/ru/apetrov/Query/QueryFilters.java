package ru.apetrov.Query;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrey on 31.10.2017.
 */
public class QueryFilters {

    private final List<Filter> filters;
    private StringBuilder builder;
    private String fields;
    private String table;


    public QueryFilters(String fields, String table) {
        this.filters = new ArrayList<>();
        this.fillFilters();
        this.builder = new StringBuilder();
        this.fields = fields;
        this.table = table;
        this.builder.append(String.format("SELECT %s FROM %s AS %s ", fields, table, table.charAt(0)));
    }

    private void fillFilters() {
        filters.add(new Equally());
        filters.add(new Larger());
        filters.add(new Smaller());
        filters.add(new Contains());
    }

    public Filter select(int key) {
        return filters.get(key);
    }

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

package ru.apetrov.Query;

/**
 * Created by Andrey on 31.10.2017.
 */
public interface Filter {

    /**
     * Ключ которому соответствует фильтр.
     * @return значение ключа.
     */
    int key();

    /**
     * Выполнение фильтра.
     * @param field поле.
     * @param criterion критерий.
     * @return запрос.
     */
    String execute(String field, String criterion);
}

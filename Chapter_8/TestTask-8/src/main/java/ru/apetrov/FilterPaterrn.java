package ru.apetrov;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Andrey on 14.12.2017.
 */
public class FilterPaterrn {

    /**
     * шаблон поиска слова java.
     */
    private final Pattern vacancyPattern = Pattern.compile("Java|java");

    /**
     * шаблон по которому будет игнорироваться слово script.
     */
    private final Pattern notVacancyPattern = Pattern.compile("Script|script");

    /**
     * Проверим существует ли слово java, и отсутствует ли слово script в переменной str.
     * @param str нзвание вакансии.
     * @return true если название корректно.
     */
    private boolean isCorrectVacancy(String str) {
        Matcher matcher = this.vacancyPattern.matcher(str);
        Matcher matcher1 = this.notVacancyPattern.matcher(str);
        return matcher.find() && (!matcher1.find());
    }

    /**
     * проверка на корректность.
     * @param vacancy вакансия.
     * @return true если название корректно.
     */
    public boolean isCorrect(String vacancy) {
        return this.isCorrectVacancy(vacancy);
    }
}

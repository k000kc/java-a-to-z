package ru.apetrov;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Andrey on 14.12.2017.
 */
public class FilterPaterrn {

    private final Pattern datePattern = Pattern.compile("([а-я]{5,7}[\\,]{1})|([\\d]{1,2}[\\s]{1}[а-я]{3}[\\s]{1}17[\\,])");
    private final Pattern vacancyPattern = Pattern.compile("Java|java");
    private final Pattern notVacancyPattern = Pattern.compile("Script|script");

//    private boolean isCorrectDate(String str) {
//        Matcher matcher = datePattern.matcher(str);
//        return matcher.find();
//    }

    private boolean isCorrectVacancy(String str) {
        Matcher matcher = this.vacancyPattern.matcher(str);
        Matcher matcher1 = this.notVacancyPattern.matcher(str);
        return matcher.find() && (!matcher1.find());
    }

    public boolean isCorrect(String vacancy, String createDate) {
        return this.isCorrectVacancy(vacancy);// && this.isCorrectDate(createDate);
    }
}

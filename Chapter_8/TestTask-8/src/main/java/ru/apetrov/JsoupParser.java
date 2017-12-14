package ru.apetrov;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Andrey on 13.12.2017.
 */
public class JsoupParser {

    private final Pattern datePattern = Pattern.compile("([а-я]{5,7}[\\,]{1})|([\\d]{1,2}[\\s]{1}[а-я]{3}[\\s]{1}17[\\,])");
    private final Pattern vacancyPattern = Pattern.compile("Java|java");
    private final Pattern notVacancyPattern = Pattern.compile("Script|script");

    private boolean isCorrectDate(String str) {
        Matcher matcher = datePattern.matcher(str);
        return matcher.find();
    }

    private boolean isCorrectVacancy(String str) {
        Matcher matcher = this.vacancyPattern.matcher(str);
        Matcher matcher1 = this.notVacancyPattern.matcher(str);
        return matcher.find() && (!matcher1.find());
    }

    private Document getPage(String url) {
        Document document = null;
        try {
            document = Jsoup.parse(new URL(url), 3000);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return document;
    }

    public void parse(String url) {
        Document document = this.getPage(url);
        Element forumTable = document.select("table[class=forumTable]").first();
        Elements values = forumTable.select("tr");

        for (Element value : values) {
            Element postslisttopic = value.select("td[class=postslisttopic]").first();
            Element altCol = value.select("td[class=altCol]").first();
            Element altColCreateDate = value.select("td[class=altCol]").last();
            if (postslisttopic != null) {
                String theme = postslisttopic.child(0).text();
                String author = altCol.text();
                String createDate = altColCreateDate.text();
                if (this.isCorrectDate(createDate) && this.isCorrectVacancy(theme)) {
                    System.out.println(theme);
                    System.out.println(author);
                    System.out.println(createDate + "\n");
                }
            }
        }
    }
}

package ru.apetrov;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;

/**
 * Created by Andrey on 13.12.2017.
 */
public class JsoupParser {

    private final FilterPaterrn filter;

    public JsoupParser() {
        this.filter = new FilterPaterrn();
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
                String vacancy = postslisttopic.child(0).text();
                String author = altCol.text();
                String createDate = altColCreateDate.text();
                if (this.filter.isCorrect(vacancy, createDate)) {
                    System.out.println(vacancy);
                    System.out.println(author);
                    System.out.println(createDate + "\n");
                }
            }
        }
    }
}

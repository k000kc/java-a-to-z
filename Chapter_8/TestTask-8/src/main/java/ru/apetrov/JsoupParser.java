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
    private boolean endLoop;
    private JDBCStorege storege;


    public JsoupParser() {
        this.filter = new FilterPaterrn();
        this.endLoop = false;
        this.storege = new JDBCStorege();

    }

    public void loop(String url) throws IOException {
        Document document = this.getPage(url);
        do {
            this.parse(document);
            url = this.next(url);
            document = this.getPage(url);
        } while (!this.endLoop);
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

    public void parse(Document document) throws IOException {
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
                    this.storege.add(vacancy,author,createDate);
                    System.out.println(vacancy);
                    System.out.println(author);
                    System.out.println(createDate + "\n");
                }
            }
        }
    }

    public String next(String url) {
        String result = url;
        Document document = this.getPage(url);
        Element sortOptions = document.select("table[class=sort_options]").last();
        Element tr = sortOptions.getElementsByTag("td").first();
        for(int i = 0; i < 10; i++) {
            if (tr.select("b").text().equals(tr.child(9).text())){
                this.endLoop=true;
                break;
            }
            if (tr.child(i).text().equals(tr.select("b").text())) {
                Element element = tr.child(++i);
                result = element.attr("href");
                break;
            }
        }
        return result;
    }
}

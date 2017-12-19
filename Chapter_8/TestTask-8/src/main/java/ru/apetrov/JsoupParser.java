package ru.apetrov;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;

/**
 * Created by Andrey on 13.12.2017.
 */
public class JsoupParser {

    private final FilterPaterrn filter;
    private boolean endLoop;
    private DateManager dateManager;

    public JsoupParser() {
        this.filter = new FilterPaterrn();
        this.endLoop = false;
        this.dateManager = new DateManager();
    }

    public void loop(String url) throws IOException {
        try(JDBCStorege storege = new JDBCStorege()) {
            Document document = this.getPage(url);
            do {
                this.parse(document, storege);
                url = this.next(url);
                document = this.getPage(url);
            } while (!this.endLoop);
        } catch (SQLException e) {
            e.printStackTrace();
        }
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

    public void parse(Document document, JDBCStorege storege) throws IOException {
        Element forumTable = document.select("table[class=forumTable]").first();
        Elements values = forumTable.select("tr");

        for (Element value : values) {
            Element postslisttopic = value.select("td[class=postslisttopic]").first();
            Element altCol = value.select("td[class=altCol]").first();
            Element altColCreateDate = value.select("td[class=altCol]").last();
            if (postslisttopic != null) {
                String name = postslisttopic.child(0).text();
                String author = altCol.text();
                String createDate = altColCreateDate.text();
                if (this.filter.isCorrect(name, createDate)) {
                    Vacancy vacancy = new Vacancy(name,author,this.dateManager.getCreateDate(createDate));
                    if (vacancy.equals(storege.getLastVacancy())) {
                        this.endLoop = true;
                        break;
                    }
                    storege.add(vacancy);
                    System.out.println(name);
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
            if (tr.select("b").text().equals(tr.child(10).text())) {
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

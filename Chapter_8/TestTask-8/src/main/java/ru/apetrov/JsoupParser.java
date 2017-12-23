package ru.apetrov;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Properties;

/**
 * Created by Andrey on 13.12.2017.
 */
public class JsoupParser implements Runnable {

    /**
     * Класс с шаблонами.
     */
    private final FilterPaterrn filter;

    /**
     * Вспомогательная переменная, чтобы определить когда нужно перестать переходить на следующую страницу.
     */
    private boolean endLoop;

    /**
     * Класс для работы с датой.
     */
    private DateManager dateManager;

    /**
     * сайт для парсинга.
     */
    private String urlForParsing;

    /**
     * Класс настроек.
     */
    private Settings settings;

    /**
     * Конструктор.
     */
    public JsoupParser() {
        this.settings = new Settings();
        this.filter = new FilterPaterrn();
        this.endLoop = false;
        this.dateManager = new DateManager();
        this.initConnection();
    }

    /**
     * читаем из конфигурационного файла значение по ключу url_for_parsing,
     * достаем страницу которую будем парсить.
     */
    private void initConnection() {
        this.urlForParsing = this.settings.getValue("url_for_parsing");
    }

    /**
     * Цикл программы.
     * @param url страница которую будем парсить.
     * @throws IOException exception.
     */
    private void loop(String url) throws IOException {
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

    /**
     * Получить страницу по URL.
     * @param url адрс.
     * @return страница html.
     */
    private Document getPage(String url) {
        Document document = null;
        try {
            document = Jsoup.parse(new URL(url), 3000);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return document;
    }

    /**
     * Парсим страницу, если в теге есть слово java, нет слова script и время публикации вакансии больше заданной даты,
     * тогда добавляем данную вакансию в базу.
     * @param document страница html.
     * @param storege хранилище (sql).
     * @throws IOException exception.
     */
    private void parse(Document document, JDBCStorege storege) throws IOException {
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
                Timestamp tsCreateDate = this.dateManager.getCreateDate(createDate);
                if (this.filter.isCorrect(name)) {
                    if (tsCreateDate.getTime() <= storege.getLastDateForVacancy().getTime()) {
                        this.endLoop = true;
                        this.settings.savePropertiesForSecondStart("last_date", tsCreateDate.toString());
                        break;
                    } else {
                        Vacancy vacancy = new Vacancy(name, author, tsCreateDate);
                        storege.add(vacancy);
                        System.out.println(name);
                        System.out.println(author);
                        System.out.println(createDate + "\n");
                    }
                }
            }
        }
    }

    /**
     * получаем адресс следующей страницы.
     * @param url адресс текущей страницы.
     * @return следующая страница.
     */
    private String next(String url) {
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

    /**
     * Запуск потока.
     */
    @Override
    public void run() {
        try {
            this.loop(this.urlForParsing);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

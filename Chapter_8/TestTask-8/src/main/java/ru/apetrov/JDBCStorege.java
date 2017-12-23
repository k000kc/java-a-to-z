package ru.apetrov;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by Andrey on 11.12.2017.
 */
public class JDBCStorege implements AutoCloseable {

    /**
     * Коннект к бзе данных.
     */
    private Connection connection;

    /**
     * Класс: !. Для преобразования даты(String) в Timestamp.
     *        2. Для выявления когда в последний раз была запущенна программа.
     */
    private DateManager manager;

    /**
     *  по умолчанию программа собирает сведения за 2017-01-01 00\:00\:00.0.
     */
    private String defaultLastDate;

    /**
     * Класс настроек.
     */
    private Settings settings;

    /**
     * Конструктор. Инициализирует коннект с базой, загружая конфигурацию из файла config.properties.
     * и создает базу с необходимыми полями, если такой таблицы ещё не существует.
     */
    public JDBCStorege() {
        this.settings = new Settings();
        this.initConnection();
        this.manager = new DateManager();
        try {
            Statement statement = this.connection.createStatement();
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS forumTable(id serial PRIMARY KEY, vacancy CHARACTER VARYING(200), author CHARACTER VARYING(50), createDate TIMESTAMP)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Инициализирует коннект с базой, загружая конфигурацию из файла config.properties.
     */
    private void initConnection() {
        try {
            String url = this.settings.getValue("jdbc.url");
            String username = this.settings.getValue("jdbc.username");
            String password = this.settings.getValue("jdbc.password");
            this.connection = DriverManager.getConnection(url, username, password);
            this.defaultLastDate = this.settings.getValue("last_date");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Добавление вакансии в базу.
     * @param vacancy вакансия.
     */
    public void add(Vacancy vacancy) {
        if (!this.isDuplicate(vacancy)) {
            try (PreparedStatement statement = this.connection.prepareStatement("INSERT INTO forumTable(vacancy, author, createDate) VALUES(?, ?, ?)")) {
                statement.setString(1, vacancy.getName());
                statement.setString(2, vacancy.getAuthor());
                statement.setTimestamp(3, vacancy.getCreateDate());
                statement.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("duplicate");
        }
    }

    /**
     * Проверка на дубликаты по названию вакансии и автору.
     * @param vacancy выкансия.
     * @return true - если такая вакансия уже есть в базе.
     */
    private boolean isDuplicate(Vacancy vacancy) {
        boolean result = false;
        try(PreparedStatement statement = this.connection.prepareStatement("SELECT ft.id FROM forumTable AS ft WHERE ft.vacancy = ? AND ft.author = ?")) {
            statement.setString(1, vacancy.getName());
            statement.setString(2, vacancy.getAuthor());
            ResultSet set = statement.executeQuery();
            if (set.next()) {
                result = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Достанет последнюю дату размещенной вакансии из базы.
     * @return дата размещения вакансии.
     */
    public Timestamp getLastDateForVacancy() {
        Timestamp createLastDate = null;
        if (this.manager.isFirstStart()) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                java.util.Date date = dateFormat.parse(this.defaultLastDate);
                createLastDate = new Timestamp(date.getTime());
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else {
            try (Statement statement = this.connection.createStatement()) {
                ResultSet result = statement.executeQuery("SELECT ft.createDate FROM forumTable AS ft WHERE ft.createDate = (SELECT max(createDate) FROM forumTable)");
                if (result.next()) {
                    createLastDate = result.getTimestamp("createDate");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return createLastDate;
    }

    /**
     * Закрываем коннект с базой.
     * @throws SQLException exception.
     */
    @Override
    public void close() throws SQLException {
        this.connection.close();
    }
}

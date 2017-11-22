package ru.apetrov.JDBC;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.PreparedStatement;

/**
 * Created by Andrey on 14.11.2017.
 */
public class WorkerJDBC {

    /**
     * Создает XML файл из данных базы.
     */
    private StAXCreate creater;

    /**
     * файл 1.xml трансформирует в файл 2.xml (при помощи transformer.xsl).
     */
    private XSLTTransformation transformation;

    /**
     * парсер для 2.xml файла.
     */
    private StaXPerser parser;

    /**
     * число значений для записи в базу и обработки.
     */
    private long n;

    /**
     * Конструктор.
     * @param n число значений для записи в базу и обработки.
     */
    public WorkerJDBC(long n) {
        this.n = n;
        creater = new StAXCreate();
        this.transformation = new XSLTTransformation();
        this.parser = new StaXPerser();
    }

    /**
     * Запуск программы.
     */
    public void run() {
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:sqlite\\numbers.db")) {
            Statement statement = connection.createStatement();
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS test(field INTEGER NOT NULL)");
            statement.executeUpdate("DELETE FROM test");
            this.insertToTable(connection, this.n);
            ResultSet res = statement.executeQuery("SELECT field FROM test");
            this.creater.initParser();
            while (res.next()) {
                this.creater.createXML(res.getInt("field"));
            }
            this.creater.closeXMLParser();
            this.transformation.runTransformation(this.creater.getFile().toString());
            this.parser.persing(this.transformation.getNewXMLFile());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Вставка значений в таблицу.
     * @param connection коннект с базой.
     * @param n число значений для записи в базу и обработки.
     * @throws SQLException exeption.
     */
    private void insertToTable(Connection connection, long n) throws SQLException {
        connection.setAutoCommit(false);
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO test(field) VALUES (?)")) {
            for (int i = 1; i <= n; i++) {
                statement.setInt(1, i);
                statement.addBatch();
            }
            statement.executeBatch();
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            e.printStackTrace();
        } finally {
            connection.setAutoCommit(true);
        }
    }

    /**
     * Main.
     * @param args args.
     */
    public static void main(String[] args) {
        WorkerJDBC workerJDBC = new WorkerJDBC(1000000);
        workerJDBC.run();
    }
}

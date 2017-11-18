package ru.apetrov.JDBC;

import java.io.IOException;
import java.sql.*;

/**
 * Created by Andrey on 14.11.2017.
 */
public class WorkerJDBC {

    private StAXCreate parser;
    private XSLTTransformation transformation;
    private long n;

    public WorkerJDBC(long n) {
        this.n = n;
        parser = new StAXCreate();
        this.transformation = new XSLTTransformation();
    }

    public void run() {
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:H:\\java\\sqlite\\numbers.db")) {
            Statement statement = connection.createStatement();
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS test(field INTEGER NOT NULL)");
            statement.executeUpdate("DELETE FROM test");
            this.insertToTable(connection, this.n);
            ResultSet res = statement.executeQuery("SELECT field FROM test");
            this.parser.initParser();
            while (res.next()) {
                this.parser.createXML(res.getInt("field"));
            }
            this.parser.closeXMLParser();
            this.transformation.runTransformation(this.parser.getFile().toString());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void insertToTable(Connection connection, long n) throws SQLException {
        connection.setAutoCommit(false);
        PreparedStatement statement = connection.prepareStatement("INSERT INTO test(field) VALUES (?)");
        for (int i = 1; i <= n; i++){
            statement.setInt(1, i);
            statement.addBatch();
        }
        statement.executeBatch();
        connection.commit();
    }

    public static void main(String[] args) {
        WorkerJDBC workerJDBC = new WorkerJDBC(100);
        workerJDBC.run();
    }
}

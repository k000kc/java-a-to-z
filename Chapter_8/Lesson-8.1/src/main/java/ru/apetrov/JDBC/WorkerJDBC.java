package ru.apetrov.JDBC;

import java.sql.*;

/**
 * Created by Andrey on 14.11.2017.
 */
public class WorkerJDBC {

    private Connection connection;
    private Statement statement;
    private PreparedStatement preparedStatement;
    private long n;

    public WorkerJDBC(long n) {
        this.n = n;
    }

    public void run() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:H:\\java\\sqlite\\numbers.db");
            this.statement = connection.createStatement();
            this.statement.executeUpdate("CREATE TABLE IF NOT EXISTS test(field integer NOT NULL)");
            this.statement.executeUpdate("DELETE FROM test");
            connection.close();
            this.insertToTable(this.n);
            ResultSet res = this.statement.executeQuery("select field from test");
            while (res.next()) {
                System.out.println(res.getInt("field"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void insertToTable(long n) throws SQLException {
        preparedStatement = this.connection.prepareStatement("INSERT INTO test(field) VALUES (?)");
        for (int i = 1; i <= n; i++){
            preparedStatement.setInt(1, i);
        }
    }

    public static void main(String[] args) {
        WorkerJDBC workerJDBC = new WorkerJDBC(10);
        workerJDBC.run();
    }
}

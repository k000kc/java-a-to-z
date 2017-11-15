package ru.apetrov.JDBC;

import java.sql.*;

/**
 * Created by Andrey on 14.11.2017.
 */
public class WorkerJDBC {

    private Connection connection;
    private long n;

    public WorkerJDBC(long n) {
        this.n = n;
    }

    public void run() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:H:\\java\\sqlite\\numbers.db");
            Statement statement = connection.createStatement();
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS test(field INTEGER NOT NULL)");
            statement.executeUpdate("DELETE FROM test");
            this.insertToTable(connection, this.n);
            ResultSet res = statement.executeQuery("SELECT field FROM test");
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

    private void insertToTable(Connection connection, long n) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("INSERT INTO test(field) VALUES (?)");
        for (int i = 1; i <= n; i++){
            statement.setInt(1, i);
            statement.executeUpdate();
        }
    }

    public static void main(String[] args) {
        WorkerJDBC workerJDBC = new WorkerJDBC(10);
        workerJDBC.run();
    }
}

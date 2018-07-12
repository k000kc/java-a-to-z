package ru.apetrov.dao;

import ru.apetrov.models.User;
import ru.apetrov.models.UserLoginMusicTypeId;
import ru.apetrov.util.ConnectionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MergeUserAndMusicTables {

    private ConnectionDB connectionDB = new ConnectionDB();

    public synchronized void addMusicTypeToTheUser(UserLoginMusicTypeId loginAndMusicType) {
        try (
                Connection connection = this.connectionDB.getConnection();
                PreparedStatement statement = connection.prepareStatement("INSERT INTO login_music_id(user_login, music_id) VALUES(?, ?)")
        ) {
            statement.setString(1, loginAndMusicType.getUserLogin());
            statement.setInt(2, loginAndMusicType.getMusicTypeId());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public synchronized Set<String> getLoginByMusicTypeId(Integer musicTypeId) {
        Set<String> result = new HashSet<>();
        ResultSet resultSet = null;
        try (
                Connection connection = this.connectionDB.getConnection();
                PreparedStatement statement = connection.prepareStatement("SELECT user_login, music_id FROM login_music_id WHERE music_id = ?")
        ) {
            statement.setInt(1, musicTypeId);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String login = resultSet.getString("user_login");
                result.add(login);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public synchronized Set<Integer> getMusicTypeIdByLogin(String login) {
        Set<Integer> result = new HashSet<>();
        ResultSet resultSet = null;
        try (
                Connection connection =this.connectionDB.getConnection();
                PreparedStatement statement = connection.prepareStatement("SELECT user_login, music_id FROM login_music_id WHERE user_login = ?")
        ) {
            statement.setString(1, login);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Integer musicTypeId = resultSet.getInt("music_id");
                result.add(musicTypeId);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}

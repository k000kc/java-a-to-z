package ru.apetrov.dao;

import ru.apetrov.models.UserLoginMusicTypeId;
import ru.apetrov.util.ConnectionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class MergeUserAndMusicTables {

    private ConnectionDB connectionDB = new ConnectionDB();

    public void addMusicTypeToTheUser(UserLoginMusicTypeId loginAndMusicType) {
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

    public Set<String> getByMusicTypeId(Integer musicTypeId) {
        Set<String> result = new HashSet<>();
        try (
                Connection connection = this.connectionDB.getConnection();
                PreparedStatement statement = connection.prepareStatement("SELECT user_login, music_id FROM login_music_id WHERE music_id = ?")
        ) {
            statement.setInt(1, musicTypeId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String login = resultSet.getString("user_login");
                result.add(login);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}

package ru.apetrov.dao;

import ru.apetrov.models.UserLoginMusicTypeId;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

public class UserLoginMusicTypeIdImpl extends ModelBaseDAO<UserLoginMusicTypeId, Integer> {

    @Override
    void create(UserLoginMusicTypeId userLoginMusicTypeId) {
        try (
                Connection connection = super.getConnection();
                PreparedStatement statement = connection.prepareStatement("INSERT INTO login_music_id(user_login, music_id) VALUES(?, ?)")
        ) {
            statement.setString(1, userLoginMusicTypeId.getUserLogin());
            statement.setInt(2, userLoginMusicTypeId.getMusicTypeId());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    UserLoginMusicTypeId getById(Integer id) {
        UserLoginMusicTypeId userLoginMusicTypeId = new UserLoginMusicTypeId();
        try (
                Connection connection = super.getConnection();
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM login_music_id WHERE id = ?")
        ) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                userLoginMusicTypeId.setId(resultSet.getInt("id"));
                userLoginMusicTypeId.setUserLogin(resultSet.getString("user_login"));
                userLoginMusicTypeId.setMusicTypeId(resultSet.getInt("music_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    Set<UserLoginMusicTypeId> getAll() {
        return null;
    }

    @Override
    void update(UserLoginMusicTypeId userLoginMusicTypeId) {

    }

    @Override
    void remove(Integer integer) {

    }
}

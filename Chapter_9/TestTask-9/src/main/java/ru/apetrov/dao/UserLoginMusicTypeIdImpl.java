package ru.apetrov.dao;

import ru.apetrov.models.UserLoginMusicTypeId;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Set;

public class UserLoginMusicTypeIdImpl extends ModelBaseDAO<UserLoginMusicTypeId, Integer> {

    @Override
    void create(UserLoginMusicTypeId userLoginMusicTypeId) {
//        Connection connection = super.getConnection();
        try (Connection connection = super.getConnection();
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
    UserLoginMusicTypeId getById(Integer integer) {
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

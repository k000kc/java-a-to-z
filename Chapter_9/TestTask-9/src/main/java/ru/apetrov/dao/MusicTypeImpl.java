package ru.apetrov.dao;

import ru.apetrov.models.MusicType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

public class MusicTypeImpl extends ModelBaseDAO<MusicType, Integer> {

    @Override
    public void create(MusicType musicType) {
        try (
                Connection connection = super.getConnection();
                PreparedStatement statement = connection.prepareStatement("INSERT INTO musics(id, music_type) VALUES(?, ?)")
        ) {
            statement.setInt(1, musicType.getId());
            statement.setString(2, musicType.getMusicType());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public MusicType getById(Integer id) {
        MusicType musicType = new MusicType();
        try (
                Connection connection = super.getConnection();
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM musics WHERE id = ?")
        ) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                musicType.setId(resultSet.getInt("id"));
                musicType.setMusicType(resultSet.getString("music_type"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return musicType;
    }

    @Override
    public Set<MusicType> getAll() {
        return null;
    }

    @Override
    public void update(MusicType musicType) {

    }

    @Override
    public void remove(Integer id) {

    }
}

package ru.apetrov.dao;

import ru.apetrov.models.MusicType;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class MusicTypeImpl extends ModelBaseDAO<MusicType, Integer> {

    @Override
    public synchronized void create(MusicType musicType) {
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
    public synchronized MusicType getById(Integer id) {
        MusicType musicType = new MusicType();
        ResultSet resultSet = null;
        try (
                Connection connection = super.getConnection();
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM musics WHERE id = ?")
        ) {
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                musicType.setId(resultSet.getInt("id"));
                musicType.setMusicType(resultSet.getString("music_type"));
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
        return musicType;
    }

    @Override
    public synchronized Set<MusicType> getAll() {
        Set<MusicType> result = new HashSet<>();
        try (
                Connection connection = super.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM musics");
        ) {
            while (resultSet.next()) {
                MusicType musicType = new MusicType();
                musicType.setId(resultSet.getInt("id"));
                musicType.setMusicType(resultSet.getString("music_type"));
                result.add(musicType);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public synchronized void update(MusicType musicType) {
        try (
                Connection connection = super.getConnection();
                PreparedStatement statement = connection.prepareStatement("UPDATE musics SET music_type = ? WHERE id = ?")
        ) {
            statement.setString(1, musicType.getMusicType());
            statement.setInt(2, musicType.getId());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public synchronized void remove(Integer id) {
        try (
                Connection connection = super.getConnection();
                PreparedStatement statement = connection.prepareStatement("DELETE FROM musics WHERE id = ?");
        ) {
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

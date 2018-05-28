package ru.apetrov.dao;

import ru.apetrov.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Set;

public class UserImpl extends ModelBaseDAO<User,String> {

    @Override
    public void create(User user) {
        try (
                Connection connection = super.getConnection();
                PreparedStatement statement = connection.prepareStatement("INSERT INTO users(login, password, user_name, email, address_id, role_id) VALUES(?, ?, ?, ?, ?, ?)")
        ) {
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getName());
            statement.setString(4, user.getEmail());
            statement.setInt(5, user.getAddress().getId());
            statement.setInt(6, user.getRole().getId());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User getById(String login) {
        return null;
    }

    @Override
    public Set<User> getAll() {
        return null;
    }

    @Override
    public void update(User user) {

    }

    @Override
    public void remove(String login) {

    }
}

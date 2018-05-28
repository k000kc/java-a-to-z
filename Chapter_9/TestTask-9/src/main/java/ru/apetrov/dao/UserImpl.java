package ru.apetrov.dao;

import ru.apetrov.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
        User user = new User();
        try (
                Connection connection = super.getConnection();
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE login = ?")
        ){
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
                user.setName(resultSet.getString("user_name"));
                user.setEmail(resultSet.getString("email"));
                Integer addressId = resultSet.getInt("address_id");
                user.setAddress(new AddressImpl().getById(addressId));
                Integer roleId = resultSet.getInt("role_id");
                user.setRole(new RoleImpl().getById(roleId));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
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

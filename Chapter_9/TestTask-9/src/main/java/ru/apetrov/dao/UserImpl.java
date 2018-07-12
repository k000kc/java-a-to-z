package ru.apetrov.dao;

import ru.apetrov.models.User;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class UserImpl extends ModelBaseDAO<User,String> {

    @Override
    public synchronized void create(User user) {
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
    public synchronized User getById(String login) {
        User user = new User();
        ResultSet resultSet = null;
        try (
                Connection connection = super.getConnection();
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE login = ?")
        ){
            statement.setString(1, login);
            resultSet = statement.executeQuery();
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
        } finally {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return user;
    }

    @Override
    public synchronized Set<User> getAll() {
        Set<User> result = new HashSet<>();
        try (
                Connection connection = super.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
        ) {
            while (resultSet.next()) {
                User user = new User();
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
                user.setName(resultSet.getString("user_name"));
                user.setEmail(resultSet.getString("email"));
                Integer addressId = resultSet.getInt("address_id");
                user.setAddress(new AddressImpl().getById(addressId));
                Integer roleId = resultSet.getInt("role_id");
                user.setRole(new RoleImpl().getById(roleId));
                result.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public synchronized void update(User user) {
        try (
                Connection connection = super.getConnection();
                PreparedStatement statement = connection.prepareStatement("UPDATE users SET password = ?, user_name = ?, email = ?, role_id = ? WHERE login = ?");
        ) {
            statement.setString(1, user.getPassword());
            statement.setString(2, user.getName());
            statement.setString(3, user.getEmail());
            statement.setInt(4, user.getRole().getId());
            statement.setString(5, user.getLogin());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public synchronized void remove(String login) {
        try (
                Connection connection = super.getConnection();
                PreparedStatement statement = connection.prepareStatement("DELETE FROM users WHERE login = ?");
        ) {
            statement.setString(1, login);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

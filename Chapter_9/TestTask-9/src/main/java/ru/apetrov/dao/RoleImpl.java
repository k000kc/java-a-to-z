package ru.apetrov.dao;

import ru.apetrov.models.Role;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class RoleImpl extends ModelBaseDAO<Role, Integer> {

    @Override
    public synchronized void create(Role role) {
        try (
                Connection connection = super.getConnection();
                PreparedStatement statement = connection.prepareStatement("INSERT INTO roles(id, role) VALUES(?, ?)")
        ) {
            statement.setInt(1, role.getId());
            statement.setString(2, role.getRoleType());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public synchronized Role getById(Integer id) {
        Role role = new Role();
        ResultSet resultSet = null;
        try (
                Connection connection = super.getConnection();
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM roles WHERE id = ?")
        ) {
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                role.setId(resultSet.getInt("id"));
                role.setRoleType(resultSet.getString("role"));
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
        return role;
    }

    @Override
    public synchronized Set<Role> getAll() {
        Set<Role> result = new HashSet<>();
        try (
                Connection connection = super.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM roles");
        ) {
            while (resultSet.next()) {
                Role role = new Role();
                role.setId(resultSet.getInt("id"));
                role.setRoleType(resultSet.getString("role"));
                result.add(role);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public synchronized void update(Role role) {
        try (
                Connection connection = super.getConnection();
                PreparedStatement statement = connection.prepareStatement("UPDATE roles SET role = ? WHERE id = ?");
        ) {
            statement.setString(1, role.getRoleType());
            statement.setInt(2, role.getId());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public synchronized void remove(Integer id) {
        try (
                Connection connection = super.getConnection();
                PreparedStatement statement = connection.prepareStatement("DELETE FROM roles WHERE id = ?");
        ) {
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

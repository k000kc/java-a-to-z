package ru.apetrov.dao;

import ru.apetrov.models.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

public class RoleImpl extends ModelBaseDAO<Role, Integer> {

    @Override
    public void create(Role role) {
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
    public Role getById(Integer id) {
        Role role = new Role();
        try (
                Connection connection = super.getConnection();
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM roles WHERE id = ?")
        ) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                role.setId(resultSet.getInt("id"));
                role.setRoleType(resultSet.getString("role"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return role;
    }

    @Override
    public Set<Role> getAll() {
        return null;
    }

    @Override
    public void update(Role role) {

    }

    @Override
    public void remove(Integer id) {

    }
}

package ru.apetrov.dao;

import ru.apetrov.models.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Set;

public class RoleImpl extends ModelBaseDAO<Role,Long> {

    @Override
    public void create(Role role) {
        Connection connection = super.getConnection();
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO roles(id, role) VALUES(?, ?)")) {
            statement.setInt(1, role.getId());
            statement.setString(2, role.getRoleType());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Role getById(Long id) {
        return null;
    }

    @Override
    public Set<Role> getAll() {
        return null;
    }

    @Override
    public void update(Role role) {

    }

    @Override
    public void remove(Long id) {

    }
}

package ru.apetrov.dao;

import ru.apetrov.models.Address;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Set;

public class AddressImpl extends ModelBaseDAO<Address,Long> {

    @Override
    public void create(Address address) {
        Connection connection = super.getConnection();
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO address(country, city, street, house) VALUES(?, ?, ?, ?)")) {
            statement.setString(1, address.getCountry());
            statement.setString(2, address.getCity());
            statement.setString(3, address.getStreet());
            statement.setString(4, address.getHouse());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Address getById(Long id) {
        return null;
    }

    @Override
    public Set<Address> getAll() {
        return null;
    }

    @Override
    public void update(Address address) {

    }

    @Override
    public void remove(Long id) {

    }
}

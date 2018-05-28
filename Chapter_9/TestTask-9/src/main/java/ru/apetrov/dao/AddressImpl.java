package ru.apetrov.dao;

import ru.apetrov.models.Address;

import java.sql.*;
import java.util.Set;

public class AddressImpl extends ModelBaseDAO<Address,Long> {

    @Override
    public void create(Address address) {
        try (
                Connection connection = super.getConnection();
                PreparedStatement statement = connection.prepareStatement("INSERT INTO address(country, city, street, house) VALUES(?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS)
        ) {
            statement.setString(1, address.getCountry());
            statement.setString(2, address.getCity());
            statement.setString(3, address.getStreet());
            statement.setString(4, address.getHouse());
            statement.execute();
            //тут устанавливаем автоматически созданный id
            try (ResultSet rs = statement.getGeneratedKeys()) {
                while (rs.next()) {
                    int id = rs.getInt(1);
                    address.setId(id);
                }
            }
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

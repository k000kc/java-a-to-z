package ru.apetrov.dao;

import ru.apetrov.models.Address;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class AddressImpl extends ModelBaseDAO<Address,Integer> {

    @Override
    public synchronized void create(Address address) {
        ResultSet rs = null;
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
            rs = statement.getGeneratedKeys();
                while (rs.next()) {
                    int id = rs.getInt(1);
                    address.setId(id);
                }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public synchronized Address getById(Integer id) {
        Address address = new Address();
        ResultSet resultSet = null;
        try (
                Connection connection = super.getConnection();
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM address WHERE id = ?")
        ) {
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                address.setId(resultSet.getInt("id"));
                address.setCountry(resultSet.getString("country"));
                address.setCity(resultSet.getString("city"));
                address.setStreet(resultSet.getString("street"));
                address.setStreet(resultSet.getString("house"));
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
        return address;
    }

    @Override
    public synchronized Set<Address> getAll() {
        Set<Address> result = new HashSet<>();
        try (
                Connection connection = super.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM address");
        ) {
            while (resultSet.next()) {
                Address address = new Address();
                address.setId(resultSet.getInt("id"));
                address.setCountry(resultSet.getString("country"));
                address.setCity(resultSet.getString("city"));
                address.setStreet(resultSet.getString("street"));
                address.setHouse(resultSet.getString("house"));
                result.add(address);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public synchronized void update(Address address) {
        try (
                Connection connection = super.getConnection();
                PreparedStatement statement = connection.prepareStatement("UPDATE address SET country = ?, city = ?, street = ?, house = ? WHERE id = ?")
        ) {
            statement.setString(1, address.getCountry());
            statement.setString(2, address.getCity());
            statement.setString(3, address.getStreet());
            statement.setString(4, address.getHouse());
            statement.setInt(5, address.getId());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public synchronized void remove(Integer id) {
        try (
                Connection connection = super.getConnection();
                PreparedStatement statement = connection.prepareStatement("DELETE FROM address WHERE id = ?");
        ) {
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

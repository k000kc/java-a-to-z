package ru.apetrov.dao;

import ru.apetrov.entity.Address;

import java.util.List;

public class AddressEntity implements EntityDAO<Long, Address> {

    @Override
    public void create(Address value) {

    }

    @Override
    public List<Address> getAll() {
        return null;
    }

    @Override
    public Address getById(Long id) {
        return null;
    }

    @Override
    public void update(Long id) {

    }

    @Override
    public void remove(Long id) {

    }
}

package ru.apetrov.dao;

import org.junit.Test;
import ru.apetrov.models.Address;
import ru.apetrov.models.Role;
import ru.apetrov.models.User;

import static org.junit.Assert.*;

public class DAOTest {

    @Test
    public void createEntityTest() {
        ModelBaseDAO<Address,Long> addressDao = new AddressImpl();
        ModelBaseDAO<User, String> userDao = new UserImpl();

        Address address = new Address();
        address.setId(2);
        address.setCountry("Russia");
        address.setCity("Moscow");
        address.setStreet("Nikolaeva");
        address.setHouse("55");
        addressDao.create(address);

        Role role = new Role();
        role.setId(1);
        role.setRoleType("user");

        User user = new User();
        user.setLogin("apetrov");
        user.setPassword("123");
        user.setName("Andrey");
        user.setEmail("ham2188@mail.ru");
        user.setAddress(address);
        user.setRole(role);
        userDao.create(user);
    }
}
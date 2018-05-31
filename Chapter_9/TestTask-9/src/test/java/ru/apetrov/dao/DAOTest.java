package ru.apetrov.dao;

import org.junit.Test;
import ru.apetrov.models.Address;
import ru.apetrov.models.MusicType;
import ru.apetrov.models.Role;
import ru.apetrov.models.User;

import static org.junit.Assert.*;

public class DAOTest {

    @Test
    public void createEntityTest() {
        ModelBaseDAO<Address,Integer> addressDao = new AddressImpl();
        ModelBaseDAO<User, String> userDao = new UserImpl();

        Address address = new Address();
        address.setCountry("dsfsdfsdf");
        address.setCity("fsdfs");
        address.setStreet("sdfsdf");
        address.setHouse("sdfsdf");
        addressDao.create(address);

        Role role = new Role();
        role.setId(2);
        role.setRoleType("user");

        User user = new User();
        user.setLogin("ssdf");
        user.setPassword("sdfsdf");
        user.setName("sdfdsf");
        user.setEmail("sdfdsf");
        user.setAddress(address);
        user.setRole(role);
        userDao.create(user);
    }


    @Test
    public void getByIdTest() {
        ModelBaseDAO<User, String> userDao = new UserImpl();
        System.out.println(userDao.getById("apetrov").getName());
    }

    @Test
    public void getAllMusikType() {
        ModelBaseDAO<MusicType, Integer> dao = new MusicTypeImpl();
        for (MusicType s : dao.getAll()) {
            System.out.println(s.getMusicType());
        }
    }
}
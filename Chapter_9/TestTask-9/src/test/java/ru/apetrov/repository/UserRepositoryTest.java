package ru.apetrov.repository;

import org.junit.Test;
import ru.apetrov.models.Address;
import ru.apetrov.models.MusicType;
import ru.apetrov.models.Role;
import ru.apetrov.models.User;

import static org.junit.Assert.*;

public class UserRepositoryTest {

    @Test
    public void testCreatemethodFromRepository() {
        UserRepository repository = new UserRepository();

        Address address = new Address();
        address.setCountry("test");
        address.setCity("test");
        address.setStreet("test");
        address.setHouse("test");

        Role role = new Role();
        role.setId(2);
        role.setRoleType("user");

        User user = new User();
        user.setLogin("test");
        user.setPassword("test");
        user.setName("test");
        user.setEmail("test");

        repository.createUser(user, address, role);

        MusicType musicType = new MusicType();
        musicType.setId(1);
        musicType.setMusicType("rap");

        MusicType musicType1 = new MusicType();
        musicType1.setId(2);
        musicType1.setMusicType("rock");

        repository.putMusicTypeToUser(user, musicType);
        repository.putMusicTypeToUser(user, musicType1);

        User user1 = repository.getUser("test");
        for (MusicType m : user1.getMusicTypes()) {

            System.out.println(m.getMusicType());
        }

    }
}
package ru.apetrov.repository;

import org.junit.Test;
import ru.apetrov.models.Address;
import ru.apetrov.models.MusicType;
import ru.apetrov.models.Role;
import ru.apetrov.models.User;

import java.util.Set;

import static org.junit.Assert.*;

public class UserRepositoryTest {

    @Test
    public void testCreatemethodFromRepository() {
        UserRepository repository = UserRepository.getInstance();

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

//        repository.createUser(user, address, role);

        MusicType musicType = new MusicType();
        musicType.setId(1);
        musicType.setMusicType("rap");

        MusicType musicType1 = new MusicType();
        musicType1.setId(2);
        musicType1.setMusicType("rock");

//        repository.putMusicTypeToUser(user, musicType);
//        repository.putMusicTypeToUser(user, musicType1);

        User user1 = repository.getUser("test");
        for (MusicType m : user1.getMusicTypes()) {

            System.out.println(m.getMusicType());
        }
    }

    @Test
    public void testGetUserByAdress() {
        UserRepository repository = UserRepository.getInstance();

        Address address = new Address();
        address.setCountry("Ru");
        address.setCity("Mc");
        address.setStreet("Nl");
        address.setHouse("6767");

        Set<User> users = repository.findUserByAddress(address);
        for (User user : users) {
            System.out.println(user.getName());
        }
    }

    @Test
    public void testGetUserByRole() {
        UserRepository repository = UserRepository.getInstance();

        Role role = new Role();
        role.setId(2);
        role.setRoleType("user");

        Set<User> users = repository.findUserByRole(role);
        for (User user : users) {
            System.out.println(user.getName());
        }
    }

    @Test
    public void testUserByMusicType() {
        UserRepository repository = UserRepository.getInstance();

        MusicType musicType = new MusicType();
        musicType.setId(1);
        musicType.setMusicType("rap");

        Set<User> users = repository.findUserByMusicType(musicType);
        for (User user : users) {
            System.out.println(user.getName());
        }
    }

    @Test
    public void testFindAll() {
        UserRepository repository = UserRepository.getInstance();
        System.out.println(repository.findAll().toString());
    }
}
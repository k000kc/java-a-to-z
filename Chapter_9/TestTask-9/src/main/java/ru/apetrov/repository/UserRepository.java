package ru.apetrov.repository;

import ru.apetrov.dao.*;
import ru.apetrov.models.*;

import java.util.HashSet;
import java.util.Set;


public class UserRepository {

    private ModelBaseDAO<User,String> userDAO;
    private ModelBaseDAO<Address,Integer> addressDAO;
    private ModelBaseDAO<Role,Integer> roleDAO;
    private ModelBaseDAO<MusicType,Integer> musicTypeDAO;
    private MergeUserAndMusicTables mergeUserMusic;

    public UserRepository() {
        this.userDAO = new UserImpl();
        this.addressDAO = new AddressImpl();
        this.roleDAO = new RoleImpl();
        this.musicTypeDAO = new MusicTypeImpl();
        this.mergeUserMusic = new MergeUserAndMusicTables();
    }

    public void createUser(User user, Address address, Role role) {
        this.addressDAO.create(address);
        user.setAddress(address);
        user.setRole(role);
        this.userDAO.create(user);
    }

    public void putMusicTypeToUser(User user, MusicType musicType) {
        UserLoginMusicTypeId loginMusic = new UserLoginMusicTypeId();
        loginMusic.setUserLogin(user.getLogin());
        loginMusic.setMusicTypeId(musicType.getId());
        this.mergeUserMusic.addMusicTypeToTheUser(loginMusic);
    }

    public User getUser(String login) {
        User user = this.userDAO.getById(login);
        Set<Integer> musicIds = this.mergeUserMusic.getMusicTypeIdByLogin(login);

        //todo

        return user;
    }
}

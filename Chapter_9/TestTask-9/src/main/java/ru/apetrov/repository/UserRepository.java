package ru.apetrov.repository;

import ru.apetrov.dao.*;
import ru.apetrov.models.*;
import ru.apetrov.util.ConnectionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;


public class UserRepository {

    private ModelBaseDAO<User,String> userDAO;
    private ModelBaseDAO<Address,Integer> addressDAO;
    private ModelBaseDAO<Role,Integer> roleDAO;
    private ModelBaseDAO<MusicType,Integer> musicTypeDAO;
    private MergeUserAndMusicTables mergeUserMusic;
    private ConnectionDB connectionDB;

    public UserRepository() {
        this.userDAO = new UserImpl();
        this.addressDAO = new AddressImpl();
        this.roleDAO = new RoleImpl();
        this.musicTypeDAO = new MusicTypeImpl();
        this.mergeUserMusic = new MergeUserAndMusicTables();
        this.connectionDB = new ConnectionDB();
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
        Set<MusicType> musicTypes = new HashSet<>();
        for (Integer id : musicIds) {
            MusicType musicType = this.musicTypeDAO.getById(id);
            musicTypes.add(musicType);
        }
        user.setMusicTypes(musicTypes);
        return user;
    }

    public Set<User> findUserByAddress(Address address) {
        Set<User> users = new HashSet<>();
        User user = null;
        try (
                Connection connection = this.connectionDB.getConnection();
                PreparedStatement statement = connection.prepareStatement("SELECT id FROM address WHERE country = ? AND city = ? AND street = ? AND house = ?")
        ) {
            statement.setString(1, address.getCountry());
            statement.setString(2, address.getCity());
            statement.setString(3, address.getStreet());
            statement.setString(4, address.getHouse());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Integer addressId = resultSet.getInt("id");
                user = this.getUserByAddressId(addressId);
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    private User getUserByAddressId(Integer addressId) {
        User user = null;
        try (
                Connection connection = this.connectionDB.getConnection();
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE address_id = ?")
        ) {
            statement.setInt(1, addressId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = new User();
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
                user.setName(resultSet.getString("user_name"));
                user.setEmail(resultSet.getString("email"));
                user.setAddress(new AddressImpl().getById(addressId));
                Integer roleId = resultSet.getInt("role_id");
                user.setRole(new RoleImpl().getById(roleId));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public Set<User> getUserByRole(Role role) {
        Set<User> users = new HashSet<>();
        User user = null;
        try (
                Connection connection = this.connectionDB.getConnection();
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE role_id = ?")
        ) {
            statement.setInt(1, role.getId());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                user = new User();
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
                user.setName(resultSet.getString("user_name"));
                user.setEmail(resultSet.getString("email"));
                Integer addressId = resultSet.getInt("address_id");
                user.setAddress(new AddressImpl().getById(addressId));
                Integer roleId = resultSet.getInt("role_id");
                user.setRole(new RoleImpl().getById(roleId));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public Set<User> getUserByMusicType(MusicType musicType) {
        Set<User> users = new HashSet<>();
        User user = null;
        try (
                Connection connection = this.connectionDB.getConnection();
                PreparedStatement statement = connection.prepareStatement("SELECT user_login FROM login_music_id WHERE music_id = ?");
        ) {
            statement.setInt(1, musicType.getId());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                user = this.userDAO.getById(resultSet.getString("user_login"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }
}

package ru.apetrov.repository;

import ru.apetrov.dao.*;
import ru.apetrov.models.*;
import ru.apetrov.util.ConnectionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public class UserRepository {

    private static UserRepository instance;

    private ModelBaseDAO<User,String> userDAO;
    private ModelBaseDAO<Address,Integer> addressDAO;
    private ModelBaseDAO<Role,Integer> roleDAO;
    private ModelBaseDAO<MusicType,Integer> musicTypeDAO;
    private MergeUserAndMusicTables mergeUserMusic;
    private ConnectionDB connectionDB;

    private UserRepository() {
    }

    public synchronized static UserRepository getInstance() {
        if (instance == null) {
            instance = new UserRepository();
            instance.init();
        }
        return instance;
    }

    private void init() {
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

    public Set<User> findAll() {
        Set<User> users = new HashSet<>();
        Set<MusicType> musicTypes = new HashSet<>();
        try (
                Connection connection = this.connectionDB.getConnection();
                PreparedStatement statement = connection.prepareStatement(
                        "SELECT u.login, u.password, u.user_name, u.email, u.address_id, a.country, a.city, a.street, a.house, u.role_id, adr.role, m.id, m.music_type FROM users AS u \n" +
                                "LEFT OUTER JOIN address AS a ON u.address_id = a.id \n" +
                                "LEFT OUTER JOIN roles AS adr ON u.role_id = adr.id\n" +
                                "LEFT OUTER JOIN login_music_id AS lm ON u.login = lm.user_login\n" +
                                "LEFT OUTER JOIN musics AS m ON lm.music_id = m.id;")
        ) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                Address address = new Address();
                Role role = new Role();
                MusicType musicType = new MusicType();

                user.setLogin(resultSet.getString("login"));
                user.setName(resultSet.getString("user_name"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));

                address.setId(resultSet.getInt("address_id"));
                address.setCountry(resultSet.getString("country"));
                address.setCity(resultSet.getString("city"));
                address.setStreet(resultSet.getString("street"));
                address.setHouse(resultSet.getString("house"));

                role.setId(resultSet.getInt("role_id"));
                role.setRoleType(resultSet.getString("role"));

                musicType.setId(resultSet.getInt("id"));
                musicType.setMusicType(resultSet.getString("music_type"));
                user.setAddress(address);
                user.setRole(role);

                if (users.contains(user)) {
                    musicTypes.add(musicType);
                    user.setMusicTypes(musicTypes);
                } else {
                    musicTypes = new HashSet<>();
                    musicTypes.add(musicType);
                    user.setMusicTypes(musicTypes);
                    users.add(user);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
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

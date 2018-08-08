package ru.apetrov.repository;

import ru.apetrov.dao.*;
import ru.apetrov.models.*;
import ru.apetrov.util.ConnectionDB;

import java.sql.*;
import java.util.*;
import java.util.concurrent.CopyOnWriteArraySet;


public class UserRepository {

    private ModelBaseDAO<User,String> userDAO;
    private ModelBaseDAO<Address,Integer> addressDAO;
    private ModelBaseDAO<Role,Integer> roleDAO;
    private ModelBaseDAO<MusicType,Integer> musicTypeDAO;
    private MergeUserAndMusicTables mergeUserMusic;
    private Connection connection;

    private static UserRepository instance;

    private UserRepository() {
        this.init();
    }

    public static UserRepository getInstance() {
        if (instance == null) {
            instance = new UserRepository();
        }
        return instance;
    }

    private void init() {
        this.userDAO = new UserImpl();
        this.addressDAO = new AddressImpl();
        this.roleDAO = new RoleImpl();
        this.musicTypeDAO = new MusicTypeImpl();
        this.mergeUserMusic = new MergeUserAndMusicTables();
        this.connection = ConnectionDB.getInstance().getConnection();
    }

    public void add(User user, Address address, Integer roleId, List<Integer> musicTypesId) throws SQLException {
        PreparedStatement statementAddress = null;
        PreparedStatement statementUser= null;
        PreparedStatement statementMusic = null;
        try {
            this.connection.setAutoCommit(false);
            statementAddress = this.connection.prepareStatement("INSERT INTO address(country, city, street, house) VALUES(?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            statementAddress.setString(1, address.getCountry());
            statementAddress.setString(2, address.getCity());
            statementAddress.setString(3, address.getStreet());
            statementAddress.setString(4, address.getHouse());
            statementAddress.execute();
            ResultSet set = statementAddress.getGeneratedKeys();

            while (set.next()) {
                int addressId = set.getInt(1);
                address.setId(addressId);
            }

            statementUser = this.connection.prepareStatement("INSERT INTO users(login, password, user_name, email, address_id, role_id) VALUES(?, ?, ?, ?, ?, ?)");
            statementUser.setString(1, user.getLogin());
            statementUser.setString(2, user.getPassword());
            statementUser.setString(3, user.getName());
            statementUser.setString(4, user.getEmail());
            statementUser.setInt(5, address.getId());
            statementUser.setInt(6, roleId);
            statementUser.execute();

            statementMusic = this.connection.prepareStatement("INSERT INTO login_music_id(user_login, music_id) VALUES(?, ?)");
            for (Integer  musicTypeId : musicTypesId) {
                statementMusic.setString(1, user.getLogin());
                statementMusic.setInt(2, musicTypeId);
                statementMusic.addBatch();
            }
            statementMusic.executeBatch();
            this.connection.commit();
        } catch (SQLException e) {
            this.connection.rollback();
            e.printStackTrace();
        } finally {
            if (statementAddress != null) statementAddress.close();
            if (statementUser != null) statementUser.close();
            if (statementMusic != null) statementMusic.close();
            this.connection.setAutoCommit(true);
        }
    }

    public void update(User user, Address address, Integer roleId, List<Integer> musicTypesId) throws SQLException {
        PreparedStatement statementAddress = null;
        PreparedStatement statementGetAddressId = null;
        PreparedStatement statementUser= null;
        PreparedStatement statementDeleteMusic = null;
        PreparedStatement statementMusic = null;
        try {
            this.connection.setAutoCommit(false);
            statementUser = this.connection.prepareStatement("UPDATE users SET password = ?, user_name = ?, email = ?, role_id = ? WHERE login = ?");
            statementUser.setString(1, user.getPassword());
            statementUser.setString(2, user.getName());
            statementUser.setString(3, user.getEmail());
            statementUser.setInt(4, roleId);
            statementUser.setString(5, user.getLogin());
            statementUser.execute();

            statementGetAddressId = this.connection.prepareStatement("SELECT address_id FROM users WHERE login = ?");
            statementGetAddressId.setString(1, user.getLogin());
            ResultSet set = statementGetAddressId.executeQuery();
            int addressId = -1;
            if (set.next()) {
                addressId = set.getInt(1);
            }

            statementAddress = this.connection.prepareStatement("UPDATE address SET country = ?, city = ?, street = ?, house = ? WHERE id = ?");
            statementAddress.setString(1, address.getCountry());
            statementAddress.setString(2, address.getCity());
            statementAddress.setString(3, address.getStreet());
            statementAddress.setString(4, address.getHouse());
            statementAddress.setInt(5, addressId);
            statementAddress.execute();

            statementDeleteMusic = this.connection.prepareStatement("DELETE FROM login_music_id WHERE user_login = ?");
            statementDeleteMusic.setString(1, user.getLogin());
            statementDeleteMusic.execute();

            statementMusic = this.connection.prepareStatement("INSERT INTO login_music_id(user_login, music_id) VALUES(?, ?)");
            for (Integer  musicTypeId : musicTypesId) {
                statementMusic.setString(1, user.getLogin());
                statementMusic.setInt(2, musicTypeId);
                statementMusic.addBatch();
            }
            statementMusic.executeBatch();
            this.connection.commit();
        } catch (SQLException e) {
            this.connection.rollback();
            e.printStackTrace();
        } finally {
            if (statementAddress != null) statementAddress.close();
            if (statementUser != null) statementUser.close();
            if (statementGetAddressId != null) statementGetAddressId.close();
            if (statementDeleteMusic != null) statementDeleteMusic.close();
            if (statementMusic != null) statementMusic.close();
            this.connection.setAutoCommit(true);
        }
    }

    public Set<User> findAll() {
        Set<User> users = new CopyOnWriteArraySet<>();
        Set<MusicType> musicTypes = new CopyOnWriteArraySet<>();
        try (
                PreparedStatement statement = this.connection.prepareStatement(
                        "SELECT u.login, u.password, u.user_name, u.email, u.address_id, a.country, a.city, a.street, a.house, u.role_id, adr.role, m.id, m.music_type FROM users AS u \n" +
                                "LEFT OUTER JOIN address AS a ON u.address_id = a.id \n" +
                                "LEFT OUTER JOIN roles AS adr ON u.role_id = adr.id\n" +
                                "LEFT OUTER JOIN login_music_id AS lm ON u.login = lm.user_login\n" +
                                "LEFT OUTER JOIN musics AS m ON lm.music_id = m.id;");
                ResultSet resultSet = statement.executeQuery();
        ) {

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
                    musicTypes = new CopyOnWriteArraySet<>();
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

    public void deleteUser(String login) throws SQLException {
        PreparedStatement statementLoginMusic = null;
        PreparedStatement statementGetAddress = null;
        PreparedStatement statementUser = null;
        PreparedStatement statementAddress = null;
        try {
            this.connection.setAutoCommit(false);
            statementLoginMusic = this.connection.prepareStatement("DELETE FROM login_music_id WHERE user_login = ?");
            statementLoginMusic.setString(1, login);
            statementLoginMusic.execute();

            statementGetAddress = this.connection.prepareStatement("SELECT address_id FROM users WHERE login = ?");
            statementGetAddress.setString(1, login);
            ResultSet resultSet = statementGetAddress.executeQuery();
            Integer addressId = -1;
            while (resultSet.next()) {
                addressId = resultSet.getInt("address_id");
            }

            statementUser = this.connection.prepareStatement("DELETE FROM users WHERE login = ?");
            statementUser.setString(1, login);
            statementUser.execute();

            statementAddress = this.connection.prepareStatement("DELETE FROM address WHERE id = ?");
            statementAddress.setInt(1, addressId);
            statementAddress.execute();

            this.connection.commit();
        } catch (SQLException e) {
            this.connection.rollback();
            e.printStackTrace();
        } finally {
            if (statementLoginMusic != null) statementLoginMusic.close();
            if (statementGetAddress != null) statementGetAddress.close();
            if (statementUser != null) statementUser.close();
            if (statementAddress != null) statementAddress.close();
            this.connection.setAutoCommit(true);
        }
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
        Set<User> users = new CopyOnWriteArraySet<>();
        Set<MusicType> musicTypes = new CopyOnWriteArraySet<>();
        try (
                PreparedStatement statement = this.connection.prepareStatement(
                        "SELECT u.login, u.password, u.user_name, u.email, u.address_id, u.role_id, adr.role, m.id, m.music_type FROM users AS u \n" +
                                "LEFT OUTER JOIN address AS a ON u.address_id = a.id\n" +
                                "LEFT OUTER JOIN roles AS adr ON u.role_id = adr.id\n" +
                                "LEFT OUTER JOIN login_music_id AS lm ON u.login = lm.user_login\n" +
                                "LEFT OUTER JOIN musics AS m ON lm.music_id = m.id WHERE a.country = ? AND a.city = ? AND a.street = ? AND a.house = ?;");
        ) {
            statement.setString(1, address.getCountry());
            statement.setString(2, address.getCity());
            statement.setString(3, address.getStreet());
            statement.setString(4, address.getHouse());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                Role role = new Role();
                MusicType musicType = new MusicType();

                user.setLogin(resultSet.getString("login"));
                user.setName(resultSet.getString("user_name"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));

                address.setId(resultSet.getInt("address_id"));

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
                    musicTypes = new CopyOnWriteArraySet<>();
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

    public Set<User> findUserByRole(Role role) {
        Set<User> users = new CopyOnWriteArraySet<>();
        Set<MusicType> musicTypes = new CopyOnWriteArraySet<>();
        try (
                PreparedStatement statement = this.connection.prepareStatement("SELECT u.login, u.password, u.user_name, u.email, u.address_id, a.country, a.city, a.street, a.house, u.role_id, adr.role, m.id, m.music_type FROM users AS u \n" +
                        "LEFT OUTER JOIN address AS a ON u.address_id = a.id\n" +
                        "LEFT OUTER JOIN roles AS adr ON u.role_id = adr.id\n" +
                        "LEFT OUTER JOIN login_music_id AS lm ON u.login = lm.user_login\n" +
                        "LEFT OUTER JOIN musics AS m ON lm.music_id = m.id WHERE adr.role = ?;")
        ) {
            statement.setString(1, role.getRoleType());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                Address address = new Address();
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

                musicType.setId(resultSet.getInt("id"));
                musicType.setMusicType(resultSet.getString("music_type"));
                user.setAddress(address);
                user.setRole(role);

                if (users.contains(user)) {
                    musicTypes.add(musicType);
                    user.setMusicTypes(musicTypes);
                } else {
                    musicTypes = new CopyOnWriteArraySet<>();
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

    public Set<User> findUserByMusicType(MusicType musicType) {
        Set<User> users = new CopyOnWriteArraySet<>();
        Set<MusicType> musicTypes = new CopyOnWriteArraySet<>();
        try (
                PreparedStatement statement = this.connection.prepareStatement("SELECT u.login, u.password, u.user_name, u.email, u.address_id, a.country, a.city, a.street, a.house, u.role_id, adr.role, m.id, m.music_type FROM users AS u \n" +
                        "LEFT OUTER JOIN address AS a ON u.address_id = a.id \n" +
                        "LEFT OUTER JOIN roles AS adr ON u.role_id = adr.id\n" +
                        "LEFT OUTER JOIN login_music_id AS lm ON u.login = lm.user_login\n" +
                        "LEFT OUTER JOIN musics AS m ON lm.music_id = m.id WHERE m.music_type = ?;");
        ) {
            statement.setString(1, musicType.getMusicType());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                Address address = new Address();
                Role role = new Role();

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
                    musicTypes = new CopyOnWriteArraySet<>();
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

    public void closeConection() {
        try {
            this.connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
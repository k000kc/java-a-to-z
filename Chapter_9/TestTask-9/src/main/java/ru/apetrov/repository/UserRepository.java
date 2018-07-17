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

    public UserRepository() {
        this.init();
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

    public void update(User user, Address address, Integer roleId, List<Integer> musicTypesId) {
        try {
            this.connection.setAutoCommit(false);
            PreparedStatement statementUser = this.connection.prepareStatement("UPDATE users SET password = ?, user_name = ?, email = ?, role_id = ? WHERE login = ?");
            statementUser.setString(1, user.getPassword());
            statementUser.setString(2, user.getName());
            statementUser.setString(3, user.getEmail());
            statementUser.setInt(4, roleId);
            statementUser.setString(5, user.getLogin());
            statementUser.execute();

            PreparedStatement statementGetAddressId = this.connection.prepareStatement("SELECT address_id FROM users WHERE login = ?");
            statementGetAddressId.setString(1, user.getLogin());
            ResultSet set = statementGetAddressId.executeQuery();
            int addressId = -1;
            if (set.next()) {
                addressId = set.getInt(1);
            }

            PreparedStatement statementAddress = this.connection.prepareStatement("UPDATE address SET country = ?, city = ?, street = ?, house = ? WHERE id = ?");
            statementAddress.setString(1, address.getCountry());
            statementAddress.setString(2, address.getCity());
            statementAddress.setString(3, address.getStreet());
            statementAddress.setString(4, address.getHouse());
            statementAddress.setInt(5, addressId);
            statementAddress.execute();

            //TODO music type
//            PreparedStatement statementMusic = this.connection.prepareStatement("UPDATE musics SET music_type = ? WHERE id = ?");
        } catch (SQLException e) {
            e.printStackTrace();
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
        ResultSet resultSet = null;
        try (
                PreparedStatement statement = this.connection.prepareStatement("SELECT id FROM address WHERE country = ? AND city = ? AND street = ? AND house = ?")
        ) {
            statement.setString(1, address.getCountry());
            statement.setString(2, address.getCity());
            statement.setString(3, address.getStreet());
            statement.setString(4, address.getHouse());
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Integer addressId = resultSet.getInt("id");
                user = this.getUserByAddressId(addressId);
                users.add(user);
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
        return users;
    }

    private User getUserByAddressId(Integer addressId) {
        User user = null;
        ResultSet resultSet = null;
        try (
                PreparedStatement statement = this.connection.prepareStatement("SELECT * FROM users WHERE address_id = ?")
        ) {
            statement.setInt(1, addressId);
            resultSet = statement.executeQuery();
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
        } finally {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return user;
    }

    public Set<User> getUserByRole(Role role) {
        Set<User> users = new HashSet<>();
        User user = null;
        ResultSet resultSet = null;
        try (
                PreparedStatement statement = this.connection.prepareStatement("SELECT * FROM users WHERE role_id = ?")
        ) {
            statement.setInt(1, role.getId());
            resultSet = statement.executeQuery();
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
        } finally {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return users;
    }

    public Set<User> getUserByMusicType(MusicType musicType) {
        Set<User> users = new HashSet<>();
        User user = null;
        ResultSet resultSet = null;
        try (
                PreparedStatement statement = this.connection.prepareStatement("SELECT user_login FROM login_music_id WHERE music_id = ?");
        ) {
            statement.setInt(1, musicType.getId());
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                user = this.userDAO.getById(resultSet.getString("user_login"));
                users.add(user);
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
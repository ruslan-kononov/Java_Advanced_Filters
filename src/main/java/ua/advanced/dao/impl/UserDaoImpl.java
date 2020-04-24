package ua.advanced.dao.impl;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.advanced.dao.UserDao;
import ua.advanced.domain.User;
import ua.advanced.utils.ConnectionUtils;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao{

    private static String CREATE = "INSERT INTO user(email,first_name,last_name,role,password) VALUES (?,?,?,?,?)";
    private static String READ_BY_ID = "SELECT*FROM user WHERE id = ?";
    private static String READ_ALL = "SELECT*FROM user";
    private static String READ_BY_EMAIL = "SELECT*FROM user WHERE email = ?";
    private static String UPDATE_BY_ID = "UPDATE user SET email=?, first_name=?, last_name=?, role=?, password=? WHERE id = ?";
    private static String DELETE_BY_ID = "DELETE FROM user WHERE id=?";

    private static Logger logger = LogManager.getLogger(UserDaoImpl.class);

    @Override
    public boolean create(User user) {
        try (Connection connection = ConnectionUtils.openConnection()) {
            try (PreparedStatement prSt = connection.prepareStatement(CREATE)) {
                prSt.setString(1, user.getEmail());
                prSt.setString(2, user.getFirst_name());
                prSt.setString(3, user.getLast_name());
                prSt.setString(4, user.getRole());
                prSt.setString(5, user.getPassword());
                prSt.executeUpdate();
            }
        }catch (InstantiationException | InvocationTargetException | NoSuchMethodException | SQLException
                | IllegalAccessException |ClassNotFoundException e) {
            logger.error(e);
        }
        return true;
    }

    @Override
    public User read(int id) {
        User user = null;
        try (Connection connection = ConnectionUtils.openConnection()) {
            try (PreparedStatement prSt = connection.prepareStatement(READ_BY_ID)) {
                prSt.setInt(1, id);
                try (ResultSet resultSet = prSt.executeQuery()) {
                    resultSet.next();
                    String email = resultSet.getString("email");
                    String first_name = resultSet.getString("first_name");
                    String last_name = resultSet.getString("last_name");
                    String role = resultSet.getString("role");
                    String password = resultSet.getString("password");
                    user = new User(email, first_name, last_name, role, password);
                }
            }
        }catch (InstantiationException | InvocationTargetException | NoSuchMethodException | SQLException
                | IllegalAccessException |ClassNotFoundException e) {
            logger.error(e);
        }
        return user;
    }

    @Override
    public List<User> readAll() {
        List<User> userList = new ArrayList<>();
        try (Connection connection = ConnectionUtils.openConnection()) {
            try (PreparedStatement prSt = connection.prepareStatement(READ_ALL)) {
                try (ResultSet resultSet = prSt.executeQuery()) {
                    while (resultSet.next()) {
                        Integer id = resultSet.getInt("id");
                        String email = resultSet.getString("email");
                        String first_name = resultSet.getString("first_name");
                        String last_name = resultSet.getString("last_name");
                        String role = resultSet.getString("role");
                        String password = resultSet.getString("password");
                        userList.add(new User(id, email, first_name, last_name, role, password));
                    }
                }
            }
        }catch (InstantiationException | InvocationTargetException | NoSuchMethodException | SQLException
                | IllegalAccessException |ClassNotFoundException e) {
            logger.error(e);
        }
        return userList;
    }

    @Override
    public User update(User user) {
        try (Connection connection = ConnectionUtils.openConnection()) {
            try (PreparedStatement prSt = connection.prepareStatement(UPDATE_BY_ID)) {
                prSt.setString(1, user.getEmail());
                prSt.setString(2, user.getFirst_name());
                prSt.setString(3, user.getLast_name());
                prSt.setString(4, user.getRole());
                prSt.setString(5, user.getPassword());
                prSt.setInt(6, user.getId());
                prSt.executeUpdate();
            }
        }catch (InstantiationException | InvocationTargetException | NoSuchMethodException | SQLException
                | IllegalAccessException |ClassNotFoundException e) {
            logger.error(e);
        }
        return null;
    }

    @Override
    public void delete(int id) {
        try (Connection connection = ConnectionUtils.openConnection()) {
            try (PreparedStatement prSt = connection.prepareStatement(DELETE_BY_ID)) {
                prSt.setInt(1, id);
                prSt.executeUpdate();
            }
        }catch (InstantiationException | InvocationTargetException | NoSuchMethodException | SQLException
                | IllegalAccessException |ClassNotFoundException e) {
            logger.error(e);
        }
    }

    @Override
    public User readByEmail(String email) {
        User user = null;
        try (Connection connection = ConnectionUtils.openConnection()) {
            try (PreparedStatement prSt = connection.prepareStatement(READ_BY_EMAIL)) {
                prSt.setString(1, email);
                try (ResultSet resultSet = prSt.executeQuery()) {
                    boolean recordExists = resultSet.next();
                    if(!recordExists) return user;
                    Integer id = resultSet.getInt("id");
                    String first_name = resultSet.getString("first_name");
                    String last_name = resultSet.getString("last_name");
                    String role = resultSet.getString("role");
                    String password = resultSet.getString("password");
                    user = new User(id, email, first_name, last_name, role, password);
                }catch (Exception e){
                    logger.error(e);
                }
            }
        }catch (InstantiationException | InvocationTargetException | NoSuchMethodException | SQLException
                | IllegalAccessException |ClassNotFoundException e) {
            logger.error(e);
        }
        return user;
    }
}

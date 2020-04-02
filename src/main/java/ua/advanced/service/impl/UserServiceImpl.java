package ua.advanced.service.impl;

import ua.advanced.dao.UserDao;
import ua.advanced.dao.impl.UserDaoImpl;
import ua.advanced.domain.User;
import ua.advanced.service.UserService;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDao userDaoImpl;
    private static UserService userService;

    private UserServiceImpl() {
        this.userDaoImpl = new UserDaoImpl();
    }

    public static UserService getUserService(){
        if(userService==null){
            userService = new UserServiceImpl();
        }
        return userService;
    }

    @Override
    public boolean create(User user) {
        return userDaoImpl.create(user);
    }

    @Override
    public User read(int id) {
        return userDaoImpl.read(id);
    }

    @Override
    public User update(User user) throws SQLException {
        return userDaoImpl.update(user);
    }

    @Override
    public void delete(int id) {
        userDaoImpl.delete(id);
    }

    @Override
    public List<User> readAll() {
        return userDaoImpl.readAll();
    }

    @Override
    public User readByEmail(String login) {
        return userDaoImpl.readByEmail(login);
    }
}

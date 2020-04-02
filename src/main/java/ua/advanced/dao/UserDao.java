package ua.advanced.dao;

import ua.advanced.domain.User;
import ua.advanced.shared.AbstractCRUD;

public interface UserDao extends AbstractCRUD<User> {
    User readByEmail(String email);
}

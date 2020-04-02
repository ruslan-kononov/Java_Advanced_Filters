package ua.advanced.service;

import ua.advanced.domain.User;
import ua.advanced.shared.AbstractCRUD;

public interface UserService extends AbstractCRUD<User> {
    User readByEmail(String email);
}

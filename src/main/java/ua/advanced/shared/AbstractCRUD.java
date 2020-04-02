package ua.advanced.shared;

import java.sql.SQLException;
import java.util.List;

public interface AbstractCRUD<T>{
    boolean create(T t);
    T read(int id);
    T update(T t) throws SQLException;
    void delete(int id);
    List<T> readAll();
}

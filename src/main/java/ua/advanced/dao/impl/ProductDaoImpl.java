package ua.advanced.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.advanced.dao.ProductDao;
import ua.advanced.domain.Product;
import ua.advanced.utils.ConnectionUtils;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl implements ProductDao {

    private static String CREATE = "INSERT INTO product(name,description,price) VALUES (?,?,?)";
    private static String READ_BY_ID = "SELECT*FROM product WHERE id = ?";
    private static String READ_ALL = "SELECT*FROM product";
    private static String UPDATE_BY_ID = "UPDATE product SET name=?, description=?, price=? WHERE id = ?";
    private static String DELETE_BY_ID = "DELETE FROM product WHERE id=?";

    private static Logger logger = LogManager.getLogger(ProductDaoImpl.class);

    @Override
    public boolean create(Product product) {
        try (Connection connection = ConnectionUtils.openConnection()) {
            try (PreparedStatement prSt = connection.prepareStatement(CREATE)) {
                prSt.setString(1, product.getName());
                prSt.setString(2, product.getDescription());
                prSt.setDouble(3, product.getPrice());
                prSt.executeUpdate();
            }
        } catch (InstantiationException | InvocationTargetException | NoSuchMethodException | SQLException
                                        | IllegalAccessException |ClassNotFoundException e) {
            logger.error(e);
        }
        return true;
    }

    @Override
    public Product read(int id) {
        Product product = null;
        try (Connection connection = ConnectionUtils.openConnection()) {
            try (PreparedStatement prSt = connection.prepareStatement(READ_BY_ID)) {
                try (ResultSet resultSet = prSt.executeQuery()) {
                    resultSet.next();
                    String name = resultSet.getString("name");
                    String description = resultSet.getString("description");
                    Double price = resultSet.getDouble("price");
                    product = new Product(name, description, price);
                }
            }
        }catch (InstantiationException | InvocationTargetException | NoSuchMethodException | SQLException
                                       | IllegalAccessException |ClassNotFoundException e) {
            logger.error(e);
        }
        return product;
    }

    @Override
    public List<Product> readAll() {
        List<Product> productList = new ArrayList<>();
        try (Connection connection = ConnectionUtils.openConnection()) {
            try (PreparedStatement prSt = connection.prepareStatement(READ_ALL)) {
                try (ResultSet resultSet = prSt.executeQuery()) {
                    while (resultSet.next()) {
                        Integer id = resultSet.getInt("id");
                        String name = resultSet.getString("name");
                        String description = resultSet.getString("description");
                        Double price = resultSet.getDouble("price");
                        productList.add(new Product(id, name, description, price));
                    }
                }
            }
        }catch (InstantiationException | InvocationTargetException | NoSuchMethodException | SQLException
                | IllegalAccessException |ClassNotFoundException e) {
            logger.error(e);
        }
        return productList;
    }

    @Override
    public Product update(Product product) {
        try (Connection connection = ConnectionUtils.openConnection()) {
            try (PreparedStatement prSt = connection.prepareStatement(UPDATE_BY_ID)) {
                prSt.setString(1, product.getName());
                prSt.setString(2, product.getDescription());
                prSt.setDouble(3, product.getPrice());
                prSt.setInt(4, product.getId());
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
}

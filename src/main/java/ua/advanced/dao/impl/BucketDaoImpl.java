package ua.advanced.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.advanced.dao.BucketDao;
import ua.advanced.domain.Bucket;
import ua.advanced.dto.UserOrder;
import ua.advanced.utils.ConnectionUtils;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BucketDaoImpl implements BucketDao {

    private static String CREATE = "INSERT INTO bucket(user_id,product_id,purchase_date,quantity) VALUES (?,?,?,?)";
    private static String READ_BY_ID = "SELECT*FROM bucket WHERE id = ?";
    private static String READ_ALL_BY_USER = "SELECT sb.id, sp.name, sp.price, sb.quantity, sb.purchase_date FROM shop.bucket sb\n" +
                                                             "JOIN shop.product sp ON sp.id = sb.product_id\n" +
                                                                                  "AND sb.user_id = ?";
    private static String READ_ALL_BY_USER_PRODUCT = "SELECT*FROM bucket WHERE user_id=? AND product_id=?";
    private static String READ_ALL = "SELECT*FROM bucket";
    private static String UPDATE_BY_ID = "UPDATE bucket SET quantity = ? WHERE id = ?";
    private static String DELETE_BY_ID = "DELETE FROM bucket WHERE id = ?";

    private static Logger logger = LogManager.getLogger(BucketDaoImpl.class);

    @Override
    public boolean create(Bucket bucket) {
        try (Connection connection = ConnectionUtils.openConnection()) {
            try (PreparedStatement prSt = connection.prepareStatement(CREATE)) {
                prSt.setInt(1, bucket.getUserId());
                prSt.setInt(2, bucket.getProductId());
                prSt.setDate(3, new Date(bucket.getPurchaseDate().getTime()));
                prSt.setInt(4, bucket.getQuantity());
                prSt.executeUpdate();
            } catch (SQLException e) {
                logger.error(e);
            }
        }catch (InstantiationException | InvocationTargetException | NoSuchMethodException | SQLException
                | IllegalAccessException |ClassNotFoundException e) {
            logger.error(e);
        }
        return true;
    }

    @Override
    public Bucket read(int id) {
        Bucket bucket = null;
        try (Connection connection = ConnectionUtils.openConnection()) {
            try (PreparedStatement prSt = connection.prepareStatement(READ_BY_ID)) {
                prSt.setInt(1, id);
                try (ResultSet resultSet = prSt.executeQuery()) {
                    resultSet.next();
                    Integer userId = resultSet.getInt("user_id");
                    Integer productId = resultSet.getInt("product_id");
                    java.util.Date purchaseDate = resultSet.getDate("purchase_date");
                    Integer quantity = resultSet.getInt("quantity");
                    bucket = new Bucket(userId, productId, purchaseDate,quantity);
                }
            }
        }catch (InstantiationException | InvocationTargetException | NoSuchMethodException | SQLException
                | IllegalAccessException |ClassNotFoundException e) {
            logger.error(e);
        }
        return bucket;
    }

    @Override
    public List<UserOrder> readAllByUser(int userId) {
        List<UserOrder> orderList = new ArrayList<>();
        try (Connection connection = ConnectionUtils.openConnection()) {
            try (PreparedStatement prSt = connection.prepareStatement(READ_ALL_BY_USER)) {
                prSt.setInt(1, userId);
                try (ResultSet result = prSt.executeQuery()) {
                    while (result.next()) {
                        UserOrder order = new UserOrder();
                        order.bucketId = result.getInt("id");
                        order.name = result.getString("name");
                        order.price = result.getDouble("price");
                        order.quantity = result.getInt("quantity");
                        order.purchaseDate = result.getDate("purchase_date");
                        orderList.add(order);
                    }
                }
            }
        }catch (InstantiationException | InvocationTargetException | NoSuchMethodException | SQLException
                | IllegalAccessException |ClassNotFoundException e) {
            logger.error(e);
        }
        return orderList;
    }

    @Override
    public Bucket readAllByUserProduct(int user_id, int product_id) {
        Bucket bucket = null;
        try (Connection connection = ConnectionUtils.openConnection()) {
            try (PreparedStatement prSt = connection.prepareStatement(READ_ALL_BY_USER_PRODUCT)) {
                prSt.setInt(1, user_id);
                prSt.setInt(2, product_id);
                try (ResultSet resultSet = prSt.executeQuery()) {
                    if(resultSet.next()){
                        resultSet.beforeFirst();
                        resultSet.next();
                        Integer bucketId = resultSet.getInt("id");
                        Integer userId = resultSet.getInt("user_id");
                        Integer productId = resultSet.getInt("product_id");
                        java.sql.Date purchaseDate = resultSet.getDate("purchase_date");
                        Integer quantity = resultSet.getInt("quantity");
                        bucket = new Bucket(bucketId,userId, productId,purchaseDate,quantity);
                    }
                }
            }
        }catch (InstantiationException | InvocationTargetException | NoSuchMethodException | SQLException
                | IllegalAccessException |ClassNotFoundException e) {
            logger.error(e);
        }
        return bucket;
    }

    @Override
    public List<Bucket> readAll() {
        List<Bucket> bucketList = new ArrayList<>();
        try (Connection connection = ConnectionUtils.openConnection()) {
            try (PreparedStatement prSt = connection.prepareStatement(READ_ALL)) {
                try (ResultSet result = prSt.executeQuery()) {
                    while (result.next()) {
                        Integer id = result.getInt("id");
                        Integer userId = result.getInt("user_id");
                        Integer productId = result.getInt("product_id");
                        java.util.Date purchaseDate = result.getDate("purchase_date");
                        Integer quantity = result.getInt("quantity");
                        bucketList.add(new Bucket(id, userId, productId, purchaseDate,quantity));
                    }
                }
            }
        }catch (InstantiationException | InvocationTargetException | NoSuchMethodException | SQLException
                | IllegalAccessException |ClassNotFoundException e) {
            logger.error(e);
        }
        return bucketList;
    }

    @Override
    public Bucket update(Bucket bucket) {
        Integer quantity = bucket.getQuantity()+1;
        try (Connection connection = ConnectionUtils.openConnection()) {
            try (PreparedStatement prSt = connection.prepareStatement(UPDATE_BY_ID)) {
                prSt.setInt(1, quantity);
                prSt.setInt(2, bucket.getId());
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
            } catch (SQLException e) {
                logger.error(e);
            }
        }catch (InstantiationException | InvocationTargetException | NoSuchMethodException | SQLException
                | IllegalAccessException |ClassNotFoundException e) {
            logger.error(e);
        }
    }
}

package ua.advanced.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.advanced.dao.BucketDao;
import ua.advanced.domain.Bucket;
import ua.advanced.utils.ConnectionUtils;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BucketDaoImpl implements BucketDao {

    private static String CREATE = "INSERT INTO bucket(user_id,product_id,purchase_date) VALUES (?,?,?)";
    private static String READ_BY_ID = "SELECT*FROM bucket WHERE id = ?";
    private static String READ_ALL = "SELECT*FROM bucket";
    private static String DELETE_BY_ID = "DELETE FROM bucket WHERE id = ?";

    private static Logger logger = LogManager.getLogger(BucketDaoImpl.class);

    @Override
    public boolean create(Bucket bucket) {
        try (Connection connection = ConnectionUtils.openConnection()) {
            try (PreparedStatement prSt = connection.prepareStatement(CREATE)) {
                prSt.setInt(1, bucket.getUserId());
                prSt.setInt(2, bucket.getProductId());
                prSt.setDate(3, new Date(bucket.getPurchaseDate().getTime()));
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
                    bucket = new Bucket(userId, productId, purchaseDate);
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
                        bucketList.add(new Bucket(id, userId, productId, purchaseDate));
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
        throw new IllegalStateException("There is no update for buckets");
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

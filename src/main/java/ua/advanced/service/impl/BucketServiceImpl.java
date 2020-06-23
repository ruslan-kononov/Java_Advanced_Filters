package ua.advanced.service.impl;

import ua.advanced.dao.BucketDao;
import ua.advanced.dao.impl.BucketDaoImpl;
import ua.advanced.domain.Bucket;
import ua.advanced.service.BucketService;

import java.sql.SQLException;
import java.util.List;

public class BucketServiceImpl implements BucketService {
    private BucketDao bucketDaoImpl;
    private static BucketService bucketService;

    private BucketServiceImpl() {
        this.bucketDaoImpl = new BucketDaoImpl();
    }

    public static BucketService getBucketService(){
        if(bucketService==null){
            bucketService = new BucketServiceImpl();
        }
        return bucketService;
    }

    @Override
    public boolean create(Bucket bucket) {
        return bucketDaoImpl.create(bucket);
    }

    @Override
    public Bucket read(int id) {
        return bucketDaoImpl.read(id);
    }


    @Override
    public Bucket update(Bucket bucket) throws SQLException {
        return bucketDaoImpl.update(bucket);
    }

    @Override
    public void delete(int id) {
        bucketDaoImpl.delete(id);
    }

    @Override
    public List<Bucket> readAll() {
        return bucketDaoImpl.readAll();
    }

    @Override
    public List<Bucket> readAllByUser(int userId) {
        return bucketDaoImpl.readAllByUser(userId);
    }

    @Override
    public List<Bucket> readAllByUserProduct(int userId, int productId) {
        return bucketDaoImpl.readAllByUserProduct(userId,productId);
    }
}

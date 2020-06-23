package ua.advanced.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.advanced.dao.BucketDao;
import ua.advanced.domain.Bucket;;
import ua.advanced.shared.FactoryManager;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class BucketDaoImpl implements BucketDao {

    private static Logger logger = LogManager.getLogger(BucketDaoImpl.class);
    private static EntityManager em = FactoryManager.getEntityManager();

    @Override
    public boolean create(Bucket bucket) {
        EntityTransaction et = null;
        try {
            et = em.getTransaction();
            et.begin();
            em.persist(bucket);
            et.commit();
        }catch (Exception e) {
            if(et!=null){
                et.rollback();
            }
            logger.error(e);
        }
        return true;
    }

    @Override
    public Bucket read(int id) {
        Bucket bucket = null;
        try {
            bucket = em.find(Bucket.class,id);
        }catch (Exception e) {
            logger.error(e);
        }
        return bucket;
    }

    @Override
    public List<Bucket> readAllByUser(int userId) {
        List<Bucket> orderList = new ArrayList<>();
        try{
            Query jpqlQuery = em.createQuery("SELECT b FROM Bucket b WHERE b.user.id=:userId");
            jpqlQuery.setParameter("userId", userId);
            orderList = (List<Bucket>) jpqlQuery.getResultList();
        }
        catch (Exception e) {
            logger.error(e);
        }
        return orderList;
    }

    @Override
    public List<Bucket> readAllByUserProduct(int userId, int productId) {
        List<Bucket> bucketList = new ArrayList<>();
        try{
            String query = "FROM Bucket b WHERE b.user_id = :userId AND b.product_id = :productId";
            Query jpqlQuery = em.createQuery("SELECT b FROM Bucket b WHERE b.user.id=:userId AND b.product.id=:productId");
            jpqlQuery.setParameter("userId", userId);
            jpqlQuery.setParameter("productId", productId);
            bucketList = (List<Bucket>) jpqlQuery.getResultList();
        }catch (Exception e) {
            logger.error(e);
        }
        return bucketList;
    }

    @Override
    public List<Bucket> readAll() {
        List<Bucket> bucketList = new ArrayList<>();
        try{
            TypedQuery<Bucket> query = em.createQuery("SELECT b FROM Bucket b", Bucket.class);
            bucketList = query.getResultList();
        }catch (Exception e) {
            logger.error(e);
        }
        return bucketList;
    }

    @Override
    public Bucket update(Bucket bucket) {
        Integer quantity = bucket.getQuantity()+1;
        EntityTransaction et = null;
        Bucket bucketFromTable = null;
        try{
            et = em.getTransaction();
            et.begin();
            bucketFromTable = em.find(Bucket.class, bucket.getId());
            bucketFromTable.setQuantity(quantity);
            em.persist(bucketFromTable);
            et.commit();
        }catch (Exception e) {
            if(et!=null){
                et.rollback();
            }
            logger.error(e);
        }
        return null;
    }

    @Override
    public void delete(int id) {
        EntityTransaction et = null;
        Bucket bucket = null;
        try {
            et = em.getTransaction();
            et.begin();
            bucket = em.find(Bucket.class, id);
            em.remove(bucket);
            et.commit();
        }catch (Exception e) {
            if(et!=null){
                et.rollback();
            }
            logger.error(e);
        }
    }
}

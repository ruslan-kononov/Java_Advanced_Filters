package ua.advanced.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.advanced.dao.ProductDao;
import ua.advanced.domain.Product;
import ua.advanced.shared.FactoryManager;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl implements ProductDao {

    private static Logger logger = LogManager.getLogger(ProductDaoImpl.class);
    private static EntityManager em = FactoryManager.getEntityManager();

    @Override
    public boolean create(Product product) {
        EntityTransaction et = null;
        try {
            et = em.getTransaction();
            et.begin();
            em.persist(product);
            et.commit();
        } catch (Exception e) {
            if(et!=null){
                et.rollback();
            }
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public Product read(int id) {
        Product product = null;
        try {
            product = em.find(Product.class,id);
        } catch (Exception e) {
            logger.error(e);
        }
        return product;
    }

    @Override
    public List<Product> readAll() {
        List<Product> productList = new ArrayList<>();
        try {
            TypedQuery<Product> query = em.createQuery("SELECT p FROM Product p", Product.class);
            productList = query.getResultList();
        }catch (Exception e) {
            logger.error(e);
        }
        return productList;
    }

    @Override
    public Product update(Product product) {
        return null;
    }

    @Override
    public void delete(int id) {
        EntityTransaction et = null;
        Product product = null;
        try {
            et = em.getTransaction();
            et.begin();
            product = em.find(Product.class, id);
            em.remove(product);
            et.commit();
        }catch (Exception e) {
            if(et!=null){
                et.rollback();
            }
            logger.error(e);
        }
    }
}

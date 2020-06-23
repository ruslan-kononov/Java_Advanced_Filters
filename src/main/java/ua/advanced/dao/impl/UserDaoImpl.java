package ua.advanced.dao.impl;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.advanced.dao.UserDao;
import ua.advanced.domain.User;
import ua.advanced.shared.FactoryManager;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao{
    private static Logger logger = LogManager.getLogger(UserDaoImpl.class);
    private EntityManager em = FactoryManager.getEntityManager();

    @Override
    public boolean create(User user) {
        EntityTransaction et = null;
        try {
            et = em.getTransaction();
            et.begin();
            em.persist(user);
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
    public User read(int id) {
        User user = null;
        try{
            user = em.find(User.class,id);
        }catch (Exception e) {
            logger.error(e);
        }
        return user;
    }

    @Override
    public List<User> readAll() {
        List<User> userList = new ArrayList<>();
        try {
            TypedQuery<User> query = em.createQuery("SELECT u FROM User u", User.class);
            userList = query.getResultList();
        }catch (Exception e) {
            logger.error(e);
        }
        return userList;
    }

    @Override
    public User readByEmail(String email) {
        User user = null;
        try{
            Query jpqlQuery = em.createQuery("SELECT u FROM User u WHERE u.email=:email");
            jpqlQuery.setParameter("email", email);
            user = (User) jpqlQuery.getSingleResult();
        }catch (Exception e) {
            logger.error(e);
        }
        return user;
    }

    @Override
    public User update(User user) {
        return null;
    }

    @Override
    public void delete(int id) {
        EntityTransaction et = null;
        User user = null;
        try {
            et = em.getTransaction();
            et.begin();
            user = em.find(User.class, id);
            em.remove(user);
            et.commit();
        }catch (Exception e) {
            if (et != null) {
                et.rollback();
            }
            logger.error(e);
        }
    }
}

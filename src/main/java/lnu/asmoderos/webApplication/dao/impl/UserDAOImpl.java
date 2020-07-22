package lnu.asmoderos.webApplication.dao.impl;

import lnu.asmoderos.webApplication.dao.IUserDAO;
import lnu.asmoderos.webApplication.model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

public class UserDAOImpl implements IUserDAO {
    @Autowired
    SessionFactory sessionFactory;

    public UserDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void saveUser(User user) {
        Session session;
        Transaction tx = null;
        try {
            session = this.sessionFactory.openSession();
            tx = session.beginTransaction();
            session.save(user);
            tx.commit();
            session.close();
        } catch (HibernateException e) {
            e.printStackTrace();
            if (tx != null) {
                tx.rollback();
            }
        }
    }

    @Override
    public User getUserById(int id) {
        Session session = this.sessionFactory.openSession();
        User user = (User) session
                .createQuery("FROM lnu.asmoderos.webApplication.model.User WHERE id = '" + id + "'")
                .uniqueResult();
        session.close();
        return user;
    }

    @Override
    public User getUserByEmail(String email) {
        Session session = this.sessionFactory.openSession();
        User user = (User) session
                .createQuery("FROM lnu.asmoderos.webApplication.model.User WHERE email = '" + email + "'")
                .uniqueResult();
        session.close();
        return user;
    }

    @Override
    public int getIdByEmail(String email) {
        Session session = this.sessionFactory.openSession();
        int id = (Integer) session
                .createQuery("SELECT id FROM lnu.asmoderos.webApplication.model.User WHERE email = '" + email + "'")
                .uniqueResult();
        session.close();
        return id;
    }

    @Override
    public void updateUser(User user) {
        Session session;
        Transaction tx = null;
        try {
            session = this.sessionFactory.openSession();
            tx = session.beginTransaction();

            List<Integer> setList = (List<Integer>) session
                    .createQuery("SELECT p FROM lnu.asmoderos.webApplication.model.User a JOIN a.subscribe p WHERE a.id = '" + user.getId() + "'")
                    .list();
            setList.add(user.getSubscribe().iterator().next());

            user.setSubscribe(setList);

            session.update(user);

            tx.commit();
            session.close();
        } catch (HibernateException e) {
            e.printStackTrace();
            if (tx != null) {
                tx.rollback();
            }
        }
    }

    @Override
    public List<Integer> getSubscribeId(int id) {
        Session session = this.sessionFactory.openSession();
        List<Integer> subscribeList = (List<Integer>) session
                .createQuery("SELECT p FROM lnu.asmoderos.webApplication.model.User a JOIN a.subscribe p WHERE a.id = '" + id + "'")
                .list();

        session.close();
        return subscribeList;
    }

}

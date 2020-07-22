package lnu.asmoderos.webApplication.dao.impl;


import lnu.asmoderos.webApplication.dao.INewsDAO;
import lnu.asmoderos.webApplication.model.Course;
import lnu.asmoderos.webApplication.model.Group;
import lnu.asmoderos.webApplication.model.News;
import lnu.asmoderos.webApplication.model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class NewsDAOImpl implements INewsDAO {

    @Autowired
    SessionFactory sessionFactory;

    public NewsDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void saveNews(News news) {
        Session session;
        Transaction tx = null;
        try {
            session = this.sessionFactory.openSession();
            tx = session.beginTransaction();
            session.save(news);
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
    public News getNewsByTitle(String title) {
        Session session = this.sessionFactory.openSession();
        News news = (News) session
                .createQuery("FROM lnu.asmoderos.webApplication.model.News WHERE title = '" + title + "'")
                .uniqueResult();
        session.close();
        return news;
    }

    @Override
    public List<News> getNewsList() {
        Session session = this.sessionFactory.openSession();
        List<News> newsList = (List<News>) session
                .createQuery("FROM lnu.asmoderos.webApplication.model.News ")
                .list();
        session.close();
        return newsList;
    }

    @Override
    public News getNewsById(int id) {
        Session session = this.sessionFactory.openSession();
        News news = (News) session
                .createQuery("FROM lnu.asmoderos.webApplication.model.News WHERE id = '" + id + "'")
                .uniqueResult();
        session.close();
        return news;
    }

    @Override
    public void deleteNewsById(int id) {
        Session session;
        Transaction tx = null;
        try {
            session = this.sessionFactory.openSession();
            tx = session.beginTransaction();
            News news = (News) session
                    .createQuery("FROM lnu.asmoderos.webApplication.model.News WHERE id = '" + id + "'")
                    .uniqueResult();
            session.delete(news);
            tx.commit();
            session.close();
        } catch (HibernateException e) {
            e.printStackTrace();
            if (tx != null) {
                tx.rollback();
            }
        }
    }

}

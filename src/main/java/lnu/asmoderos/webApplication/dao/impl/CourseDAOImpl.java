package lnu.asmoderos.webApplication.dao.impl;

import lnu.asmoderos.webApplication.dao.ICourseDAO;
import lnu.asmoderos.webApplication.model.Course;
import lnu.asmoderos.webApplication.model.News;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CourseDAOImpl implements ICourseDAO {


    @Autowired
    SessionFactory sessionFactory;

    public CourseDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void saveCourse(Course course) {
        Session session;
        Transaction tx = null;
        try {
            session = this.sessionFactory.openSession();
            tx = session.beginTransaction();
            session.save(course);
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
    public Course getCourseByTitle(String title) {
        Session session = this.sessionFactory.openSession();
        Course course = (Course) session
                .createQuery("FROM lnu.asmoderos.webApplication.model.Course WHERE title = '" + title + "'")
                .uniqueResult();
        session.close();
        return course;
    }

    @Override
    public List<String> getLessonsList(int id) {
        Session session = this.sessionFactory.openSession();
        List<String> courseList = (List<String>) session
                .createQuery("SELECT p FROM lnu.asmoderos.webApplication.model.Course a JOIN a.lessons p WHERE a.course_id = '" + id + "'")
                .list();

        session.close();
        return courseList;
    }

    @Override
    public Course getCourseById(int id) {
        Session session = this.sessionFactory.openSession();
        Course course = (Course) session
                .createQuery("FROM lnu.asmoderos.webApplication.model.Course WHERE course_id = '" + id + "'")
                .uniqueResult();
        session.close();
        return course;
    }

    @Override
    public List<Course> getCourseList() {
        Session session = this.sessionFactory.openSession();
        List<Course> courseList = (List<Course>) session
                .createQuery("FROM lnu.asmoderos.webApplication.model.Course ")
                .list();
        session.close();
        return courseList;
    }

    @Override
    public void deleteCourseById(int id) {
        Session session;
        Transaction tx = null;
        try {
            session = this.sessionFactory.openSession();
            tx = session.beginTransaction();
            Course course = (Course) session
                    .createQuery("FROM lnu.asmoderos.webApplication.model.Course WHERE course_id = '" + id + "'")
                    .uniqueResult();
            //session.createQuery("DELETE p FROM lnu.asmoderos.webApplication.model.User a JOIN a.subscribe p WHERE a.subscribe = '" + id + "'");
            session.delete(course);
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

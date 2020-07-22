package lnu.asmoderos.webApplication.dao.impl;

import lnu.asmoderos.webApplication.dao.ICategoryDAO;
import lnu.asmoderos.webApplication.model.Category;
import lnu.asmoderos.webApplication.model.Group;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CategoryDAOImpl implements ICategoryDAO {

    @Autowired
    SessionFactory sessionFactory;

    public CategoryDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public List<Category> getCategoryList() {
        Session session = this.sessionFactory.openSession();
        List<Category> categoryList = (List<Category>) session
                .createQuery("SELECT * FROM lnu.asmoderos.webApplication.model.Category ")
                .uniqueResult();
        session.close();
        return categoryList;
    }
}

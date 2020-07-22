package lnu.asmoderos.webApplication.dao.impl;

import lnu.asmoderos.webApplication.dao.IGroupDAO;
import lnu.asmoderos.webApplication.model.Group;
import lnu.asmoderos.webApplication.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class GroupDAOImpl implements IGroupDAO {
    @Autowired
    SessionFactory sessionFactory;

    public GroupDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Group> getGroupList() {
        Session session = this.sessionFactory.openSession();
        List<Group> groupList = (List<Group>) session
                .createQuery("SELECT * FROM lnu.asmoderos.webApplication.model.Group ")
                .uniqueResult();
        session.close();
        return groupList;
    }
}

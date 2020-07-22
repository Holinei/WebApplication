package lnu.asmoderos.webApplication.services.impl;

import lnu.asmoderos.webApplication.dao.IGroupDAO;
import lnu.asmoderos.webApplication.dao.IUserDAO;
import lnu.asmoderos.webApplication.model.Group;
import lnu.asmoderos.webApplication.services.IGroupService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class GroupServiceImpl implements IGroupService {
    @Autowired
    IGroupDAO groupDAO;

    public GroupServiceImpl(IGroupDAO groupDAO) {
        this.groupDAO = groupDAO;
    }

    @Override
    public List<Group> getGroupList() {
        return groupDAO.getGroupList();
    }
}

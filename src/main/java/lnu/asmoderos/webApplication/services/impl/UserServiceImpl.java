package lnu.asmoderos.webApplication.services.impl;

import lnu.asmoderos.webApplication.dao.IUserDAO;
import lnu.asmoderos.webApplication.model.User;
import lnu.asmoderos.webApplication.services.IUserService;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.List;

public class UserServiceImpl implements IUserService {
    IUserDAO userDAO;

    public UserServiceImpl(IUserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public void registerUser(User user) {
        User userFromDb = this.userDAO.getUserByEmail(user.getEmail());
        if (userFromDb == null) {
            user.setPassword(DigestUtils.md5Hex(user.getPassword()));
            this.userDAO.saveUser(user);
        }
    }

    @Override
    public User getUserById(int id) {
        return this.userDAO.getUserById(id);
    }

    @Override
    public void updateUser(User user) {
        this.userDAO.updateUser(user);
    }

    @Override
    public List<Integer> getSubscribeId(int id) {
        return this.userDAO.getSubscribeId(id);
    }

    @Override
    public User getUserByEmail(String email) {
        return this.userDAO.getUserByEmail(email);
    }

    @Override
    public int getIdByEmail(String email) {
        return this.userDAO.getIdByEmail(email);
    }


}

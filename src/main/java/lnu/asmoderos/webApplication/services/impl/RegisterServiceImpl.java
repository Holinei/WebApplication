package lnu.asmoderos.webApplication.services.impl;

import lnu.asmoderos.webApplication.dao.IUserDAO;
import lnu.asmoderos.webApplication.model.Role;
import lnu.asmoderos.webApplication.model.User;
import lnu.asmoderos.webApplication.services.IRegisterService;

import java.util.HashSet;

public class RegisterServiceImpl implements IRegisterService {
    IUserDAO userDAO;

    public RegisterServiceImpl(IUserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public boolean checkUser(User user) {
        User userFromDb = this.userDAO.getUserByEmail(user.getEmail());

        if (userFromDb == null) {
            return true;
        }
        return false;
    }

}

package lnu.asmoderos.webApplication.services.impl;

import lnu.asmoderos.webApplication.dao.IUserDAO;
import lnu.asmoderos.webApplication.model.Role;
import lnu.asmoderos.webApplication.model.User;
import lnu.asmoderos.webApplication.services.IAuthenticationService;
import org.apache.commons.codec.digest.DigestUtils;

public class AuthenticationServiceImpl implements IAuthenticationService {

    IUserDAO userDAO;

    public AuthenticationServiceImpl(IUserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public boolean authenticateUser(User user) {
        User userFromDb = this.userDAO.getUserByEmail(user.getEmail());

        if (userFromDb != null &&
                userFromDb.getPassword().equals(DigestUtils.md5Hex(user.getPassword()))) {
            return true;
        }

        return false;
    }

    @Override
    public int authenticateRole(User user) {
        User userFromDb = this.userDAO.getUserByEmail(user.getEmail());

        if (userFromDb != null &&
                userFromDb.getRoles().contains(Role.ADMIN)) {
            return 0;  //ADMIN
        }

        if (userFromDb != null &&
                userFromDb.getRoles().contains(Role.STUDENT)) {
            return 1; //STUDENT
        }

        return 2; //OTHER
    }


}

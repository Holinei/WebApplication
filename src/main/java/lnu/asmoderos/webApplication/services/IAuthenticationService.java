package lnu.asmoderos.webApplication.services;

import lnu.asmoderos.webApplication.model.User;

public interface IAuthenticationService {
    boolean authenticateUser(User user);

    int authenticateRole(User user);

}

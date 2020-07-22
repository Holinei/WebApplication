package lnu.asmoderos.webApplication.services;

import lnu.asmoderos.webApplication.model.User;

public interface IRegisterService {
    boolean checkUser(User user);
}

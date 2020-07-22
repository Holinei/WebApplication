package lnu.asmoderos.webApplication.dao;

import lnu.asmoderos.webApplication.model.User;

import java.util.List;

public interface IUserDAO {
    void saveUser(User user);

    User getUserById(int id);

    User getUserByEmail(String email);

    int getIdByEmail(String email);

    void updateUser(User user);

    List<Integer> getSubscribeId(int id);
}

package dbassets.daos;

import dbassets.models.Message;
import dbassets.models.Chat;
import dbassets.models.User;

import java.util.List;

public interface UserDAO {

    List<User> getAll();

    boolean addUser(User user);
    boolean checkUser(User user);
    int getIdByEmail(User user);
    User getUserById(int Id);
    boolean isAdmin(User user);
    void delete(User user);
    List<User> getUserByName(String name);
    List<User> getUserByInterest(String interest);
}
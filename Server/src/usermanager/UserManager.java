package usermanager;

import exceptions.UserExistenceException;
import general.User;

public interface UserManager {
    void insertToRegister(User user) throws UserExistenceException;

    void deleteFromRegister(User user) throws UserExistenceException;

    boolean isRegistered(User user) throws UserExistenceException;

    boolean isExist(User user) throws UserExistenceException;
}

package data;

import javaBean.UserData;

import java.util.List;

public interface UserDataProvider
{
    void addUser(UserData user) throws Exception;

    List<UserData> getAllUsers() throws Exception;

    List<UserData> getUsers(int offset, int count) throws Exception;

    UserData getUserByID(String userID) throws Exception;

    void removeUser(UserData user) throws Exception;

    boolean userExist(UserData user) throws Exception;

    boolean isLoginSuccess(UserData data) throws Exception;

    // void modifyUserData(UserData data) throws Exception;
}

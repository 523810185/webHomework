package data;

import java.util.List;

import com.alibaba.fastjson.JSON;
import javaBean.UserData;
import util.Cache;
import util.FileUtil;

public class JsonUserDataProvider implements UserDataProvider
{
    final String filePath = Cache.getRelativePath("users.json");

    public UserData getUserByID(String userID) throws Exception
    {
        List<UserData> list = getUsersFromFile();
        for(UserData data : list)
        {
            if(userID.equals(data.getUserID()))
            {
                return data;
            }
        }
        // TODO.. need throw
        return null;
    }

    public void addUser(UserData user) throws Exception {
        List<UserData> list = getUsersFromFile();
        if(searchById(list, user) != null)
            throw new Exception("用户已存在");
        list.add(user);
        saveUsersToFile(list);
    }

    public List<UserData> getAllUsers() throws Exception {
        return getUsersFromFile();
    }

    public List<UserData> getUsers(int offset, int count) throws Exception {
        List<UserData> list = getUsersFromFile();
        return list.subList(offset, offset + count - 1);
    }

    public UserData getUser(UserData user) throws Exception {
        return null;
    }

    public void removeUser(UserData user) throws Exception {
        List<UserData> list = getUsersFromFile();
        UserData p = searchById(list, user);
        if(p != null) {
            list.remove(p);
            saveUsersToFile(list);
        }
    }

    public boolean userExist(UserData data) throws Exception
    {
        List<UserData> list = getUsersFromFile();
        if(searchById(list, data) == null)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    public boolean isLoginSuccess(UserData data) throws Exception
    {
        List<UserData> list = getUsersFromFile();
        for(UserData temp : list)
        {
            if(temp.getUserID().equals(data.getUserID()))
            {
                if(temp.getPassword().equals(data.getPassword()))
                {
                    return true;
                }
                else
                {
                    return  false;
                }
            }
        }
        return false;
    }

    private List<UserData> getUsersFromFile()
    {
        String content;
        try {
            content = FileUtil.readAllContent(filePath);
        } catch (Exception e) {
            content = "[]";
        }
        return JSON.parseArray(content, UserData.class);
    }

    private void saveUsersToFile(List<UserData> list) throws Exception
    {
        String s = JSON.toJSONString(list);
        FileUtil.writeAllContent(filePath, s);
    }

    private UserData searchById(List<UserData> list, UserData user)
    {
        for (UserData u : list) {
            if(u.equals(user)) return u;
        }
        return null;
    }
}

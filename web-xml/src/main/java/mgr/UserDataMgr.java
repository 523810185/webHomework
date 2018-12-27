package mgr;

import javaBean.UserData;
import myEnum.eLoginResult;
import myEnum.eRegistResult;

import java.util.HashMap;
import java.util.Map;

public class UserDataMgr {

    private static UserDataMgr m_pInstance;

    private Map<String, String> m_mapUserData;

    public static UserDataMgr getInstance()
    {
        if(m_pInstance == null)
        {
            m_pInstance = new UserDataMgr();
        }
        return  m_pInstance;
    }

    private  UserDataMgr()
    {
        m_mapUserData = new HashMap<String, String>();
    }

    /**
     * 将用户数据保存
     * @param data 数据
     */
    public eRegistResult saveUserData(UserData data)
    {
        String userName = data.getUserName();
        String password = data.getPassword();
        if(m_mapUserData.containsKey(userName))
        {
            // 用户名存在
            return eRegistResult.USERNAME_EXIST;
        }
        else
        {
            m_mapUserData.put(userName, password);
            return  eRegistResult.SUCCESS;
        }
    }

    /**
     * 检验登录结果
     * @param userName 输入的用户名
     * @param password 输入的密码
     * @return  登录结果
     */
    public eLoginResult checkLoginResult(String userName, String password)
    {
        if(m_mapUserData.containsKey(userName) == false)
        {
            return  eLoginResult.FAIL;
        }
        String realPassword = m_mapUserData.get(userName);
        if(realPassword.equals(password))
        {
            return  eLoginResult.SUCCESS;
        }
        else
        {
            return eLoginResult.FAIL;
        }
    }
}

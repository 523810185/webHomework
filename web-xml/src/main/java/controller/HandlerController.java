package controller;

import com.alibaba.fastjson.JSON;
import data.JsonUserDataProvider;
import data.UserDataProvider;
import javaBean.UserData;
import mgr.UserDataMgr;
import myEnum.eLoginResult;
import myEnum.eRegistResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import util.AjaxUtil;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HandlerController {

    @RequestMapping("/newUserRegist")
    @ResponseBody
    public String newUserRegist(@RequestBody UserData data) throws Exception{
        if(provider.userExist(data))
        {
            return "USERNAME_EXIST";
        }
        provider.addUser(data);
        return  "SUCCESS";
//        eRegistResult result = UserDataMgr.getInstance().saveUserData(data);
//        if(result == eRegistResult.SUCCESS)
//        {
//            return "SUCCESS";
//        }
//        else if(result == eRegistResult.USERNAME_EXIST)
//        {
//            return "USERNAME_EXIST";
//        }
//        else
//        {
//            return "UNDEFINE";
//        }
    }

    @RequestMapping(value = "/queryNameByID", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String queryNameByID(@RequestBody UserData data) throws Exception{
        UserData ret = provider.getUserByID(data.getUserID());
        if(ret == null)
        {
            // TODO.. throw
            return "";
        }
        else
        {
            List<UserData> list = new ArrayList<UserData>();
            list.add(ret);
            return AjaxUtil.getJson(toJSON(list));
        }
    }

    @RequestMapping("/checkLoginResult")
    @ResponseBody
    public String checkLoginResult(@RequestBody UserData data) throws Exception{
        return provider.isLoginSuccess(data) ? "SUCCESS" : "FAIL";
//        eLoginResult result = UserDataMgr.getInstance().checkLoginResult(data.getUserName(), data.getPassword());
//        if(result == eLoginResult.SUCCESS)
//        {
//            return "SUCCESS";
//        }
//        else if(result == eLoginResult.FAIL)
//        {
//            return "FAIL";
//        }
//        else
//        {
//            return "UNDEFINE";
//        }
    }

    UserDataProvider provider;

    public HandlerController()
    {
        provider = new JsonUserDataProvider();
    }

    @RequestMapping(value = "/queryStudentList", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String queryStudentList() throws Exception
    {
        return AjaxUtil.getJson(toJSON(provider.getAllUsers()));
    }

    @RequestMapping("/getrange")
    @ResponseBody
    public String getUsers(@RequestParam("offset") int offset, @RequestParam("count") int count) throws Exception
    {
        return AjaxUtil.getJson(toJSON(provider.getUsers(offset, count)));
    }

    @RequestMapping("/remove")
    @ResponseBody
    public String removeUser(@RequestParam("userid") String userId) throws Exception
    {
        UserData user = new UserData();
        user.setUserID(userId);
        provider.removeUser(user);
        return AjaxUtil.getJson(null);
    }

    @ExceptionHandler
    @ResponseBody
    private String handleException(Exception e)
    {
        return AjaxUtil.getJson(10003, e.getMessage(), null);
    }

    private String toJSON(List<UserData> list)
    {
        return JSON.toJSONString(list);
    }

}

package cn.edu.cqupt.nmid.mailbook.service;

import cn.edu.cqupt.nmid.mailbook.web.info.ReturnInfo;
import cn.edu.cqupt.nmid.mailbook.pojo.User;

/****
 * @author:MrWangx
 * @description
 * @Date 2019/3/26 0:12
 *****/
public interface UserService {


    /**
     * 登录
     * @param username
     * @param password
     * @return
     */
    User login(String username, String password);

    /**
     * 注册
     * @param user
     * @return
     */
    boolean signUp(User user);

    /**
     * 是否被注册
     * @param value
     * @param type  字段类型
     * @return
     */
    boolean isSignedUp(String type, String value);

    /**
     * 修改用户信息
     * @param user
     * @return
     * */
    boolean updateUserInfo(User user);


    /**
     * 注册检测
     * @param user
     * @return
     */
    ReturnInfo signUpCheck(User user) throws IllegalAccessException;

}

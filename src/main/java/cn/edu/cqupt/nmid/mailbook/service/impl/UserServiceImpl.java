package cn.edu.cqupt.nmid.mailbook.service.impl;

import cn.edu.cqupt.nmid.mailbook.web.info.ReturnInfo;
import cn.edu.cqupt.nmid.mailbook.dao.mapper.UserMapper;
import cn.edu.cqupt.nmid.mailbook.pojo.User;
import cn.edu.cqupt.nmid.mailbook.pojo.UserExample;
import cn.edu.cqupt.nmid.mailbook.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.util.List;

/****
 * @author:MrWangx
 * @description
 * @Date 2019/3/26 0:20
 *****/
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    /**
     * 登录
     *
     * @param username
     * @param password
     * @return
     */
    @Override
    public User login(String username, String password) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUsernameEqualTo(username).andPasswordEqualTo(password);
        List<User> users = userMapper.selectByExample(userExample);
        if (users.size() == 1) {
            return users.get(0);
        }
        return null;
    }

    /**
     * 注册
     *
     * @param user
     * @return
     */
    @Override
    public boolean signUp(User user) {
        return userMapper.insert(user) > 0;
    }

    /**
     * 是否被注册
     *
     * @param value
     * @param type  字段类型
     * @return
     */
    @Override
    public boolean isSignedUp(String type, String value) {
        UserExample userExample = new UserExample();
        if ("username".equals(type)) {
            userExample.createCriteria().andUsernameEqualTo(value);
        }
        return userMapper.selectByExample(userExample).size() > 0;
    }

    /**
     * 修改用户信息
     *
     * @param user
     * @return
     */
    @Override
    public boolean updateUserInfo(User user) {
        return userMapper.updateByPrimaryKeySelective(user) > 0;
    }


    /**
     * 用户注册检测
     * @param user
     * @return
     * @throws IllegalAccessException
     */
    @Override
    public ReturnInfo signUpCheck(User user) throws IllegalAccessException {
        Field[] fields = User.class.getDeclaredFields();
        for (Field f : fields) {
            f.setAccessible(true);
            if ("username".equals(f.getName()) || "registertime".equals(f.getName()) || "photo".equals(f.getName())) {
                continue;
            }
            if (f.get(user) == null) {
                return ReturnInfo.PARAMS_ERROR;
            }
        }
        if (!user.getPassword().matches("^[0-9A-Za-z_\\.\\?\\!]{6,22}$")
                && !user.getNickname().matches("^[0-9A-Za-z\\u4E00-\\u9FA5]{2,22}$")
                && !user.getTel().matches("^[0-9]{11,11}$")
                && !user.getQq().matches("^[0-9]{6,11}")
                && !(user.getAddress().length() >= 6 && user.getAddress().length() <= 255)) {
            return ReturnInfo.PARAMS_ERROR;
        }
        return ReturnInfo.SUCCESS;
    }
}

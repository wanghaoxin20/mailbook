package cn.edu.cqupt.nmid.mailbook.service.impl;

import cn.edu.cqupt.nmid.mailbook.dao.mapper.UserCollectMapper;
import cn.edu.cqupt.nmid.mailbook.pojo.UserCollect;
import cn.edu.cqupt.nmid.mailbook.pojo.UserCollectExample;
import cn.edu.cqupt.nmid.mailbook.service.UserCollectService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/****
 * @author:MrWangx
 * @description
 * @Date 2019/3/26 15:25
 *****/
@Service
public class UserCollectServiceImpl implements UserCollectService {

    @Resource
    private UserCollectMapper userCollectMapper;

    /**
     * 获取用户收藏列表
     *
     * @param username
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public PageInfo<UserCollect> getUserCollections(String username, int pageNum, int pageSize) {
        UserCollectExample userCollectExample = new UserCollectExample();
        userCollectExample.createCriteria().andUsernameEqualTo(username);
        PageHelper.startPage(pageNum, pageSize);
        List<UserCollect> userCollects = userCollectMapper.selectByExample(userCollectExample);
        return new PageInfo<>(userCollects);
    }

    /**
     * 收藏书籍
     *
     * @param username
     * @param bookid
     * @return
     */
    @Override
    public boolean collectBook(String username, Integer bookid) {
        UserCollect userCollect = new UserCollect();
        userCollect.setUsername(username);
        userCollect.setBookid(bookid);
        return userCollectMapper.insert(userCollect) > 0;
    }
}

package cn.edu.cqupt.nmid.mailbook.service;

import cn.edu.cqupt.nmid.mailbook.pojo.UserCollect;
import com.github.pagehelper.PageInfo;

/****
 * @author:MrWangx
 * @description
 * @Date 2019/3/26 15:23
 *****/
public interface UserCollectService {

    /**
     * 获取用户收藏列表
     * @param username
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo<UserCollect> getUserCollections(String username, int pageNum, int pageSize);

    /**
     * 收藏书籍
     * @param username
     * @param bookid
     * @return
     */
    boolean collectBook(String username, Integer bookid);

}

package cn.edu.cqupt.nmid.mailbook.service;

import cn.edu.cqupt.nmid.mailbook.pojo.BookRecord;
import com.github.pagehelper.PageInfo;

/****
 * @author:MrWangx
 * @description
 * @Date 2019/3/26 22:34
 *****/
public interface BookRecordService {

    /**
     * 获取用户记录
     * @param username
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo<BookRecord> getBookRecordsByUsername(String username, int pageNum, int pageSize);


}

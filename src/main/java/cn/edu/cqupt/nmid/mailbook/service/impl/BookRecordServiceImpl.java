package cn.edu.cqupt.nmid.mailbook.service.impl;

import cn.edu.cqupt.nmid.mailbook.dao.mapper.BookRecordMapper;
import cn.edu.cqupt.nmid.mailbook.pojo.BookRecord;
import cn.edu.cqupt.nmid.mailbook.pojo.BookRecordExample;
import cn.edu.cqupt.nmid.mailbook.service.BookRecordService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/****
 * @author:MrWangx
 * @description
 * @Date 2019/3/26 22:39
 *****/
@Service
public class BookRecordServiceImpl implements BookRecordService {

    @Resource
    private BookRecordMapper bookRecordMapper;

    /**
     * 获取用户记录
     *
     * @param username
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public PageInfo<BookRecord> getBookRecordsByUsername(String username, int pageNum, int pageSize) {
        BookRecordExample bookRecordExample = new BookRecordExample();
        bookRecordExample.createCriteria().andUsernameEqualTo(username);
        PageHelper.startPage(pageNum, pageSize);
        List<BookRecord> bookRecords = bookRecordMapper.selectByExample(bookRecordExample);
        return new PageInfo<>(bookRecords);
    }
}

package cn.edu.cqupt.nmid.mailbook.service.impl;

import cn.edu.cqupt.nmid.mailbook.web.info.BookType;
import cn.edu.cqupt.nmid.mailbook.web.info.IsHandled;
import cn.edu.cqupt.nmid.mailbook.dao.mapper.BookMapper;
import cn.edu.cqupt.nmid.mailbook.dao.mapper.BookRecordMapper;
import cn.edu.cqupt.nmid.mailbook.pojo.Book;
import cn.edu.cqupt.nmid.mailbook.pojo.BookExample;
import cn.edu.cqupt.nmid.mailbook.pojo.BookRecord;
import cn.edu.cqupt.nmid.mailbook.service.BookService;
import cn.edu.cqupt.nmid.mailbook.util.PojoFactory;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/****
 * @author:MrWangx
 * @description
 * @Date 2019/3/26 12:57
 *****/
@Service
public class BookServiceImpl implements BookService {

    @Resource
    private BookMapper bookMapper;

    @Resource
    private BookRecordMapper bookRecordMapper;

    /**
     * 添加书籍
     *
     * @param book
     * @return
     */
    @Override
    public boolean addBook(Book book) {
        return bookMapper.insertSelective(book) > 0;
    }

    /**
     * 设置书籍已完成
     *
     * @param username
     * @param id
     * @return
     */
    @Transactional
    @Override
    public boolean setBookHandled(String username, Integer id) {
        Book book = new Book();
        book.setId(id);
        book.setIshandled(IsHandled.HANDLED.getByteValue());
        if (bookMapper.updateByPrimaryKeySelective(book) <= 0) {
            return false;
        }
        BookRecord bookRecord = PojoFactory.createBookRecord(null, username, book.getId(), null);
        if (bookRecordMapper.insert(bookRecord) <= 0) {
            return false;
        }
        return true;
    }

    /**
     * 根据书籍类型和是否解决分页获取书籍
     * @param bookType  书籍类型    为null查找全部
     * @param ishandled 是否已经解决 为null查找全部
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public PageInfo<Book> getBooks(String username, BookType bookType, IsHandled ishandled, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        BookExample bookExample = new BookExample();
        BookExample.Criteria criteria = bookExample.createCriteria();
        if (null != username) {
            criteria.andUsernameEqualTo(username);
        }
        if (BookType.ALL != bookType) {
            criteria.andTypeEqualTo(bookType.getByteValue());
        }
        if (IsHandled.ALL != ishandled) {
            criteria.andIshandledEqualTo(ishandled.getByteValue());
        }
        List<Book> books = bookMapper.selectByExample(bookExample);
        return new PageInfo<>(books);
    }

    /**
     * 根据书籍id查找
     *
     * @param id
     * @return
     */
    @Override
    public Book getBookById(Integer id) {
        return bookMapper.selectByPrimaryKey(id);
    }
}

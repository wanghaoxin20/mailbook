package cn.edu.cqupt.nmid.mailbook.service;

import cn.edu.cqupt.nmid.mailbook.web.info.BookType;
import cn.edu.cqupt.nmid.mailbook.web.info.IsHandled;
import cn.edu.cqupt.nmid.mailbook.pojo.Book;
import com.github.pagehelper.PageInfo;

/****
 * @author:MrWangx
 * @description
 * @Date 2019/3/26 12:12
 *****/
public interface BookService {

    /**
     * 添加书籍
     * @param book
     * @return
     */
    boolean addBook(Book book);

    /**
     * 设置书籍已完成
     * @param username
     * @param id
     * @return
     */
    boolean setBookHandled(String username, Integer id);

    /**
     * 根据书籍类型和是否解决分页获取书籍
     * @param bookType  书籍类型    为null查找全部
     * @param ishandled 是否已经解决 为null查找全部
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo<Book> getBooks(String username, BookType bookType, IsHandled ishandled, int pageNum, int pageSize);

    /**
     * 根据书籍id查找
     * @param id
     * @return
     */
    Book getBookById(Integer id);

}

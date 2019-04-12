package cn.edu.cqupt.nmid.mailbook;

import cn.edu.cqupt.nmid.mailbook.web.info.BookType;
import cn.edu.cqupt.nmid.mailbook.web.info.IsHandled;
import cn.edu.cqupt.nmid.mailbook.pojo.Book;
import cn.edu.cqupt.nmid.mailbook.pojo.User;
import cn.edu.cqupt.nmid.mailbook.service.BookRecordService;
import cn.edu.cqupt.nmid.mailbook.service.BookService;
import cn.edu.cqupt.nmid.mailbook.service.UserCollectService;
import cn.edu.cqupt.nmid.mailbook.service.UserService;
import cn.edu.cqupt.nmid.mailbook.util.JSONUtil;
import cn.edu.cqupt.nmid.mailbook.util.PojoFactory;
import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageInfo;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MailbookApplicationTests {

    @Resource
    UserService userService;

    @Resource
    BookService bookService;

    @Resource
    UserCollectService userCollectService;

    @Resource
    BookRecordService bookRecordService;

    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @Ignore
    public void MybatisTest() {
        System.out.println(JSON.toJSONString(userService.login("706519033@qq.com", "666666")));
        System.out.println(userService.isSignedUp("username", "706519033@qq.com"));
    }

    @Test
    @Ignore
    public void userRegisterTest() {
        User user = PojoFactory.createUser("706519033@qq.com", "用户1", "123456", "/img/user/default.jpg", "18966666666", "123456789", "重庆邮电大学");
        if (userService.isSignedUp("username", user.getUsername())) {
            System.out.println("username被注册");
        }
    }

    @Test
    @Ignore
    public void addBookTest() {
        for (int i = 1; i <= 20; i++) {
            Book book = new Book();
            String username = i % 2 == 0 ? "706519033@qq.com" : "wang.haoxin@nexuslink.cn";
            book.setId(null);
            book.setUsername(username);
            book.setName("测试书籍添加");
            book.setPublisher("出版社" + i);
            book.setPrice(12.30f);
            book.setDegree(0.7f);
            book.setTime(null);
            book.setDescription("这是一本测试添加的书" + i);
            book.setCover("/img/book/default.jpg");
            book.setIncover("/img/book/default.jpg");
            book.setIshandled(i % 2 == 1 ? Byte.valueOf("0") : Byte.valueOf("1"));
            book.setType((i % 2 == 0 ? BookType.BOOK_PUBLISHED : BookType.BOOK_WANTED).getByteValue());
            System.out.println("测试书籍添加" + i + ":" + bookService.addBook(book));
        }
    }

    @Test
    @Ignore
    public void getBookTest() {
        PageInfo<Book> pageInfo = bookService.getBooks("706519033@qq.com", BookType.ALL, IsHandled.ALL, 1, 10);
        System.out.println("*******************************");
        System.out.println(pageInfo.getTotal());
        pageInfo.getList().forEach(e -> System.out.println(JSON.toJSONString(e)));
        System.out.println("*******************************");
        System.out.println(JSON.toJSONString(bookService.getBookById(1)));
    }

    @Test
    @Ignore
    public void setBookHandledTest() {
        Book book = bookService.getBookById(1);
        if (book == null) {
            System.out.println("书籍不存在");
        } else if (IsHandled.HANDLED.getByteValue().equals(book.getIshandled())) {
            System.out.println("书籍已经已完成");
        } else {
            System.out.println(bookService.setBookHandled(book.getUsername(), book.getId()));
        }
    }

    @Test
    @Ignore
    public void collectBookTest() {
        bookService.getBooks("706519033@qq.com", BookType.ALL, IsHandled.ALL, 1, Integer.MAX_VALUE)
                .getList().forEach(e -> {
            System.out.println("收藏" + e.getId() + ":" + userCollectService.collectBook("wang.haoxin@nexuslink.cn", e.getId()));
        });
    }

    @Test
    @Ignore
    public void getUserCollectionsTest() {
        userCollectService.getUserCollections("wang.haoxin@nexuslink.cn", 1, Integer.MAX_VALUE)
        .getList().forEach(e -> System.out.println(JSONUtil.toJSONString(e, "username")));
    }

    @Test
    @Ignore
    public void getUserRecordTest() {
        bookRecordService.getBookRecordsByUsername("wang.haoxin@nexuslink.cn", 1, 2)
        .getList().forEach(e -> System.out.println(JSONUtil.toJSONString(e)));
    }

    @Test
    @Ignore
    public void regexTest() {
        String s = "hellosdf";
        System.out.println(s.matches("^[0-9A-Za-z_\\.\\?\\!]{6,22}"));
    }

}

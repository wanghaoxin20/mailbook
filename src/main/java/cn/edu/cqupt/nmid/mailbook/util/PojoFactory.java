package cn.edu.cqupt.nmid.mailbook.util;

import cn.edu.cqupt.nmid.mailbook.pojo.BookRecord;
import cn.edu.cqupt.nmid.mailbook.pojo.User;

import java.util.Date;

/****
 * @author:MrWangx
 * @description
 * @Date 2019/3/26 1:01
 *****/
public class PojoFactory {

    /**
     * @param username
     * @param nickname
     * @param password
     * @param photo
     * @param tel
     * @param qq
     * @param address
     * @return
     */
    public static User createUser(String username, String nickname, String password, String photo, String tel, String qq, String address) {
        User user = new User();
        user.setUsername(username);
        user.setNickname(nickname);
        user.setPassword(password);
        user.setPhoto(photo);
        user.setTel(tel);
        user.setQq(qq);
        user.setAddress(address);
        return user;
    }

    /**
     * @param id
     * @param username
     * @param bookid
     * @param time
     * @return
     */
    public static BookRecord createBookRecord(Integer id, String username, Integer bookid, Date time) {
        BookRecord bookRecord = new BookRecord();
        bookRecord.setId(id);
        bookRecord.setUsername(username);
        bookRecord.setBookid(bookid);
        bookRecord.setTime(time);
        return bookRecord;
    }

}

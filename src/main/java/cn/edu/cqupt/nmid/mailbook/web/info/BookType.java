package cn.edu.cqupt.nmid.mailbook.web.info;

/****
 * @author:MrWangx
 * @description 书籍类型信息
 * @Date 2019/3/26 12:09
 *****/
public enum BookType {

    ALL(null),          //不限类型
    BOOK_WANTED(0),     //书籍求购
    BOOK_PUBLISHED(1);  //书籍发布

    private Integer value;

    BookType(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public Byte getByteValue() {
        return Byte.parseByte(value.toString());
    }
}

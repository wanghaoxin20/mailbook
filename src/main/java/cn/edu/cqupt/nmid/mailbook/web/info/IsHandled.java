package cn.edu.cqupt.nmid.mailbook.web.info;

/****
 * @author:MrWangx
 * @description
 * @Date 2019/3/26 14:33
 *****/
public enum IsHandled {

    ALL(null),      //所有
    HANDLED(1),     //已解决
    UNHANDLED(0);   //未解决

    private Integer value;

    IsHandled(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public Byte getByteValue() {
        return Byte.valueOf(value.toString());
    }

}

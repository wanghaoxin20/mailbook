package cn.edu.cqupt.nmid.mailbook.web.info;

import java.util.HashMap;
import java.util.Map;

/****
 * @author:MrWangx
 * @description
 * @Date 2019/3/27 16:05
 *****/
public class Return {

    // 状态码
    private int code;
    // 描述信息
    private String msg;
    // 数据体
    private Map<String,Object> data;

    public Return addData(String key, Object value) {
        this.data.put(key, value);
        return this;
    }

    public Return() {
        this.code = ReturnInfo.SUCCESS.getCode();
        this.msg = ReturnInfo.SUCCESS.getMsg();
        this.data = new HashMap<>();
    }

    public Return setReturnInfo(ReturnInfo returnInfo) {
        this.code = returnInfo.getCode();
        this.msg = returnInfo.getMsg();
        return this;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public Map<String, Object> getData() {
        return data;
    }

}

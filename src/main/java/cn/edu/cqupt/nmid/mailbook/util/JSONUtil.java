package cn.edu.cqupt.nmid.mailbook.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.PropertyFilter;

import java.util.Arrays;
import java.util.List;

/****
 * @author:MrWangx
 * @description
 * @Date 2019/3/26 15:44
 *****/
public class JSONUtil {

    /**
     * 忽略字段输出json
     * @param object
     * @param properties 忽略的属性
     * @return
     */
    public static String toJSONString(Object object, String... properties) {
        List<String> props = Arrays.asList(properties);
        return JSON.toJSONString(object, (PropertyFilter) (o, name, value) -> props.contains(name) ? false : true);
    }

}

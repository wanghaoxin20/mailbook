package cn.edu.cqupt.nmid.mailbook.web.info;

/****
 * @author:MrWangx
 * @description
 * @Date 2019/3/27 16:12
 *****/
public enum ReturnInfo {

    SUCCESS(200, "success"),                                        //操作成功
    FAILED(202, "failed"),                                          //失败
    NOT_LOGIN(300, "not login"),                                    //未登录
    WRONG_USERNAME_OR_PASSWORD(301, "wrong username or password"),  //账号或者密码错误
    PARAMS_ERROR(400, "params error"),                              //参数错误
    RESOURCES_ALREADY_EXISTS(401, "resources already exists"),      //资源已存在
    RESOURCES_NOT_EXISTS(404, "resources not exists"),              //资源不存在
    SERVER_INTERNAL_ERROR(500, "server internal error"),            //服务器内部错误
    VERIFICATION_TIMEOUT(600, "verification timeout"),          //验证码过时
    ;

    private int code;
    private String msg;

    ReturnInfo(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}

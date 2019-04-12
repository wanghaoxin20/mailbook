package cn.edu.cqupt.nmid.mailbook.web.controller;

import cn.edu.cqupt.nmid.mailbook.util.JSONUtil;
import cn.edu.cqupt.nmid.mailbook.web.info.Return;
import cn.edu.cqupt.nmid.mailbook.web.info.ReturnInfo;
import cn.edu.cqupt.nmid.mailbook.pojo.User;
import cn.edu.cqupt.nmid.mailbook.service.UserService;
import cn.edu.cqupt.nmid.mailbook.service.mail.MailService;
import cn.edu.cqupt.nmid.mailbook.util.CodeCheck;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;

/****
 * @author:MrWangx
 * @description
 * @Date 2019/3/27 21:01
 *****/
@RestController
public class SignController {

    private Logger logger = LoggerFactory.getLogger(SignController.class);

    @Resource
    private UserService userService;

    @Resource
    private MailService mailService;

    /**
     * 发送验证码
     *
     * @param username
     * @param session
     * @return
     */
    @PostMapping("/verificationCode/send")
    public Return verificationCheck(@RequestParam String username, HttpSession session) {
        Return r = new Return();
        try {
            // TODO: 2019/3/27 2分钟只能发一次验证码
            if (session.getAttribute("verificationTime") != null && (System.currentTimeMillis() - (long) session.getAttribute("verificationTime")) <= 120 * 1000) {
                r.setReturnInfo(ReturnInfo.FAILED);
            } else {
                // 邮箱参数不符合规范  正则验证
                if (null == username) {
                    r.setReturnInfo(ReturnInfo.PARAMS_ERROR);
                } else if (username.length() <= 60 && !username.matches("^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*\\.[a-zA-Z0-9]{2,6}$")) {
                    r.setReturnInfo(ReturnInfo.PARAMS_ERROR);
                } else if (userService.isSignedUp("username", username)) { //邮箱被注册
                    r.setReturnInfo(ReturnInfo.RESOURCES_ALREADY_EXISTS);
                } else {
                    int verificationCode = CodeCheck.getRandom(1000, 9999);
                    String content = "<h1>您好,您的验证码为" + verificationCode + "</h1>";
                    mailService.sendHtmlMail("来自邮书的验证码", content, username);
                    session.setAttribute("verificationUsername", username);
                    session.setAttribute("verificationTime", System.currentTimeMillis());
                    session.setAttribute("verificationCode", verificationCode);
                }
            }
        } catch (MessagingException e) {
            logger.error("发送验证码邮件[" + username + "]错误", e);
            r.setReturnInfo(ReturnInfo.SERVER_INTERNAL_ERROR);
        } catch (Exception e) {
            logger.error("发送验证码错误", e);
            r.setReturnInfo(ReturnInfo.SERVER_INTERNAL_ERROR);
        }
        return r;
    }


    /**
     * 验证验证码
     *
     * @param verificationCode 验证码
     * @param session
     * @return
     */
    @PostMapping("/verificationCode/check")
    public Return verificationCheck(@RequestParam int verificationCode, HttpSession session) {
        Return r = new Return();
        try {
            if (null == session.getAttribute("verificationCode") || null == session.getAttribute("verificationTime")) {
                r.setReturnInfo(ReturnInfo.RESOURCES_NOT_EXISTS);
            } else if (System.currentTimeMillis() - (long) session.getAttribute("verificationTime") > 120 * 1000) {
                r.setReturnInfo(ReturnInfo.VERIFICATION_TIMEOUT);
            } else {
                session.removeAttribute("verificationCode");
                session.removeAttribute("verificationTime");
                session.setAttribute("verificationCheckTime", System.currentTimeMillis());
            }
        } catch (Exception e) {
            logger.error("验证验证码错误", e);
            r.setReturnInfo(ReturnInfo.SERVER_INTERNAL_ERROR);
        }
        return r;
    }


    /**
     * 添加用户
     *
     * @param user
     * @param session
     * @return
     */
    @PostMapping("/signUp")
    public Return singUp(@RequestBody User user, HttpSession session) {
        Return r = new Return();
        try {
            logger.info(JSONUtil.toJSONString(user));
            if (session.getAttribute("verificationCheckTime") == null) {
                r.setReturnInfo(ReturnInfo.FAILED);
            } else if (System.currentTimeMillis() - (long) session.getAttribute("verificationCheckTime") > 60 * 1000) { //输入验证码后一分钟内注册
                r.setReturnInfo(ReturnInfo.VERIFICATION_TIMEOUT);
            } else {
                // TODO: 2019/3/27 注册用户
                ReturnInfo rinfo = userService.signUpCheck(user);
                if (rinfo == ReturnInfo.SUCCESS) {
                    user.setUsername(session.getAttribute("verificationUsername").toString());
                    user.setPhoto("/img/user/default.jpg");
                    if (!userService.signUp(user)) {
                        r.setReturnInfo(ReturnInfo.FAILED);
                    } else {
                        session.removeAttribute("verificationUsername");
                        session.removeAttribute("verificationCheckTime");
                    }
                } else {
                    r.setReturnInfo(rinfo);
                }
            }
        } catch (Exception e) {
            logger.error("添加用户错误", e);
            r.setReturnInfo(ReturnInfo.SERVER_INTERNAL_ERROR);
        }
        return r;
    }

    /**
     * 用户登录
     * @return
     */
    @PostMapping("/signIn")
    public Return signIn() {
        Return r = new Return();
        return r;
    }
}

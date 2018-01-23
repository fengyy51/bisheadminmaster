package com.binwang.controller;

import com.binwang.dao.UserDao;
import com.binwang.util.MD5Util;
import com.binwang.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by yy on 17/4/28.
 */
@Controller
public class LoginController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    @Resource
    private UserDao userDao;
//    @Value("${login.username}")
//    private String localUserName;
//
//    @Value("${login.password}")
//    private String localPassword;

    @Value("${fd-binwang.isMaintaining}")
    private Boolean isMaintaining;
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public Object register(@RequestParam("username") String username,
                           @RequestParam("password") String password) {
        try {
            userDao.register(username, MD5Util.getMD5(password));
            return ResponseUtil.okBoolean(true);
        } catch (Exception e) {
            return ResponseUtil.errorJSON("注册失败，请重试");
        }
    }

    @RequestMapping(value = "/is-username-ok", method = RequestMethod.POST)
    @ResponseBody
    public Object isUserNameOk(@RequestParam("username") String username) {
        try {
            int res = userDao.isUserName(username);
            if (res > 0)
                return ResponseUtil.okBoolean(false);
            else
                return ResponseUtil.okBoolean(true);
        } catch (Exception e) {
            return ResponseUtil.errorJSON("查询失败");
        }
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Object login(@RequestParam("username") String username,
                        @RequestParam("password") String password) {
        try {
            String _password = userDao.login(username);
            if (MD5Util.getMD5(password).equals(_password)) {
                Map<String, Object> m = new HashMap<>();
                m.put("result", true);
                return ResponseUtil.okJSON(m);
            } else
                return ResponseUtil.okBoolean(false);
        } catch (Exception e) {
            return ResponseUtil.errorJSON("登录失败，请重试");
        }
    }

    @RequestMapping(value = "/update-pwd", method = RequestMethod.POST)
    @ResponseBody
    public Object updatePwd(@RequestParam("username") String username,
                        @RequestParam("password") String password) {
        try {
            int res = userDao.updatePwd(username,MD5Util.getMD5(password));
            if (res > 0)
                return ResponseUtil.okBoolean(true);
            else
                return ResponseUtil.okBoolean(false);
        } catch (Exception e) {
            return ResponseUtil.errorJSON("修改密码失败，请重试");
        }
    }
//    @RequestMapping(value = "/login", method = RequestMethod.POST)
//    @ResponseBody
//    public Object login(@RequestParam(value = "userName") String userName,
//                        @RequestParam(value = "password") String password) {
//        if (Objects.equals(userName.trim(), localUserName) && Objects.equals(password.trim(), localPassword)) {
//            Map<String, Boolean> m = new HashMap<>();
//            m.put("result", true);
//            return ResponseUtil.okJSON(m);
//        } else
//            return ResponseUtil.errorJSON("不正确，请重新输入");
//    }
//

    @RequestMapping(value = "/is-maintaining", method = RequestMethod.GET)
    @ResponseBody
    public Object isMaintaining() {
        return ResponseUtil.okJSON(isMaintaining);
    }
}

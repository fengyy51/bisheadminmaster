package com.binwang.controller;

import com.binwang.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by yy on 17/4/28.
 */
@Controller
public class LoginController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    @Value("${login.username}")
    private String localUserName;

    @Value("${login.password}")
    private String localPassword;

    @Value("${fd-binwang.isMaintaining}")
    private Boolean isMaintaining;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Object login(@RequestParam(value = "userName") String userName,
                        @RequestParam(value = "password") String password) {
        if (Objects.equals(userName.trim(), localUserName) && Objects.equals(password.trim(), localPassword)) {
            Map<String, Boolean> m = new HashMap<>();
            m.put("result", true);
            return ResponseUtil.okJSON(m);
        } else
            return ResponseUtil.errorJSON("不正确，请重新输入");
    }


    @RequestMapping(value = "/is-maintaining", method = RequestMethod.GET)
    @ResponseBody
    public Object isMaintaining() {
        return ResponseUtil.okJSON(isMaintaining);
    }
}

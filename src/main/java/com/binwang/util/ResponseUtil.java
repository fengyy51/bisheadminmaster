package com.binwang.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.HashMap;

/**
 * Created by owen on 17/4/28.
 */
public class ResponseUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(ResponseUtil.class);

    public static Object errorJSON(String message) {
        HashMap<String, Object> returnMap = new HashMap<>();
        HashMap<String, String> map = new HashMap<>();
        map.put("msg", message);
        returnMap.put("error", map);
        returnMap.put("code", 500);
        return returnMap;
    }

    public static Object okJSON(Object object) {
        HashMap<String, Object> returnMap = new HashMap<>();
        returnMap.put("code", 200);
        returnMap.put("data", object);
        return returnMap;
    }

    public static void responseText(HttpServletResponse response, String json) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/text;charset=UTF-8");
        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.write(json);
        } catch (Exception e) {
            LOGGER.error("responseJSON : 网络异常, json=" + json, e);
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }
}

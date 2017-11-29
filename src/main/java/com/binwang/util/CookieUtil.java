package com.binwang.util;

import org.springframework.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * Created by owen on 17/4/28.
 */
public class CookieUtil {
    public static Cookie getCookie(HttpServletRequest request, String name) {
        Cookie cookies[] = request.getCookies();
        if (cookies == null || name == null || name.length() == 0) {
            return null;
        }
        for (int i = 0; i < cookies.length; i++) {
            if (name.equals(cookies[i].getName())) {
                return cookies[i];
            }
        }
        return null;
    }


    public static void updateCookie(HttpServletResponse response, Cookie cookie, String value) {
        if (cookie != null) {
            cookie.setValue(value);
            response.addCookie(cookie);
        }
    }


    public static void deleteCookie(HttpServletRequest request, HttpServletResponse response,
                                    Cookie cookie) {
        if (cookie != null) {
            cookie.setPath("/");
            cookie.setValue("");
            cookie.setMaxAge(0);
            response.addCookie(cookie);
//            Date date = DateUtil.getDateAfter(new Date(),cookie.getMaxAge()/(24 * 60 * 60));
//            response.addHeader("Set-Cookie", cookie.getName()+"="+cookie.getValue()+";"+"Path="+cookie.getPath()+";Expires="+date+";HttpOnly");
        }
    }


    public static void deleteCookie(HttpServletRequest request, HttpServletResponse response,
                                    String name, String domain) {
        Cookie cookie = new Cookie(name, null);
        if (!StringUtils.isEmpty(domain))
            cookie.setDomain(domain);
        deleteCookie(request, response, cookie);
    }

    public static void setCookie(
            HttpServletResponse response, String name, String value) {
        setCookie(response, name, value, -1, false);
    }

    public static void setCookie(HttpServletResponse response, String name, String value, int maxAge, boolean isHttpOnly) {
        Cookie cookie = new Cookie(name, value == null ? "" : value);
        cookie.setMaxAge(maxAge);
        cookie.setPath("/");
        cookie.setHttpOnly(isHttpOnly);
//        cookie.setDomain("huzhu.liuhongnan.com");
//        cookie.setSecure(false);
//        Date date = DateUtil.getDateAfter(new Date(),maxAge/(24 * 60 * 60));
//        response.addHeader("Set-Cookie", cookie.getName()+"="+cookie.getValue()+";"+"Path="+cookie.getPath()+";Expires="+date+";HttpOnly");
        response.addCookie(cookie);
    }


    public static void setCookieHttpOnly(HttpServletResponse response, String name, String value, int maxAge) {
        Date date = DateUtil.getDateAfter(new Date(), maxAge / (24 * 60 * 60));
        response.addHeader("Set-Cookie", name + "=" + value + ";" + "Path=huzhu.liuhongnan.com;Domain=123.57.37.50:8081" + (maxAge < 0 ? "" : (";Expires=" + date)) + ";HttpOnly");
    }


    public static void setCookieHttpOnly(HttpServletResponse response, String name, String value, String domain, int maxAge) {
        Date date = DateUtil.getDateAfter(new Date(), maxAge / (24 * 60 * 60));
        response.addHeader("Set-Cookie", name + "=" + value + ";" + "Path=/;Domain=" + domain + (maxAge < 0 ? "" : (";Expires=" + date)) + ";HttpOnly");
    }
}

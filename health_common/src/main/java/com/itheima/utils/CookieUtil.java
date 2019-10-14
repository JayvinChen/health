package com.itheima.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class CookieUtil {
    /**
     * @Description: 该方法是创建并设置cookie
     * @param:
     * @return: 
     * @auther: Jayvin
     * @date: 2019/8/8 19:22
     */
    public static Cookie creatCookie(String name, String value,int time,String path) {
        Cookie cookie = new Cookie(name, value);
        cookie.setMaxAge(time);
        cookie.setPath(path);
        return cookie;
    }

    // 根据name获取cookie的值
    public static String getCookieValue(HttpServletRequest request,String name) {
        Cookie[] cookies = request.getCookies();
        String value = null;
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if (name.equals(cookie.getName())){
                    value = cookie.getValue();
                }
            }
        }
        return value;
    }
}

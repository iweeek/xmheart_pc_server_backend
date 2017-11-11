package com.xmheart.service.impl;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WebUtils {
    /** 
     * 设置cookie到response中 
     * @param response 
     * @param name  cookie名字 
     * @param value cookie值 
     * @param maxAge cookie生命周期， 以分钟为单位 
     */  
    public static void addCookie(HttpServletResponse response, String name, String value, int maxAge){  
        Cookie cookie = new Cookie(name, value);  
        cookie.setPath("/");  
        if(maxAge>0){  
            cookie.setMaxAge(maxAge*60);  
        }  
        response.addCookie(cookie);  
    }  
      
    /** 
     * 根据名字从request中获取cookie的值 
     * @param request 
     * @param name cookie名字 
     * @return 
     */  
    public static String getCookieByName(HttpServletRequest request, String name){  
        Cookie[] cookies = request.getCookies();//获取cookie数组  
        for(Cookie cookie : cookies){  
            if(cookie.getName().equals(name)){  
                return cookie.getValue();  
            }  
        }  
        return null;  
    }  
}

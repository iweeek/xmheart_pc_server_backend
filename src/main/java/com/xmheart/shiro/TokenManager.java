package com.xmheart.shiro;

import org.apache.shiro.SecurityUtils;

import com.xmheart.model.XPWUser;


public class TokenManager {

    /**
     * 获取当前登录的用户User对象
     * @return
     */
    public static XPWUser getToken(){
        XPWUser token = (XPWUser)SecurityUtils.getSubject().getPrincipal();
        return token ;
    }
    
    /**
     * 登录
     * @param user
     * @param rememberMe
     * @return
     */
    public static XPWUser login(XPWUser user,Boolean rememberMe){
        MyUsernamePasswordToken token = new MyUsernamePasswordToken(user.getUsername(), user.getPassword());
        token.setRememberMe(rememberMe);
        SecurityUtils.getSubject().login(token);
        return getToken();
    }
    
}

package com.xmheart.shiro;

import org.apache.shiro.authc.UsernamePasswordToken;

public class MyUsernamePasswordToken extends UsernamePasswordToken{
    
    private String salt;

    public MyUsernamePasswordToken(String username, String password) {
        super(username, password);
        // TODO Auto-generated constructor stub
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
}

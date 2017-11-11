package com.xmheart.service;

import java.awt.image.BufferedImage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xmheart.model.XPWCaptcha;

public interface CaptchaService {

    /**
     * 生成一个验证码并保存到数据库中 
     * @param response
     * @return
     */
    BufferedImage genCaptcha(HttpServletResponse response);
    
    /** 
     * 检查验证码是否正确，并把结果写入数据库中 
     */ 
    Boolean verifyCaptcha(HttpServletRequest request, String captcha);
    
    /** 
     * 判断验证码是否已经通过
     */ 
    Boolean verifyCaptchaIsPassed(HttpServletRequest request, XPWCaptcha captcha);
    
}

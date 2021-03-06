package com.xmheart.service.impl;

import java.awt.image.BufferedImage;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmheart.mapper.XPWCaptchaMapper;
import com.xmheart.model.XPWCaptcha;
import com.xmheart.model.XPWCaptchaExample;
import com.xmheart.service.CaptchaService;
import com.xmheart.util.CaptchaUtil;

@Service
public class CaptchaServiceImpl implements CaptchaService{
    
    @Autowired
    XPWCaptchaMapper captchaMapper;
    
    @Override
    public BufferedImage genCaptcha(HttpServletResponse response) {
        // cookie, 默认30分钟
        String cookieId = UUID.randomUUID().toString().replace("-", "").toUpperCase(); 
        WebUtils.addCookie(response, "XPWCookie", cookieId, 30);  
        
        //生成一个校验码  
        String genCaptcha = CaptchaUtil.genCaptcha(5);  
        XPWCaptcha captcha = new XPWCaptcha();
        captcha.setCookie(cookieId);
        captcha.setCaptcha(genCaptcha);
        captcha.setExpired(30);
        captcha.setIsPassed((byte) 0);
        
        captchaMapper.insert(captcha);
         
        //把校验码转为图像  
        BufferedImage image = CaptchaUtil.genCaptchaImg(genCaptcha);  
        return image;  
    }
    
    @Override  
    public Boolean verifyCaptcha(HttpServletRequest request, String captcha) {  
          
        if(!StringUtils.isEmpty(captcha)){  
            String cookieId = WebUtils.getCookieByName(request, "XPWCookie");  
            // 从数据库里取出cookie判断是否匹配
            XPWCaptchaExample example = new XPWCaptchaExample();
            example.createCriteria().andCookieEqualTo(cookieId);
            List<XPWCaptcha> list = captchaMapper.selectByExample(example);
            if (list.size() > 0) {
                XPWCaptcha capt = list.get(0);
                if (captcha.toUpperCase().equals(capt.getCaptcha())) {
                    capt.setIsPassed((byte) 1);
                    captchaMapper.updateByPrimaryKey(capt);
                    return true;
                } else {
                    capt.setIsPassed((byte) 0);
                    captchaMapper.updateByPrimaryKey(capt);
                    return false;
                }
            } else {
                return false;
            }
        }  
        return false;
    }

    @Override
    public Boolean verifyCaptchaIsPassed(HttpServletRequest request, XPWCaptcha captcha) {
        String cookieId = WebUtils.getCookieByName(request, "XPWCookie");  
        // 从数据库里取出cookie判断是否匹配
        XPWCaptchaExample example = new XPWCaptchaExample();
        example.createCriteria().andCookieEqualTo(cookieId);
        List<XPWCaptcha> list = captchaMapper.selectByExample(example);
        if (list.size() > 0) {
            XPWCaptcha capt = list.get(0);
            if (capt.getIsPassed() == 1) {
                captcha.setId(capt.getId());;
                // 删除这个验证码记录
//                captchaMapper.deleteByPrimaryKey(capt.getId());
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }  
}

package com.xmheart.backend.controller;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xmheart.mapper.XPWCaptchaMapper;
import com.xmheart.model.XPWCaptcha;
import com.xmheart.service.CaptchaService;
import com.xmheart.service.TokenService;
import com.xmheart.util.ResponseBody;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * token资源的restful控制器
 * 
 * @author x1ny
 * @date 2017年5月22日
 */
@Api(tags = "Token相关接口")
@RestController
public class TokenController {

    // private int status = 0;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private CaptchaService captchaService;
    
    @Autowired
    XPWCaptchaMapper captchaMapper;

    /**
     * 验证username和password，创建token
     * 
     * @param username
     *            用户名
     * @param password
     *            密码
     * @param expiredHour
     *            过期时间(小时)
     */
    @SuppressWarnings("rawtypes")
    @ApiOperation(value = "创建token", notes = "验证用户名与密码，为用户创建一个用于鉴权的Token")
    @RequestMapping(value = { "/tokens" }, method = RequestMethod.POST)
    public ResponseEntity<?> create(@ApiParam("用户名") @RequestParam String username,
            @ApiParam("密码") @RequestParam String password, @ApiParam("盐值") @RequestParam String salt,
            @ApiParam("有效时间(单位:小时)，不填则默认为1") @RequestParam(required = false, defaultValue = "1") Integer expiredHour,
            HttpServletRequest request) {
//        XPWCaptcha captcha = new XPWCaptcha();
//        Boolean isPassed = captchaService.verifyCaptchaIsPassed(request, captcha);
//        if (isPassed) {
            ResponseBody body = new ResponseBody();
            int status = tokenService.create(username, password, salt, expiredHour, body);
            if (status == 0) {
                // 删除本次的验证码
//                if (captcha != null) {
//                    captchaMapper.deleteByPrimaryKey(captcha.getId());
//                }
                return ResponseEntity.status(HttpServletResponse.SC_OK).body(body);
            } else {
//                return ResponseEntity.status(HttpServletResponse.SC_NOT_FOUND).body(isPassed);
                return ResponseEntity.status(HttpServletResponse.SC_NOT_FOUND).body(null);
            }
//        } else {
//            return ResponseEntity.status(HttpServletResponse.SC_OK).body(isPassed);
//        }
    }

    @SuppressWarnings("rawtypes")
    @ApiOperation(value = "创建验证码", notes = "创建验证码，将创建好的验证码存入数据库中")
    @RequestMapping(value = { "/captcha" }, method = RequestMethod.GET)
    public void create(HttpServletResponse response) {

        try {
            // 把校验码转为图像
            BufferedImage image = captchaService.genCaptcha(response);

            response.setContentType("image/jpeg");
            System.out.println(response);
            // 输出图像
            ServletOutputStream outStream = response.getOutputStream();
            ImageIO.write(image, "jpeg", outStream);
            response.flushBuffer();
            outStream.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 检查验证码是否正确
     * 
     * @param request
     * @param map
     * @return
     */
    @RequestMapping(value = "/verifyCaptcha", method = RequestMethod.POST)
    public ResponseEntity<?> verifyCaptcha(HttpServletRequest request, @ApiParam("验证码") @RequestParam String captcha) {        Cookie[] cookies = request.getCookies();
        Boolean verifyCaptcha = captchaService.verifyCaptcha(request, captcha);
        return ResponseEntity.status(HttpServletResponse.SC_OK).body(verifyCaptcha);
    }

}

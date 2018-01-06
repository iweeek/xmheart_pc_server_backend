package com.xmheart.service.impl;

import java.awt.image.BufferedImage;
import java.security.acl.Permission;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.time.DateUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Service;

import com.xmheart.mapper.XPWPrivMapper;
import com.xmheart.mapper.XPWUserMapper;
import com.xmheart.mapper.XPWVisitLogMapper;
import com.xmheart.model.XPWPriv;
import com.xmheart.model.XPWUser;
import com.xmheart.model.XPWUserExample;
import com.xmheart.model.XPWVisitLog;
import com.xmheart.service.PermissionService;
import com.xmheart.service.TokenService;
import com.xmheart.util.CaptchaUtil;
import com.xmheart.util.MessageDigestUtil;
import com.xmheart.util.ResponseBody;

import io.jsonwebtoken.CompressionCodecs;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
* TokenService 实现类.
* 
* @author x1ny
* @date 2017年5月22日
*/
@Service
public class PermissionServiceImpl implements PermissionService {
	
	private static final Logger logger = LoggerFactory.getLogger(PermissionServiceImpl.class);
	
	String logMsg = "";
	
   @Autowired
    private XPWPrivMapper privMapper;

    @Override
    public int create(XPWPriv role) {
        return 0;
    }
    
    @Override
    public int update(XPWPriv role) {
        return 0;
    }
    
    @Override
    public int delete(Long id) {
        return 0;
    }
    
    @Override
    public XPWPriv read(Long id) {
        return privMapper.selectByPrimaryKey(id);
    }
    
    @Override
    public List<XPWPriv> index() {
        return null;
    }
   
	
}

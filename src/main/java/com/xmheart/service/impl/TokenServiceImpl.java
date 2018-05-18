package com.xmheart.service.impl;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.time.DateUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.xmheart.mapper.XPWUserMapper;
import com.xmheart.mapper.XPWVisitLogMapper;
import com.xmheart.model.XPWUser;
import com.xmheart.model.XPWUserExample;
import com.xmheart.model.XPWVisitLog;
import com.xmheart.service.TokenService;
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
public class TokenServiceImpl implements TokenService {
	
	private static final Logger logger = LoggerFactory.getLogger(TokenServiceImpl.class);
	
	String logMsg = "";
	
   @Autowired
    private XPWUserMapper userMapper;
   
   @Autowired 
   XPWVisitLogMapper visitLogMapper;

	/**
	 * jwt加密、解密的密匙
	 */
	private final String KEY;
	
	@Autowired
	public TokenServiceImpl(@Value("${jwt.key}") String key) {		
		KEY = key;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public int create(String username, String password, String salt, int expiredHour, ResponseBody body, HttpSession httpSession,
            HttpServletRequest request) {
        XPWUser user;
        XPWUserExample example = new XPWUserExample();
        example.createCriteria().andUsernameEqualTo(username);
        List<XPWUser> list = userMapper.selectByExample(example);
        if (list.size() == 0) {
            return -1;
        } else {
            user = list.get(0);
        }
        
        String result = MessageDigestUtil.Md5(user.getPassword() + salt);
        if (!result.equals(password)) {
            return -1;
        }
        
        //创建token
        long userId = user.getId();
        Date expiredDate = DateUtils.addHours(new Date(), expiredHour);
        
        // 统计日志
        String userAgent = request.getHeader("User-Agent");
        String requestURI = request.getRequestURI();
        XPWVisitLog visitLog = new XPWVisitLog();
        visitLog.setAccessTime(new DateTime().toDate());
        visitLog.setUri(requestURI);
        visitLog.setUserAgent(userAgent);
        visitLog.setUserId(Long.valueOf(userId));
        try {
            visitLogMapper.insert(visitLog);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // 密码清零
        user.setPassword("");
        httpSession.setAttribute("user", user);
//        getSubject().getSession().setAttribute("user", user);
        body.obj1 = user;
        body.obj2 = Jwts.builder()
                .setSubject(String.valueOf(userId))
                .setExpiration(expiredDate)
                .compressWith(CompressionCodecs.DEFLATE)
                .signWith(SignatureAlgorithm.HS512, KEY)
                .compact();
        
        return 0;
    }

    @Override
    public Subject getSubject() {
         Subject subject = SecurityUtils.getSubject();
         return subject;
    }
}

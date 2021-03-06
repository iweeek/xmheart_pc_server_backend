package com.xmheart.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.joda.time.DateTime;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.xmheart.mapper.XPWPrivMapper;
import com.xmheart.mapper.XPWVisitLogMapper;
import com.xmheart.model.XPWPriv;
import com.xmheart.model.XPWPrivExample;
import com.xmheart.model.XPWRole;
import com.xmheart.model.XPWUser;
import com.xmheart.model.XPWVisitLog;
import com.xmheart.service.RoleService;
import com.xmheart.service.UserService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

/**
 * token拦截器,拦截带有token的请求，对其进行认证.
 * 
 * @author x1ny
 * @date 2017年5月22日
 */
public class TokenInterceptor extends HandlerInterceptorAdapter {

	private static final Logger logger = LogManager.getLogger(TokenInterceptor.class);

	String logMsg = "";

	@Autowired
	UserService userService;
    
    @Autowired
    RoleService roleService;
    
    @Autowired
    XPWPrivMapper privMapper;
    
    @Autowired
    XPWVisitLogMapper visitLogMapper;

	/**
	 * jwt加密、解密的密匙
	 */
	private final String KEY;

	@Autowired
	public TokenInterceptor(@Value("${jwt.key}") String key) {
		KEY = key;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.web.servlet.handler.HandlerInterceptorAdapter#preHandle(
	 * javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse, java.lang.Object)
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if (request.getMethod().equals("OPTIONS")) {

		} else {
			String token = request.getHeader("Authorization");
			List<XPWPriv> privs = null;
//			System.out.println("token: " + token);
			System.out.println("request.getRequestURI():" + request.getRequestURI() + " request.getMethod(): " + request.getMethod());
			if (token != null) {
				Claims claims = Jwts.parser().setSigningKey(KEY).parseClaimsJws(token).getBody();
				String userId = claims.getSubject();
				String tableName = "";
				boolean isGranted = false;
				
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
                
				// 判断权限
				XPWUser user = userService.read(Long.valueOf(userId));
				XPWRole role = roleService.read(Long.valueOf(user.getRoleIds()));
				String privIds = role.getPrivIds();
				XPWPrivExample example = new XPWPrivExample();
		        if (privIds != null) {
		            String[] split = privIds.split(",");
		          
		            StringBuilder sb = new StringBuilder();
		            for (String item : split) {
		                long pId= Long.parseLong(item);
		                example.or().andIdEqualTo(pId);
		            }
		            privs = privMapper.selectByExample(example);
		            for (int i = 0; i< privs.size(); i++) {
//		                isGranted = privs.get(i).getTableName().contains(tableName);
		                tableName = privs.get(i).getTableName();
		                String[] split2 = tableName.split(",");
		                for (String item : split2) {
		                    isGranted = request.getRequestURI().contains(item);
		                    if (isGranted) {
		                        break;
		                    }
		                }
		                
		                if (isGranted) {
		                    break;
		                }
		            }
		            // 图片和视频
		            if (request.getRequestURI().contains("images_storage") ||
		                    request.getRequestURI().contains("videos_storage")) {
		                isGranted = true;
		            }
		            // column需要单独放开
		            if (request.getRequestURI().contains("column")) {
                        isGranted = true;
                    }
//		            if (!isGranted) {
//		                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
//		                return false;
//		            }
		        }
		        
			} else {
				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				return false;
			}
		}

		return super.preHandle(request, response, handler);
	}

}

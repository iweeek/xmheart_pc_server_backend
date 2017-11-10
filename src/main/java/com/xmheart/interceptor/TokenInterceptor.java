package com.xmheart.interceptor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.xmheart.model.XPWUser;
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
			System.out.println("token: " + token);
			System.out.println("request.getRequestURI():" + request.getRequestURI() + "   request.getMethod(): " + request.getMethod());
			if (token != null) {
				Claims claims = Jwts.parser().setSigningKey(KEY).parseClaimsJws(token).getBody();
				String userId = claims.getSubject();
				XPWUser user = userService.read(Long.valueOf(userId));
				if (request.getRequestURI().equals("/users")) {
					if (user.getUserType() == 2) {
						Set<String> keySet = request.getParameterMap().keySet();
						if (keySet.contains("roleId")) {
							response.setStatus(HttpServletResponse.SC_FORBIDDEN);
							return false;
						}
					} else {
						return true;
					}
				}
			} else {
				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				return false;
			}
		}

		return super.preHandle(request, response, handler);
	}

}

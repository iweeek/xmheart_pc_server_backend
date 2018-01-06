package com.xmheart.shiro;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.xmheart.model.XPWPriv;
import com.xmheart.model.XPWRole;
import com.xmheart.model.XPWUser;
import com.xmheart.service.ArticleService;
import com.xmheart.service.PermissionService;
import com.xmheart.service.RoleService;
import com.xmheart.service.UserService;
import com.xmheart.util.MessageDigestUtil;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.CompressionCodecs;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
* 根据Token进行Shiro认证的Realm类
* 
* @author x1ny
* @date 2017年5月22日
*/
public class TokenRealm extends AuthorizingRealm{

	/**
	 * jwt加密、解密的密匙
	 */
	private final String KEY;
	
	public TokenRealm(@Value("${jwt.key}") String key) {		
		KEY = key;
	}
	
    @Autowired 
    UserService userService;
    
    @Autowired 
    RoleService roleService;
    
    @Autowired 
    PermissionService permissionService;
	
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		XPWUser user = (XPWUser) principals.getPrimaryPrincipal();
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		
		// 添加角色
		String roles = user.getRoleIds();
		Set<String> roleIds = new HashSet<String>();
		String[] split = roles.split(",");
		for (String s : split) {
		    if (StringUtils.isEmpty(s)) {
		        continue;
		    }
		    roleIds.add(s);
		}
		System.out.println("roleIds: " + roleIds );
		authorizationInfo.setRoles(roleIds);
		
		// 添加权限
		
		Set<String> permissions = new HashSet<String>();
		for(String roleId : roleIds.toArray(new String[0])) {
            XPWRole role = roleService.read(Long.valueOf(roleId));
            String privIds = role.getPrivIds();
            String[] split2 = privIds.split(",");
            for (String id : split2) {
                if (StringUtils.isEmpty(id)) {
                    continue;
                }
//                permissionIds.add(id);
                XPWPriv priv = permissionService.read(Long.valueOf(id));
                permissions.add(priv.getTableName());
            }
        }
		System.out.println("permissions: " + permissions );
		authorizationInfo.setStringPermissions(permissions);
		
		return authorizationInfo;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
	    XPWUser user = null;
	    
	    String username = (String) authcToken.getPrincipal();
	    System.out.println("username: " + username);

	    MyUsernamePasswordToken token = (MyUsernamePasswordToken) authcToken;
	    String accountName = token.getUsername();
	    System.out.println("token:" + token.getPassword());
	    System.out.println("accountName: " + accountName);
	    
	    if (!StringUtils.isEmpty(accountName)) {
	        user = userService.findUser(username);
	        if (user != null) {
	             String result = MessageDigestUtil.Md5(user.getPassword() + token.getSalt());
	             SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(user, result, getName());
	             SecurityUtils.getSubject().getSession().setAttribute("user", user);
	             return simpleAuthenticationInfo;
	        }
	    }
	    return null;
	}

}

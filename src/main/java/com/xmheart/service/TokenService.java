package com.xmheart.service;

import com.xmheart.util.ResponseBody;

/**
* token service interface.
* 
* @author x1ny
* @date 2017年5月22日
*/
public interface TokenService {
	
	/**
	* 验证用户名和密码，生成指定时限token.
	* 
	* @param username 用户名
	* @param password 密码
	 * @param salt 
	* @param expiredHour 过期时间(小时)
	* @return token传输对象
	*/
	@SuppressWarnings("rawtypes")
	int create(String username, String password, String salt, int expiredHour, ResponseBody body);
	

}

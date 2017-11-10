package com.xmheart.service;

import java.util.List;

import com.xmheart.model.XPWUser;
import com.xmheart.util.ResponseBody;

/**
* user service interface.
* 
* @author x1ny
* @date 2017年5月22日
*/
public interface UserService {

	List<XPWUser> search(XPWUser user);

	@SuppressWarnings("rawtypes")
	int update(XPWUser user);
	
	int create(XPWUser user);
	
	XPWUser read(Long id);
	
    String getAvatarUrl(String fileName);

    List<XPWUser> index();

}

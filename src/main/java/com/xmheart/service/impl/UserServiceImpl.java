package com.xmheart.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmheart.mapper.XPWUserMapper;
import com.xmheart.model.XPWUser;
import com.xmheart.model.XPWUserExample;
import com.xmheart.service.UserService;
import com.xmheart.util.ResponseBody;


/**
* TokenService 实现类.
* 
* @author x1ny
* @date 2017年5月22日
*/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    XPWUserMapper userMapper;

	@Override
	public List<XPWUser> search(XPWUser user) {
		return null;
	}
    
    @Override
    public int update(XPWUser user) {
        return userMapper.updateByPrimaryKeySelective(user);
       
    }

    @Override
    public XPWUser read(Long id) {
        XPWUser user = userMapper.selectByPrimaryKey(id);
        return user;
    }

    @Override
    public String getAvatarUrl(String fileName) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<XPWUser> index() {
        List<XPWUser> list = userMapper.index();
        return list;
    }

    @Override
    public int create(XPWUser user) {
        int ret = userMapper.insertSelective(user);
        return ret;
    }

    @Override
    public XPWUser findUser(String username) {
        XPWUserExample example = new XPWUserExample();
        example.createCriteria().andUsernameEqualTo(username);
        List<XPWUser> selectByExample = userMapper.selectByExample(example);
        if (selectByExample != null) {
            return selectByExample.get(0);
        }
        return null;
    }
	
}

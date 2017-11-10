package com.xmheart.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmheart.mapper.XPWUserRoleMapper;
import com.xmheart.model.XPWUserRole;
import com.xmheart.model.XPWUserRoleExample;
import com.xmheart.model.XPWUserRoleExample.Criteria;
import com.xmheart.service.UserRoleService;

@Service
public class UserRoleServiceImpl implements UserRoleService {

	@Autowired
	XPWUserRoleMapper userRoleMapper;

	@Override
	public int create(XPWUserRole role) {
		return userRoleMapper.insert(role);
	}

	@Override
	public int update(XPWUserRole role) {
		int ret = userRoleMapper.updateByPrimaryKeySelective(role);
		return ret;
	}

	@Override
	public int delete(Long id) {
		int ret = userRoleMapper.deleteByPrimaryKey(id);
		return ret;
	}

	@Override
	public XPWUserRole read(Long id) {
		XPWUserRole role = userRoleMapper.selectByPrimaryKey(id);
		return role;
	}

	@Override
	public List<XPWUserRole> index() {
		XPWUserRoleExample example = new XPWUserRoleExample();
		// example.setOrderByClause("is_pinned desc, pin_order asc, publish_time desc");
		List<XPWUserRole> list = userRoleMapper.selectByExample(example);
		return list;
	}

	@Override
	public List<XPWUserRole> index(Long userId) {
		XPWUserRoleExample example = new XPWUserRoleExample();
		example.createCriteria().andUserIdEqualTo(userId);
		List<XPWUserRole> list = userRoleMapper.selectByExample(example);
		return list;
	}
	
}

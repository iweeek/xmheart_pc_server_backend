package com.xmheart.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmheart.mapper.XPWPrivMapper;
import com.xmheart.mapper.XPWRoleMapper;
import com.xmheart.model.XPWPriv;
import com.xmheart.model.XPWPrivExample;
import com.xmheart.model.XPWRole;
import com.xmheart.model.XPWRoleExample;
import com.xmheart.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService{
	
	@Autowired
	XPWRoleMapper roleMapper;
	@Autowired
	XPWPrivMapper privMapper;
	
	@Override
	public int create(XPWRole role) {
		return roleMapper.insert(role);
	}

    @Override
    public int update(XPWRole role) {
        int ret = roleMapper.updateByPrimaryKeySelective(role);
        return ret;
    }
    
    @Override
    public int delete(Long id) {
        int ret = roleMapper.deleteByPrimaryKey(id);
        return ret;
    }
    
    @Override
    public XPWRole read(Long id) {
        XPWRole role = roleMapper.selectByPrimaryKey(id);
        return role;
    }
    
    @Override
    public List<XPWRole> index() {
        XPWRoleExample example = new XPWRoleExample();
        List<XPWRole> list = roleMapper.selectByExample(example);
        return list;
    }

	@Override
	public List<XPWPriv> indexPriv() {
        List<XPWPriv> list = privMapper.selectWithColumnNameByExample();
		return list;
	}

}

package com.xmheart.service;

import java.util.List;

import com.xmheart.model.XPWRole;
import com.xmheart.model.XPWUserRole;

public interface UserRoleService {
	int create(XPWUserRole role);
	
	public int update(XPWUserRole role);
	
	public int delete(Long id);
	
	public XPWUserRole read(Long id);
	
    public List<XPWUserRole> index();
    
    public List<XPWUserRole> index(Long userId);
    
}
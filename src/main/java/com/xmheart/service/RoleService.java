package com.xmheart.service;

import java.util.List;

import com.xmheart.model.XPWPriv;
import com.xmheart.model.XPWRole;

public interface RoleService {

	int create(XPWRole role);
	
	public int update(XPWRole role);
	
	public int delete(Long id);
	
	public XPWRole read(Long id);
	
    public List<XPWRole> index();
    
    public List<XPWPriv> indexPriv();
}

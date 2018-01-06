package com.xmheart.service;

import java.util.List;

import com.xmheart.model.XPWPriv;

/**
* token service interface.
* 
* @author x1ny
* @date 2017年5月22日
*/
public interface PermissionService {
	
    int create(XPWPriv role);
    
    public int update(XPWPriv role);
    
    public int delete(Long id);
    
    public XPWPriv read(Long id);
    
    public List<XPWPriv> index();
    
}

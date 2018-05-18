package com.xmheart.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmheart.mapper.XPWPrivMapper;
import com.xmheart.model.XPWPriv;
import com.xmheart.service.PermissionService;

/**
* TokenService 实现类. 
* 
* @author x1ny
* @date 2017年5月22日
*/
@Service
public class PermissionServiceImpl implements PermissionService {
	
	private static final Logger logger = LoggerFactory.getLogger(PermissionServiceImpl.class);
	
	String logMsg = "";
	
   @Autowired
    private XPWPrivMapper privMapper;

    @Override
    public int create(XPWPriv role) {
        return 0;
    }
    
    @Override
    public int update(XPWPriv role) {
        return 0;
    }
    
    @Override
    public int delete(Long id) {
        return 0;
    }
    
    @Override
    public XPWPriv read(Long id) {
        return privMapper.selectByPrimaryKey(id);
    }
    
    @Override
    public List<XPWPriv> index() {
        return null;
    }
   
	
}

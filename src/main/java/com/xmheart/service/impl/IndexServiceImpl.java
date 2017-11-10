package com.xmheart.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmheart.mapper.XPWIndexMapper;
import com.xmheart.mapper.XPWXTIndexMapper;
import com.xmheart.model.XPWIndex;
import com.xmheart.model.XPWXTIndex;
import com.xmheart.model.XPWXTIndexExample;
import com.xmheart.service.IndexService;

@Service
public class IndexServiceImpl implements IndexService {

    @Autowired
    XPWIndexMapper indexMapper;
    
    @Autowired
    XPWXTIndexMapper xtIndexMapper;
    
    @Override
    public XPWIndex indexRead() {
        XPWIndex index = indexMapper.selectByExample(null).get(0);
        return index;
    }

    @Override
    public int update(XPWIndex index) {
        int ret = indexMapper.updateByPrimaryKeySelective(index);
        return ret;
    }

    @Override
    public XPWXTIndex xtIndexRead() {
        List<XPWXTIndex> list = xtIndexMapper.selectByExample(null);
        if (list.size() != 0) {
            return list.get(0);
        } else {
            return null;
        }
    }

    @Override
    public int xtUpdate(XPWXTIndex index) {
        int ret = xtIndexMapper.updateByPrimaryKeySelective(index);
        return ret;
    }

    @Override
    public XPWXTIndex getXTIndex() {
        XPWXTIndexExample example = new XPWXTIndexExample();
        return null;
    }
	

}

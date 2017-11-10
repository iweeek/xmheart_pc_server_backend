package com.xmheart.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmheart.mapper.XPWOnlineVideoMapper;
import com.xmheart.model.XPWOnlineVideo;
import com.xmheart.model.XPWOnlineVideoExample;
import com.xmheart.service.OnlineVideoService;

@Service
public class OnlineVideoServiceImpl implements OnlineVideoService {

    @Autowired
    XPWOnlineVideoMapper onlineVideoMapper;
    
    @Override
    public int create(XPWOnlineVideo video) {
        int ret = onlineVideoMapper.insert(video);
        return ret;
    }
    
    @Override
    public int update(XPWOnlineVideo video) {
        int ret = onlineVideoMapper.updateByPrimaryKeySelective(video);
        return ret;
    }

    @Override
    public List<XPWOnlineVideo> index() {
        List<XPWOnlineVideo> list = onlineVideoMapper.selectByExample(null);
        return list;
    }

    @Override
    public XPWOnlineVideo read(Long id) {
        XPWOnlineVideo video = onlineVideoMapper.selectByPrimaryKey(id);
        return video;
    }

	@Override
	public List<XPWOnlineVideo> index(boolean isPublished) {
		XPWOnlineVideoExample example = new XPWOnlineVideoExample();

        example.createCriteria().andIsPublishedEqualTo(isPublished);

        example.setOrderByClause("publish_time desc");

        List<XPWOnlineVideo> list = onlineVideoMapper.selectByExample(example);

        return list;
	}
}

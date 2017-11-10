package com.xmheart.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmheart.mapper.XPWVideoMapper;
import com.xmheart.model.XPWArticle;
import com.xmheart.model.XPWColumn;
import com.xmheart.model.XPWElecNewspaper;
import com.xmheart.model.XPWVideo;
import com.xmheart.model.XPWVideoExample;
import com.xmheart.service.VideoService;

@Service
public class VideoServiceImpl implements VideoService {

    @Autowired
    XPWVideoMapper videoMapper;
    
    @Override
    public int create(XPWVideo video) {
        int ret = videoMapper.insert(video);
        return ret;
    }
    
    @Override
    public int update(XPWVideo video) {
        int ret = videoMapper.updateByPrimaryKeySelective(video);
        return ret;
    }

    @Override
    public List<XPWVideo> index() {
        List<XPWVideo> list = videoMapper.selectByExample(null);
        return list;
    }

    @Override
    public XPWVideo read(Long id) {
        XPWVideo video = videoMapper.selectByPrimaryKey(id);
        return video;
    }

	@Override
	public List<XPWVideo> index(boolean isPublished) {
		XPWVideoExample example = new XPWVideoExample();

        example.createCriteria().andIsPublishedEqualTo(isPublished);

        example.setOrderByClause("publish_time desc");

        List<XPWVideo> list = videoMapper.selectByExample(example);

        return list;
	}

    @Override
    public List<XPWVideo> indexLastest() {
        List<XPWVideo> list = videoMapper.selectLastest();
        return list;
    }
}

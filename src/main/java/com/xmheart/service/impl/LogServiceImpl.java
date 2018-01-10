package com.xmheart.service.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmheart.mapper.XPWArticleMapper;
import com.xmheart.mapper.XPWVisitLogMapper;
import com.xmheart.model.XPWArticle;
import com.xmheart.model.XPWArticleExample;
import com.xmheart.model.XPWArticleExample.Criteria;
import com.xmheart.model.XPWVideo;
import com.xmheart.model.XPWVisitLog;
import com.xmheart.model.XPWVisitLogExample;
import com.xmheart.service.ArticleService;
import com.xmheart.service.LogService;

@Service
public class LogServiceImpl implements LogService {
	
	@Autowired
	XPWVisitLogMapper logMapper;

    @Override
    public List<XPWVisitLog> index() {
//        XPWVisitLogExample example = new XPWVisitLogExample();
//        example.setOrderByClause("access_time desc");
        List<XPWVisitLog> list = logMapper.selectWithUsername();
        return list;
    }

	@Override
	public int delete(Long id) {
		return logMapper.deleteByPrimaryKey(id);
	}

    @Override
    public XPWVisitLog read(Long id) {
        XPWVisitLog article = logMapper.selectByPrimaryKey(id);
        return article;
    }
}

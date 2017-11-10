package com.xmheart.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.xmheart.mapper.XPWArticleMapper;
import com.xmheart.mapper.XPWElecNewspaperMapper;
import com.xmheart.model.XPWArticle;
import com.xmheart.model.XPWArticleExample;
import com.xmheart.model.XPWElecNewspaper;
import com.xmheart.model.XPWElecNewspaperExample;
import com.xmheart.model.XPWElecNewspaperExample.Criteria;
import com.xmheart.service.NewsService;

@Service
public class NewsServiceImpl implements NewsService {

	static final long MEDIA_NEWS_COLUMN_ID = 20;
	static final long HOSPITAL_NEWS_COLUMN_ID = 21;
	static final long ELEC_NEWS_COLUMN_ID = 23;

	@Autowired
	XPWArticleMapper xpwArticleMapper;
	@Autowired
	XPWElecNewspaperMapper xpwElecNewspaperMapper;

	@Override
	public List<XPWArticle> getPinnedMediaNews() {
		XPWArticleExample example = new XPWArticleExample();
		example.createCriteria().andColumnIdEqualTo(MEDIA_NEWS_COLUMN_ID).andIsPinnedEqualTo(true);

		List<XPWArticle> list = xpwArticleMapper.selectByExampleWithBLOBs(example);
		return list;
	}

	@Override
	public List<XPWArticle> getNoPinnedMediaNews() {
		XPWArticleExample example = new XPWArticleExample();
		example.createCriteria().andColumnIdEqualTo(MEDIA_NEWS_COLUMN_ID).andIsPinnedEqualTo(false);
		example.setOrderByClause("publish_time desc");

		List<XPWArticle> list = xpwArticleMapper.selectByExampleWithBLOBs(example);

		return list;
	}

	@Override
	public List<XPWArticle> getPinnedHospitalNews() {
		XPWArticleExample example = new XPWArticleExample();
		example.createCriteria().andColumnIdEqualTo(HOSPITAL_NEWS_COLUMN_ID).andIsPinnedEqualTo(true);

		List<XPWArticle> list = xpwArticleMapper.selectByExampleWithBLOBs(example);
		return list;
	}

	@Override
	public List<XPWArticle> getNoPinnedHospitalNews() {
		XPWArticleExample example = new XPWArticleExample();
		example.createCriteria().andColumnIdEqualTo(HOSPITAL_NEWS_COLUMN_ID).andIsPinnedEqualTo(false);

		List<XPWArticle> list = xpwArticleMapper.selectByExampleWithBLOBs(example);
		return list;
	}

	@Override
	public XPWArticle getNewsByTitle(String title) {
		XPWArticleExample example = new XPWArticleExample();
		example.createCriteria().andTitleEqualTo(title);
		XPWArticle article = xpwArticleMapper.selectByExampleWithBLOBs(example).get(0);

		return article;
	}

	@Override
	public XPWArticle getNewsById(Long id) {
		XPWArticleExample example = new XPWArticleExample();
		example.createCriteria().andIdEqualTo(id).andIsPublishedEqualTo(true);
		List<XPWArticle> list = xpwArticleMapper.selectByExampleWithBLOBs(example);
		if (list.size() > 0) {
		    return list.get(0);
		} else {
		    return null;
		}
	}

	@Override
	public List<XPWElecNewspaper> getElecNewsPaper(String year, String times) {
		XPWElecNewspaperExample example = new XPWElecNewspaperExample();
		Criteria criteria = example.createCriteria();
		
		if (!year.equals("")) {
			criteria.andYearsEqualTo(year);
		} 
		
		if (!times.equals("")) {
			criteria.andTimesEqualTo(times);
		}
		
		criteria.andIsPublishedEqualTo(true);
		example.setOrderByClause("`years`, times desc, page asc");
		List<XPWElecNewspaper> list = xpwElecNewspaperMapper.selectByExample(example);
		return list;
	}

	@Override
	public List<String> getNewsPaperYears() {
		List<String> list = xpwElecNewspaperMapper.getNewsPaperYears();
		return list;
	}

	@Override
	public List<XPWArticle> getNews() {
		List<XPWArticle> list = xpwArticleMapper.selectByExampleWithBLOBs(null);
		return list;
	}

	@Override
	public List<String> getNewsPaperTimes(String year) {
		List<String> list = xpwElecNewspaperMapper.getNewsPaperTimes(year);
		return list;
	}
		@Override
	public int updateNews(XPWArticle news) {
		int ret = xpwArticleMapper.updateByPrimaryKey(news);
		return ret;
	}

        @Override
        public XPWArticle getColPrevNewsById(Long colId, Long id) {
            XPWArticleExample example = new XPWArticleExample();
            example.createCriteria().andColumnIdEqualTo(colId).andIdLessThan(id).andIsPublishedEqualTo(true);
            example.setOrderByClause("id desc");
            
            PageHelper.startPage(1, 1);
            List<XPWArticle> list = xpwArticleMapper.selectByExampleWithBLOBs(example);
            if (list.size() != 0) {
                return list.get(0);
            } else {
                return null;
            }
        }

        @Override
        public XPWArticle getColNextNewsById(Long colId, Long id) {
            XPWArticleExample example = new XPWArticleExample();
            example.createCriteria().andColumnIdEqualTo(colId).andIdGreaterThan(id).andIsPublishedEqualTo(true);
            example.setOrderByClause("id asc");
            
            PageHelper.startPage(1, 1);
            List<XPWArticle> list = xpwArticleMapper.selectByExampleWithBLOBs(example);
            if (list.size() != 0) {
                return list.get(0);
            } else {
                return null;
            }
        }

}

package com.xmheart.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmheart.mapper.XPWColumnEnglishMapper;
import com.xmheart.mapper.XPWColumnMapper;
import com.xmheart.mapper.XPWIndexMapper;
import com.xmheart.mapper.XPWNavMapper;
import com.xmheart.model.XPWColumn;
import com.xmheart.model.XPWColumnEnglish;
import com.xmheart.model.XPWColumnEnglishExample;
import com.xmheart.model.XPWColumnExample;
import com.xmheart.model.XPWIndex;
import com.xmheart.model.XPWNav;
import com.xmheart.model.XPWNavExample;
import com.xmheart.service.ColumnEnglishService;
import com.xmheart.service.ColumnService;

@Service
public class ColumnEnglishServiceImpl implements ColumnEnglishService {

    @Autowired
    private XPWColumnEnglishMapper xpwColumnEnglishMapper;
	   
	@Autowired
	private XPWNavMapper xpwNavMapper;
	
	@Autowired
	private XPWIndexMapper xpwIndexMapper;
	
	@Override
	public List<XPWColumnEnglish> getTopFirstColumns() {
		XPWColumnEnglishExample example = new XPWColumnEnglishExample();
		example.createCriteria().andParentColumnIdEqualTo(0l).andPositionEqualTo(false);
		List<XPWColumnEnglish> list = null;
		list = xpwColumnEnglishMapper.selectEnglishColumns();
		return list;
	}
	
	@Override
	public List<XPWColumnEnglish> getChildColumnsById(long id) {
//		XPWColumnExample example = new XPWColumnExample();
//		example.createCriteria().andParentColumnIdEqualTo(id);
		List<XPWColumnEnglish> list = null;
		list = xpwColumnEnglishMapper.selectAllEnglishColumnsByParentColumnId(id);
		return list;
	}

	@Override
	public List<XPWNav> getNavsByColumnId(long id) {
//		XPWNavExample example = new XPWNavExample();
//		example.createCriteria().andColumnIdEqualTo(id);
		List<XPWNav> list = null;
		list = xpwNavMapper.selectEnglishByColumnId(id);
		return list;
	}
	
    @Override
    public List<XPWNav> getNavsByChildColumnIdOrderByPublishTime(long id) {
        XPWNavExample example = new XPWNavExample();
        example.createCriteria().andChildColumnIdEqualTo(id);
        List<XPWNav> list = xpwNavMapper.selectByExample(example);
        return list;
    }

	@Override
	public List<XPWNav> getNavListBySecondColumnName(String name) {
		XPWNavExample example = new XPWNavExample();
		example.createCriteria().andChildColumnNameEqualTo(name);
		List<XPWNav> list = xpwNavMapper.selectByExample(example);
		return list;
	}

	@Override
	public int createNav(XPWNav nav) {
		XPWNavExample example = new XPWNavExample();
		example.createCriteria()
			   .andChildColumnNameEqualTo(nav.getChildColumnName())
			   .andArticleTitleEqualTo(nav.getArticleTitle());
		List<XPWNav> list = xpwNavMapper.selectByExample(example);
		if (list.size() > 0) {
			// 已经存在
			return HttpServletResponse.SC_CONFLICT;
		} else {
			int result = xpwNavMapper.insertSelective(nav);
			if (result > 0) {
				return HttpServletResponse.SC_OK;
			} else {
				return HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
			}
		}
	}

	@Override
	public int updateNav(XPWNav nav) {
		XPWNavExample example = new XPWNavExample();
		example.createCriteria().andIdEqualTo(nav.getId());
		List<XPWNav> list = xpwNavMapper.selectByExample(example);
		if (list.size() > 0) {
			xpwNavMapper.updateByPrimaryKeySelective(nav);
			return HttpServletResponse.SC_OK;
		} else {
			return HttpServletResponse.SC_NOT_FOUND;
		}
	}
	
   @Override
    public int updateNavByExample(XPWNav nav) {
//        XPWNavExample example = new XPWNavExample();
//        example.createCriteria().andIdEqualTo(nav.getId());
//        List<XPWNav> list = xpwNavMapper.selectByExample(example);
        int ret = xpwNavMapper.updateByPrimaryKey(nav);
        if (ret > 0) {
            return HttpServletResponse.SC_OK;
        } else {
            return HttpServletResponse.SC_NOT_FOUND;
        }
    }
	
    @Override
    public List<XPWColumnEnglish> getColumns() {
        List<XPWColumnEnglish> list = xpwColumnEnglishMapper.selectByExample(null);
        return list;
    }

    @Override
    public List<XPWColumnEnglish> getColumnsByParentId(Long parentColumnId) {
        List<XPWColumnEnglish> list = new ArrayList<>();
//        if (getLanguage() == 1) {
        list = xpwColumnEnglishMapper.selectEnglishByParentId(parentColumnId);
//            XPWColumn column = null;
//            for (XPWColumnEnglish c : l) {
//                column = new XPWColumn();
//                column = clone(c);
//                list.add(column);
//            }
//        } else {
//            XPWColumnExample example = new XPWColumnExample();
//            example.createCriteria().andParentColumnIdEqualTo(parentColumnId);
//            list = xpwColumnMapper.selectByExample(example);
//        }
        
        return list;
    }

    @Override
    public XPWColumnEnglish getColumnById(Long id) {
        XPWColumnEnglish column = xpwColumnEnglishMapper.selectEnglishByPrimaryKey(id);
        return column;
    }

    @Override
    public int updateColumnEnglish(XPWColumnEnglish column) {
        int ret = xpwColumnEnglishMapper.updateByPrimaryKeySelective(column);
        return ret;
    }

    @Override
    public XPWColumnEnglish getParentColumnById(long id) {
        long parentId = xpwColumnEnglishMapper.selectByPrimaryKey(id).getParentColumnId();
        XPWColumnEnglish column = null;
        
//        if (getLanguage() == 1) {
            column = xpwColumnEnglishMapper.selectEnglishByPrimaryKey(parentId);
//            column = clone(temp);
//		} else {
//			column = xpwColumnMapper.selectByPrimaryKey(parentId);
//		}
        return column;
    }

    public XPWColumn clone(XPWColumnEnglish c) {
        if (c == null) {
            return null;
        }
        XPWColumn column = new XPWColumn();
        column.setColumnName(c.getColumnName());
        column.setColumnNameEn(c.getColumnNameEn());
        column.setId(c.getId());
        column.setParentColumnId(c.getParentColumnId());
        column.setPublishTime(c.getPublishTime());
        column.setUrl(c.getUrl());
        column.setPosition(c.getPosition());
        return column;
    }
    
    @Override
    public List<XPWColumnEnglish> readSubColumn(Long id) {
        XPWColumnEnglishExample example = new XPWColumnEnglishExample();
        example.createCriteria().andParentColumnIdEqualTo(id);
        List<XPWColumnEnglish> list = xpwColumnEnglishMapper.selectByExample(example);
        return list;
    }
    
	@Override
	public List<XPWNav> getNavsByChildColumnName(String childColumnName) {
		XPWNavExample example = new XPWNavExample();
		example.createCriteria().andChildColumnNameEqualTo(childColumnName);
		List<XPWNav> list = xpwNavMapper.selectByExample(example);
		return list;
	}
	
	@Override
	public List<XPWNav> getNavsByChildColumnId(Long id) {
		XPWNavExample example = new XPWNavExample();
		example.createCriteria().andChildColumnIdEqualTo(id);
		List<XPWNav> list = null;
//		if (getLanguage() == 1) {
			list = xpwNavMapper.selectEnglishByChildColumnId(id);
//		} else {
//			list = xpwNavMapper.selectByExample(example);
//		}
		
		return list;
	}

	@Override
	public XPWNav getNavById(Long id) {
		XPWNav nav = xpwNavMapper.selectByPrimaryKey(id);
        return nav;
	}

    @Override
    public List<XPWNav> getNavList() {
        List<XPWNav> list = xpwNavMapper.selectByExample(null);
        return list;
    }

}

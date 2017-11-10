package com.xmheart.service;

import java.util.List;

import com.xmheart.model.XPWArticle;
import com.xmheart.model.XPWColumn;
import com.xmheart.model.XPWElecNewspaper;
import com.xmheart.model.XPWVideo;


/**
 * The Interface NewsService.
 * 用于处理文章相关
 */
public interface VideoService {

    int create(XPWVideo video);

    int update(XPWVideo video);

    List<XPWVideo> index();

    XPWVideo read(Long id);
    
    List<XPWVideo> index(boolean isPublished);
    
    List<XPWVideo> indexLastest();
}

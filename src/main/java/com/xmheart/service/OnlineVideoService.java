package com.xmheart.service;

import java.util.List;

import com.xmheart.model.XPWArticle;
import com.xmheart.model.XPWColumn;
import com.xmheart.model.XPWOnlineVideo;
import com.xmheart.model.XPWVideo;


/**
 * The Interface NewsService.
 * 用于处理文章相关
 */
public interface OnlineVideoService {

    int create(XPWOnlineVideo video);

    int update(XPWOnlineVideo video);

    List<XPWOnlineVideo> index();

    XPWOnlineVideo read(Long id);
    
    List<XPWOnlineVideo> index(boolean isPublished);
}

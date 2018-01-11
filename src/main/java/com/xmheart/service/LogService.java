package com.xmheart.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.xmheart.model.XPWArticle;
import com.xmheart.model.XPWVideo;
import com.xmheart.model.XPWVisitLog;


/**
 * The Interface NewsService.
 * 用于处理日志相关
 */
public interface LogService {
    
    XPWVisitLog read(Long id);
    
    int delete(Long id);

    List<XPWVisitLog> index(java.util.Date startTime, java.util.Date endTime);

    List<XPWVisitLog> index();
}

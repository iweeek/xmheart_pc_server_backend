package com.xmheart.service;

import com.xmheart.model.XPWIndex;
import com.xmheart.model.XPWXTIndex;


/**
 * The Interface IndexService.
 * 首页相关接口
 */
public interface IndexService {

    XPWIndex indexRead();

    int update(XPWIndex index);

    XPWXTIndex xtIndexRead();

    int xtUpdate(XPWXTIndex index);

    XPWXTIndex getXTIndex();

}

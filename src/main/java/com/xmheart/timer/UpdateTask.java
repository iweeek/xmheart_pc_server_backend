package com.xmheart.timer;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xmheart.model.XPWElecNewspaper;
import com.xmheart.model.XPWNav;
import com.xmheart.model.XPWVideo;
import com.xmheart.service.ColumnService;
import com.xmheart.service.NewspaperService;
import com.xmheart.service.VideoService;

@Controller
public class UpdateTask {
    
    @Autowired
    private VideoService videoService;
    
    @Autowired
    private NewspaperService newspaperService;
    
    @Autowired
    private ColumnService columnService;
    
    private final static long NEWSPAPER_COLUMN_ID = 23;  
    private final static long VIDEO_COLUMN_ID = 22;  
    
   
//    @RequestMapping(value = { "/test" }, method = RequestMethod.POST)
//    public ResponseEntity<?> job()   {  
    @Scheduled(cron = "0 0 */1 * * *")
    public void job()   {  
        // 电子院报
        List<XPWElecNewspaper> lastestNewspaper = newspaperService.indexLastest();
        if (lastestNewspaper.size() > 4) {
            lastestNewspaper = lastestNewspaper.subList(0, 4);
        }
        
        List<XPWNav> newspaperNavs = columnService.getNavsByChildColumnIdOrderByPublishTime(NEWSPAPER_COLUMN_ID);
        
        for (int i = 0; i < lastestNewspaper.size(); i++) {
            XPWNav xpwNav = newspaperNavs.get(i);
            xpwNav.setArticleTitle(lastestNewspaper.get(i).getTitle());
            xpwNav.setUrl(lastestNewspaper.get(i).getUrl());
            xpwNav.setImgUrl(lastestNewspaper.get(i).getImageUrl());
            xpwNav.setPublishTime(lastestNewspaper.get(i).getPublishTime());
            columnService.updateNav(xpwNav);
        }
        
        // 影像厦心
        List<XPWVideo> lastestVideo = videoService.indexLastest();
        if (lastestVideo.size() > 4) {
            lastestVideo = lastestVideo.subList(0, 4);
        }
        
        List<XPWNav> videoNavs = columnService.getNavsByChildColumnIdOrderByPublishTime(VIDEO_COLUMN_ID);
        
        for (int i = 0; i < lastestVideo.size(); i++) {
            XPWNav xpwNav = videoNavs.get(i);
            xpwNav.setArticleTitle(lastestVideo.get(i).getTitle());
            xpwNav.setUrl(lastestVideo.get(i).getUrl());
            xpwNav.setImgUrl(lastestVideo.get(i).getImgUrl());
            xpwNav.setPublishTime(lastestVideo.get(i).getPublishTime());
            columnService.updateNav(xpwNav);
        }
        
//        return ResponseEntity.ok(videoNavs);
    }
}

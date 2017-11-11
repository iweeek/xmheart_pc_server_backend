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

import com.xmheart.model.XPWArticle;
import com.xmheart.model.XPWElecNewspaper;
import com.xmheart.model.XPWNav;
import com.xmheart.model.XPWVideo;
import com.xmheart.service.ArticleService;
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
    
    @Autowired
    private ArticleService articleService;
    
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
        
        // 媒体看厦心
        List<XPWNav> mediaNavs = columnService.getNavsByChildColumnIdOrderByPublishTime(20);
        List<XPWArticle> lastestMedias = articleService.indexLastestColumn("媒体看厦心");
        if (lastestMedias.size() > mediaNavs.size()) {
            lastestMedias = lastestMedias.subList(0, mediaNavs.size());
        }
        
        for (int i = 0; i < lastestMedias.size(); i++) {
            XPWNav xpwNav = mediaNavs.get(i);
            xpwNav.setArticleTitle(lastestMedias.get(i).getTitle());
            xpwNav.setUrl(lastestMedias.get(i).getUrl());
            xpwNav.setImgUrl(lastestMedias.get(i).getImgUrl());
            xpwNav.setPublishTime(lastestMedias.get(i).getPublishTime());
            columnService.updateNav(xpwNav);
        }
        
        // 医院新闻
        List<XPWNav> hospitalNewsNavs = columnService.getNavsByChildColumnIdOrderByPublishTime(21);
        List<XPWArticle> lastestHospitalNews = articleService.indexLastestColumn("医院新闻");
        if (lastestHospitalNews.size() > hospitalNewsNavs.size()) {
            lastestHospitalNews = lastestHospitalNews.subList(0, hospitalNewsNavs.size());
        }
        
        for (int i = 0; i < lastestHospitalNews.size(); i++) {
            XPWNav xpwNav = hospitalNewsNavs.get(i);
            xpwNav.setArticleTitle(lastestHospitalNews.get(i).getTitle());
            xpwNav.setUrl(lastestHospitalNews.get(i).getUrl());
            xpwNav.setImgUrl(lastestHospitalNews.get(i).getImgUrl());
            xpwNav.setPublishTime(lastestHospitalNews.get(i).getPublishTime());
            columnService.updateNav(xpwNav);
        }
        
        // 科教动态
        List<XPWNav> scienceNavs = columnService.getNavsByChildColumnIdOrderByPublishTime(48);
        List<XPWArticle> lastestSciences = articleService.indexLastestColumn("科教动态");
        if (lastestSciences.size() > scienceNavs.size()) {
            lastestSciences = lastestSciences.subList(0, scienceNavs.size());
        }        
        
        for (int i = 0; i < lastestSciences.size(); i++) {
            XPWNav xpwNav = scienceNavs.get(i);
            xpwNav.setArticleTitle(lastestSciences.get(i).getTitle());
            xpwNav.setUrl(lastestSciences.get(i).getUrl());
            xpwNav.setImgUrl(lastestSciences.get(i).getImgUrl());
            xpwNav.setPublishTime(lastestSciences.get(i).getPublishTime());
            columnService.updateNav(xpwNav);
        }
        
        // 临床教学
        List<XPWNav> clinicNavs = columnService.getNavsByChildColumnIdOrderByPublishTime(49);
        List<XPWArticle> lastestClinics = articleService.indexLastestColumn("临床教学");
        if (lastestClinics.size() > clinicNavs.size()) {
            lastestClinics = lastestClinics.subList(0, clinicNavs.size());
        }
        
        for (int i = 0; i < lastestClinics.size(); i++) {
            XPWNav xpwNav = clinicNavs.get(i);
            xpwNav.setArticleTitle(lastestClinics.get(i).getTitle());
            xpwNav.setUrl(lastestClinics.get(i).getUrl());
            xpwNav.setImgUrl(lastestClinics.get(i).getImgUrl());
            xpwNav.setPublishTime(lastestClinics.get(i).getPublishTime());
            columnService.updateNav(xpwNav);
        }
        
        // 研究生教育
        List<XPWNav> graduateNavs = columnService.getNavsByChildColumnIdOrderByPublishTime(50);
        List<XPWArticle> lastestGraduates = articleService.indexLastestColumn("研究生教育");
        if (lastestGraduates.size() > graduateNavs.size()) {
            lastestGraduates = lastestGraduates.subList(0, graduateNavs.size());
        }
        
        for (int i = 0; i < lastestGraduates.size(); i++) {
            XPWNav xpwNav = graduateNavs.get(i);
            xpwNav.setArticleTitle(lastestGraduates.get(i).getTitle());
            xpwNav.setUrl(lastestGraduates.get(i).getUrl());
            xpwNav.setImgUrl(lastestGraduates.get(i).getImgUrl());
            xpwNav.setPublishTime(lastestGraduates.get(i).getPublishTime());
            columnService.updateNav(xpwNav);
        }
//        return ResponseEntity.ok(videoNavs);
    }
    
//    @RequestMapping(value = { "/test" }, method = RequestMethod.POST)
//    public ResponseEntity<?> jobs()   {  
//        
//        List<XPWNav> clinicNavs = columnService.getNavsByChildColumnIdOrderByPublishTime(49);
//        // 临床教学
//        List<XPWArticle> lastestClinics = articleService.indexLastestColumn("临床教学");
//        if (lastestClinics.size() > clinicNavs.size()) {
//            lastestClinics = lastestClinics.subList(0, clinicNavs.size());
//        }
//        
//        for (int i = 0; i < lastestClinics.size(); i++) {
//            XPWNav xpwNav = clinicNavs.get(i);
//            xpwNav.setArticleTitle(lastestClinics.get(i).getTitle());
//            xpwNav.setUrl(lastestClinics.get(i).getUrl());
//            xpwNav.setImgUrl(lastestClinics.get(i).getImgUrl());
//            xpwNav.setPublishTime(lastestClinics.get(i).getPublishTime());
//            columnService.updateNav(xpwNav);
//        }
//       
//        return ResponseEntity.ok(clinicNavs);
//    }
}

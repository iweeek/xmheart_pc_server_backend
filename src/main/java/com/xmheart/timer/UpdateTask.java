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
    
    /*
     * 2017年12月21日15:08:34 暂时取消后台使用定时任务
     */
//    @Scheduled(cron = "0 */5 * * * *")
//    public void job()   {  
//        System.out.println("后台开始执行定时任务......");
//        
//        // 电子院报
//        List<XPWElecNewspaper> lastestNewspaper = newspaperService.indexLastest();
//        if (lastestNewspaper.size() > 4) {
//            lastestNewspaper = lastestNewspaper.subList(0, 4);
//        }
//        
//        List<XPWNav> newspaperNavs = columnService.getNavsByChildColumnIdOrderByPublishTime(NEWSPAPER_COLUMN_ID);
//        
//        for (int i = 0; i < lastestNewspaper.size(); i++) {
//            XPWNav xpwNav = newspaperNavs.get(i);
//            xpwNav.setArticleTitle(lastestNewspaper.get(i).getTitle());
//            xpwNav.setUrl(lastestNewspaper.get(i).getUrl());
//            xpwNav.setImgUrl(lastestNewspaper.get(i).getImageUrl());
//            xpwNav.setPublishTime(lastestNewspaper.get(i).getPublishTime());
//            columnService.updateNav(xpwNav);
//        }
//        
//        // 影像厦心
//        List<XPWVideo> lastestVideo = videoService.indexLastest();
//        if (lastestVideo.size() > 4) {
//            lastestVideo = lastestVideo.subList(0, 4);
//        }
//        
//        List<XPWNav> videoNavs = columnService.getNavsByChildColumnIdOrderByPublishTime(VIDEO_COLUMN_ID);
//        
//        for (int i = 0; i < lastestVideo.size(); i++) {
//            XPWNav xpwNav = videoNavs.get(i);
//            xpwNav.setArticleTitle(lastestVideo.get(i).getTitle());
//            xpwNav.setUrl(lastestVideo.get(i).getUrl());
//            xpwNav.setImgUrl(lastestVideo.get(i).getImgUrl());
//            xpwNav.setPublishTime(lastestVideo.get(i).getPublishTime());
//            columnService.updateNav(xpwNav);
//        }
//        
//        // 媒体看厦心 第一条不自动更新
//        List<XPWNav> mediaNavs = columnService.getNavsByChildColumnIdOrderByPublishTime(20);
//        List<XPWArticle> lastestMedias = articleService.indexLastestColumn("媒体看厦心");
//        if (lastestMedias.size() > mediaNavs.size()) {
//            lastestMedias = lastestMedias.subList(0, mediaNavs.size());
//        }
//        
//        for (int i = 1; i < lastestMedias.size(); i++) {
//            XPWNav xpwNav = mediaNavs.get(i);
//            xpwNav.setArticleTitle(lastestMedias.get(i).getTitle());
//            xpwNav.setUrl(lastestMedias.get(i).getUrl());
//            xpwNav.setImgUrl(lastestMedias.get(i).getImgUrl());
//            xpwNav.setPublishTime(lastestMedias.get(i).getPublishTime());
//            columnService.updateNav(xpwNav);
//        }
//        
//        // 医院新闻
//        List<XPWNav> hospitalNewsNavs = columnService.getNavsByChildColumnIdOrderByPublishTime(21);
//        List<XPWArticle> lastestHospitalNews = articleService.indexLastestColumn("医院新闻");
//        if (lastestHospitalNews.size() > hospitalNewsNavs.size()) {
//            lastestHospitalNews = lastestHospitalNews.subList(0, hospitalNewsNavs.size());
//        }
//        
//        for (int i = 0; i < lastestHospitalNews.size(); i++) {
//            XPWNav xpwNav = hospitalNewsNavs.get(i);
//            xpwNav.setArticleTitle(lastestHospitalNews.get(i).getTitle());
//            xpwNav.setUrl(lastestHospitalNews.get(i).getUrl());
//            xpwNav.setImgUrl(lastestHospitalNews.get(i).getImgUrl());
//            xpwNav.setPublishTime(lastestHospitalNews.get(i).getPublishTime());
//            columnService.updateNav(xpwNav);
//        }
//        
//        // 科教概况 第一条不自动更新
//        List<XPWNav> scienceGeneralNavs = columnService.getNavsByChildColumnIdOrderByPublishTime(47);
//        List<XPWArticle> lastestScienceGeneral = articleService.indexLastestColumn("科教概况");
//        if (lastestScienceGeneral.size() > scienceGeneralNavs.size()) {
//            lastestScienceGeneral = lastestScienceGeneral.subList(0, scienceGeneralNavs.size());
//        }        
//        
//        for (int i = 1; i < lastestScienceGeneral.size(); i++) {
//            XPWNav xpwNav = scienceGeneralNavs.get(i);
//            xpwNav.setArticleTitle(lastestScienceGeneral.get(i).getTitle());
//            xpwNav.setUrl(lastestScienceGeneral.get(i).getUrl());
//            xpwNav.setImgUrl(lastestScienceGeneral.get(i).getImgUrl());
//            xpwNav.setPublishTime(lastestScienceGeneral.get(i).getPublishTime());
//            columnService.updateNav(xpwNav);
//        }
//        
//        // 科教动态
//        List<XPWNav> scienceNavs = columnService.getNavsByChildColumnIdOrderByPublishTime(48);
//        List<XPWArticle> lastestSciences = articleService.indexLastestColumn("科教动态");
//        if (lastestSciences.size() > scienceNavs.size()) {
//            lastestSciences = lastestSciences.subList(0, scienceNavs.size());
//        }        
//        
//        for (int i = 0; i < lastestSciences.size(); i++) {
//            XPWNav xpwNav = scienceNavs.get(i);
//            xpwNav.setArticleTitle(lastestSciences.get(i).getTitle());
//            xpwNav.setUrl(lastestSciences.get(i).getUrl());
//            xpwNav.setImgUrl(lastestSciences.get(i).getImgUrl());
//            xpwNav.setPublishTime(lastestSciences.get(i).getPublishTime());
//            columnService.updateNav(xpwNav);
//        }
//        
//        // 临床教学
//        List<XPWNav> clinicNavs = columnService.getNavsByChildColumnIdOrderByPublishTime(49);
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
//        // 研究生教育
//        List<XPWNav> graduateNavs = columnService.getNavsByChildColumnIdOrderByPublishTime(50);
//        List<XPWArticle> lastestGraduates = articleService.indexLastestColumn("研究生教育");
//        if (lastestGraduates.size() > graduateNavs.size()) {
//            lastestGraduates = lastestGraduates.subList(0, graduateNavs.size());
//        }
//        
//        for (int i = 0; i < lastestGraduates.size(); i++) {
//            XPWNav xpwNav = graduateNavs.get(i);
//            xpwNav.setArticleTitle(lastestGraduates.get(i).getTitle());
//            xpwNav.setUrl(lastestGraduates.get(i).getUrl());
//            xpwNav.setImgUrl(lastestGraduates.get(i).getImgUrl());
//            xpwNav.setPublishTime(lastestGraduates.get(i).getPublishTime());
//            columnService.updateNav(xpwNav);
//        }
//        
//        // 党务工作
//        List<XPWNav> partyWorkNavs = columnService.getNavsByChildColumnIdOrderByPublishTime(38);
//        List<XPWArticle> lastestPartyWork = articleService.indexLastestColumn("党务工作");
//        if (lastestPartyWork.size() > partyWorkNavs.size()) {
//            lastestPartyWork = lastestPartyWork.subList(0, partyWorkNavs.size());
//        }
//        
//        for (int i = 0; i < lastestPartyWork.size(); i++) {
//            XPWNav xpwNav = partyWorkNavs.get(i);
//            xpwNav.setArticleTitle(lastestPartyWork.get(i).getTitle());
//            xpwNav.setUrl(lastestPartyWork.get(i).getUrl());
//            xpwNav.setImgUrl(lastestPartyWork.get(i).getImgUrl());
//            xpwNav.setPublishTime(lastestPartyWork.get(i).getPublishTime());
//            columnService.updateNav(xpwNav);
//        }
//        
//        // 纪检监察
//        List<XPWNav> superviseNavs = columnService.getNavsByChildColumnIdOrderByPublishTime(39);
//        List<XPWArticle> lastestSupervise = articleService.indexLastestColumn("纪检监察");
//        if (lastestSupervise.size() > superviseNavs.size()) {
//            lastestSupervise = lastestSupervise.subList(0, superviseNavs.size());
//        }
//        
//        for (int i = 0; i < lastestSupervise.size(); i++) {
//            XPWNav xpwNav = superviseNavs.get(i);
//            xpwNav.setArticleTitle(lastestSupervise.get(i).getTitle());
//            xpwNav.setUrl(lastestSupervise.get(i).getUrl());
//            xpwNav.setImgUrl(lastestSupervise.get(i).getImgUrl());
//            xpwNav.setPublishTime(lastestSupervise.get(i).getPublishTime());
//            columnService.updateNav(xpwNav);
//        }
//        
//        // 志愿服务
//        List<XPWNav> volunteerNavs = columnService.getNavsByChildColumnIdOrderByPublishTime(40);
//        List<XPWArticle> lastestVolunteer = articleService.indexLastestColumn("志愿服务");
//        if (lastestVolunteer.size() > volunteerNavs.size()) {
//            lastestVolunteer = lastestVolunteer.subList(0, volunteerNavs.size());
//        }
//        
//        for (int i = 0; i < lastestVolunteer.size(); i++) {
//            XPWNav xpwNav = volunteerNavs.get(i);
//            xpwNav.setArticleTitle(lastestVolunteer.get(i).getTitle());
//            xpwNav.setUrl(lastestVolunteer.get(i).getUrl());
//            xpwNav.setImgUrl(lastestVolunteer.get(i).getImgUrl());
//            xpwNav.setPublishTime(lastestVolunteer.get(i).getPublishTime());
//            columnService.updateNav(xpwNav);
//        }
//        
//        // 患者表扬
//        List<XPWNav> praiseNavs = columnService.getNavsByChildColumnIdOrderByPublishTime(41);
//        List<XPWArticle> lastestPraise = articleService.indexLastestColumn("患者表扬");
//        if (lastestPraise.size() > praiseNavs.size()) {
//            lastestPraise = lastestPraise.subList(0, praiseNavs.size());
//        }
//        
//        for (int i = 0; i < lastestPraise.size(); i++) {
//            XPWNav xpwNav = praiseNavs.get(i);
//            xpwNav.setArticleTitle(lastestPraise.get(i).getTitle());
//            xpwNav.setUrl(lastestPraise.get(i).getUrl());
//            xpwNav.setImgUrl(lastestPraise.get(i).getImgUrl());
//            xpwNav.setPublishTime(lastestPraise.get(i).getPublishTime());
//            columnService.updateNav(xpwNav);
//        }
//        
//        // 护理概况 第一条不自动更新
//        List<XPWNav> careNavs = columnService.getNavsByChildColumnIdOrderByPublishTime(43);
//        List<XPWArticle> lastestCare = articleService.indexLastestColumn("护理概况");
//        if (lastestCare.size() > careNavs.size()) {
//            lastestCare = lastestCare.subList(0, careNavs.size());
//        }
//        
//        for (int i = 1; i < lastestCare.size(); i++) {
//            XPWNav xpwNav = careNavs.get(i);
//            xpwNav.setArticleTitle(lastestCare.get(i).getTitle());
//            xpwNav.setUrl(lastestCare.get(i).getUrl());
//            xpwNav.setImgUrl(lastestCare.get(i).getImgUrl());
//            xpwNav.setPublishTime(lastestCare.get(i).getPublishTime());
//            columnService.updateNav(xpwNav);
//        }
//        
//        // 护理新闻
//        List<XPWNav> careNewsNavs = columnService.getNavsByChildColumnIdOrderByPublishTime(44);
//        List<XPWArticle> lastestCareNews = articleService.indexLastestColumn("护理新闻");
//        if (lastestCareNews.size() > careNewsNavs.size()) {
//            lastestCareNews = lastestCareNews.subList(0, careNewsNavs.size());
//        }
//        
//        for (int i = 0; i < lastestCareNews.size(); i++) {
//            XPWNav xpwNav = careNewsNavs.get(i);
//            xpwNav.setArticleTitle(lastestCareNews.get(i).getTitle());
//            xpwNav.setUrl(lastestCareNews.get(i).getUrl());
//            xpwNav.setImgUrl(lastestCareNews.get(i).getImgUrl());
//            xpwNav.setPublishTime(lastestCareNews.get(i).getPublishTime());
//            columnService.updateNav(xpwNav);
//        }
//        
//        // 天使风采
//        List<XPWNav> angleNavs = columnService.getNavsByChildColumnIdOrderByPublishTime(45);
//        List<XPWArticle> lastestAngle = articleService.indexLastestColumn("天使风采");
//        if (lastestAngle.size() > angleNavs.size()) {
//            lastestAngle = lastestAngle.subList(0, angleNavs.size());
//        }
//        
//        for (int i = 0; i < lastestAngle.size(); i++) {
//            XPWNav xpwNav = angleNavs.get(i);
//            xpwNav.setArticleTitle(lastestAngle.get(i).getTitle());
//            xpwNav.setUrl(lastestAngle.get(i).getUrl());
//            xpwNav.setImgUrl(lastestAngle.get(i).getImgUrl());
//            xpwNav.setPublishTime(lastestAngle.get(i).getPublishTime());
//            columnService.updateNav(xpwNav);
//        }
//        
//        // 护理宣教
//        List<XPWNav> angleNewsNavs = columnService.getNavsByChildColumnIdOrderByPublishTime(46);
//        List<XPWArticle> lastestAngleNews = articleService.indexLastestColumn("护理宣教");
//        if (lastestAngleNews.size() > angleNewsNavs.size()) {
//            lastestAngleNews = lastestAngleNews.subList(0, angleNewsNavs.size());
//        }
//        
//        for (int i = 0; i < lastestAngleNews.size(); i++) {
//            XPWNav xpwNav = angleNewsNavs.get(i);
//            xpwNav.setArticleTitle(lastestAngleNews.get(i).getTitle());
//            xpwNav.setUrl(lastestAngleNews.get(i).getUrl());
//            xpwNav.setImgUrl(lastestAngleNews.get(i).getImgUrl());
//            xpwNav.setPublishTime(lastestAngleNews.get(i).getPublishTime());
//            columnService.updateNav(xpwNav);
//        }
////        return ResponseEntity.ok(videoNavs);
//        System.out.println(">>>>server后台 结束执行定时任务......");
//    }
    
    
    @RequestMapping(value = { "/updateTask" }, method = RequestMethod.POST)
    public ResponseEntity<?> jobs()   {  
        
     // 护理宣教
        List<XPWNav> angleNewsNavs = columnService.getNavsByChildColumnIdOrderByPublishTime(46);
        List<XPWArticle> lastestAngleNews = articleService.indexLastestColumn("护理宣教");
        if (lastestAngleNews.size() > angleNewsNavs.size()) {
            lastestAngleNews = lastestAngleNews.subList(0, angleNewsNavs.size());
        }
        
        for (int i = 0; i < lastestAngleNews.size(); i++) {
            XPWNav xpwNav = angleNewsNavs.get(i);
            xpwNav.setArticleTitle(lastestAngleNews.get(i).getTitle());
            xpwNav.setUrl(lastestAngleNews.get(i).getUrl());
            xpwNav.setImgUrl(lastestAngleNews.get(i).getImgUrl());
            xpwNav.setPublishTime(lastestAngleNews.get(i).getPublishTime());
            columnService.updateNav(xpwNav);
        }
        
//        job();
        return ResponseEntity.ok(angleNewsNavs);
    }
}

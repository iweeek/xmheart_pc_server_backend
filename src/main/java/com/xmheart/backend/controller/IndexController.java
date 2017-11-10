package com.xmheart.backend.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.xmheart.model.XPWIndex;
import com.xmheart.model.XPWXTIndex;
import com.xmheart.service.IndexService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = "首页管理接口")
@Controller
public class IndexController {
	
	@Autowired 
	IndexService indexService;
	
    @ApiOperation(value = "获取首页信息", notes = "获取首页信息")
    @RequestMapping(value = { "/indexPage" }, method = RequestMethod.GET)
    public ResponseEntity<?> index() {
        
        XPWIndex index = indexService.indexRead();

        return ResponseEntity.ok(index);
        
    }
    
    @ApiOperation(value = "更新首页信息", notes = "更新首页信息")
    @RequestMapping(value = { "/indexPage/{id}" }, method = RequestMethod.POST)
    public ResponseEntity<?> update(@ApiParam("首页信息的Id") @PathVariable Long id,
            @ApiParam("轮播图1的地址") @RequestParam(required = false) String bannerImage1Url, 
            @ApiParam("轮播图2的地址") @RequestParam(required = false) String bannerImage2Url,
            @ApiParam("轮播图3的地址") @RequestParam(required = false) String bannerImage3Url,
            @ApiParam("轮播图1的响应地址") @RequestParam(required = false) String bannerImage1ActionUrl,
            @ApiParam("轮播图2的响应地址") @RequestParam(required = false) String bannerImage2ActionUrl,
            @ApiParam("轮播图3的响应地址") @RequestParam(required = false) String bannerImage3ActionUrl,
            @ApiParam("轮播文章1的题目") @RequestParam(required = false) String bannerArticle1Title,
            @ApiParam("轮播文章2的题目") @RequestParam(required = false) String bannerArticle2Title,
            @ApiParam("轮播文章3的题目") @RequestParam(required = false) String bannerArticle3Title,
            @ApiParam("轮播文章1的地址") @RequestParam(required = false) String bannerArticle1Url,
            @ApiParam("轮播文章2的地址") @RequestParam(required = false) String bannerArticle2Url,
            @ApiParam("轮播文章3的地址") @RequestParam(required = false) String bannerArticle3Url,
            @ApiParam("轮播文章1的摘要") @RequestParam(required = false) String bannerArticle1Brief,
            @ApiParam("轮播文章2的摘要") @RequestParam(required = false) String bannerArticle2Brief,
            @ApiParam("轮播文章3的摘要") @RequestParam(required = false) String bannerArticle3Brief,
            @ApiParam("轮播文章1的标签") @RequestParam(required = false) String bannerArticle1Tag,
            @ApiParam("轮播文章2的标签") @RequestParam(required = false) String bannerArticle2Tag,
            @ApiParam("轮播文章3的标签") @RequestParam(required = false) String bannerArticle3Tag
            ) {
        XPWIndex index = new XPWIndex();
        index.setId(id);
        
        if (bannerImage1Url != null) {
            index.setBannerImage1Url(bannerImage1Url);
        }
        
        if (bannerImage2Url != null) {
            index.setBannerImage2Url(bannerImage2Url);
        }
        
        if (bannerImage3Url != null) {
            index.setBannerImage3Url(bannerImage3Url);
        }
        
        if (bannerImage1ActionUrl != null) {
            index.setBannerImage1ActionUrl(bannerImage1ActionUrl);
        }
        
        if (bannerImage2ActionUrl != null) {
            index.setBannerImage2ActionUrl(bannerImage2ActionUrl);
        }
        
        if (bannerImage3ActionUrl != null) {
            index.setBannerImage3ActionUrl(bannerImage3ActionUrl);
        }
        
        if (bannerArticle1Url != null) { 
            index.setBannerArticle1Url(bannerArticle1Url);
        }
        
        if (bannerArticle2Url != null) {
            index.setBannerArticle2Url(bannerArticle2Url);
        }
        
        if (bannerArticle3Url != null) {
            index.setBannerArticle3Url(bannerArticle3Url);
        }
        
        if (bannerArticle1Brief != null) {
            index.setBannerArticle1Brief(bannerArticle1Brief);
        }
        
        if (bannerArticle2Brief != null) {
            index.setBannerArticle2Brief(bannerArticle2Brief);
        }
        
        if (bannerArticle3Brief != null) {
            index.setBannerArticle3Brief(bannerArticle3Brief);
        }
        
        if (bannerArticle1Title != null) {
            index.setBannerArticle1Title(bannerArticle1Title);
        }
        
        if (bannerArticle2Title != null) {
            index.setBannerArticle2Title(bannerArticle2Title);
        }
        
        if (bannerArticle3Title != null) {
            index.setBannerArticle3Title(bannerArticle3Title);
        }
        
        if (bannerArticle1Tag != null) {
            index.setBannerArticle1Tag(bannerArticle1Tag);
        }
        
        if (bannerArticle2Tag != null) {
            index.setBannerArticle2Tag(bannerArticle2Tag);
        }
        
        if (bannerArticle3Tag != null) {
            index.setBannerArticle3Tag(bannerArticle3Tag);
        }        
        
        int ret = indexService.update(index);
        if (ret > 0) {
            return ResponseEntity.ok(index);
        } else {
            return ResponseEntity.status(HttpServletResponse.SC_INTERNAL_SERVER_ERROR).body(null);
        }
    }
    
    @ApiOperation(value = "获取胸痛首页信息", notes = "获取胸痛首页信息")
    @RequestMapping(value = { "/xtIndexPage" }, method = RequestMethod.GET)
    public ResponseEntity<?> xtIndex() {
        
        XPWXTIndex index = indexService.xtIndexRead();

        return ResponseEntity.ok(index);
        
    }
    
    @ApiOperation(value = "更新胸痛首页信息", notes = "更新胸痛首页信息")
    @RequestMapping(value = { "/xtIndexPage/{id}" }, method = RequestMethod.POST)
    public ResponseEntity<?> update(@ApiParam("首页信息的Id") @PathVariable Long id,
            @ApiParam("轮播图1的地址") @RequestParam() String bannerImage1Url, 
            @ApiParam("轮播图2的地址") @RequestParam() String bannerImage2Url,
            @ApiParam("轮播图3的地址") @RequestParam() String bannerImage3Url,
            @ApiParam("轮播图1的响应地址") @RequestParam() String bannerImage1ActionUrl,
            @ApiParam("轮播图2的响应地址") @RequestParam() String bannerImage2ActionUrl,
            @ApiParam("轮播图3的响应地址") @RequestParam() String bannerImage3ActionUrl
            ) {
        XPWXTIndex index = new XPWXTIndex();
        index.setId(id);
        index.setBannerImage1Url(bannerImage1Url);
        index.setBannerImage2Url(bannerImage2Url);
        index.setBannerImage3Url(bannerImage3Url);
        index.setBannerImage1ActionUrl(bannerImage1ActionUrl);
        index.setBannerImage2ActionUrl(bannerImage2ActionUrl);
        index.setBannerImage3ActionUrl(bannerImage3ActionUrl);
        
        int ret = indexService.xtUpdate(index);
        if (ret > 0) {
            return ResponseEntity.ok(index);
        } else {
            return ResponseEntity.status(HttpServletResponse.SC_INTERNAL_SERVER_ERROR).body(null);
        }
    }
    
}

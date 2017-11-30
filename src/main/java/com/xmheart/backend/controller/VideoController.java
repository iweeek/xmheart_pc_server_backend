package com.xmheart.backend.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.xmheart.model.XPWColumn;
import com.xmheart.model.XPWVideo;
import com.xmheart.service.VideoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = "影像管理接口")
@Controller
public class VideoController {

    @Autowired
    VideoService videoService;
    
    @ApiOperation(value = "获取视频列表", notes = "获取视频列表")
    @RequestMapping(value = { "/videos" }, method = RequestMethod.GET)
    public ResponseEntity<?> index(Model model) {
        List<XPWVideo> list = new ArrayList<XPWVideo>();

        list = videoService.index();
        if (list.size() > 0) {
            return ResponseEntity.ok(list);
        } else {
            return ResponseEntity.status(HttpServletResponse.SC_NOT_FOUND).body(null);
        }
    }
    
    @ApiOperation(value = "获取院报", notes = "获取院报列表")
    @RequestMapping(value = { "/videos/{id}" }, method = RequestMethod.GET)
    public ResponseEntity<?> read(@PathVariable Long id) {
        XPWVideo video = videoService.read(id);
        if (video != null) {
            return ResponseEntity.ok(video);
        } else {
            return ResponseEntity.status(HttpServletResponse.SC_NOT_FOUND).body(null);
        }
    }    

    @ApiOperation(value = "创建一个视频", notes = "创建一个视频")
    @RequestMapping(value = { "/videos" }, method = RequestMethod.POST)
    public ResponseEntity<?> create(@ApiParam("视频标题") @RequestParam String title,
            @ApiParam("视频缩略图的地址") @RequestParam(required = false) String imgUrl,
            @ApiParam("视频地址") @RequestParam(required = false) String videoUrl,
            @ApiParam("视频描述") @RequestParam(required = false) String brief,
            @ApiParam("是否置顶") @RequestParam(required = false) Boolean isPinned,
            @ApiParam("是否发布") @RequestParam(required = false) Boolean isPublished,
            @ApiParam("发布时间") @RequestParam(required = false) String publishTime) {
        XPWVideo video = new XPWVideo();

        if (imgUrl != null) {
        		video.setTitle(title);
        } else {
        		video.setTitle("");
        }

        if (imgUrl != null) {
            video.setImgUrl(imgUrl);
        } else {
        		video.setImgUrl("");
        }

        if (videoUrl != null) {
            video.setVideoUrl(videoUrl);
        } else {
    			video.setVideoUrl("");
        }

        if (brief != null) {
            video.setBrief(brief);
        } else {
        		video.setBrief("");
        }

        if (isPinned != null) {
            video.setIsPinned(isPinned);
        } else {
        		video.setIsPinned(false);
        }
        
        if (isPublished != null) {
            video.setIsPublished(isPublished);
        } else {
        		video.setIsPublished(false);
        }
      
        if (publishTime != null) {
            long milliSeconds= Long.parseLong(publishTime);
            video.setPublishTime(new Date(milliSeconds));
        } else {
            video.setPublishTime(new Date());
        }
       
        video.setUrl("");

        int ret = videoService.create(video);
        if (ret > 0) {
            video.setUrl("/videoNewsDetail?id=" + String.valueOf(video.getId()));
            videoService.update(video);
            return ResponseEntity.status(HttpServletResponse.SC_CREATED).body(video);
        } else {
            return ResponseEntity.status(HttpServletResponse.SC_INTERNAL_SERVER_ERROR).body(null);
        }
    }
    
    @ApiOperation(value = "更新一个视频", notes = "更新一篇视频")
    @RequestMapping(value = { "/videos/{id}" }, method = RequestMethod.POST)
    public ResponseEntity<?> update(@ApiParam("视频Id") @PathVariable Long id,
            @ApiParam("视频标题") @RequestParam(required = false) String title,
            @ApiParam("视频缩略图的地址") @RequestParam(required = false) String imgUrl,
            @ApiParam("视频地址") @RequestParam(required = false) String videoUrl,
            @ApiParam("视频描述") @RequestParam(required = false) String brief,
            @ApiParam("文章发表时间，可选") @RequestParam(required = false) String publishTime,
            @ApiParam("是否置顶") @RequestParam(required = false) Boolean isPinned,
            @ApiParam("图片地址") @RequestParam(required = false) Boolean isPublished) {
        XPWVideo video = new XPWVideo();
        
        video.setId(id);

        video.setTitle(title);
        
        if (imgUrl != null) {
            video.setImgUrl(imgUrl);
        }

        if (videoUrl != null) {
            video.setVideoUrl(videoUrl);
        }

        if (brief != null) {
            video.setBrief(brief);
        }

        if (isPinned != null) {
            video.setIsPinned(isPinned);
        }
        
        if (isPublished != null) {
            video.setIsPublished(isPublished);
        }
        
        if (publishTime != null) {
            long milliSeconds= Long.parseLong(publishTime);
            video.setPublishTime(new Date(milliSeconds));
        }

        int ret = videoService.update(video);
        if (ret > 0) {
            return ResponseEntity.ok(null);
        } else {
            return ResponseEntity.status(HttpServletResponse.SC_INTERNAL_SERVER_ERROR).body(null);
        }
    }

}

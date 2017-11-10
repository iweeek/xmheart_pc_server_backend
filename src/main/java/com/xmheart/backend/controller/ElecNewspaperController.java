package com.xmheart.backend.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.xmheart.model.XPWElecNewspaper;
import com.xmheart.service.NewspaperService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = "院报管理接口")
@Controller
public class ElecNewspaperController {

    @Autowired
    NewspaperService newspaperService;
    
    @ApiOperation(value = "获取院报列表", notes = "获取院报列表")
    @RequestMapping(value = { "/newspapers" }, method = RequestMethod.GET)
    public ResponseEntity<?> index() {
        List<XPWElecNewspaper> list = new ArrayList<XPWElecNewspaper>();

        list = newspaperService.index();
        if (list.size() > 0) {
            return ResponseEntity.ok(list);
        } else {
            return ResponseEntity.status(HttpServletResponse.SC_NOT_FOUND).body(null);
        }
    }
    
    @ApiOperation(value = "获取院报列表", notes = "获取院报列表")
    @RequestMapping(value = { "/newspapers/{id}" }, method = RequestMethod.GET)
    public ResponseEntity<?> read(@PathVariable Long id) {
        XPWElecNewspaper newspaper = newspaperService.read(id);
        if (newspaper != null) {
            return ResponseEntity.ok(newspaper);
        } else {
            return ResponseEntity.status(HttpServletResponse.SC_NOT_FOUND).body(null);
        }
    }    

    @ApiOperation(value = "创建一个院报", notes = "创建一个院报")
    @RequestMapping(value = { "/newspapers" }, method = RequestMethod.POST)
    public ResponseEntity<?> create(@ApiParam("院报标题") @RequestParam String title,
            @ApiParam("院报年份") @RequestParam String year,
            @ApiParam("院报期数") @RequestParam String times,
            @ApiParam("页数") @RequestParam() Byte page,
            @ApiParam("下载地址") @RequestParam(required = false) String downloadUrl,
            @ApiParam("图片地址") @RequestParam(required = false) String imageUrl,
            @ApiParam("是否发布") @RequestParam(required = false) Boolean isPublished) {
        XPWElecNewspaper newspaper = new XPWElecNewspaper();

        newspaper.setTitle(title);

        newspaper.setYears(year);

        newspaper.setTimes(times);
        
        newspaper.setPage(page);

        if (downloadUrl != null) {
            newspaper.setDownloadUrl(downloadUrl);
        } else {
        		newspaper.setDownloadUrl("");
        }

        if (imageUrl != null) {
            newspaper.setImageUrl(imageUrl);
        } else {
    			newspaper.setImageUrl("");
        }
        
        if (isPublished != null) {
            newspaper.setIsPublished(isPublished);
        } else {
    			newspaper.setIsPublished(false);
        }
        
        newspaper.setUrl("/elecNewsPaper?page=" + page + "&year=" + year + "&times=" + times);
        
        newspaper.setPublishTime(new Date());

        int ret = newspaperService.create(newspaper);
        if (ret > 0) {
            return ResponseEntity.ok(newspaper);
        } else {
            return ResponseEntity.status(HttpServletResponse.SC_INTERNAL_SERVER_ERROR).body(null);
        }

    }
    
    @ApiOperation(value = "更新一个视频", notes = "更新一篇视频")
    @RequestMapping(value = { "/newspapers/{id}" }, method = RequestMethod.POST)
    public ResponseEntity<?> update(@ApiParam("院报Id") @PathVariable Long id,
            @ApiParam("院报标题") @RequestParam(required = false) String title,
            @ApiParam("院报年份") @RequestParam(required = false) String year,
            @ApiParam("院报期数") @RequestParam(required = false) String times,
            @ApiParam("页数") @RequestParam(required = false) Byte page,
            @ApiParam("下载地址") @RequestParam(required = false) String downloadUrl,
            @ApiParam("图片地址") @RequestParam(required = false) String imageUrl,
            @ApiParam("是否发布") @RequestParam(required = false) Boolean isPublished) {
        XPWElecNewspaper newspaper = new XPWElecNewspaper();
        
        newspaper.setId(id);

        if (title != null) { 
            newspaper.setTitle(title);
        }

        newspaper.setYears(year);

        newspaper.setTimes(times);
        
        newspaper.setPage(page);
        
        newspaper.setUrl("/elecNewsPaper?page=" + page + "&year=" + year + "&times=" + times);

        if (downloadUrl != null) {
            newspaper.setDownloadUrl(downloadUrl);
        }

        if (imageUrl != null) {
            newspaper.setImageUrl(imageUrl);
        }
        
        if (isPublished != null) {
            newspaper.setIsPublished(isPublished);
        }
        
        int ret = newspaperService.update(newspaper);
        if (ret > 0) {
            return ResponseEntity.ok(null);
        } else {
            return ResponseEntity.status(HttpServletResponse.SC_INTERNAL_SERVER_ERROR).body(null);
        }

    }

}

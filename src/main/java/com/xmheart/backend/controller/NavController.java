package com.xmheart.backend.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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

import com.xmheart.model.XPWArticle;
import com.xmheart.model.XPWColumn;
import com.xmheart.model.XPWElecNewspaper;
import com.xmheart.model.XPWNav;
import com.xmheart.service.ArticleService;
import com.xmheart.service.ColumnService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = "后台接口，导航管理接口")
@Controller
public class NavController {
    
    @Autowired 
    private ColumnService ColumnService;
    
    @Autowired 
    ArticleService articleService;
    
    @ApiOperation(value = "根据一级栏目获取所有导航内容", notes = "根据一级栏目获取所有导航内容")
    @RequestMapping(value = { "/navs" }, method = RequestMethod.GET)
    public ResponseEntity<?> index(@ApiParam("一级栏目的Id") @RequestParam Long columnId) {
        
        List<XPWNav> list;
        list = ColumnService.getNavsByColumnId(columnId);
        
        return ResponseEntity.status(HttpServletResponse.SC_OK).body(list);
    }
    
    @ApiOperation(value = "根据Id获取导航内容", notes = "根据Id获取导航内容")
    @RequestMapping(value = { "/navs/{id}" }, method = RequestMethod.GET)
    public ResponseEntity<?> read(@ApiParam("导航条目的Id") @PathVariable Long id) {
        
        XPWNav nav = ColumnService.getNavById(id);
        if (nav == null) {
            return ResponseEntity.status(HttpServletResponse.SC_NOT_FOUND).body(null);
        } else {
            return ResponseEntity.status(HttpServletResponse.SC_OK).body(nav);
        }
    }
    
    @ApiOperation(value = "根据Id更新导航内容", notes = "根据Id更新导航内容")
    @RequestMapping(value = { "/navs/{id}" }, method = RequestMethod.POST)
    public ResponseEntity<?> update(@ApiParam("导航条目的Id") @PathVariable Long id, 
    			@ApiParam("导航标题") @RequestParam(required = false) String articleTitle, 
    			@ApiParam("文章摘要") @RequestParam(required = false) String brief,
    			@ApiParam("导航条目的Id") @RequestParam(required = false) String url, 
    			@ApiParam("导航条目的Id") @RequestParam(required = false) String publishTime, 
            @ApiParam("图片地址") @RequestParam(required = false) String imgUrl) {
        
        XPWNav nav = new XPWNav();
        nav.setId(id);
        
        nav.setArticleTitle(articleTitle);
        if (!publishTime.equals("")) {
	        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	        try {
				nav.setPublishTime(format.parse(publishTime));
			} catch (ParseException e) {
				e.printStackTrace();
			}
        } else {
        		nav.setPublishTime(new Date());
        }
        nav.setUrl(url);
        nav.setImgUrl(imgUrl);
        if (brief.length() > 200) {
            brief = brief.substring(0, 200);
        }
        nav.setBrief(brief);
        
        int ret = ColumnService.updateNav(nav);
        if (ret == 0) {
            return ResponseEntity.status(HttpServletResponse.SC_NOT_FOUND).body(null);
        } else {
            return ResponseEntity.status(HttpServletResponse.SC_OK).body(null);
        }
    }

}

package com.xmheart.backend.controller;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.xmheart.model.XPWArticle;
import com.xmheart.model.XPWColumn;
import com.xmheart.model.XPWNav;
import com.xmheart.service.ArticleService;
import com.xmheart.service.ColumnService;
import com.xmheart.service.NewsService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Controller
public class BackendNewsController {

    @Autowired
    private ColumnService columnService;
    
    @Autowired
    private ArticleService articleService;

    @Autowired
    private NewsService newsService;

    private final int PAGE_SIZE = 10;

    private final String NEWS_COLUMN_NAME = "新闻公告";
    private final String MEDIA_NEWS_COLUMN_NAME = "媒体看厦心";
    private final String HOSPITAL_NEWS_COLUMN_NAME = "医院新闻";
    private final String VIDEO_NEWS_COLUMN_NAME = "影像厦心";
    private final String ELECPAPER_NEWS_COLUMN_NAME = "电子院报";

    static final long NEWS_COLUMN_ID = 5;
    static final long EXPERT_COLUMN_ID = 3;

    public String delHTMLTag(String htmlStr) {
        String regEx_style = "<style[^>]*?>[\\s\\S]*?<\\/style>"; // 定义style的正则表达式
        String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式

        Pattern p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
        Matcher m_style = p_style.matcher(htmlStr);
        htmlStr = m_style.replaceAll(""); // 过滤style标签

        Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
        Matcher m_html = p_html.matcher(htmlStr);
        htmlStr = m_html.replaceAll(""); // 过滤html标签

        htmlStr = htmlStr.replace(" ", "");
        htmlStr = htmlStr.replaceAll("\\s*|\t|\r|\n", "");
        htmlStr = htmlStr.replace("“", "");
        htmlStr = htmlStr.replace("”", "");
        htmlStr = htmlStr.replaceAll("　", "");
        htmlStr = htmlStr.replaceAll("&nbsp;", " ");

        return htmlStr.trim(); // 返回文本字符串
    }

//    @ApiOperation(value = "获取一级栏目", notes = "")
//    @RequestMapping(value = { "/column/first" }, method = RequestMethod.GET)
//    public ResponseEntity<?> getFirstColumns() {
//        List<XPWColumn> columnList = columnService.getFirstColumns();
//
//        return ResponseEntity.ok().body(columnList);
//    }
//
//    @ApiOperation(value = "获取二级栏目", notes = "")
//    @RequestMapping(value = { "/column/second" }, method = RequestMethod.GET)
//    public ResponseEntity<?> getSecondColumns(@ApiParam("父级id") @RequestParam long id) {
//        List<XPWColumn> columnList = columnService.getChildColumnsById(id);
//
//        return ResponseEntity.ok(columnList);
//    }
//
//    @ApiOperation(value = "获取导航信息", notes = "获取导航信息")
//    @RequestMapping(value = { "/news/navs" }, method = RequestMethod.GET)
//    public ResponseEntity<?> navs() {
//        List<XPWNav> columnList = columnService.getNavListBySecondColumnName(NEWS_COLUMN_NAME);
//
//        return ResponseEntity.ok(columnList);
//    }
    
    @ApiOperation(value = "媒体看厦心、医院新闻搜索文章", notes = "媒体看厦心、医院新闻搜索文章")
    @RequestMapping(value = { "/news/articles/show" }, method = RequestMethod.GET)
    public ResponseEntity<?> show(@ApiParam("文章标题的关键字") @RequestParam String keyword) {
        //TODO 需要做分页，需不需要送栏目Id呢？这个地方的结构要考虑一下。
        List<XPWArticle> list = articleService.show(keyword);

        return ResponseEntity.ok(list);
    }

    // @ApiOperation(value = "获取Nav", notes = "根据title获取Nav")
    // @RequestMapping(value = { "/column" }, method = RequestMethod.GET)
    // public ResponseEntity<?> getChildNavsByTitle(@ApiParam("title")
    // @RequestParam String title) {
    // List<XPWNav> columnList = ColumnService.getChildNavsByTitle(title);
    //
    // return ResponseEntity.ok(columnList);
    // }

//    @ApiOperation(value = "添加nav", notes = "")
//    @RequestMapping(value = { "/news/navs/{id}" }, method = RequestMethod.POST)
//    public ResponseEntity<?> createNav(@ApiParam("一级栏目id") @RequestParam Long columnId,
//            @ApiParam("一级栏目名称") @RequestParam String columnName,
//            @ApiParam("二级栏目名称") @RequestParam String childColumnName,
//            @ApiParam("文章标题") @RequestParam String articleTitle, @ApiParam("url") @RequestParam String url,
//            @ApiParam("图片url") @RequestParam String imgUrl) {
//        XPWNav nav = new XPWNav();
//        nav.setColumnId(columnId);
//        nav.setColumnName(childColumnName);
//        nav.setChildColumnName(childColumnName);
//        nav.setArticleTitle(articleTitle);
//        nav.setUrl(url);
//        nav.setImgUrl(imgUrl);
//        nav.setPublishTime(new java.util.Date());
//
//        int result = ColumnService.createNav(nav);
//        return ResponseEntity.status(result).body(null);
//    }

    @ApiOperation(value = "根据id修改新闻nav部分，修改媒体看厦心和医院新闻", notes = "根据id修改新闻nav部分，修改媒体看厦心和医院新闻")
    @RequestMapping(value = { "/news/navs/{id}" }, method = RequestMethod.POST)
    public ResponseEntity<?> updateNav(@ApiParam("唯一主键id") @PathVariable Long id, @ApiParam("文章标题") @RequestParam String title,
            @ApiParam("文章地址") @RequestParam String url) {
        XPWNav nav = new XPWNav();
        nav.setId(id);
        nav.setArticleTitle(title);
        nav.setUrl(url);
//        nav.setImgUrl(imgUrl);
        nav.setPublishTime(new java.util.Date());

        int result = columnService.updateNav(nav);
        return ResponseEntity.status(result).body(null);
    }

}
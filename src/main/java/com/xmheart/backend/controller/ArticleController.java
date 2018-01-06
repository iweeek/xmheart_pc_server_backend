package com.xmheart.backend.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xmheart.mapper.XPWPrivMapper;
import com.xmheart.model.XPWArticle;
import com.xmheart.model.XPWColumn;
import com.xmheart.model.XPWPriv;
import com.xmheart.model.XPWPrivExample;
import com.xmheart.model.XPWRole;
import com.xmheart.model.XPWUser;
import com.xmheart.service.ArticleService;
import com.xmheart.service.ColumnService;
import com.xmheart.service.RoleService;
import com.xmheart.util.FileUtil;
import com.xmheart.util.PathUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = "文章管理接口")
@Controller
public class ArticleController {
	
	@Autowired 
	ArticleService articleService;
	
    @Autowired
    private ColumnService columnService;
    
    @Autowired
    RoleService roleService;
    
    @Autowired
    XPWPrivMapper privMapper;
	
    @RequiresPermissions("article")
	@ApiOperation(value = "获取文章", notes = "获取文章")
	@RequestMapping(value = { "/articles" }, method = RequestMethod.GET)
	public ResponseEntity<?> index(@ApiParam("开始页号") @RequestParam(required = false, defaultValue = "1") Integer pageNo,
			@ApiParam("每页的数目") @RequestParam(required = false, defaultValue = "10") Integer pageSize,
			@ApiParam("栏目Id") @RequestParam(required = false) Long columnId, HttpSession httpSession) {
		List<XPWArticle> list = null;
		List<Long> allColumns = null;
		List<XPWPriv> privs = null;
		List<Long> filterColumns = new ArrayList();
		List<Long> filterAllColumns = new ArrayList();
		allColumns = getChildColumns(allColumns, columnId);
		
		XPWUser user = (XPWUser) httpSession.getAttribute("user");
		XPWUser user1 = (XPWUser) SecurityUtils.getSubject().getPrincipal();
		
		boolean permitted = SecurityUtils.getSubject().isPermitted("admin");
		XPWRole role = roleService.read(Long.valueOf(user.getRoleIds()));
		String privIds = role.getPrivIds();
        XPWPrivExample example = new XPWPrivExample();
        if (privIds != null) {
            String[] split = privIds.split(",");
          
            StringBuilder sb = new StringBuilder();
            for (String item : split) {
                long pId= Long.parseLong(item);
                example.or().andIdEqualTo(pId);
            }
            privs = privMapper.selectByExample(example);
            
        }
        
        for (int i = 0; i< privs.size(); i++) {
            getChildColumns(filterAllColumns, privs.get(i).getColumnId());
        }
        PageHelper.startPage(1, pageSize);
        if (columnId == null || columnId == 0) {
            PageHelper.startPage(pageNo, pageSize);
            list = articleService.index(filterAllColumns);
        } else {
            if (filterAllColumns.contains(columnId)) {
                filterColumns = getChildColumns(filterColumns, columnId);
                PageHelper.startPage(pageNo, pageSize);
                list = (articleService.index(filterColumns));
            } else {
                return ResponseEntity.status(HttpServletResponse.SC_FORBIDDEN).body(null);
            }
        }
        
        PageInfo pageInfo = new PageInfo(list);
        // model.addAttribute("pageInfo", pageInfo);
		return ResponseEntity.ok(pageInfo);
	}

	/**
	 * 得到所有的这一栏目下的子栏目ID，包括父栏目
	 * @param list
	 * @param columnId
	 * @return
	 */
	public List<Long> getChildColumns(List<Long> list, Long columnId) {
		if (list == null) {
			list = new ArrayList<Long>();
		}
		if (!list.contains(columnId)) {
		    list.add(columnId);
		}
		List<XPWColumn> columns = columnService.getColumnsByParentId(columnId);
		if (columns.size() == 0) {
		    if (!list.contains(columnId)) {
		        list.add(columnId);
		    }
			return list;
		} else {
			for (XPWColumn item : columns) {
				getChildColumns(list, item.getId());
			}
		}
		System.out.println(list);
		return list;
	}
    
	
    //TODO 这个接口要移到后台系统中去
	@ApiOperation(value = "创建一篇文章", notes = "创建一篇文章")
	@RequestMapping(value = { "/articles" }, method = RequestMethod.POST)
    public ResponseEntity<?> create(@ApiParam("子栏目Id，必填") @RequestParam Long columnId, 
            @ApiParam("文章标题，必填") @RequestParam String title, 
            @ApiParam("文章配图，可选") @RequestParam(required = false) String imgUrl, 
            @ApiParam("文章关键字，可选") @RequestParam(required = false) String tags, 
            @ApiParam("文章发表时间，可选") @RequestParam(required = false) String publishTime,
            @ApiParam("文章摘要，可选") @RequestParam(required = false) String brief, 
            @ApiParam("文章内容，可选") @RequestParam(required = false) String content) {
		XPWArticle article = new XPWArticle();
		
		article.setColumnId(columnId);
		String columnName = columnService.getColumnById(columnId).getColumnName();
		article.setColumnName(columnName);
		article.setTitle(title);
		
		if (imgUrl != null) {
		    article.setImgUrl(imgUrl);
		} else {
		    article.setImgUrl("");
		}
		
		if (publishTime != null) {
		    long milliSeconds= Long.parseLong(publishTime);
            article.setPublishTime(new Date(milliSeconds));
		} else {
		    article.setPublishTime(new Date());
		}
		
		//创建的时候不设置这几项
		article.setIsPinned(false);
		article.setIsPublished(false);
		article.setUrl("");
		article.setPinOrder((byte) 0);
		
		if (tags != null) {
		    article.setTags(tags);
		} else {
		   article.setTags("");
		}
		
		if (brief != null) {
		    article.setBrief(brief);
		} else {
		    article.setBrief("");
		}
		
		if (content != null) {
		    article.setContent(content);
		} else {
		    article.setContent("");
		}
		
	    article.setIsEnglish((byte)0);
	      
		int ret = articleService.create(article);
		if (ret > 0) {
		    article.setUrl("/newsDetail?id=" + String.valueOf(article.getId()));
		    articleService.update(article);
			return ResponseEntity.ok(article);
		} else {
			return ResponseEntity.status(HttpServletResponse.SC_INTERNAL_SERVER_ERROR).body(null);
		}
    }
	
	@ApiOperation(value = "交换文章置顶顺序", notes = "交换文章置顶顺序")
    @RequestMapping(value = { "/articles/swapPinOrder" }, method = RequestMethod.POST)
	public ResponseEntity<?> swapPinOrder(@ApiParam("文章1的Id") Long articleId1, 
	        @ApiParam("文章2的Id") Long articleId2) {
	    int ret = articleService.swapPinOrder(articleId1, articleId2);
	    if (ret == 0) {
	        return ResponseEntity.ok(null);
	    } else {
	        return ResponseEntity.status(HttpServletResponse.SC_FORBIDDEN).body(null);
	    }
	}
	
    @ApiOperation(value = "更新一篇文章", notes = "更新一篇文章")
    @RequestMapping(value = { "/articles/{id}" }, method = RequestMethod.POST)
    public ResponseEntity<?> update(@ApiParam("文章Id，必填") @PathVariable Long id,  
            @ApiParam("栏目Id，仅限于子栏目，可选") @RequestParam(required = false) Long columnId, 
            @ApiParam("文章配图，可选") @RequestParam(required = false) String imgUrl, 
            @ApiParam("文章是否置顶，可选") @RequestParam(required = false) Boolean isPinned, 
            @ApiParam("文章置顶的顺序，可选") @RequestParam(required = false) Byte pinOrder, 
            @ApiParam("文章发表时间，可选") @RequestParam(required = false) String publishTime,
            @ApiParam("文章是否发表，可选") @RequestParam(required = false) Boolean isPublished, 
            @ApiParam("文章关键字，可选") @RequestParam(required = false) String tags, 
            @ApiParam("文章标题，可选") @RequestParam(required = false) String title, 
            @ApiParam("文章摘要，可选") @RequestParam(required = false) String brief,
            @ApiParam("文章内容，可选") @RequestParam(required = false) String content) {
        
        XPWArticle article = new XPWArticle();
        article.setId(id);
        if (columnId != null) {
            article.setColumnId(columnId);
            XPWColumn column = columnService.getColumnById(columnId);
            article.setColumnName(column.getColumnName());
        }
        
        if (imgUrl != null) {
            article.setImgUrl(imgUrl);
        }
        
        //pinOrder和isPinned具有联动性
        if (isPinned != null) {
            article.setIsPinned(isPinned);
            if (isPinned) {
                pinOrder = articleService.getMaxPinOrder();
                article.setPinOrder((byte) (pinOrder + 1));
                
            } else {
                article.setPinOrder((byte) 0);
            }
        }
        
//        if (publishTime == null) {
//            Date date = new Date();
////            Timestamp ts = Timestamp.valueOf(publishTime); 
////            date = ts;
//            article.setPublishTime(date);
//        } else {
//        		
//        }
        
        if (publishTime != null) {
            long milliSeconds= Long.parseLong(publishTime);
            article.setPublishTime(new Date(milliSeconds));
        }
        
        if (isPublished != null) {
            article.setIsPublished(isPublished);
//            if (isPublished) {
//                article.setPublishTime(new Date());
//            }
        }
        
        if (tags != null) {
            article.setTags(tags);
        }
        
        if (title != null) {
            article.setTitle(title);
        }
        
        if (brief != null) {
            article.setBrief(brief);
        }
        
        if (content != null) {
            article.setContent(content);
        }
        
        int ret = articleService.update(article);
        if (ret > 0) {
            return ResponseEntity.ok(null);
        } else {
            return ResponseEntity.status(HttpServletResponse.SC_NOT_FOUND).body(null);
        }
    }
    
	@RequestMapping(value = { "/articles/{id}" }, method = RequestMethod.DELETE)
	public ResponseEntity<?> delete(@ApiParam("文章Id，必填") @PathVariable Long id) {
		
		int ret = articleService.delete(id);
		if (ret > 0) {
			return ResponseEntity.ok(null);
		} else {
			return ResponseEntity.status(HttpServletResponse.SC_INTERNAL_SERVER_ERROR).body(null);
		}
	}
    
    @ApiOperation(value = "获取一篇文章", notes = "获取一篇文章")
    @RequestMapping(value = { "/articles/{id}" }, method = RequestMethod.GET)
    public ResponseEntity<?> read(@ApiParam("文章Id，必填") @PathVariable Long id) {
        
        XPWArticle article = new XPWArticle();
        
        article.setId(id);
        
        article = articleService.read(id);
        if (article != null) {
            return ResponseEntity.ok(article);
        } else {
            return ResponseEntity.status(HttpServletResponse.SC_NOT_FOUND).body(null);
        }
    }
	
    @RequestMapping(value = { "/uploadImage" }, method = RequestMethod.POST)
    public ResponseEntity<?> uploadImage(@ApiParam("图片") @RequestParam MultipartFile image) {
        String imagePath = "";
        String imageUrl = "";
        try {
            imagePath = FileUtil.uploadFile(PathUtil.IMG_STORAGE_PATH, image);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        imageUrl = PathUtil.getInstance().ORIGIN + File.separator + PathUtil.IMG_FOLDER_PATH + imagePath;
        return ResponseEntity.status(HttpServletResponse.SC_CREATED).body(imageUrl);
    }
    
    @RequestMapping(value = { "/uploadVideo" }, method = RequestMethod.POST)
    public ResponseEntity<?> uploadVideo(@ApiParam("视频") @RequestParam MultipartFile video) {
        String videoPath = "";
        String videoUrl = "";
        try {
            videoPath = FileUtil.uploadFile(PathUtil.VIDEO_STORAGE_PATH, video);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        videoUrl = PathUtil.getInstance().ORIGIN + File.separator + PathUtil.VIDEO_FOLDER_PATH + videoPath;
        return ResponseEntity.status(HttpServletResponse.SC_CREATED).body(videoUrl);
    }
    
    @ApiOperation(value = "搜索文章", notes = "搜索文章")
    @RequestMapping(value = { "/articles/show" }, method = RequestMethod.GET)
    public ResponseEntity<?> show(@ApiParam("文章标题的关键字") @RequestParam String keyword, 
            @ApiParam("文章的栏目Id") @RequestParam(required = false) Long columnId,
            @ApiParam("文章的栏目的名称") @RequestParam(required = false) String columnName) {
        List<XPWArticle> list;
        if (columnId != null) {
            list = articleService.show(columnId, keyword);
        } else {
            list = articleService.show(keyword);
        }
        
        if (columnName != null) {
        	list = articleService.showByColNameAndKey(columnName, keyword);
        } else {
            list = articleService.show(keyword);
        }
        
        if (list.size() != 0) {
            return ResponseEntity.ok(list);
        } else {
            return ResponseEntity.status(HttpServletResponse.SC_NOT_FOUND).body(null);
        }
    }
}

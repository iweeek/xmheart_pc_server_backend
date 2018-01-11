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
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.joda.time.DateTime;
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
import com.mysql.cj.api.log.Log;
import com.xmheart.mapper.XPWPrivMapper;
import com.xmheart.model.XPWArticle;
import com.xmheart.model.XPWColumn;
import com.xmheart.model.XPWPriv;
import com.xmheart.model.XPWPrivExample;
import com.xmheart.model.XPWRole;
import com.xmheart.model.XPWUser;
import com.xmheart.model.XPWVisitLog;
import com.xmheart.service.ArticleService;
import com.xmheart.service.ColumnService;
import com.xmheart.service.LogService;
import com.xmheart.service.RoleService;
import com.xmheart.util.FileUtil;
import com.xmheart.util.PathUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = "日志管理接口")
@Controller
public class LogController {
	
	@Autowired 
	LogService logService;
	
    @Autowired
    RoleService roleService;
    
    @Autowired
    XPWPrivMapper privMapper;
	
    @RequiresPermissions("logs")
	@ApiOperation(value = "获取日志", notes = "获取日志")
	@RequestMapping(value = { "/logs" }, method = RequestMethod.GET)
	public ResponseEntity<?> index(@ApiParam("开始页号") @RequestParam(required = false, defaultValue = "1") Integer pageNo,
			@ApiParam("每页的数目") @RequestParam(required = false, defaultValue = "10") Integer pageSize,
			@RequestParam(required = false, value = "startTime") String startTime,
			@RequestParam(required = false, value = "endTime") String endTime, HttpSession httpSession) {
		List<XPWVisitLog> list = null;
		List<XPWPriv> privs = null;
		
		XPWUser user = (XPWUser) httpSession.getAttribute("user");
		XPWUser user1 = (XPWUser) SecurityUtils.getSubject().getPrincipal();
		
		if (startTime.equals("0") || startTime == null) {
		    PageHelper.startPage(pageNo, pageSize);
		    list = logService.index();
		} else {
		
		    long startMilliSeconds= Long.parseLong(startTime);
    	        long endMilliSeconds= Long.parseLong(endTime);
            
    	        privs = getGrantedColumns(user);
    	        PageHelper.startPage(pageNo, pageSize);
    	        Date date = new Date(startMilliSeconds);
    	        Date date2 = new Date(endMilliSeconds);
    	        System.out.println(date);
    	        System.out.println(date2);
            list = logService.index(new Date(startMilliSeconds), new Date(endMilliSeconds));
		}
        PageInfo pageInfo = new PageInfo(list);
        return ResponseEntity.ok(pageInfo);
	}
    
    public List<XPWPriv> getGrantedColumns(XPWUser user) {
        List<XPWPriv> privs = null;
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
        return privs;
    }

}

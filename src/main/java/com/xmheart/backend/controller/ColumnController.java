package com.xmheart.backend.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.xmheart.mapper.XPWPrivMapper;
import com.xmheart.model.XPWColumn;
import com.xmheart.model.XPWPriv;
import com.xmheart.model.XPWPrivExample;
import com.xmheart.model.XPWRole;
import com.xmheart.model.XPWUser;
import com.xmheart.service.ColumnService;
import com.xmheart.service.RoleService;
import com.xmheart.service.TokenService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = "栏目管理接口")
@Controller
public class ColumnController {
    @Autowired 
    private TokenService tokenService;
    
    @Autowired 
    private ColumnService ColumnService;
    
    @Autowired
    private ColumnService columnService;
    
    @Autowired
    RoleService roleService;
    
    @Autowired
    XPWPrivMapper privMapper;
    
    @ApiOperation(value = "获取所有栏目", notes = "获取所有栏目")
    @RequestMapping(value = { "/columns" }, method = RequestMethod.GET)
    public ResponseEntity<?> index(
            @ApiParam("父栏目的Id，父栏目Id为0的表示没有父栏目") @RequestParam(required = false) Long parentColumnId, 
            HttpSession httpSession, HttpServletRequest request) {
        List<XPWPriv> privs = null;
        
        XPWUser user = (XPWUser) SecurityUtils.getSubject().getPrincipal();
//        XPWUser user = (XPWUser) httpSession.getAttribute("user");
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.checkPermission("article");
        } catch (UnauthenticatedException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
        
        XPWUser user1 = (XPWUser) subject.getPrincipal();
        List<XPWColumn> list = new ArrayList();
        List<XPWColumn> temp = new ArrayList();
        
        
        if (parentColumnId == null) {
            // TODO 但是前台不会传null？
            list = ColumnService.getColumns();
        } else {
            if (parentColumnId == 0) {
                temp = ColumnService.getColumnsByParentId(parentColumnId);
                 // 过滤
                  XPWRole role = roleService.read(Long.valueOf(user.getRoleIds()));
                  String privIds = role.getPrivIds();
                  XPWPrivExample example = new XPWPrivExample();
                  if (privIds != null) {
                      String[] split = privIds.split(",");
                    
                      for (String item : split) {
                          long pId= Long.parseLong(item);
                          if (pId <= 22 || pId == 30) {
                              example.or().andIdEqualTo(pId);
                          }
                      }
                      privs = privMapper.selectByExample(example);
                  }
                  
                  for (XPWColumn col : temp) {
                      for (XPWPriv p : privs) {
                          if (col.getId() == p.getColumnId()) {
                              list.add(col);
                          }
                      }
                  }
            } else {
                list = ColumnService.getColumnsByParentId(parentColumnId);
            }
            
        }
        return ResponseEntity.status(HttpServletResponse.SC_OK).body(list);
    }
    
    @ApiOperation(value = "根据Id更新栏目名称", notes = "根据Id更新栏目名称")
    @RequestMapping(value = { "/columns/{id}" }, method = RequestMethod.POST)
    public ResponseEntity<?> update(@ApiParam("栏目的Id") @PathVariable Long id, @ApiParam("栏目的名称") @RequestParam String columnName) {
        
        XPWColumn column = new XPWColumn();
        column.setId(id);
        column.setColumnName(columnName);
        
        int ret = ColumnService.updateColumn(column);
        if (ret == 0) {
            return ResponseEntity.status(HttpServletResponse.SC_NOT_FOUND).body(null);
        } else {
            return ResponseEntity.status(HttpServletResponse.SC_OK).body(null);
        }
        
    }
    
    @ApiOperation(value = "获取子栏目", notes = "获取子栏目")
    @RequestMapping(value = { "/columns/{id}" }, method = RequestMethod.GET)
    public ResponseEntity<?> subColumn(@ApiParam("栏目的Id") @PathVariable Long id, HttpSession httpSession) {
        List<XPWPriv> privs = null;
        XPWUser user = (XPWUser) httpSession.getAttribute("user");
        
        List<XPWColumn> list = new ArrayList<>();
        List<XPWColumn> temp = new ArrayList();
        
        if (id == 0) {
              temp = ColumnService.readSubColumn(id);
             // 过滤 TODO
              XPWRole role = roleService.read(Long.valueOf(user.getRoleIds()));
              String privIds = role.getPrivIds();
              XPWPrivExample example = new XPWPrivExample();
              if (privIds != null) {
                  String[] split = privIds.split(",");
                
                  for (String item : split) {
                      long pId= Long.parseLong(item);
                      if (pId <= 22 || pId == 30) {
                          example.or().andIdEqualTo(pId);
                      }
                  }
                  privs = privMapper.selectByExample(example);
              }
              
              for (XPWColumn col : temp) {
                  for (XPWPriv p : privs) {
                      if (col.getId() == p.getColumnId()) {
                          list.add(col);
                      }
                  }
              }
        } else {
            list = ColumnService.readSubColumn(id);
        }
        
        if (list.size() == 0) {
            return ResponseEntity.status(HttpServletResponse.SC_NOT_FOUND).body(null);
        } else {
            return ResponseEntity.status(HttpServletResponse.SC_OK).body(list);
        }
        
    }

}

package com.xmheart.backend.controller;

import org.apache.commons.digester.RegexMatcher;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import com.github.pagehelper.PageHelper;
import com.xmheart.model.XPWPriv;
import com.xmheart.model.XPWUser;
import com.xmheart.model.XPWUserRole;
import com.xmheart.service.RoleService;
import com.xmheart.service.UserRoleService;
import com.xmheart.service.UserService;
import com.xmheart.util.MessageDigestUtil;
import com.xmheart.util.ResponseBody;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletResponse;

/**
 * User Controller.
 * 
 * @author x1ny
 * @date 2017年5月26日
 */
@Api(tags = "User相关接口")
@RestController()
@RequestMapping(value = "", produces = "application/json;charset=UTF-8")
public class UserController {

    private static final Logger logger = LogManager.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    UserRoleService userRoleService;

    @Autowired
    RoleService roleService;
    

    @ApiOperation(value = "用户列表", notes = "用户列表")
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ResponseEntity<?> index() {
        List<XPWUser> list = userService.index();
        if (list.size() > 0) {
            return ResponseEntity.status(HttpServletResponse.SC_OK).body(list);
        } else {
            return ResponseEntity.status(HttpServletResponse.SC_NOT_FOUND).body(null);
        }
    }

    @ApiOperation(value = "更新当前用户密码", notes = "更新用户信息")
    @RequestMapping(value = "/users/update", method = RequestMethod.POST)
    public ResponseEntity<?> updatePassword(
            @ApiParam("密码") @RequestParam(required = false) String password
            ) {
        
        Subject subject = SecurityUtils.getSubject();
        XPWUser user = (XPWUser) subject.getPrincipal();
        
        String md5Password = MessageDigestUtil.Md5(password);
        user.setPassword(md5Password);
        userService.update(user);
        
        return ResponseEntity.status(HttpServletResponse.SC_OK).body(null);
    }
    
    
    @ApiOperation(value = "更新用户信息", notes = "更新用户信息")
    @RequestMapping(value = "/users/{id}", method = RequestMethod.POST)
    public ResponseEntity<?> update(@ApiParam("用户Id") @PathVariable Long id,
            @ApiParam("用户名") @RequestParam(required = false) String username,
            @ApiParam("密码") @RequestParam(required = false) String password,
            @ApiParam("角色") @RequestParam(required = false) Long roleId,
            @ApiParam("上线下线") @RequestParam(required = false) Boolean isEnabled
            ) throws IOException {


        XPWUser user = userService.read(id);
        if (user == null) {
            // resBody.statusMsg = "没有找到该用户";
            // resBody.obj = null;
            return ResponseEntity.status(HttpServletResponse.SC_NOT_FOUND).body(null);
        }

        if (username != null) {
            user.setUsername(username);
        }
        // 使用正则表达式进行密码的验证。
        if (password != null) {
//            String rex = "[0-9A-Za-z]{6,20}";
//            String rex = "(?=.*\\d)(?=.*[a-zA-Z])(?=.*[^a-zA-Z0-9]).{8,30}";
//            Pattern pattern = Pattern.compile(rex);
//            Matcher matcher = pattern.matcher(password);
//            if (!matcher.matches()) {
//                return ResponseEntity.status(HttpServletResponse.SC_OK).body("no-match");
//            }
            String md5Password = MessageDigestUtil.Md5(password);
            user.setPassword(md5Password);
        }
        
        if (roleId != null) {
            user.setRoleIds(String.valueOf(roleId));
        }
        
        if (isEnabled != null) {
            user.setIsEnabled(isEnabled);
        }
        
        int ret = userService.update(user);
        if (ret > 0) {
            // resBody.statusMsg = "更新成功";
            return ResponseEntity.status(HttpServletResponse.SC_OK).body(user);
        } else {
            // resBody.statusMsg = "更新失败";
            return ResponseEntity.status(HttpServletResponse.SC_INTERNAL_SERVER_ERROR).body(null);
        }
    }

    public static void main(String[] args) {
        String rex = "^[a-zA-Z0-9]{6,18}$";
//        String rex = "(?=.*\\d)(?=.*[a-zA-Z])(?=.*[^a-zA-Z0-9]).{8,30}";
        
        Pattern pattern = Pattern.compile(rex);
        Matcher matcher = pattern.matcher("123jhias");
        boolean matches = matcher.matches();
        System.out.println(matches);
    }
    
    @ApiOperation(value = "创建一个用户", notes = "创建一个用户")
    @RequestMapping(value = { "/users" }, method = RequestMethod.POST)
    public ResponseEntity<?> create(@ApiParam("用户名") @RequestParam(required = false) String username,
            @ApiParam("密码") @RequestParam(required = false) String password,
            @ApiParam("角色Id") @RequestParam(required = false) Long roleId) {

        XPWUser user = new XPWUser();

        if (username != null) {
            user.setUsername(username);
        }
        if (password != null) {
            user.setPassword(password);
        }
        if (roleId != null) {
            user.setRoleIds(String.valueOf(roleId));
        }
        user.setUserType((byte) 2);
        user.setSalt("0");
        
        int ret = userService.create(user);
        if (ret > 0) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpServletResponse.SC_INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @ApiOperation(value = "读取一个用户信息", notes = "读取一个用户信息")
    @RequestMapping(value = { "/users/{id}" }, method = RequestMethod.GET)
    public ResponseEntity<?> read(@ApiParam("用户角色对应关系Id，必填") @PathVariable Long id) {
        XPWUser user = userService.read(id);

        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpServletResponse.SC_NOT_FOUND).body(null);
        }
    }
    
    @ApiOperation(value = "获取当前用户所有权限列表", notes = "获取当前用户所有权限列表")
    @RequestMapping(value = { "/users/privs/{id}" }, method = RequestMethod.GET)
    public ResponseEntity<?> indexPrivs() {
        List<XPWPriv> list = roleService.indexPriv();

        if (list != null) {
            return ResponseEntity.ok(list);
        } else {
            return ResponseEntity.status(HttpServletResponse.SC_FORBIDDEN).body(null);
        }
    }

}

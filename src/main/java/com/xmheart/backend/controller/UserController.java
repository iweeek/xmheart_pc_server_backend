package com.xmheart.backend.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
import com.xmheart.model.XPWUser;
import com.xmheart.model.XPWUserRole;
import com.xmheart.service.UserRoleService;
import com.xmheart.service.UserService;
import com.xmheart.util.ResponseBody;

import java.io.IOException;
import java.util.List;

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

	@ApiOperation(value = "更新用户信息", notes = "更新用户信息")
	@RequestMapping(value = "/users/{id}", method = RequestMethod.POST)
	public ResponseEntity<?> update(@ApiParam("用户Id") @PathVariable Long id,
			@ApiParam("用户名") @RequestParam(required = false) String username,
			@ApiParam("密码") @RequestParam(required = false) String password,
			@ApiParam("角色") @RequestParam(required = false) Long roleId) throws IOException {

		// ResponseBody resBody = new ResponseBody<XPWUser>();

		XPWUser user = userService.read(id);
		if (user == null) {
			// resBody.statusMsg = "没有找到该用户";
			// resBody.obj = null;
			return ResponseEntity.status(HttpServletResponse.SC_NOT_FOUND).body(null);
		}

		if (username != null) {
			user.setUsername(username);
		}
		if (password != null) {
			user.setPassword(password);
		}
		if (roleId != null) {
			user.setRoleId(roleId);
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
			user.setRoleId(roleId);
		}
		user.setUserType((byte) 2);

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

}

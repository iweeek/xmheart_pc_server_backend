package com.xmheart.backend.controller;

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

import com.github.pagehelper.PageHelper;
import com.xmheart.model.XPWArticle;
import com.xmheart.model.XPWRole;
import com.xmheart.model.XPWUserRole;
import com.xmheart.model.XPWUserRoleExample;
import com.xmheart.service.RoleService;
import com.xmheart.service.UserRoleService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Controller
public class UserRoleController {

	@Autowired
	UserRoleService userRoleService;
	
	@RequestMapping(value = { "/userRole" }, method = RequestMethod.POST)
	public ResponseEntity<?> create(Model model,
			@ApiParam("用户Id") @RequestParam Long userId,
			@ApiParam("角色Id") @RequestParam Long roleId) {
		
		XPWUserRole userRole = new XPWUserRole();
		
		userRole.setUserId(userId);
		userRole.setRoleId(roleId);
		
		int ret = userRoleService.create(userRole);
		if (ret > 0) {
			return ResponseEntity.ok(userRole);
		} else {
			return ResponseEntity.status(HttpServletResponse.SC_FORBIDDEN).body(null);
		}
	}
	
	@RequestMapping(value = { "/userRole/{id}" }, method = RequestMethod.POST)
	public ResponseEntity<?> update(Model model,
			@ApiParam("用户角色对应关系id，必填") @PathVariable Long id, 
			@ApiParam("用户Id") @RequestParam Long userId,
			@ApiParam("角色Id") @RequestParam Long roleId) {
		
		XPWUserRole userRole = new XPWUserRole();
		userRole.setId(id);
		
		userRole.setUserId(userId);
		userRole.setRoleId(roleId);
		
		int ret = userRoleService.update(userRole);
		if (ret > 0) {
			return ResponseEntity.ok(userRole);
		} else {
			return ResponseEntity.status(HttpServletResponse.SC_FORBIDDEN).body(null);
		}
	}
	
	@RequestMapping(value = { "/userRole/{id}" }, method = RequestMethod.DELETE)
	public ResponseEntity<?> delete(Model model,
			@ApiParam("用户角色对应关系Id，必填") @PathVariable Long id) {
		
		int ret = userRoleService.delete(id);
		if (ret > 0) {
			return ResponseEntity.ok(null);
		} else {
			return ResponseEntity.status(HttpServletResponse.SC_FORBIDDEN).body(null);
		}
	}
	
	@RequestMapping(value = { "/userRole/{id}" }, method = RequestMethod.GET)
	public ResponseEntity<?> read(Model model,
			@ApiParam("用户角色对应关系Id，必填") @PathVariable Long id) {
		XPWUserRole userRole = userRoleService.read(id);
		
		if (userRole != null) {
			return ResponseEntity.ok(userRole);
		} else {
			return ResponseEntity.status(HttpServletResponse.SC_FORBIDDEN).body(null);
		}
	}

	
	@ApiOperation(value = "获取所有用户拥有的角色", notes = "获取所有用户拥有的角色")
	@RequestMapping(value = { "/userRole" }, method = RequestMethod.GET)
	public ResponseEntity<?> index(@ApiParam("用户Id") @RequestParam(required = false) Long userId,
			@ApiParam("开始页号") @RequestParam(required = false, defaultValue = "1") Integer pageNo,
			@ApiParam("每页的数目") @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
		
		List<XPWUserRole> list = null;
		
		PageHelper.startPage(pageNo, pageSize);

		if (userId == null) {
			list = userRoleService.index();
		} else {
			list = userRoleService.index(userId);
		}
		
		if (list != null) {
			return ResponseEntity.ok(list);
		} else {
			return ResponseEntity.status(HttpServletResponse.SC_NOT_FOUND).body(null);
		}
	}
}

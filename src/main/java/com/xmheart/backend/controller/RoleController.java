package com.xmheart.backend.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xmheart.model.XPWPriv;
import com.xmheart.model.XPWRole;
import com.xmheart.service.RoleService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Controller
public class RoleController {
	
	@Autowired
	RoleService roleService;

	@RequestMapping(value = { "/roles" }, method = RequestMethod.POST)
	public ResponseEntity<?> create(@ApiParam("角色名字") @RequestParam String name,
			@ApiParam("角色拥有的权限Id") @RequestParam("privIds[]") Long[] privIds) {
		
		XPWRole role = new XPWRole();
		if (name != null) {
			role.setName(name);
		}
		
		if (privIds != null) {
			StringBuilder sb = new StringBuilder();
			for (long id : privIds) {
				sb.append(id);
				sb.append(",");
			}
			String idsStr = sb.substring(0, sb.toString().length() - 1);
			role.setPrivIds(idsStr);
		}
		int ret = roleService.create(role);
		if (ret > 0) {
			return ResponseEntity.ok(role);
		} else {
			return ResponseEntity.status(HttpServletResponse.SC_FORBIDDEN).body(null);
		}
	}
	
	@RequestMapping(value = { "/roles/{id}" }, method = RequestMethod.POST)
	public ResponseEntity<?> update(
			@ApiParam("角色Id，必填") @PathVariable Long id, 
			@ApiParam("角色名字") @RequestParam String name,
			@ApiParam("角色拥有的权限Id") @RequestParam Long[] privIds) {
		
		XPWRole role = new XPWRole();
		role.setId(id);
		if (name != null) {
			role.setName(name);
		}
		
		if (privIds != null) {
			StringBuilder sb = new StringBuilder();
			for (long pId : privIds) {
				sb.append(pId);
				sb.append(",");
			}
			String idsStr = sb.substring(0, sb.toString().length() - 1);
			role.setPrivIds(idsStr);
		}
		
		int ret = roleService.update(role);
		if (ret > 0) {
			return ResponseEntity.ok(role);
		} else {
			return ResponseEntity.status(HttpServletResponse.SC_FORBIDDEN).body(null);
		}
	}
	
	@RequestMapping(value = { "/deleteRoles/{id}" }, method = RequestMethod.POST)
	public ResponseEntity<?> delete(@ApiParam("角色Id，必填") @PathVariable Long id) {
		
		int ret = roleService.delete(id);
		if (ret > 0) {
			return ResponseEntity.ok(null);
		} else {
			return ResponseEntity.status(HttpServletResponse.SC_FORBIDDEN).body(null);
		}
	}
	
	@RequestMapping(value = { "/roles/{id}" }, method = RequestMethod.GET)
	public ResponseEntity<?> read(@ApiParam("角色Id，必填") @PathVariable Long id) {
		XPWRole role = roleService.read(id);
		
		if (role != null) {
			return ResponseEntity.ok(role);
		} else {
			return ResponseEntity.status(HttpServletResponse.SC_FORBIDDEN).body(null);
		}
	}
	
	@ApiOperation(value = "获取所有角色列表", notes = "获取所有角色列表")
	@RequestMapping(value = { "/roles" }, method = RequestMethod.GET)
	public ResponseEntity<?> indexRoles() {
		List<XPWRole> list = roleService.index();
		
		if (list != null) {
			return ResponseEntity.ok(list);
		} else {
			return ResponseEntity.status(HttpServletResponse.SC_FORBIDDEN).body(null);
		}
	}
	
	@ApiOperation(value = "获取所有权限列表", notes = "获取所有权限列表")
	@RequestMapping(value = { "/privs" }, method = RequestMethod.GET)
	public ResponseEntity<?> indexPrivs() {
		List<XPWPriv> list = roleService.indexPriv();
		
		if (list != null) {
			return ResponseEntity.ok(list);
		} else {
			return ResponseEntity.status(HttpServletResponse.SC_FORBIDDEN).body(null);
		}
	}
}

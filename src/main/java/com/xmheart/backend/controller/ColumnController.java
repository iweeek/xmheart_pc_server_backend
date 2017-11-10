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

import com.xmheart.model.XPWColumn;
import com.xmheart.service.ColumnService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = "栏目管理接口")
@Controller
public class ColumnController {
    
    @Autowired 
    private ColumnService ColumnService;
    
    @ApiOperation(value = "获取所有栏目", notes = "获取所有栏目")
    @RequestMapping(value = { "/columns" }, method = RequestMethod.GET)
    public ResponseEntity<?> index(@ApiParam("父栏目的Id，父栏目Id为0的表示没有父栏目") @RequestParam(required = false) Long parentColumnId) {
        
        List<XPWColumn> list;
        if (parentColumnId == null) {
            list = ColumnService.getColumns();
        } else {
            list = ColumnService.getColumnsByParentId(parentColumnId);
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
    public ResponseEntity<?> subColumn(@ApiParam("栏目的Id") @PathVariable Long id) {
        
        List<XPWColumn> list = ColumnService.readSubColumn(id);
        if (list.size() == 0) {
            return ResponseEntity.status(HttpServletResponse.SC_NOT_FOUND).body(null);
        } else {
            return ResponseEntity.status(HttpServletResponse.SC_OK).body(list);
        }
        
    }

}

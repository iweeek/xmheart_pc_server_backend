package com.xmheart.backend.controller;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xmheart.model.XPWDoctor;
import com.xmheart.service.DoctorAndDeptService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = "后台医生相关接口")
@RestController
public class DoctorController {
    
    class ResponseBody<T> {
        @SuppressWarnings("rawtypes")
        private PageInfo pageInfo;
        private T obj;
        public PageInfo getPageInfo() {
            return pageInfo;
        }
        public void setPageInfo(PageInfo pageInfo) {
            this.pageInfo = pageInfo;
        }
        public T getObj() {
            return obj;
        }
        public void setObj(T obj) {
            this.obj = obj;
        }
    }
    
    @Autowired
    private DoctorAndDeptService doctorAndDeptService;
    
    @RequiresPermissions("doctor")
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @ApiOperation(value = "获取医生列表", notes = "获取医生列表")
    @RequestMapping(value = { "/doctors" }, method = RequestMethod.GET)
    public ResponseEntity<?> index(@ApiParam("开始页号") @RequestParam(required = false, defaultValue = "1") Integer pageNo,
            @ApiParam("每页的数目") @RequestParam(required = false, defaultValue = "10") Integer pageSize,
            @ApiParam("科室的Id，可选") @RequestParam(required = false) Long deptId) {
        
        List<XPWDoctor> list;
        if (deptId == null) {
            PageHelper.startPage(pageNo, pageSize);
            list = doctorAndDeptService.getDisplayDoctors();
        } else {
            PageHelper.startPage(pageNo, pageSize);
            list = doctorAndDeptService.getDoctorsByDeptId(deptId);
        }
        
        PageInfo pageInfo = new PageInfo(list);
        
        ResponseBody object = new ResponseBody();
        object.obj = list;
        object.pageInfo = pageInfo;
        
        if (list.size() == 0) {
            return ResponseEntity.status(HttpServletResponse.SC_NOT_FOUND).body(null);
        } else {
            return ResponseEntity.status(HttpServletResponse.SC_OK).body(pageInfo);
        }
    }
    
    @RequiresPermissions("doctor")
    @ApiOperation(value = "根据Id获取医生信息", notes = "根据Id获取医生信息")
    @RequestMapping(value = { "/doctors/{id}" }, method = RequestMethod.GET)
    public ResponseEntity<?> read(@ApiParam("医生的Id") @PathVariable Long id) {
        
        XPWDoctor doctor;
        doctor = doctorAndDeptService.getDoctorById(id);
        
        if (doctor == null) {
            return ResponseEntity.status(HttpServletResponse.SC_NOT_FOUND).body(null);
        } else {
            return ResponseEntity.status(HttpServletResponse.SC_OK).body(doctor);
        }
    }
    
    @RequiresPermissions("doctor")
    @ApiOperation(value = "根据Id更新医生信息", notes = "根据Id更新医生信息")
    @RequestMapping(value = { "/doctors/{id}" }, method = RequestMethod.POST)
    public ResponseEntity<?> update(@ApiParam("医生的Id") @PathVariable Long id,
            @ApiParam("医生的姓名，可选") @RequestParam(required = false) String name,
            @ApiParam("医生的头像，可选") @RequestParam(required = false) String imageUrl,
            @ApiParam("科室的Id，可选") @RequestParam(required = false) Long deptId,
            @ApiParam("职务，可选") @RequestParam(required = false) String duty,
            @ApiParam("职称，可选") @RequestParam(required = false) String professionalTitle,
            @ApiParam("介绍，可选") @RequestParam(required = false) String intro,
            @ApiParam("是否可以展示，可选") @RequestParam(required = false) Boolean isDisplayed,
            @ApiParam("预约地址，可选") @RequestParam(required = false) String appointmentUrl) {
        
        XPWDoctor doctor;
        doctor = doctorAndDeptService.getDoctorById(id);
        
        if (name != null) {
            doctor.setName(name);
        }
        
        if (imageUrl != null) {
            doctor.setImageUrl(imageUrl);
        }
        
        if (deptId != null) {
            doctor.setDeptId(deptId);
        }
        
        if (duty != null) {
            doctor.setDuty(duty);
        }
        
        if (professionalTitle != null) {
            doctor.setProfessionalTitle(professionalTitle);
        }
        
        if (intro != null) {
            doctor.setIntro(intro);
            String brief = delHTMLTag(intro);
            if (brief.length() > 100) {
        			brief = brief.substring(0, 100);
            }
            doctor.setBrief(brief);
        } 

        if (isDisplayed != null) {
            doctor.setIsDisplayed(isDisplayed);
        }
        
        if (appointmentUrl != null) {
            doctor.setAppointmentUrl(appointmentUrl);
        }
        
        int ret = doctorAndDeptService.updateDoctor(doctor);
        if (ret == 0) {
            return ResponseEntity.status(HttpServletResponse.SC_NOT_FOUND).body(null);
        } else {
            return ResponseEntity.status(HttpServletResponse.SC_OK).body(doctor);
        }
    }
    
    @RequiresPermissions("doctor")
    @ApiOperation(value = "创建医生信息", notes = "创建医生信息")
    @RequestMapping(value = { "/doctors" }, method = RequestMethod.POST)
    public ResponseEntity<?> create(@ApiParam("医生的姓名，可选") @RequestParam(required = false) String name,
            @ApiParam("科室的Id，可选") @RequestParam(required = false) Long deptId,
            @ApiParam("职务，可选") @RequestParam(required = false) String duty,
            @ApiParam("职称，可选") @RequestParam(required = false) String professionalTitle,
            @ApiParam("介绍，可选") @RequestParam(required = false) String intro,
            @ApiParam("医生头像，可选") @RequestParam(required = false) String imageUrl,
            @ApiParam("是否可以展示，可选") @RequestParam(required = false) Boolean isDisplayed,
            @ApiParam("医生的顺序，可选") @RequestParam(required = false) Integer docOrder,
            @ApiParam("预约地址，可选") @RequestParam(required = false) String appointmentUrl) {
        
        XPWDoctor doctor = new XPWDoctor();
        
        if (name != null) {
            doctor.setName(name);
        } else {
            doctor.setName("");
        }
        
        if (imageUrl != null) {
            doctor.setImageUrl(imageUrl);
        }
        
        if (deptId != null) {
            doctor.setDeptId(deptId);
        } else {
            doctor.setDeptId(0l);
        }
        
        if (duty != null) {
            doctor.setDuty(duty);
        } else {
            doctor.setDuty("");
        }
        
        if (professionalTitle != null) {
            doctor.setProfessionalTitle(professionalTitle);
        } else {
            doctor.setProfessionalTitle("");
        }
        
        if (intro != null) {
            doctor.setIntro(intro);
            String brief = delHTMLTag(intro);
            if (brief.length() > 100) {
            		brief = brief.substring(0, 100);
            }
            doctor.setBrief(brief);
        } else {
            doctor.setIntro("");
        }
        
        if (isDisplayed != null) {
            doctor.setIsDisplayed(isDisplayed);
            if (isDisplayed) {
                docOrder = doctorAndDeptService.getMaxOrder();
                doctor.setDocOrder((docOrder + 1));
                
            } else {
                doctor.setDocOrder(0);
            }
        } else {
            doctor.setIsDisplayed(false);
        }
        
        if (appointmentUrl != null) {
            doctor.setAppointmentUrl(appointmentUrl);
        } else {
            doctor.setAppointmentUrl("");
        }
        
        doctor.setHisId(0);
        
        int maxOrder = doctorAndDeptService.getMaxOrder();
        doctor.setDocOrder(maxOrder + 1);
        
        int ret = doctorAndDeptService.createDoctor(doctor);
        if (ret > 0) {
        		doctor.setUrl("/doctorDetail?id=" + String.valueOf(doctor.getId()));
	        	doctorAndDeptService.updateDoctor(doctor);
            return ResponseEntity.status(HttpServletResponse.SC_CREATED).body(doctor);
        } else {
        		return ResponseEntity.status(HttpServletResponse.SC_INTERNAL_SERVER_ERROR).body(null);
        }
    }
    
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
    
    @ApiOperation(value = "交换医生顺序", notes = "交换医生顺序")
    @RequestMapping(value = { "/doctors/swapDocOrder" }, method = RequestMethod.POST)
    public ResponseEntity<?> swapDocOrder(@ApiParam("医生的Id") Long doctorId1, 
            @ApiParam("医生的Id") Long doctorId2) {
        int ret = doctorAndDeptService.swapDocOrder(doctorId1, doctorId2);
        if (ret == 0) {
            return ResponseEntity.ok(null);
        } else {
            return ResponseEntity.status(HttpServletResponse.SC_FORBIDDEN).body(null);
        }
    }

}
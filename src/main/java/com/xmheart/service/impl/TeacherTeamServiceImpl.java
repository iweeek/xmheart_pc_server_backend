package com.xmheart.service.impl;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmheart.mapper.XPWDeptMapper;
import com.xmheart.mapper.XPWDoctorMapper;
import com.xmheart.mapper.XPWTeacherMapper;
import com.xmheart.model.XPWDept;
import com.xmheart.model.XPWDeptExample;
import com.xmheart.model.XPWDoctor;
import com.xmheart.model.XPWDoctorExample;
import com.xmheart.model.XPWTeacher;
import com.xmheart.model.XPWTeacherExample;
import com.xmheart.service.DoctorAndDeptService;
import com.xmheart.service.TeacherTeamService;

@Service
public class TeacherTeamServiceImpl implements TeacherTeamService {
	
	@Autowired
	private XPWTeacherMapper xpwTeacherMapper;
	
	@Autowired
	private XPWDeptMapper xpwDeptMapper;

	@Override
	public List<XPWTeacher> getDisplayTeachers() {
	    XPWTeacherExample example = new XPWTeacherExample();
	    example.createCriteria().andIsDisplayedEqualTo(true);
	    example.setOrderByClause("doc_order asc");
		List<XPWTeacher> list = xpwTeacherMapper.selectByExample(example);
		return list;
	}

	@Override
	public List<XPWDept> getOutServiceDepts() {
	    XPWDeptExample example = new XPWDeptExample();
	    example.createCriteria().andOutServiceEqualTo(true);
		List<XPWDept> list = xpwDeptMapper.selectByExampleWithBLOBs(example);
		return list;
	}

	@Override
	public XPWTeacher getTeacherAndDeptById(Long id) {
		XPWTeacher teacher = xpwTeacherMapper.selectTeacherWithDeptByPrimaryKey(id);
		return teacher;
	}

	@Override
	public XPWDept getDeptAndTeachersById(Long id) {
		XPWDept dept = xpwDeptMapper.selectDeptWithTeachersByPrimaryKey(id);
		return dept;
	}

    @Override
    public List<XPWTeacher> getTeachersByDeptId(Long deptId) {
        XPWTeacherExample example = new XPWTeacherExample();
        example.createCriteria().andDeptIdEqualTo(deptId);
        example.setOrderByClause("doc_order asc");
        List<XPWTeacher> list = xpwTeacherMapper.selectByExample(example);
        return list;
    }

    @Override
    public XPWTeacher getTeacherById(Long id) {
        XPWTeacher teacher = xpwTeacherMapper.selectByPrimaryKey(id);
        return teacher;
    }

    @Override
    public int updateTeacher(XPWTeacher teacher) {
        int ret = xpwTeacherMapper.updateByPrimaryKeySelective(teacher);
        return ret;
    }

    @Override
    public int createTeacher(XPWTeacher teacher) {
        int ret = xpwTeacherMapper.insertSelective(teacher);
        return ret;
    }

    @Override
    public List<XPWDept> getDepts() {
        List<XPWDept> list = xpwDeptMapper.selectByExampleWithBLOBs(null);
        return list;
    }

    @Override
    public XPWDept getDeptById(Long id) {
        XPWDept dept = xpwDeptMapper.selectByPrimaryKey(id);
        return dept;
    }

    @Override
    public int updateDept(XPWDept dept) {
        int ret = xpwDeptMapper.updateByPrimaryKeySelective(dept);
        return ret;
    }

    @Override
    public int createDept(XPWDept dept) {
        int ret = xpwDeptMapper.insertSelective(dept);
        return ret;
    }
    
    @Override
    public int swapDocOrder(Long doctorId1, Long doctorId2) {
        XPWTeacher doctor1 = xpwTeacherMapper.selectByPrimaryKey(doctorId1);
        XPWTeacher doctor2 = xpwTeacherMapper.selectByPrimaryKey(doctorId2);
        
        //只有置顶的文章才可以交换order
        if (!doctor1.getIsDisplayed() || !doctor2.getIsDisplayed()) {
            return -1;
        }
        
//        if (doctor1.getColumnId() != doctor2.getColumnId()) {
//            return -1;
//        }
        
        Integer order1 = doctor1.getDocOrder();
        doctor1.setDocOrder(doctor2.getDocOrder());
        xpwTeacherMapper.updateByPrimaryKey(doctor1);
        
        doctor2.setDocOrder(order1);
        xpwTeacherMapper.updateByPrimaryKey(doctor2);
        
        return 0;
    }
    
    @Override
    public int getMaxOrder() {
        XPWTeacherExample example = new XPWTeacherExample();
        example.createCriteria().andIsDisplayedEqualTo(true);
        example.setOrderByClause("doc_order desc limit 1");
        List<XPWTeacher> list = xpwTeacherMapper.selectByExample(example);
        if (list.size() != 0) {
            return list.get(0).getDocOrder();
        } else {
            return 0;
        }
    }

}

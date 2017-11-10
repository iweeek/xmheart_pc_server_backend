package com.xmheart.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmheart.mapper.XPWDeptMapper;
import com.xmheart.mapper.XPWDoctorMapper;
import com.xmheart.model.XPWArticle;
import com.xmheart.model.XPWArticleExample;
import com.xmheart.model.XPWDept;
import com.xmheart.model.XPWDeptExample;
import com.xmheart.model.XPWDoctor;
import com.xmheart.model.XPWDoctorExample;
import com.xmheart.service.DoctorAndDeptService;

@Service
public class DoctorAndDeptServiceImpl implements DoctorAndDeptService {
	
	@Autowired
	private XPWDoctorMapper xpwDoctorMapper;
	
	@Autowired
	private XPWDeptMapper xpwDeptMapper;

	@Override
	public List<XPWDoctor> getDisplayDoctors() {
	    XPWDoctorExample example = new XPWDoctorExample();
	    example.createCriteria().andIsDisplayedEqualTo(true);
	    example.setOrderByClause("doc_order asc");
		List<XPWDoctor> list = xpwDoctorMapper.selectByExample(example);
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
	public XPWDoctor getDoctorAndDeptById(Long id) {
		XPWDoctor doctor = xpwDoctorMapper.selectDoctorWithDeptByPrimaryKey(id);
		return doctor;
	}

	@Override
	public XPWDept getDeptAndDoctorsById(Long id) {
		XPWDept dept = xpwDeptMapper.selectDeptWithDoctorsByPrimaryKey(id);
		return dept;
	}

    @Override
    public List<XPWDoctor> getDoctorsByDeptId(Long deptId) {
        XPWDoctorExample example = new XPWDoctorExample();
        example.createCriteria().andDeptIdEqualTo(deptId);
        example.setOrderByClause("doc_order asc");
        List<XPWDoctor> list = xpwDoctorMapper.selectByExample(example);
        return list;
    }

    @Override
    public XPWDoctor getDoctorById(Long id) {
        XPWDoctor doctor = xpwDoctorMapper.selectByPrimaryKey(id);
        return doctor;
    }

    @Override
    public int updateDoctor(XPWDoctor doctor) {
        int ret = xpwDoctorMapper.updateByPrimaryKeySelective(doctor);
        return ret;
    }

    @Override
    public int createDoctor(XPWDoctor doctor) {
        int ret = xpwDoctorMapper.insertSelective(doctor);
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
    public List<XPWDoctor> searchDoctorByName(String name) {
        List<XPWDoctor> list = xpwDoctorMapper.selectDoctorByName(name);
        return list;
    }
    
    @Override
    public int swapDocOrder(Long doctorId1, Long doctorId2) {
        XPWDoctor doctor1 = xpwDoctorMapper.selectByPrimaryKey(doctorId1);
        XPWDoctor doctor2 = xpwDoctorMapper.selectByPrimaryKey(doctorId2);
        
        //只有置顶的文章才可以交换order
        if (!doctor1.getIsDisplayed() || !doctor2.getIsDisplayed()) {
            return -1;
        }
        
//        if (doctor1.getColumnId() != doctor2.getColumnId()) {
//            return -1;
//        }
        
        int order1 = doctor1.getDocOrder();
        doctor1.setDocOrder(doctor2.getDocOrder());
        xpwDoctorMapper.updateByPrimaryKey(doctor1);
        
        doctor2.setDocOrder(order1);
        xpwDoctorMapper.updateByPrimaryKey(doctor2);
        
        return 0;
    }
    
    @Override
    public int getMaxOrder() {
        XPWDoctorExample example = new XPWDoctorExample();
        example.createCriteria().andIsDisplayedEqualTo(true);
        example.setOrderByClause("doc_order desc limit 1");
        List<XPWDoctor> list = xpwDoctorMapper.selectByExample(example);
        if (list.size() != 0) {
            return list.get(0).getDocOrder();
        } else {
            return 0;
        }
    }
}

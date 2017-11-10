package com.xmheart.service;

import java.util.List;

import com.xmheart.model.XPWArticle;
import com.xmheart.model.XPWDept;
import com.xmheart.model.XPWDoctor;

public interface DoctorAndDeptService {

	List<XPWDoctor> getDisplayDoctors();

	List<XPWDept> getOutServiceDepts();

	XPWDoctor getDoctorAndDeptById(Long id);

	XPWDept getDeptAndDoctorsById(Long id);

    List<XPWDoctor> getDoctorsByDeptId(Long deptId);

    XPWDoctor getDoctorById(Long id);

    int updateDoctor(XPWDoctor doctor);

    int createDoctor(XPWDoctor doctor);

    List<XPWDept> getDepts();

    XPWDept getDeptById(Long id);

    int updateDept(XPWDept dept);

    int createDept(XPWDept dept);

    List<XPWDoctor> searchDoctorByName(String name);

    int getMaxOrder();

    int swapDocOrder(Long doctorId1, Long doctorId2);

}

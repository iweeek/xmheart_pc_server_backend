package com.xmheart.service;

import java.util.List;

import com.xmheart.model.XPWDept;
import com.xmheart.model.XPWDoctor;
import com.xmheart.model.XPWTeacher;

public interface TeacherTeamService {

	List<XPWTeacher> getDisplayTeachers();

	List<XPWDept> getOutServiceDepts();

	XPWTeacher getTeacherAndDeptById(Long id);

	XPWDept getDeptAndTeachersById(Long id);

    List<XPWTeacher> getTeachersByDeptId(Long deptId);

    XPWTeacher getTeacherById(Long id);

    int updateTeacher(XPWTeacher teacher);

    int createTeacher(XPWTeacher teacher);

    List<XPWDept> getDepts();

    XPWDept getDeptById(Long id);

    int updateDept(XPWDept dept);

    int createDept(XPWDept dept);

    int swapDocOrder(Long doctorId1, Long doctorId2);

    int getMaxOrder();

}

package com.xmheart.model;

import java.util.List;

public class XPWDept {

	/**
     * This field was generated by MyBatis Generator. This field corresponds to the database column xpw_dept.id
     * @mbg.generated  Mon Jan 29 14:12:50 CST 2018
     */
    private Long id;
    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column xpw_dept.his_id
     * @mbg.generated  Mon Jan 29 14:12:50 CST 2018
     */
    private Integer hisId;
    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column xpw_dept.name
     * @mbg.generated  Mon Jan 29 14:12:50 CST 2018
     */
    private String name;
    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column xpw_dept.image_url
     * @mbg.generated  Mon Jan 29 14:12:50 CST 2018
     */
    private String imageUrl;
    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column xpw_dept.url
     * @mbg.generated  Mon Jan 29 14:12:50 CST 2018
     */
    private String url;
    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column xpw_dept.brief
     * @mbg.generated  Mon Jan 29 14:12:50 CST 2018
     */
    private String brief;
    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column xpw_dept.dept_doctor_url
     * @mbg.generated  Mon Jan 29 14:12:50 CST 2018
     */
    private String deptDoctorUrl;
    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column xpw_dept.out_service
     * @mbg.generated  Mon Jan 29 14:12:50 CST 2018
     */
    private Boolean outService;
    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column xpw_dept.admin_expert_id
     * @mbg.generated  Mon Jan 29 14:12:50 CST 2018
     */
    private Long adminExpertId;
    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column xpw_dept.is_displayed
     * @mbg.generated  Mon Jan 29 14:12:50 CST 2018
     */
    private Boolean isDisplayed;
    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column xpw_dept.name_en
     * @mbg.generated  Mon Jan 29 14:12:50 CST 2018
     */
    private String nameEn;
    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column xpw_dept.intro
     * @mbg.generated  Mon Jan 29 14:12:50 CST 2018
     */
    private String intro;

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column xpw_dept.id
     * @return  the value of xpw_dept.id
     * @mbg.generated  Mon Jan 29 14:12:50 CST 2018
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column xpw_dept.id
     * @param id  the value for xpw_dept.id
     * @mbg.generated  Mon Jan 29 14:12:50 CST 2018
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column xpw_dept.his_id
     * @return  the value of xpw_dept.his_id
     * @mbg.generated  Mon Jan 29 14:12:50 CST 2018
     */
    public Integer getHisId() {
        return hisId;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column xpw_dept.his_id
     * @param hisId  the value for xpw_dept.his_id
     * @mbg.generated  Mon Jan 29 14:12:50 CST 2018
     */
    public void setHisId(Integer hisId) {
        this.hisId = hisId;
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column xpw_dept.name
     * @return  the value of xpw_dept.name
     * @mbg.generated  Mon Jan 29 14:12:50 CST 2018
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column xpw_dept.name
     * @param name  the value for xpw_dept.name
     * @mbg.generated  Mon Jan 29 14:12:50 CST 2018
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column xpw_dept.image_url
     * @return  the value of xpw_dept.image_url
     * @mbg.generated  Mon Jan 29 14:12:50 CST 2018
     */
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column xpw_dept.image_url
     * @param imageUrl  the value for xpw_dept.image_url
     * @mbg.generated  Mon Jan 29 14:12:50 CST 2018
     */
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column xpw_dept.url
     * @return  the value of xpw_dept.url
     * @mbg.generated  Mon Jan 29 14:12:50 CST 2018
     */
    public String getUrl() {
        return url;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column xpw_dept.url
     * @param url  the value for xpw_dept.url
     * @mbg.generated  Mon Jan 29 14:12:50 CST 2018
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column xpw_dept.brief
     * @return  the value of xpw_dept.brief
     * @mbg.generated  Mon Jan 29 14:12:50 CST 2018
     */
    public String getBrief() {
        return brief;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column xpw_dept.brief
     * @param brief  the value for xpw_dept.brief
     * @mbg.generated  Mon Jan 29 14:12:50 CST 2018
     */
    public void setBrief(String brief) {
        this.brief = brief;
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column xpw_dept.dept_doctor_url
     * @return  the value of xpw_dept.dept_doctor_url
     * @mbg.generated  Mon Jan 29 14:12:50 CST 2018
     */
    public String getDeptDoctorUrl() {
        return deptDoctorUrl;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column xpw_dept.dept_doctor_url
     * @param deptDoctorUrl  the value for xpw_dept.dept_doctor_url
     * @mbg.generated  Mon Jan 29 14:12:50 CST 2018
     */
    public void setDeptDoctorUrl(String deptDoctorUrl) {
        this.deptDoctorUrl = deptDoctorUrl;
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column xpw_dept.out_service
     * @return  the value of xpw_dept.out_service
     * @mbg.generated  Mon Jan 29 14:12:50 CST 2018
     */
    public Boolean getOutService() {
        return outService;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column xpw_dept.out_service
     * @param outService  the value for xpw_dept.out_service
     * @mbg.generated  Mon Jan 29 14:12:50 CST 2018
     */
    public void setOutService(Boolean outService) {
        this.outService = outService;
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column xpw_dept.admin_expert_id
     * @return  the value of xpw_dept.admin_expert_id
     * @mbg.generated  Mon Jan 29 14:12:50 CST 2018
     */
    public Long getAdminExpertId() {
        return adminExpertId;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column xpw_dept.admin_expert_id
     * @param adminExpertId  the value for xpw_dept.admin_expert_id
     * @mbg.generated  Mon Jan 29 14:12:50 CST 2018
     */
    public void setAdminExpertId(Long adminExpertId) {
        this.adminExpertId = adminExpertId;
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column xpw_dept.is_displayed
     * @return  the value of xpw_dept.is_displayed
     * @mbg.generated  Mon Jan 29 14:12:50 CST 2018
     */
    public Boolean getIsDisplayed() {
        return isDisplayed;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column xpw_dept.is_displayed
     * @param isDisplayed  the value for xpw_dept.is_displayed
     * @mbg.generated  Mon Jan 29 14:12:50 CST 2018
     */
    public void setIsDisplayed(Boolean isDisplayed) {
        this.isDisplayed = isDisplayed;
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column xpw_dept.name_en
     * @return  the value of xpw_dept.name_en
     * @mbg.generated  Mon Jan 29 14:12:50 CST 2018
     */
    public String getNameEn() {
        return nameEn;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column xpw_dept.name_en
     * @param nameEn  the value for xpw_dept.name_en
     * @mbg.generated  Mon Jan 29 14:12:50 CST 2018
     */
    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column xpw_dept.intro
     * @return  the value of xpw_dept.intro
     * @mbg.generated  Mon Jan 29 14:12:50 CST 2018
     */
    public String getIntro() {
        return intro;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column xpw_dept.intro
     * @param intro  the value for xpw_dept.intro
     * @mbg.generated  Mon Jan 29 14:12:50 CST 2018
     */
    public void setIntro(String intro) {
        this.intro = intro;
    }


    public List<XPWDoctor> doctors;

	public List<XPWDoctor> getDoctors() {
		return doctors;
	}

	public void setDoctors(List<XPWDoctor> doctors) {
		this.doctors = doctors;
	}
	

    public List<XPWTeacher> teachers;

    public List<XPWTeacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<XPWTeacher> teachers) {
        this.teachers = teachers;
    }
}
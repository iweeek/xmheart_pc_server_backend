package com.xmheart.mapper;

import com.xmheart.model.XPWDept;
import com.xmheart.model.XPWDeptExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

public interface XPWDeptMapper {

	/**
     * This method was generated by MyBatis Generator. This method corresponds to the database table xpw_dept
     * @mbg.generated  Sat Jan 27 17:07:32 CST 2018
     */
    long countByExample(XPWDeptExample example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table xpw_dept
     * @mbg.generated  Sat Jan 27 17:07:32 CST 2018
     */
    int deleteByExample(XPWDeptExample example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table xpw_dept
     * @mbg.generated  Sat Jan 27 17:07:32 CST 2018
     */
    @Delete({ "delete from xpw_dept", "where id = #{id,jdbcType=BIGINT}" })
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table xpw_dept
     * @mbg.generated  Sat Jan 27 17:07:32 CST 2018
     */
    @Insert({ "insert into xpw_dept (his_id, name, ", "image_url, url, brief, ", "dept_doctor_url, out_service, ",
            "admin_expert_id, is_displayed, ", "name_en, intro)",
            "values (#{hisId,jdbcType=INTEGER}, #{name,jdbcType=VARCHAR}, ",
            "#{imageUrl,jdbcType=VARCHAR}, #{url,jdbcType=VARCHAR}, #{brief,jdbcType=VARCHAR}, ",
            "#{deptDoctorUrl,jdbcType=VARCHAR}, #{outService,jdbcType=TINYINT}, ",
            "#{adminExpertId,jdbcType=BIGINT}, #{isDisplayed,jdbcType=TINYINT}, ",
            "#{nameEn,jdbcType=VARCHAR}, #{intro,jdbcType=LONGVARCHAR})" })
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id", before = false, resultType = Long.class)
    int insert(XPWDept record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table xpw_dept
     * @mbg.generated  Sat Jan 27 17:07:32 CST 2018
     */
    int insertSelective(XPWDept record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table xpw_dept
     * @mbg.generated  Sat Jan 27 17:07:32 CST 2018
     */
    List<XPWDept> selectByExampleWithBLOBs(XPWDeptExample example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table xpw_dept
     * @mbg.generated  Sat Jan 27 17:07:32 CST 2018
     */
    List<XPWDept> selectByExample(XPWDeptExample example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table xpw_dept
     * @mbg.generated  Sat Jan 27 17:07:32 CST 2018
     */
    @Select({ "select", "id, his_id, name, image_url, url, brief, dept_doctor_url, out_service, admin_expert_id, ",
            "is_displayed, name_en, intro", "from xpw_dept", "where id = #{id,jdbcType=BIGINT}" })
    @ResultMap("com.xmheart.mapper.XPWDeptMapper.ResultMapWithBLOBs")
    XPWDept selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table xpw_dept
     * @mbg.generated  Sat Jan 27 17:07:32 CST 2018
     */
    int updateByExampleSelective(@Param("record") XPWDept record, @Param("example") XPWDeptExample example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table xpw_dept
     * @mbg.generated  Sat Jan 27 17:07:32 CST 2018
     */
    int updateByExampleWithBLOBs(@Param("record") XPWDept record, @Param("example") XPWDeptExample example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table xpw_dept
     * @mbg.generated  Sat Jan 27 17:07:32 CST 2018
     */
    int updateByExample(@Param("record") XPWDept record, @Param("example") XPWDeptExample example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table xpw_dept
     * @mbg.generated  Sat Jan 27 17:07:32 CST 2018
     */
    int updateByPrimaryKeySelective(XPWDept record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table xpw_dept
     * @mbg.generated  Sat Jan 27 17:07:32 CST 2018
     */
    @Update({ "update xpw_dept", "set his_id = #{hisId,jdbcType=INTEGER},", "name = #{name,jdbcType=VARCHAR},",
            "image_url = #{imageUrl,jdbcType=VARCHAR},", "url = #{url,jdbcType=VARCHAR},",
            "brief = #{brief,jdbcType=VARCHAR},", "dept_doctor_url = #{deptDoctorUrl,jdbcType=VARCHAR},",
            "out_service = #{outService,jdbcType=TINYINT},", "admin_expert_id = #{adminExpertId,jdbcType=BIGINT},",
            "is_displayed = #{isDisplayed,jdbcType=TINYINT},", "name_en = #{nameEn,jdbcType=VARCHAR},",
            "intro = #{intro,jdbcType=LONGVARCHAR}", "where id = #{id,jdbcType=BIGINT}" })
    int updateByPrimaryKeyWithBLOBs(XPWDept record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table xpw_dept
     * @mbg.generated  Sat Jan 27 17:07:32 CST 2018
     */
    @Update({ "update xpw_dept", "set his_id = #{hisId,jdbcType=INTEGER},", "name = #{name,jdbcType=VARCHAR},",
            "image_url = #{imageUrl,jdbcType=VARCHAR},", "url = #{url,jdbcType=VARCHAR},",
            "brief = #{brief,jdbcType=VARCHAR},", "dept_doctor_url = #{deptDoctorUrl,jdbcType=VARCHAR},",
            "out_service = #{outService,jdbcType=TINYINT},", "admin_expert_id = #{adminExpertId,jdbcType=BIGINT},",
            "is_displayed = #{isDisplayed,jdbcType=TINYINT},", "name_en = #{nameEn,jdbcType=VARCHAR}",
            "where id = #{id,jdbcType=BIGINT}" })
    int updateByPrimaryKey(XPWDept record);

    @Select("SELECT d.id as id, d.name as name, d.image_url as image_url, d.intro as intro, d.dept_doctor_url as dept_doctor_url, "
			+ "d.url as url, " +
		    "t.name as doctor_name, t.url as doctor_url, t.image_url as doctor_image_url, "
		    + "t.duty as doctor_duty, t.professional_title as doctor_professional_title " +
		    "FROM xpw_dept as d " +
		    "LEFT JOIN xpw_doctor AS t " +
    	    "ON " +  
    		"t.dept_id = d.id where d.id = #{id, jdbcType = BIGINT}")
	@ResultMap(value = { "deptWithDoctorsMap" })
	XPWDept selectDeptWithDoctorsByPrimaryKey(@Param("id") long id);
    
    @Select("SELECT d.id as id, d.name as name, d.image_url as image_url, d.intro as intro, d.dept_doctor_url as dept_doctor_url, "
            + "d.url as url, " +
            "t.name as doctor_name, t.url as doctor_url, t.image_url as doctor_image_url, "
            + "t.duty as doctor_duty, t.professional_title as doctor_professional_title " +
            "FROM xpw_dept as d " +
            "LEFT JOIN xpw_teacher AS t " +
            "ON " +  
            "t.dept_id = d.id where d.id = #{id, jdbcType = BIGINT}")
    @ResultMap(value = { "deptWithTeachersMap" })
    XPWDept selectDeptWithTeachersByPrimaryKey(@Param("id") long id);
}
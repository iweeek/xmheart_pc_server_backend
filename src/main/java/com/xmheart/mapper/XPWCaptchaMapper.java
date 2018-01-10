package com.xmheart.mapper;

import com.xmheart.model.XPWCaptcha;
import com.xmheart.model.XPWCaptchaExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

public interface XPWCaptchaMapper {

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table xpw_captcha
     * @mbg.generated  Mon Jan 08 11:06:36 CST 2018
     */
    long countByExample(XPWCaptchaExample example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table xpw_captcha
     * @mbg.generated  Mon Jan 08 11:06:36 CST 2018
     */
    int deleteByExample(XPWCaptchaExample example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table xpw_captcha
     * @mbg.generated  Mon Jan 08 11:06:36 CST 2018
     */
    @Delete({ "delete from xpw_captcha", "where id = #{id,jdbcType=INTEGER}" })
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table xpw_captcha
     * @mbg.generated  Mon Jan 08 11:06:36 CST 2018
     */
    @Insert({ "insert into xpw_captcha (cookie, captcha, ", "expired, is_passed)",
            "values (#{cookie,jdbcType=VARCHAR}, #{captcha,jdbcType=VARCHAR}, ",
            "#{expired,jdbcType=INTEGER}, #{isPassed,jdbcType=TINYINT})" })
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id", before = false, resultType = Integer.class)
    int insert(XPWCaptcha record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table xpw_captcha
     * @mbg.generated  Mon Jan 08 11:06:36 CST 2018
     */
    int insertSelective(XPWCaptcha record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table xpw_captcha
     * @mbg.generated  Mon Jan 08 11:06:36 CST 2018
     */
    List<XPWCaptcha> selectByExample(XPWCaptchaExample example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table xpw_captcha
     * @mbg.generated  Mon Jan 08 11:06:36 CST 2018
     */
    @Select({ "select", "id, cookie, captcha, expired, is_passed", "from xpw_captcha",
            "where id = #{id,jdbcType=INTEGER}" })
    @ResultMap("com.xmheart.mapper.XPWCaptchaMapper.BaseResultMap")
    XPWCaptcha selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table xpw_captcha
     * @mbg.generated  Mon Jan 08 11:06:36 CST 2018
     */
    int updateByExampleSelective(@Param("record") XPWCaptcha record, @Param("example") XPWCaptchaExample example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table xpw_captcha
     * @mbg.generated  Mon Jan 08 11:06:36 CST 2018
     */
    int updateByExample(@Param("record") XPWCaptcha record, @Param("example") XPWCaptchaExample example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table xpw_captcha
     * @mbg.generated  Mon Jan 08 11:06:36 CST 2018
     */
    int updateByPrimaryKeySelective(XPWCaptcha record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table xpw_captcha
     * @mbg.generated  Mon Jan 08 11:06:36 CST 2018
     */
    @Update({ "update xpw_captcha", "set cookie = #{cookie,jdbcType=VARCHAR},",
            "captcha = #{captcha,jdbcType=VARCHAR},", "expired = #{expired,jdbcType=INTEGER},",
            "is_passed = #{isPassed,jdbcType=TINYINT}", "where id = #{id,jdbcType=INTEGER}" })
    int updateByPrimaryKey(XPWCaptcha record);
}
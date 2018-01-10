package com.xmheart.mapper;

import com.xmheart.model.XPWVisitLog;
import com.xmheart.model.XPWVisitLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

public interface XPWVisitLogMapper {

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table xpw_visit_log
     * @mbg.generated  Mon Jan 08 11:06:36 CST 2018
     */
    long countByExample(XPWVisitLogExample example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table xpw_visit_log
     * @mbg.generated  Mon Jan 08 11:06:36 CST 2018
     */
    int deleteByExample(XPWVisitLogExample example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table xpw_visit_log
     * @mbg.generated  Mon Jan 08 11:06:36 CST 2018
     */
    @Delete({ "delete from xpw_visit_log", "where id = #{id,jdbcType=BIGINT}" })
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table xpw_visit_log
     * @mbg.generated  Mon Jan 08 11:06:36 CST 2018
     */
    @Insert({ "insert into xpw_visit_log (uri, user_id, ", "user_agent, access_time)",
            "values (#{uri,jdbcType=VARCHAR}, #{userId,jdbcType=BIGINT}, ",
            "#{userAgent,jdbcType=VARCHAR}, #{accessTime,jdbcType=TIMESTAMP})" })
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id", before = false, resultType = Long.class)
    int insert(XPWVisitLog record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table xpw_visit_log
     * @mbg.generated  Mon Jan 08 11:06:36 CST 2018
     */
    int insertSelective(XPWVisitLog record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table xpw_visit_log
     * @mbg.generated  Mon Jan 08 11:06:36 CST 2018
     */
    List<XPWVisitLog> selectByExample(XPWVisitLogExample example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table xpw_visit_log
     * @mbg.generated  Mon Jan 08 11:06:36 CST 2018
     */
    @Select({ "select", "id, uri, user_id, user_agent, access_time", "from xpw_visit_log",
            "where id = #{id,jdbcType=BIGINT}" })
    @ResultMap("com.xmheart.mapper.XPWVisitLogMapper.BaseResultMap")
    XPWVisitLog selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table xpw_visit_log
     * @mbg.generated  Mon Jan 08 11:06:36 CST 2018
     */
    int updateByExampleSelective(@Param("record") XPWVisitLog record, @Param("example") XPWVisitLogExample example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table xpw_visit_log
     * @mbg.generated  Mon Jan 08 11:06:36 CST 2018
     */
    int updateByExample(@Param("record") XPWVisitLog record, @Param("example") XPWVisitLogExample example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table xpw_visit_log
     * @mbg.generated  Mon Jan 08 11:06:36 CST 2018
     */
    int updateByPrimaryKeySelective(XPWVisitLog record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table xpw_visit_log
     * @mbg.generated  Mon Jan 08 11:06:36 CST 2018
     */
    @Update({ "update xpw_visit_log", "set uri = #{uri,jdbcType=VARCHAR},", "user_id = #{userId,jdbcType=BIGINT},",
            "user_agent = #{userAgent,jdbcType=VARCHAR},", "access_time = #{accessTime,jdbcType=TIMESTAMP}",
            "where id = #{id,jdbcType=BIGINT}" })
    int updateByPrimaryKey(XPWVisitLog record);
}
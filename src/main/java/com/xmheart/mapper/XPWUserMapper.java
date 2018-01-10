package com.xmheart.mapper;

import com.xmheart.model.XPWUser;
import com.xmheart.model.XPWUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

public interface XPWUserMapper {

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table xpw_user
     * @mbg.generated  Wed Jan 10 23:58:45 CST 2018
     */
    long countByExample(XPWUserExample example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table xpw_user
     * @mbg.generated  Wed Jan 10 23:58:45 CST 2018
     */
    int deleteByExample(XPWUserExample example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table xpw_user
     * @mbg.generated  Wed Jan 10 23:58:45 CST 2018
     */
    @Delete({ "delete from xpw_user", "where id = #{id,jdbcType=BIGINT}" })
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table xpw_user
     * @mbg.generated  Wed Jan 10 23:58:45 CST 2018
     */
    @Insert({ "insert into xpw_user (username, password, ", "salt, user_type, ", "role_ids)",
            "values (#{username,jdbcType=VARCHAR}, #{password,jdbcType=VARCHAR}, ",
            "#{salt,jdbcType=VARCHAR}, #{userType,jdbcType=TINYINT}, ", "#{roleIds,jdbcType=VARCHAR})" })
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id", before = false, resultType = Long.class)
    int insert(XPWUser record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table xpw_user
     * @mbg.generated  Wed Jan 10 23:58:45 CST 2018
     */
    int insertSelective(XPWUser record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table xpw_user
     * @mbg.generated  Wed Jan 10 23:58:45 CST 2018
     */
    List<XPWUser> selectByExample(XPWUserExample example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table xpw_user
     * @mbg.generated  Wed Jan 10 23:58:45 CST 2018
     */
    @Select({ "select", "id, username, password, salt, user_type, role_ids", "from xpw_user",
            "where id = #{id,jdbcType=BIGINT}" })
    @ResultMap("com.xmheart.mapper.XPWUserMapper.BaseResultMap")
    XPWUser selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table xpw_user
     * @mbg.generated  Wed Jan 10 23:58:45 CST 2018
     */
    int updateByExampleSelective(@Param("record") XPWUser record, @Param("example") XPWUserExample example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table xpw_user
     * @mbg.generated  Wed Jan 10 23:58:45 CST 2018
     */
    int updateByExample(@Param("record") XPWUser record, @Param("example") XPWUserExample example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table xpw_user
     * @mbg.generated  Wed Jan 10 23:58:45 CST 2018
     */
    int updateByPrimaryKeySelective(XPWUser record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table xpw_user
     * @mbg.generated  Wed Jan 10 23:58:45 CST 2018
     */
    @Update({ "update xpw_user", "set username = #{username,jdbcType=VARCHAR},",
            "password = #{password,jdbcType=VARCHAR},", "salt = #{salt,jdbcType=VARCHAR},",
            "user_type = #{userType,jdbcType=TINYINT},", "role_ids = #{roleIds,jdbcType=VARCHAR}",
            "where id = #{id,jdbcType=BIGINT}" })
    int updateByPrimaryKey(XPWUser record);

    @Select({ "select u.id, u.username, u.`password`, u.user_type, u.role_ids, r.name as role_name \n" + 
            "from xpw_user as u \n" + 
            "left JOIN xpw_role as r\n" + 
            "on u.role_ids = r.id" })
    @ResultMap("com.xmheart.mapper.XPWUserMapper.BaseResultMap")
    List<XPWUser> index();
}
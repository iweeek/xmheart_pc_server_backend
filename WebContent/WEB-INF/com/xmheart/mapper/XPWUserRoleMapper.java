package com.xmheart.mapper;

import com.xmheart.model.XPWUserRole;
import com.xmheart.model.XPWUserRoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

public interface XPWUserRoleMapper {

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table xpw_user_role
     * @mbg.generated  Fri Jan 05 20:33:21 CST 2018
     */
    long countByExample(XPWUserRoleExample example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table xpw_user_role
     * @mbg.generated  Fri Jan 05 20:33:21 CST 2018
     */
    int deleteByExample(XPWUserRoleExample example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table xpw_user_role
     * @mbg.generated  Fri Jan 05 20:33:21 CST 2018
     */
    @Delete({ "delete from xpw_user_role", "where id = #{id,jdbcType=BIGINT}" })
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table xpw_user_role
     * @mbg.generated  Fri Jan 05 20:33:21 CST 2018
     */
    @Insert({ "insert into xpw_user_role (user_id, role_id)",
            "values (#{userId,jdbcType=BIGINT}, #{roleId,jdbcType=BIGINT})" })
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id", before = false, resultType = Long.class)
    int insert(XPWUserRole record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table xpw_user_role
     * @mbg.generated  Fri Jan 05 20:33:21 CST 2018
     */
    int insertSelective(XPWUserRole record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table xpw_user_role
     * @mbg.generated  Fri Jan 05 20:33:21 CST 2018
     */
    List<XPWUserRole> selectByExample(XPWUserRoleExample example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table xpw_user_role
     * @mbg.generated  Fri Jan 05 20:33:21 CST 2018
     */
    @Select({ "select", "id, user_id, role_id", "from xpw_user_role", "where id = #{id,jdbcType=BIGINT}" })
    @ResultMap("com.xmheart.mapper.XPWUserRoleMapper.BaseResultMap")
    XPWUserRole selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table xpw_user_role
     * @mbg.generated  Fri Jan 05 20:33:21 CST 2018
     */
    int updateByExampleSelective(@Param("record") XPWUserRole record, @Param("example") XPWUserRoleExample example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table xpw_user_role
     * @mbg.generated  Fri Jan 05 20:33:21 CST 2018
     */
    int updateByExample(@Param("record") XPWUserRole record, @Param("example") XPWUserRoleExample example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table xpw_user_role
     * @mbg.generated  Fri Jan 05 20:33:21 CST 2018
     */
    int updateByPrimaryKeySelective(XPWUserRole record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table xpw_user_role
     * @mbg.generated  Fri Jan 05 20:33:21 CST 2018
     */
    @Update({ "update xpw_user_role", "set user_id = #{userId,jdbcType=BIGINT},", "role_id = #{roleId,jdbcType=BIGINT}",
            "where id = #{id,jdbcType=BIGINT}" })
    int updateByPrimaryKey(XPWUserRole record);
}
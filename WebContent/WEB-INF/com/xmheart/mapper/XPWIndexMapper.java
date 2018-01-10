package com.xmheart.mapper;

import com.xmheart.model.XPWIndex;
import com.xmheart.model.XPWIndexExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

public interface XPWIndexMapper {

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table xpw_index
     * @mbg.generated  Fri Jan 05 20:33:21 CST 2018
     */
    long countByExample(XPWIndexExample example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table xpw_index
     * @mbg.generated  Fri Jan 05 20:33:21 CST 2018
     */
    int deleteByExample(XPWIndexExample example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table xpw_index
     * @mbg.generated  Fri Jan 05 20:33:21 CST 2018
     */
    @Delete({ "delete from xpw_index", "where id = #{id,jdbcType=BIGINT}" })
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table xpw_index
     * @mbg.generated  Fri Jan 05 20:33:21 CST 2018
     */
    @Insert({ "insert into xpw_index (language, banner_image_1_url, ",
            "banner_image_1_action_url, banner_image_2_url, ", "banner_image_2_action_url, banner_image_3_url, ",
            "banner_image_3_action_url, banner_article_1_title, ", "banner_article_1_url, banner_article_1_brief, ",
            "banner_article_1_tag, banner_article_2_title, ", "banner_article_2_url, banner_article_2_brief, ",
            "banner_article_2_tag, banner_article_3_title, ", "banner_article_3_url, banner_article_3_brief, ",
            "banner_article_3_tag)", "values (#{language,jdbcType=INTEGER}, #{bannerImage1Url,jdbcType=VARCHAR}, ",
            "#{bannerImage1ActionUrl,jdbcType=VARCHAR}, #{bannerImage2Url,jdbcType=VARCHAR}, ",
            "#{bannerImage2ActionUrl,jdbcType=VARCHAR}, #{bannerImage3Url,jdbcType=VARCHAR}, ",
            "#{bannerImage3ActionUrl,jdbcType=VARCHAR}, #{bannerArticle1Title,jdbcType=VARCHAR}, ",
            "#{bannerArticle1Url,jdbcType=VARCHAR}, #{bannerArticle1Brief,jdbcType=VARCHAR}, ",
            "#{bannerArticle1Tag,jdbcType=VARCHAR}, #{bannerArticle2Title,jdbcType=VARCHAR}, ",
            "#{bannerArticle2Url,jdbcType=VARCHAR}, #{bannerArticle2Brief,jdbcType=VARCHAR}, ",
            "#{bannerArticle2Tag,jdbcType=VARCHAR}, #{bannerArticle3Title,jdbcType=VARCHAR}, ",
            "#{bannerArticle3Url,jdbcType=VARCHAR}, #{bannerArticle3Brief,jdbcType=VARCHAR}, ",
            "#{bannerArticle3Tag,jdbcType=VARCHAR})" })
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id", before = false, resultType = Long.class)
    int insert(XPWIndex record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table xpw_index
     * @mbg.generated  Fri Jan 05 20:33:21 CST 2018
     */
    int insertSelective(XPWIndex record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table xpw_index
     * @mbg.generated  Fri Jan 05 20:33:21 CST 2018
     */
    List<XPWIndex> selectByExample(XPWIndexExample example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table xpw_index
     * @mbg.generated  Fri Jan 05 20:33:21 CST 2018
     */
    @Select({ "select", "id, language, banner_image_1_url, banner_image_1_action_url, banner_image_2_url, ",
            "banner_image_2_action_url, banner_image_3_url, banner_image_3_action_url, banner_article_1_title, ",
            "banner_article_1_url, banner_article_1_brief, banner_article_1_tag, banner_article_2_title, ",
            "banner_article_2_url, banner_article_2_brief, banner_article_2_tag, banner_article_3_title, ",
            "banner_article_3_url, banner_article_3_brief, banner_article_3_tag", "from xpw_index",
            "where id = #{id,jdbcType=BIGINT}" })
    @ResultMap("com.xmheart.mapper.XPWIndexMapper.BaseResultMap")
    XPWIndex selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table xpw_index
     * @mbg.generated  Fri Jan 05 20:33:21 CST 2018
     */
    int updateByExampleSelective(@Param("record") XPWIndex record, @Param("example") XPWIndexExample example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table xpw_index
     * @mbg.generated  Fri Jan 05 20:33:21 CST 2018
     */
    int updateByExample(@Param("record") XPWIndex record, @Param("example") XPWIndexExample example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table xpw_index
     * @mbg.generated  Fri Jan 05 20:33:21 CST 2018
     */
    int updateByPrimaryKeySelective(XPWIndex record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table xpw_index
     * @mbg.generated  Fri Jan 05 20:33:21 CST 2018
     */
    @Update({ "update xpw_index", "set language = #{language,jdbcType=INTEGER},",
            "banner_image_1_url = #{bannerImage1Url,jdbcType=VARCHAR},",
            "banner_image_1_action_url = #{bannerImage1ActionUrl,jdbcType=VARCHAR},",
            "banner_image_2_url = #{bannerImage2Url,jdbcType=VARCHAR},",
            "banner_image_2_action_url = #{bannerImage2ActionUrl,jdbcType=VARCHAR},",
            "banner_image_3_url = #{bannerImage3Url,jdbcType=VARCHAR},",
            "banner_image_3_action_url = #{bannerImage3ActionUrl,jdbcType=VARCHAR},",
            "banner_article_1_title = #{bannerArticle1Title,jdbcType=VARCHAR},",
            "banner_article_1_url = #{bannerArticle1Url,jdbcType=VARCHAR},",
            "banner_article_1_brief = #{bannerArticle1Brief,jdbcType=VARCHAR},",
            "banner_article_1_tag = #{bannerArticle1Tag,jdbcType=VARCHAR},",
            "banner_article_2_title = #{bannerArticle2Title,jdbcType=VARCHAR},",
            "banner_article_2_url = #{bannerArticle2Url,jdbcType=VARCHAR},",
            "banner_article_2_brief = #{bannerArticle2Brief,jdbcType=VARCHAR},",
            "banner_article_2_tag = #{bannerArticle2Tag,jdbcType=VARCHAR},",
            "banner_article_3_title = #{bannerArticle3Title,jdbcType=VARCHAR},",
            "banner_article_3_url = #{bannerArticle3Url,jdbcType=VARCHAR},",
            "banner_article_3_brief = #{bannerArticle3Brief,jdbcType=VARCHAR},",
            "banner_article_3_tag = #{bannerArticle3Tag,jdbcType=VARCHAR}", "where id = #{id,jdbcType=BIGINT}" })
    int updateByPrimaryKey(XPWIndex record);
}
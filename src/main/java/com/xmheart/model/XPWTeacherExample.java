package com.xmheart.model;

import java.util.ArrayList;
import java.util.List;

public class XPWTeacherExample {
    /**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table xpw_teacher
	 * @mbg.generated  Fri Mar 09 11:20:39 CST 2018
	 */
	protected String orderByClause;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table xpw_teacher
	 * @mbg.generated  Fri Mar 09 11:20:39 CST 2018
	 */
	protected boolean distinct;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table xpw_teacher
	 * @mbg.generated  Fri Mar 09 11:20:39 CST 2018
	 */
	protected List<Criteria> oredCriteria;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table xpw_teacher
	 * @mbg.generated  Fri Mar 09 11:20:39 CST 2018
	 */
	public XPWTeacherExample() {
		oredCriteria = new ArrayList<Criteria>();
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table xpw_teacher
	 * @mbg.generated  Fri Mar 09 11:20:39 CST 2018
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table xpw_teacher
	 * @mbg.generated  Fri Mar 09 11:20:39 CST 2018
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table xpw_teacher
	 * @mbg.generated  Fri Mar 09 11:20:39 CST 2018
	 */
	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table xpw_teacher
	 * @mbg.generated  Fri Mar 09 11:20:39 CST 2018
	 */
	public boolean isDistinct() {
		return distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table xpw_teacher
	 * @mbg.generated  Fri Mar 09 11:20:39 CST 2018
	 */
	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table xpw_teacher
	 * @mbg.generated  Fri Mar 09 11:20:39 CST 2018
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table xpw_teacher
	 * @mbg.generated  Fri Mar 09 11:20:39 CST 2018
	 */
	public Criteria or() {
		Criteria criteria = createCriteriaInternal();
		oredCriteria.add(criteria);
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table xpw_teacher
	 * @mbg.generated  Fri Mar 09 11:20:39 CST 2018
	 */
	public Criteria createCriteria() {
		Criteria criteria = createCriteriaInternal();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table xpw_teacher
	 * @mbg.generated  Fri Mar 09 11:20:39 CST 2018
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table xpw_teacher
	 * @mbg.generated  Fri Mar 09 11:20:39 CST 2018
	 */
	public void clear() {
		oredCriteria.clear();
		orderByClause = null;
		distinct = false;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table xpw_teacher
	 * @mbg.generated  Fri Mar 09 11:20:39 CST 2018
	 */
	protected abstract static class GeneratedCriteria {
		protected List<Criterion> criteria;

		protected GeneratedCriteria() {
			super();
			criteria = new ArrayList<Criterion>();
		}

		public boolean isValid() {
			return criteria.size() > 0;
		}

		public List<Criterion> getAllCriteria() {
			return criteria;
		}

		public List<Criterion> getCriteria() {
			return criteria;
		}

		protected void addCriterion(String condition) {
			if (condition == null) {
				throw new RuntimeException("Value for condition cannot be null");
			}
			criteria.add(new Criterion(condition));
		}

		protected void addCriterion(String condition, Object value, String property) {
			if (value == null) {
				throw new RuntimeException("Value for " + property + " cannot be null");
			}
			criteria.add(new Criterion(condition, value));
		}

		protected void addCriterion(String condition, Object value1, Object value2, String property) {
			if (value1 == null || value2 == null) {
				throw new RuntimeException("Between values for " + property + " cannot be null");
			}
			criteria.add(new Criterion(condition, value1, value2));
		}

		public Criteria andIdIsNull() {
			addCriterion("id is null");
			return (Criteria) this;
		}

		public Criteria andIdIsNotNull() {
			addCriterion("id is not null");
			return (Criteria) this;
		}

		public Criteria andIdEqualTo(Long value) {
			addCriterion("id =", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotEqualTo(Long value) {
			addCriterion("id <>", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdGreaterThan(Long value) {
			addCriterion("id >", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdGreaterThanOrEqualTo(Long value) {
			addCriterion("id >=", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdLessThan(Long value) {
			addCriterion("id <", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdLessThanOrEqualTo(Long value) {
			addCriterion("id <=", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdIn(List<Long> values) {
			addCriterion("id in", values, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotIn(List<Long> values) {
			addCriterion("id not in", values, "id");
			return (Criteria) this;
		}

		public Criteria andIdBetween(Long value1, Long value2) {
			addCriterion("id between", value1, value2, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotBetween(Long value1, Long value2) {
			addCriterion("id not between", value1, value2, "id");
			return (Criteria) this;
		}

		public Criteria andNameIsNull() {
			addCriterion("name is null");
			return (Criteria) this;
		}

		public Criteria andNameIsNotNull() {
			addCriterion("name is not null");
			return (Criteria) this;
		}

		public Criteria andNameEqualTo(String value) {
			addCriterion("name =", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameNotEqualTo(String value) {
			addCriterion("name <>", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameGreaterThan(String value) {
			addCriterion("name >", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameGreaterThanOrEqualTo(String value) {
			addCriterion("name >=", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameLessThan(String value) {
			addCriterion("name <", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameLessThanOrEqualTo(String value) {
			addCriterion("name <=", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameLike(String value) {
			addCriterion("name like", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameNotLike(String value) {
			addCriterion("name not like", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameIn(List<String> values) {
			addCriterion("name in", values, "name");
			return (Criteria) this;
		}

		public Criteria andNameNotIn(List<String> values) {
			addCriterion("name not in", values, "name");
			return (Criteria) this;
		}

		public Criteria andNameBetween(String value1, String value2) {
			addCriterion("name between", value1, value2, "name");
			return (Criteria) this;
		}

		public Criteria andNameNotBetween(String value1, String value2) {
			addCriterion("name not between", value1, value2, "name");
			return (Criteria) this;
		}

		public Criteria andDeptIdIsNull() {
			addCriterion("dept_id is null");
			return (Criteria) this;
		}

		public Criteria andDeptIdIsNotNull() {
			addCriterion("dept_id is not null");
			return (Criteria) this;
		}

		public Criteria andDeptIdEqualTo(Long value) {
			addCriterion("dept_id =", value, "deptId");
			return (Criteria) this;
		}

		public Criteria andDeptIdNotEqualTo(Long value) {
			addCriterion("dept_id <>", value, "deptId");
			return (Criteria) this;
		}

		public Criteria andDeptIdGreaterThan(Long value) {
			addCriterion("dept_id >", value, "deptId");
			return (Criteria) this;
		}

		public Criteria andDeptIdGreaterThanOrEqualTo(Long value) {
			addCriterion("dept_id >=", value, "deptId");
			return (Criteria) this;
		}

		public Criteria andDeptIdLessThan(Long value) {
			addCriterion("dept_id <", value, "deptId");
			return (Criteria) this;
		}

		public Criteria andDeptIdLessThanOrEqualTo(Long value) {
			addCriterion("dept_id <=", value, "deptId");
			return (Criteria) this;
		}

		public Criteria andDeptIdIn(List<Long> values) {
			addCriterion("dept_id in", values, "deptId");
			return (Criteria) this;
		}

		public Criteria andDeptIdNotIn(List<Long> values) {
			addCriterion("dept_id not in", values, "deptId");
			return (Criteria) this;
		}

		public Criteria andDeptIdBetween(Long value1, Long value2) {
			addCriterion("dept_id between", value1, value2, "deptId");
			return (Criteria) this;
		}

		public Criteria andDeptIdNotBetween(Long value1, Long value2) {
			addCriterion("dept_id not between", value1, value2, "deptId");
			return (Criteria) this;
		}

		public Criteria andDutyIsNull() {
			addCriterion("duty is null");
			return (Criteria) this;
		}

		public Criteria andDutyIsNotNull() {
			addCriterion("duty is not null");
			return (Criteria) this;
		}

		public Criteria andDutyEqualTo(String value) {
			addCriterion("duty =", value, "duty");
			return (Criteria) this;
		}

		public Criteria andDutyNotEqualTo(String value) {
			addCriterion("duty <>", value, "duty");
			return (Criteria) this;
		}

		public Criteria andDutyGreaterThan(String value) {
			addCriterion("duty >", value, "duty");
			return (Criteria) this;
		}

		public Criteria andDutyGreaterThanOrEqualTo(String value) {
			addCriterion("duty >=", value, "duty");
			return (Criteria) this;
		}

		public Criteria andDutyLessThan(String value) {
			addCriterion("duty <", value, "duty");
			return (Criteria) this;
		}

		public Criteria andDutyLessThanOrEqualTo(String value) {
			addCriterion("duty <=", value, "duty");
			return (Criteria) this;
		}

		public Criteria andDutyLike(String value) {
			addCriterion("duty like", value, "duty");
			return (Criteria) this;
		}

		public Criteria andDutyNotLike(String value) {
			addCriterion("duty not like", value, "duty");
			return (Criteria) this;
		}

		public Criteria andDutyIn(List<String> values) {
			addCriterion("duty in", values, "duty");
			return (Criteria) this;
		}

		public Criteria andDutyNotIn(List<String> values) {
			addCriterion("duty not in", values, "duty");
			return (Criteria) this;
		}

		public Criteria andDutyBetween(String value1, String value2) {
			addCriterion("duty between", value1, value2, "duty");
			return (Criteria) this;
		}

		public Criteria andDutyNotBetween(String value1, String value2) {
			addCriterion("duty not between", value1, value2, "duty");
			return (Criteria) this;
		}

		public Criteria andProfessionalTitleIsNull() {
			addCriterion("professional_title is null");
			return (Criteria) this;
		}

		public Criteria andProfessionalTitleIsNotNull() {
			addCriterion("professional_title is not null");
			return (Criteria) this;
		}

		public Criteria andProfessionalTitleEqualTo(String value) {
			addCriterion("professional_title =", value, "professionalTitle");
			return (Criteria) this;
		}

		public Criteria andProfessionalTitleNotEqualTo(String value) {
			addCriterion("professional_title <>", value, "professionalTitle");
			return (Criteria) this;
		}

		public Criteria andProfessionalTitleGreaterThan(String value) {
			addCriterion("professional_title >", value, "professionalTitle");
			return (Criteria) this;
		}

		public Criteria andProfessionalTitleGreaterThanOrEqualTo(String value) {
			addCriterion("professional_title >=", value, "professionalTitle");
			return (Criteria) this;
		}

		public Criteria andProfessionalTitleLessThan(String value) {
			addCriterion("professional_title <", value, "professionalTitle");
			return (Criteria) this;
		}

		public Criteria andProfessionalTitleLessThanOrEqualTo(String value) {
			addCriterion("professional_title <=", value, "professionalTitle");
			return (Criteria) this;
		}

		public Criteria andProfessionalTitleLike(String value) {
			addCriterion("professional_title like", value, "professionalTitle");
			return (Criteria) this;
		}

		public Criteria andProfessionalTitleNotLike(String value) {
			addCriterion("professional_title not like", value, "professionalTitle");
			return (Criteria) this;
		}

		public Criteria andProfessionalTitleIn(List<String> values) {
			addCriterion("professional_title in", values, "professionalTitle");
			return (Criteria) this;
		}

		public Criteria andProfessionalTitleNotIn(List<String> values) {
			addCriterion("professional_title not in", values, "professionalTitle");
			return (Criteria) this;
		}

		public Criteria andProfessionalTitleBetween(String value1, String value2) {
			addCriterion("professional_title between", value1, value2, "professionalTitle");
			return (Criteria) this;
		}

		public Criteria andProfessionalTitleNotBetween(String value1, String value2) {
			addCriterion("professional_title not between", value1, value2, "professionalTitle");
			return (Criteria) this;
		}

		public Criteria andGradeIsNull() {
			addCriterion("grade is null");
			return (Criteria) this;
		}

		public Criteria andGradeIsNotNull() {
			addCriterion("grade is not null");
			return (Criteria) this;
		}

		public Criteria andGradeEqualTo(String value) {
			addCriterion("grade =", value, "grade");
			return (Criteria) this;
		}

		public Criteria andGradeNotEqualTo(String value) {
			addCriterion("grade <>", value, "grade");
			return (Criteria) this;
		}

		public Criteria andGradeGreaterThan(String value) {
			addCriterion("grade >", value, "grade");
			return (Criteria) this;
		}

		public Criteria andGradeGreaterThanOrEqualTo(String value) {
			addCriterion("grade >=", value, "grade");
			return (Criteria) this;
		}

		public Criteria andGradeLessThan(String value) {
			addCriterion("grade <", value, "grade");
			return (Criteria) this;
		}

		public Criteria andGradeLessThanOrEqualTo(String value) {
			addCriterion("grade <=", value, "grade");
			return (Criteria) this;
		}

		public Criteria andGradeLike(String value) {
			addCriterion("grade like", value, "grade");
			return (Criteria) this;
		}

		public Criteria andGradeNotLike(String value) {
			addCriterion("grade not like", value, "grade");
			return (Criteria) this;
		}

		public Criteria andGradeIn(List<String> values) {
			addCriterion("grade in", values, "grade");
			return (Criteria) this;
		}

		public Criteria andGradeNotIn(List<String> values) {
			addCriterion("grade not in", values, "grade");
			return (Criteria) this;
		}

		public Criteria andGradeBetween(String value1, String value2) {
			addCriterion("grade between", value1, value2, "grade");
			return (Criteria) this;
		}

		public Criteria andGradeNotBetween(String value1, String value2) {
			addCriterion("grade not between", value1, value2, "grade");
			return (Criteria) this;
		}

		public Criteria andBriefIsNull() {
			addCriterion("brief is null");
			return (Criteria) this;
		}

		public Criteria andBriefIsNotNull() {
			addCriterion("brief is not null");
			return (Criteria) this;
		}

		public Criteria andBriefEqualTo(String value) {
			addCriterion("brief =", value, "brief");
			return (Criteria) this;
		}

		public Criteria andBriefNotEqualTo(String value) {
			addCriterion("brief <>", value, "brief");
			return (Criteria) this;
		}

		public Criteria andBriefGreaterThan(String value) {
			addCriterion("brief >", value, "brief");
			return (Criteria) this;
		}

		public Criteria andBriefGreaterThanOrEqualTo(String value) {
			addCriterion("brief >=", value, "brief");
			return (Criteria) this;
		}

		public Criteria andBriefLessThan(String value) {
			addCriterion("brief <", value, "brief");
			return (Criteria) this;
		}

		public Criteria andBriefLessThanOrEqualTo(String value) {
			addCriterion("brief <=", value, "brief");
			return (Criteria) this;
		}

		public Criteria andBriefLike(String value) {
			addCriterion("brief like", value, "brief");
			return (Criteria) this;
		}

		public Criteria andBriefNotLike(String value) {
			addCriterion("brief not like", value, "brief");
			return (Criteria) this;
		}

		public Criteria andBriefIn(List<String> values) {
			addCriterion("brief in", values, "brief");
			return (Criteria) this;
		}

		public Criteria andBriefNotIn(List<String> values) {
			addCriterion("brief not in", values, "brief");
			return (Criteria) this;
		}

		public Criteria andBriefBetween(String value1, String value2) {
			addCriterion("brief between", value1, value2, "brief");
			return (Criteria) this;
		}

		public Criteria andBriefNotBetween(String value1, String value2) {
			addCriterion("brief not between", value1, value2, "brief");
			return (Criteria) this;
		}

		public Criteria andIntroIsNull() {
			addCriterion("intro is null");
			return (Criteria) this;
		}

		public Criteria andIntroIsNotNull() {
			addCriterion("intro is not null");
			return (Criteria) this;
		}

		public Criteria andIntroEqualTo(String value) {
			addCriterion("intro =", value, "intro");
			return (Criteria) this;
		}

		public Criteria andIntroNotEqualTo(String value) {
			addCriterion("intro <>", value, "intro");
			return (Criteria) this;
		}

		public Criteria andIntroGreaterThan(String value) {
			addCriterion("intro >", value, "intro");
			return (Criteria) this;
		}

		public Criteria andIntroGreaterThanOrEqualTo(String value) {
			addCriterion("intro >=", value, "intro");
			return (Criteria) this;
		}

		public Criteria andIntroLessThan(String value) {
			addCriterion("intro <", value, "intro");
			return (Criteria) this;
		}

		public Criteria andIntroLessThanOrEqualTo(String value) {
			addCriterion("intro <=", value, "intro");
			return (Criteria) this;
		}

		public Criteria andIntroLike(String value) {
			addCriterion("intro like", value, "intro");
			return (Criteria) this;
		}

		public Criteria andIntroNotLike(String value) {
			addCriterion("intro not like", value, "intro");
			return (Criteria) this;
		}

		public Criteria andIntroIn(List<String> values) {
			addCriterion("intro in", values, "intro");
			return (Criteria) this;
		}

		public Criteria andIntroNotIn(List<String> values) {
			addCriterion("intro not in", values, "intro");
			return (Criteria) this;
		}

		public Criteria andIntroBetween(String value1, String value2) {
			addCriterion("intro between", value1, value2, "intro");
			return (Criteria) this;
		}

		public Criteria andIntroNotBetween(String value1, String value2) {
			addCriterion("intro not between", value1, value2, "intro");
			return (Criteria) this;
		}

		public Criteria andUrlIsNull() {
			addCriterion("url is null");
			return (Criteria) this;
		}

		public Criteria andUrlIsNotNull() {
			addCriterion("url is not null");
			return (Criteria) this;
		}

		public Criteria andUrlEqualTo(String value) {
			addCriterion("url =", value, "url");
			return (Criteria) this;
		}

		public Criteria andUrlNotEqualTo(String value) {
			addCriterion("url <>", value, "url");
			return (Criteria) this;
		}

		public Criteria andUrlGreaterThan(String value) {
			addCriterion("url >", value, "url");
			return (Criteria) this;
		}

		public Criteria andUrlGreaterThanOrEqualTo(String value) {
			addCriterion("url >=", value, "url");
			return (Criteria) this;
		}

		public Criteria andUrlLessThan(String value) {
			addCriterion("url <", value, "url");
			return (Criteria) this;
		}

		public Criteria andUrlLessThanOrEqualTo(String value) {
			addCriterion("url <=", value, "url");
			return (Criteria) this;
		}

		public Criteria andUrlLike(String value) {
			addCriterion("url like", value, "url");
			return (Criteria) this;
		}

		public Criteria andUrlNotLike(String value) {
			addCriterion("url not like", value, "url");
			return (Criteria) this;
		}

		public Criteria andUrlIn(List<String> values) {
			addCriterion("url in", values, "url");
			return (Criteria) this;
		}

		public Criteria andUrlNotIn(List<String> values) {
			addCriterion("url not in", values, "url");
			return (Criteria) this;
		}

		public Criteria andUrlBetween(String value1, String value2) {
			addCriterion("url between", value1, value2, "url");
			return (Criteria) this;
		}

		public Criteria andUrlNotBetween(String value1, String value2) {
			addCriterion("url not between", value1, value2, "url");
			return (Criteria) this;
		}

		public Criteria andImageUrlIsNull() {
			addCriterion("image_url is null");
			return (Criteria) this;
		}

		public Criteria andImageUrlIsNotNull() {
			addCriterion("image_url is not null");
			return (Criteria) this;
		}

		public Criteria andImageUrlEqualTo(String value) {
			addCriterion("image_url =", value, "imageUrl");
			return (Criteria) this;
		}

		public Criteria andImageUrlNotEqualTo(String value) {
			addCriterion("image_url <>", value, "imageUrl");
			return (Criteria) this;
		}

		public Criteria andImageUrlGreaterThan(String value) {
			addCriterion("image_url >", value, "imageUrl");
			return (Criteria) this;
		}

		public Criteria andImageUrlGreaterThanOrEqualTo(String value) {
			addCriterion("image_url >=", value, "imageUrl");
			return (Criteria) this;
		}

		public Criteria andImageUrlLessThan(String value) {
			addCriterion("image_url <", value, "imageUrl");
			return (Criteria) this;
		}

		public Criteria andImageUrlLessThanOrEqualTo(String value) {
			addCriterion("image_url <=", value, "imageUrl");
			return (Criteria) this;
		}

		public Criteria andImageUrlLike(String value) {
			addCriterion("image_url like", value, "imageUrl");
			return (Criteria) this;
		}

		public Criteria andImageUrlNotLike(String value) {
			addCriterion("image_url not like", value, "imageUrl");
			return (Criteria) this;
		}

		public Criteria andImageUrlIn(List<String> values) {
			addCriterion("image_url in", values, "imageUrl");
			return (Criteria) this;
		}

		public Criteria andImageUrlNotIn(List<String> values) {
			addCriterion("image_url not in", values, "imageUrl");
			return (Criteria) this;
		}

		public Criteria andImageUrlBetween(String value1, String value2) {
			addCriterion("image_url between", value1, value2, "imageUrl");
			return (Criteria) this;
		}

		public Criteria andImageUrlNotBetween(String value1, String value2) {
			addCriterion("image_url not between", value1, value2, "imageUrl");
			return (Criteria) this;
		}

		public Criteria andIsDisplayedIsNull() {
			addCriterion("is_displayed is null");
			return (Criteria) this;
		}

		public Criteria andIsDisplayedIsNotNull() {
			addCriterion("is_displayed is not null");
			return (Criteria) this;
		}

		public Criteria andIsDisplayedEqualTo(Boolean value) {
			addCriterion("is_displayed =", value, "isDisplayed");
			return (Criteria) this;
		}

		public Criteria andIsDisplayedNotEqualTo(Boolean value) {
			addCriterion("is_displayed <>", value, "isDisplayed");
			return (Criteria) this;
		}

		public Criteria andIsDisplayedGreaterThan(Boolean value) {
			addCriterion("is_displayed >", value, "isDisplayed");
			return (Criteria) this;
		}

		public Criteria andIsDisplayedGreaterThanOrEqualTo(Boolean value) {
			addCriterion("is_displayed >=", value, "isDisplayed");
			return (Criteria) this;
		}

		public Criteria andIsDisplayedLessThan(Boolean value) {
			addCriterion("is_displayed <", value, "isDisplayed");
			return (Criteria) this;
		}

		public Criteria andIsDisplayedLessThanOrEqualTo(Boolean value) {
			addCriterion("is_displayed <=", value, "isDisplayed");
			return (Criteria) this;
		}

		public Criteria andIsDisplayedIn(List<Boolean> values) {
			addCriterion("is_displayed in", values, "isDisplayed");
			return (Criteria) this;
		}

		public Criteria andIsDisplayedNotIn(List<Boolean> values) {
			addCriterion("is_displayed not in", values, "isDisplayed");
			return (Criteria) this;
		}

		public Criteria andIsDisplayedBetween(Boolean value1, Boolean value2) {
			addCriterion("is_displayed between", value1, value2, "isDisplayed");
			return (Criteria) this;
		}

		public Criteria andIsDisplayedNotBetween(Boolean value1, Boolean value2) {
			addCriterion("is_displayed not between", value1, value2, "isDisplayed");
			return (Criteria) this;
		}

		public Criteria andDocOrderIsNull() {
			addCriterion("doc_order is null");
			return (Criteria) this;
		}

		public Criteria andDocOrderIsNotNull() {
			addCriterion("doc_order is not null");
			return (Criteria) this;
		}

		public Criteria andDocOrderEqualTo(Integer value) {
			addCriterion("doc_order =", value, "docOrder");
			return (Criteria) this;
		}

		public Criteria andDocOrderNotEqualTo(Integer value) {
			addCriterion("doc_order <>", value, "docOrder");
			return (Criteria) this;
		}

		public Criteria andDocOrderGreaterThan(Integer value) {
			addCriterion("doc_order >", value, "docOrder");
			return (Criteria) this;
		}

		public Criteria andDocOrderGreaterThanOrEqualTo(Integer value) {
			addCriterion("doc_order >=", value, "docOrder");
			return (Criteria) this;
		}

		public Criteria andDocOrderLessThan(Integer value) {
			addCriterion("doc_order <", value, "docOrder");
			return (Criteria) this;
		}

		public Criteria andDocOrderLessThanOrEqualTo(Integer value) {
			addCriterion("doc_order <=", value, "docOrder");
			return (Criteria) this;
		}

		public Criteria andDocOrderIn(List<Integer> values) {
			addCriterion("doc_order in", values, "docOrder");
			return (Criteria) this;
		}

		public Criteria andDocOrderNotIn(List<Integer> values) {
			addCriterion("doc_order not in", values, "docOrder");
			return (Criteria) this;
		}

		public Criteria andDocOrderBetween(Integer value1, Integer value2) {
			addCriterion("doc_order between", value1, value2, "docOrder");
			return (Criteria) this;
		}

		public Criteria andDocOrderNotBetween(Integer value1, Integer value2) {
			addCriterion("doc_order not between", value1, value2, "docOrder");
			return (Criteria) this;
		}

		public Criteria andAppointmentUrlIsNull() {
			addCriterion("appointment_url is null");
			return (Criteria) this;
		}

		public Criteria andAppointmentUrlIsNotNull() {
			addCriterion("appointment_url is not null");
			return (Criteria) this;
		}

		public Criteria andAppointmentUrlEqualTo(String value) {
			addCriterion("appointment_url =", value, "appointmentUrl");
			return (Criteria) this;
		}

		public Criteria andAppointmentUrlNotEqualTo(String value) {
			addCriterion("appointment_url <>", value, "appointmentUrl");
			return (Criteria) this;
		}

		public Criteria andAppointmentUrlGreaterThan(String value) {
			addCriterion("appointment_url >", value, "appointmentUrl");
			return (Criteria) this;
		}

		public Criteria andAppointmentUrlGreaterThanOrEqualTo(String value) {
			addCriterion("appointment_url >=", value, "appointmentUrl");
			return (Criteria) this;
		}

		public Criteria andAppointmentUrlLessThan(String value) {
			addCriterion("appointment_url <", value, "appointmentUrl");
			return (Criteria) this;
		}

		public Criteria andAppointmentUrlLessThanOrEqualTo(String value) {
			addCriterion("appointment_url <=", value, "appointmentUrl");
			return (Criteria) this;
		}

		public Criteria andAppointmentUrlLike(String value) {
			addCriterion("appointment_url like", value, "appointmentUrl");
			return (Criteria) this;
		}

		public Criteria andAppointmentUrlNotLike(String value) {
			addCriterion("appointment_url not like", value, "appointmentUrl");
			return (Criteria) this;
		}

		public Criteria andAppointmentUrlIn(List<String> values) {
			addCriterion("appointment_url in", values, "appointmentUrl");
			return (Criteria) this;
		}

		public Criteria andAppointmentUrlNotIn(List<String> values) {
			addCriterion("appointment_url not in", values, "appointmentUrl");
			return (Criteria) this;
		}

		public Criteria andAppointmentUrlBetween(String value1, String value2) {
			addCriterion("appointment_url between", value1, value2, "appointmentUrl");
			return (Criteria) this;
		}

		public Criteria andAppointmentUrlNotBetween(String value1, String value2) {
			addCriterion("appointment_url not between", value1, value2, "appointmentUrl");
			return (Criteria) this;
		}
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table xpw_teacher
	 * @mbg.generated  Fri Mar 09 11:20:39 CST 2018
	 */
	public static class Criterion {
		private String condition;
		private Object value;
		private Object secondValue;
		private boolean noValue;
		private boolean singleValue;
		private boolean betweenValue;
		private boolean listValue;
		private String typeHandler;

		public String getCondition() {
			return condition;
		}

		public Object getValue() {
			return value;
		}

		public Object getSecondValue() {
			return secondValue;
		}

		public boolean isNoValue() {
			return noValue;
		}

		public boolean isSingleValue() {
			return singleValue;
		}

		public boolean isBetweenValue() {
			return betweenValue;
		}

		public boolean isListValue() {
			return listValue;
		}

		public String getTypeHandler() {
			return typeHandler;
		}

		protected Criterion(String condition) {
			super();
			this.condition = condition;
			this.typeHandler = null;
			this.noValue = true;
		}

		protected Criterion(String condition, Object value, String typeHandler) {
			super();
			this.condition = condition;
			this.value = value;
			this.typeHandler = typeHandler;
			if (value instanceof List<?>) {
				this.listValue = true;
			} else {
				this.singleValue = true;
			}
		}

		protected Criterion(String condition, Object value) {
			this(condition, value, null);
		}

		protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
			super();
			this.condition = condition;
			this.value = value;
			this.secondValue = secondValue;
			this.typeHandler = typeHandler;
			this.betweenValue = true;
		}

		protected Criterion(String condition, Object value, Object secondValue) {
			this(condition, value, secondValue, null);
		}
	}

	/**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table xpw_teacher
     *
     * @mbg.generated do_not_delete_during_merge Thu Nov 02 22:54:40 CST 2017
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }
}
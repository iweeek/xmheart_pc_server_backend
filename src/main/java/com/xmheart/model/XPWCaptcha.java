package com.xmheart.model;

public class XPWCaptcha {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column xpw_captcha.id
	 * @mbg.generated  Sun Mar 11 22:24:33 CST 2018
	 */
	private Integer id;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column xpw_captcha.cookie
	 * @mbg.generated  Sun Mar 11 22:24:33 CST 2018
	 */
	private String cookie;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column xpw_captcha.captcha
	 * @mbg.generated  Sun Mar 11 22:24:33 CST 2018
	 */
	private String captcha;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column xpw_captcha.expired
	 * @mbg.generated  Sun Mar 11 22:24:33 CST 2018
	 */
	private Integer expired;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column xpw_captcha.is_passed
	 * @mbg.generated  Sun Mar 11 22:24:33 CST 2018
	 */
	private Byte isPassed;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column xpw_captcha.id
	 * @return  the value of xpw_captcha.id
	 * @mbg.generated  Sun Mar 11 22:24:33 CST 2018
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column xpw_captcha.id
	 * @param id  the value for xpw_captcha.id
	 * @mbg.generated  Sun Mar 11 22:24:33 CST 2018
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column xpw_captcha.cookie
	 * @return  the value of xpw_captcha.cookie
	 * @mbg.generated  Sun Mar 11 22:24:33 CST 2018
	 */
	public String getCookie() {
		return cookie;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column xpw_captcha.cookie
	 * @param cookie  the value for xpw_captcha.cookie
	 * @mbg.generated  Sun Mar 11 22:24:33 CST 2018
	 */
	public void setCookie(String cookie) {
		this.cookie = cookie;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column xpw_captcha.captcha
	 * @return  the value of xpw_captcha.captcha
	 * @mbg.generated  Sun Mar 11 22:24:33 CST 2018
	 */
	public String getCaptcha() {
		return captcha;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column xpw_captcha.captcha
	 * @param captcha  the value for xpw_captcha.captcha
	 * @mbg.generated  Sun Mar 11 22:24:33 CST 2018
	 */
	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column xpw_captcha.expired
	 * @return  the value of xpw_captcha.expired
	 * @mbg.generated  Sun Mar 11 22:24:33 CST 2018
	 */
	public Integer getExpired() {
		return expired;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column xpw_captcha.expired
	 * @param expired  the value for xpw_captcha.expired
	 * @mbg.generated  Sun Mar 11 22:24:33 CST 2018
	 */
	public void setExpired(Integer expired) {
		this.expired = expired;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column xpw_captcha.is_passed
	 * @return  the value of xpw_captcha.is_passed
	 * @mbg.generated  Sun Mar 11 22:24:33 CST 2018
	 */
	public Byte getIsPassed() {
		return isPassed;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column xpw_captcha.is_passed
	 * @param isPassed  the value for xpw_captcha.is_passed
	 * @mbg.generated  Sun Mar 11 22:24:33 CST 2018
	 */
	public void setIsPassed(Byte isPassed) {
		this.isPassed = isPassed;
	}
}
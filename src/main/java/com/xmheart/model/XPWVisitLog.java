package com.xmheart.model;

import java.util.Date;

public class XPWVisitLog {

    /**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column xpw_visit_log.id
	 * @mbg.generated  Fri Mar 09 11:20:39 CST 2018
	 */
	private Long id;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column xpw_visit_log.uri
	 * @mbg.generated  Fri Mar 09 11:20:39 CST 2018
	 */
	private String uri;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column xpw_visit_log.user_id
	 * @mbg.generated  Fri Mar 09 11:20:39 CST 2018
	 */
	private Long userId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column xpw_visit_log.user_agent
	 * @mbg.generated  Fri Mar 09 11:20:39 CST 2018
	 */
	private String userAgent;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column xpw_visit_log.access_time
	 * @mbg.generated  Fri Mar 09 11:20:39 CST 2018
	 */
	private Date accessTime;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column xpw_visit_log.id
	 * @return  the value of xpw_visit_log.id
	 * @mbg.generated  Fri Mar 09 11:20:39 CST 2018
	 */
	public Long getId() {
		return id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column xpw_visit_log.id
	 * @param id  the value for xpw_visit_log.id
	 * @mbg.generated  Fri Mar 09 11:20:39 CST 2018
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column xpw_visit_log.uri
	 * @return  the value of xpw_visit_log.uri
	 * @mbg.generated  Fri Mar 09 11:20:39 CST 2018
	 */
	public String getUri() {
		return uri;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column xpw_visit_log.uri
	 * @param uri  the value for xpw_visit_log.uri
	 * @mbg.generated  Fri Mar 09 11:20:39 CST 2018
	 */
	public void setUri(String uri) {
		this.uri = uri;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column xpw_visit_log.user_id
	 * @return  the value of xpw_visit_log.user_id
	 * @mbg.generated  Fri Mar 09 11:20:39 CST 2018
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column xpw_visit_log.user_id
	 * @param userId  the value for xpw_visit_log.user_id
	 * @mbg.generated  Fri Mar 09 11:20:39 CST 2018
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column xpw_visit_log.user_agent
	 * @return  the value of xpw_visit_log.user_agent
	 * @mbg.generated  Fri Mar 09 11:20:39 CST 2018
	 */
	public String getUserAgent() {
		return userAgent;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column xpw_visit_log.user_agent
	 * @param userAgent  the value for xpw_visit_log.user_agent
	 * @mbg.generated  Fri Mar 09 11:20:39 CST 2018
	 */
	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column xpw_visit_log.access_time
	 * @return  the value of xpw_visit_log.access_time
	 * @mbg.generated  Fri Mar 09 11:20:39 CST 2018
	 */
	public Date getAccessTime() {
		return accessTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column xpw_visit_log.access_time
	 * @param accessTime  the value for xpw_visit_log.access_time
	 * @mbg.generated  Fri Mar 09 11:20:39 CST 2018
	 */
	public void setAccessTime(Date accessTime) {
		this.accessTime = accessTime;
	}

	private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
package com.xmheart.model;

import java.util.List;

public class XPWRole {

    /**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column xpw_role.id
	 * @mbg.generated  Sun Mar 11 22:24:33 CST 2018
	 */
	private Long id;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column xpw_role.name
	 * @mbg.generated  Sun Mar 11 22:24:33 CST 2018
	 */
	private String name;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column xpw_role.priv_ids
	 * @mbg.generated  Sun Mar 11 22:24:33 CST 2018
	 */
	private String privIds;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column xpw_role.id
	 * @return  the value of xpw_role.id
	 * @mbg.generated  Sun Mar 11 22:24:33 CST 2018
	 */
	public Long getId() {
		return id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column xpw_role.id
	 * @param id  the value for xpw_role.id
	 * @mbg.generated  Sun Mar 11 22:24:33 CST 2018
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column xpw_role.name
	 * @return  the value of xpw_role.name
	 * @mbg.generated  Sun Mar 11 22:24:33 CST 2018
	 */
	public String getName() {
		return name;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column xpw_role.name
	 * @param name  the value for xpw_role.name
	 * @mbg.generated  Sun Mar 11 22:24:33 CST 2018
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column xpw_role.priv_ids
	 * @return  the value of xpw_role.priv_ids
	 * @mbg.generated  Sun Mar 11 22:24:33 CST 2018
	 */
	public String getPrivIds() {
		return privIds;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column xpw_role.priv_ids
	 * @param privIds  the value for xpw_role.priv_ids
	 * @mbg.generated  Sun Mar 11 22:24:33 CST 2018
	 */
	public void setPrivIds(String privIds) {
		this.privIds = privIds;
	}

	private List<String> privName;

    public List<String> getPrivName() {
        return privName;
    }

    public void setPrivName(List<String> privName) {
        this.privName = privName;
    }

}
package com.xmheart.model;

public class XPWRole {

    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column xpw_role.id
     * @mbg.generated  Fri Nov 10 23:30:11 CST 2017
     */
    private Long id;
    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column xpw_role.name
     * @mbg.generated  Fri Nov 10 23:30:11 CST 2017
     */
    private String name;
    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column xpw_role.priv_ids
     * @mbg.generated  Fri Nov 10 23:30:11 CST 2017
     */
    private String privIds;

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column xpw_role.id
     * @return  the value of xpw_role.id
     * @mbg.generated  Fri Nov 10 23:30:11 CST 2017
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column xpw_role.id
     * @param id  the value for xpw_role.id
     * @mbg.generated  Fri Nov 10 23:30:11 CST 2017
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column xpw_role.name
     * @return  the value of xpw_role.name
     * @mbg.generated  Fri Nov 10 23:30:11 CST 2017
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column xpw_role.name
     * @param name  the value for xpw_role.name
     * @mbg.generated  Fri Nov 10 23:30:11 CST 2017
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column xpw_role.priv_ids
     * @return  the value of xpw_role.priv_ids
     * @mbg.generated  Fri Nov 10 23:30:11 CST 2017
     */
    public String getPrivIds() {
        return privIds;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column xpw_role.priv_ids
     * @param privIds  the value for xpw_role.priv_ids
     * @mbg.generated  Fri Nov 10 23:30:11 CST 2017
     */
    public void setPrivIds(String privIds) {
        this.privIds = privIds;
    }
}
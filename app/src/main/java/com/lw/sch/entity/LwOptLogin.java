package com.lw.sch.entity;

import java.sql.Timestamp;

/**
 * LwOptLogin entity. @author MyEclipse Persistence Tools
 */

public class LwOptLogin implements java.io.Serializable {

	// Fields

	private Integer loginId;
	private LwOptPersonnel lwOptPersonnel;
	private String loginName;
	private String loginPaw;
	private Timestamp createDate;
	private String createBy;
	private String isUse;

	// Constructors

	/** default constructor */
	public LwOptLogin() {
	}

	/** full constructor */
	public LwOptLogin(LwOptPersonnel lwOptPersonnel, String loginName,
			String loginPaw, Timestamp createDate, String createBy, String isUse) {
		this.lwOptPersonnel = lwOptPersonnel;
		this.loginName = loginName;
		this.loginPaw = loginPaw;
		this.createDate = createDate;
		this.createBy = createBy;
		this.isUse = isUse;
	}

	// Property accessors

	public Integer getLoginId() {
		return this.loginId;
	}

	public void setLoginId(Integer loginId) {
		this.loginId = loginId;
	}

	public LwOptPersonnel getLwOptPersonnel() {
		return this.lwOptPersonnel;
	}

	public void setLwOptPersonnel(LwOptPersonnel lwOptPersonnel) {
		this.lwOptPersonnel = lwOptPersonnel;
	}

	public String getLoginName() {
		return this.loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getLoginPaw() {
		return this.loginPaw;
	}

	public void setLoginPaw(String loginPaw) {
		this.loginPaw = loginPaw;
	}

	public Timestamp getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	public String getCreateBy() {
		return this.createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public String getIsUse() {
		return this.isUse;
	}

	public void setIsUse(String isUse) {
		this.isUse = isUse;
	}

}
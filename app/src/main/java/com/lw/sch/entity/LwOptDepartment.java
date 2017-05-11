package com.lw.sch.entity;

/**
 * LwOptDepartment entity. @author MyEclipse Persistence Tools
 */

public class LwOptDepartment implements java.io.Serializable {

	// Fields

	private Integer departmentId;
	private String departmentName;

	// Constructors

	/** default constructor */
	public LwOptDepartment() {
	}

	/** full constructor */
	public LwOptDepartment(String departmentName) {
		this.departmentName = departmentName;
	}

	// Property accessors

	public Integer getDepartmentId() {
		return this.departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	public String getDepartmentName() {
		return this.departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

}
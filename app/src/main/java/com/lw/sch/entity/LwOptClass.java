package com.lw.sch.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * LwOptClass entity. @author MyEclipse Persistence Tools
 */

public class LwOptClass implements java.io.Serializable {

	// Fields

	private Integer classId;
	private String className;
	private String classDepartment;
	private Integer classNumber;
	private Set lwOptPersonnelClasses = new HashSet(0);

	// Constructors

	/** default constructor
	 * @param s*/
	public LwOptClass(String s) {
		this.className = s;
	}

	public LwOptClass() {
	}

	/** full constructor */
	public LwOptClass(String className, String classDepartment,
			Integer classNumber, Set lwOptPersonnelClasses) {
		this.className = className;
		this.classDepartment = classDepartment;
		this.classNumber = classNumber;
		this.lwOptPersonnelClasses = lwOptPersonnelClasses;
	}

	// Property accessors

	public Integer getClassId() {
		return this.classId;
	}

	public void setClassId(Integer classId) {
		this.classId = classId;
	}

	public String getClassName() {
		return this.className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getClassDepartment() {
		return this.classDepartment;
	}

	public void setClassDepartment(String classDepartment) {
		this.classDepartment = classDepartment;
	}

	public Integer getClassNumber() {
		return this.classNumber;
	}

	public void setClassNumber(Integer classNumber) {
		this.classNumber = classNumber;
	}

	public Set getLwOptPersonnelClasses() {
		return this.lwOptPersonnelClasses;
	}

	public void setLwOptPersonnelClasses(Set lwOptPersonnelClasses) {
		this.lwOptPersonnelClasses = lwOptPersonnelClasses;
	}

}
package com.lw.sch.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * LwOptCurriculum entity. @author MyEclipse Persistence Tools
 */

public class LwOptCurriculum implements java.io.Serializable {

	// Fields

	private Integer curriculumId;
	private String curriculumName;
	private Double curriculumCredit;
	private Set lwOptCurriculumStudentses = new HashSet(0);

	// Constructors

	/** default constructor */
	public LwOptCurriculum() {
	}

	/** full constructor */
	public LwOptCurriculum(String curriculumName, Double curriculumCredit,
			Set lwOptCurriculumStudentses) {
		this.curriculumName = curriculumName;
		this.curriculumCredit = curriculumCredit;
		this.lwOptCurriculumStudentses = lwOptCurriculumStudentses;
	}

	// Property accessors

	public Integer getCurriculumId() {
		return this.curriculumId;
	}

	public void setCurriculumId(Integer curriculumId) {
		this.curriculumId = curriculumId;
	}

	public String getCurriculumName() {
		return this.curriculumName;
	}

	public void setCurriculumName(String curriculumName) {
		this.curriculumName = curriculumName;
	}

	public Double getCurriculumCredit() {
		return this.curriculumCredit;
	}

	public void setCurriculumCredit(Double curriculumCredit) {
		this.curriculumCredit = curriculumCredit;
	}

	public Set getLwOptCurriculumStudentses() {
		return this.lwOptCurriculumStudentses;
	}

	public void setLwOptCurriculumStudentses(Set lwOptCurriculumStudentses) {
		this.lwOptCurriculumStudentses = lwOptCurriculumStudentses;
	}

}
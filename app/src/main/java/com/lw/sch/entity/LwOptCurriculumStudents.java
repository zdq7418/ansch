package com.lw.sch.entity;

/**
 * LwOptCurriculumStudents entity. @author MyEclipse Persistence Tools
 */

public class LwOptCurriculumStudents implements java.io.Serializable {

	// Fields

	private Integer curriculumPersonnelId;
	private LwOptCurriculum lwOptCurriculum;
	private LwOptPersonnel lwOptPersonnel;

	// Constructors

	/** default constructor */
	public LwOptCurriculumStudents() {
	}

	/** full constructor */
	public LwOptCurriculumStudents(LwOptCurriculum lwOptCurriculum,
			LwOptPersonnel lwOptPersonnel) {
		this.lwOptCurriculum = lwOptCurriculum;
		this.lwOptPersonnel = lwOptPersonnel;
	}

	// Property accessors

	public Integer getCurriculumPersonnelId() {
		return this.curriculumPersonnelId;
	}

	public void setCurriculumPersonnelId(Integer curriculumPersonnelId) {
		this.curriculumPersonnelId = curriculumPersonnelId;
	}

	public LwOptCurriculum getLwOptCurriculum() {
		return this.lwOptCurriculum;
	}

	public void setLwOptCurriculum(LwOptCurriculum lwOptCurriculum) {
		this.lwOptCurriculum = lwOptCurriculum;
	}

	public LwOptPersonnel getLwOptPersonnel() {
		return this.lwOptPersonnel;
	}

	public void setLwOptPersonnel(LwOptPersonnel lwOptPersonnel) {
		this.lwOptPersonnel = lwOptPersonnel;
	}

}
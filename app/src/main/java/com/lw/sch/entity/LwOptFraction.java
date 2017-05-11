package com.lw.sch.entity;

/**
 * LwOptFraction entity. @author MyEclipse Persistence Tools
 */

public class LwOptFraction implements java.io.Serializable {

	// Fields

	private Integer fractionId;
	private LwOptPersonnel lwOptPersonnel;
	private String subjectName;
	private Double subjectFraction;

	// Constructors

	/** default constructor */
	public LwOptFraction() {
	}

	/** full constructor */
	public LwOptFraction(LwOptPersonnel lwOptPersonnel, String subjectName,
			Double subjectFraction) {
		this.lwOptPersonnel = lwOptPersonnel;
		this.subjectName = subjectName;
		this.subjectFraction = subjectFraction;
	}

	// Property accessors

	public Integer getFractionId() {
		return this.fractionId;
	}

	public void setFractionId(Integer fractionId) {
		this.fractionId = fractionId;
	}

	public LwOptPersonnel getLwOptPersonnel() {
		return this.lwOptPersonnel;
	}

	public void setLwOptPersonnel(LwOptPersonnel lwOptPersonnel) {
		this.lwOptPersonnel = lwOptPersonnel;
	}

	public String getSubjectName() {
		return this.subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public Double getSubjectFraction() {
		return this.subjectFraction;
	}

	public void setSubjectFraction(Double subjectFraction) {
		this.subjectFraction = subjectFraction;
	}

}
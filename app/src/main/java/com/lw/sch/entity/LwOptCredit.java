package com.lw.sch.entity;

/**
 * LwOptCredit entity. @author MyEclipse Persistence Tools
 */

public class LwOptCredit implements java.io.Serializable {

	// Fields

	private Integer creditId;
	private LwOptPersonnel lwOptPersonnel;
	private Double creditRecord;
	private String creditSource;

	// Constructors

	/** default constructor */
	public LwOptCredit() {
	}

	/** full constructor */
	public LwOptCredit(LwOptPersonnel lwOptPersonnel, Double creditRecord,
			String creditSource) {
		this.lwOptPersonnel = lwOptPersonnel;
		this.creditRecord = creditRecord;
		this.creditSource = creditSource;
	}

	// Property accessors

	public Integer getCreditId() {
		return this.creditId;
	}

	public void setCreditId(Integer creditId) {
		this.creditId = creditId;
	}

	public LwOptPersonnel getLwOptPersonnel() {
		return this.lwOptPersonnel;
	}

	public void setLwOptPersonnel(LwOptPersonnel lwOptPersonnel) {
		this.lwOptPersonnel = lwOptPersonnel;
	}

	public Double getCreditRecord() {
		return this.creditRecord;
	}

	public void setCreditRecord(Double creditRecord) {
		this.creditRecord = creditRecord;
	}

	public String getCreditSource() {
		return this.creditSource;
	}

	public void setCreditSource(String creditSource) {
		this.creditSource = creditSource;
	}

}
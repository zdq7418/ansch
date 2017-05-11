package com.lw.sch.entity;

/**
 * LwOptPersonnelClass entity. @author MyEclipse Persistence Tools
 */

public class LwOptPersonnelClass implements java.io.Serializable {

	// Fields

	private LwOptPersonnelClassId id;
	private LwOptClass lwOptClass;
	private LwOptPersonnel lwOptPersonnel;

	// Constructors

	/** default constructor */
	public LwOptPersonnelClass() {
	}

	/** minimal constructor */
	public LwOptPersonnelClass(LwOptPersonnelClassId id) {
		this.id = id;
	}

	/** full constructor */
	public LwOptPersonnelClass(LwOptPersonnelClassId id, LwOptClass lwOptClass,
			LwOptPersonnel lwOptPersonnel) {
		this.id = id;
		this.lwOptClass = lwOptClass;
		this.lwOptPersonnel = lwOptPersonnel;
	}

	// Property accessors

	public LwOptPersonnelClassId getId() {
		return this.id;
	}

	public void setId(LwOptPersonnelClassId id) {
		this.id = id;
	}

	public LwOptClass getLwOptClass() {
		return this.lwOptClass;
	}

	public void setLwOptClass(LwOptClass lwOptClass) {
		this.lwOptClass = lwOptClass;
	}

	public LwOptPersonnel getLwOptPersonnel() {
		return this.lwOptPersonnel;
	}

	public void setLwOptPersonnel(LwOptPersonnel lwOptPersonnel) {
		this.lwOptPersonnel = lwOptPersonnel;
	}

}
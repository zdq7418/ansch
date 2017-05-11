package com.lw.sch.entity;

/**
 * LwOptPersonnelRole entity. @author MyEclipse Persistence Tools
 */

public class LwOptPersonnelRole implements java.io.Serializable {

	// Fields

	private LwOptPersonnelRoleId id;
	private LwOptRole lwOptRole;
	private LwOptPersonnel lwOptPersonnel;

	// Constructors

	/** default constructor */
	public LwOptPersonnelRole() {
	}

	/** full constructor */
	public LwOptPersonnelRole(LwOptPersonnelRoleId id, LwOptRole lwOptRole,
			LwOptPersonnel lwOptPersonnel) {
		this.id = id;
		this.lwOptRole = lwOptRole;
		this.lwOptPersonnel = lwOptPersonnel;
	}

	// Property accessors

	public LwOptPersonnelRoleId getId() {
		return this.id;
	}

	public void setId(LwOptPersonnelRoleId id) {
		this.id = id;
	}

	public LwOptRole getLwOptRole() {
		return this.lwOptRole;
	}

	public void setLwOptRole(LwOptRole lwOptRole) {
		this.lwOptRole = lwOptRole;
	}

	public LwOptPersonnel getLwOptPersonnel() {
		return this.lwOptPersonnel;
	}

	public void setLwOptPersonnel(LwOptPersonnel lwOptPersonnel) {
		this.lwOptPersonnel = lwOptPersonnel;
	}

}
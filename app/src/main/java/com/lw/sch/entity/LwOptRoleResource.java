package com.lw.sch.entity;

/**
 * LwOptRoleResource entity. @author MyEclipse Persistence Tools
 */

public class LwOptRoleResource implements java.io.Serializable {

	// Fields

	private LwOptRoleResourceId id;
	private LwOptRole lwOptRole;
	private LwOptResource lwOptResource;

	// Constructors

	/** default constructor */
	public LwOptRoleResource() {
	}

	/** full constructor */
	public LwOptRoleResource(LwOptRoleResourceId id, LwOptRole lwOptRole,
			LwOptResource lwOptResource) {
		this.id = id;
		this.lwOptRole = lwOptRole;
		this.lwOptResource = lwOptResource;
	}

	// Property accessors

	public LwOptRoleResourceId getId() {
		return this.id;
	}

	public void setId(LwOptRoleResourceId id) {
		this.id = id;
	}

	public LwOptRole getLwOptRole() {
		return this.lwOptRole;
	}

	public void setLwOptRole(LwOptRole lwOptRole) {
		this.lwOptRole = lwOptRole;
	}

	public LwOptResource getLwOptResource() {
		return this.lwOptResource;
	}

	public void setLwOptResource(LwOptResource lwOptResource) {
		this.lwOptResource = lwOptResource;
	}

}
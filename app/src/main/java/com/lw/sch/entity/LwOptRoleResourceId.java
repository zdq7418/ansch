package com.lw.sch.entity;

/**
 * LwOptRoleResourceId entity. @author MyEclipse Persistence Tools
 */

public class LwOptRoleResourceId implements java.io.Serializable {

	// Fields

	private LwOptRole lwOptRole;
	private LwOptResource lwOptResource;

	// Constructors

	/** default constructor */
	public LwOptRoleResourceId() {
	}

	/** full constructor */
	public LwOptRoleResourceId(LwOptRole lwOptRole, LwOptResource lwOptResource) {
		this.lwOptRole = lwOptRole;
		this.lwOptResource = lwOptResource;
	}

	// Property accessors

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

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof LwOptRoleResourceId))
			return false;
		LwOptRoleResourceId castOther = (LwOptRoleResourceId) other;

		return ((this.getLwOptRole() == castOther.getLwOptRole()) || (this
				.getLwOptRole() != null && castOther.getLwOptRole() != null && this
				.getLwOptRole().equals(castOther.getLwOptRole())))
				&& ((this.getLwOptResource() == castOther.getLwOptResource()) || (this
						.getLwOptResource() != null
						&& castOther.getLwOptResource() != null && this
						.getLwOptResource()
						.equals(castOther.getLwOptResource())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getLwOptRole() == null ? 0 : this.getLwOptRole().hashCode());
		result = 37
				* result
				+ (getLwOptResource() == null ? 0 : this.getLwOptResource()
						.hashCode());
		return result;
	}

}
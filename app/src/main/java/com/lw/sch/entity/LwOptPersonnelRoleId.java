package com.lw.sch.entity;

/**
 * LwOptPersonnelRoleId entity. @author MyEclipse Persistence Tools
 */

public class LwOptPersonnelRoleId implements java.io.Serializable {

	// Fields

	private LwOptPersonnel lwOptPersonnel;
	private LwOptRole lwOptRole;

	// Constructors

	/** default constructor */
	public LwOptPersonnelRoleId() {
	}

	/** full constructor */
	public LwOptPersonnelRoleId(LwOptPersonnel lwOptPersonnel,
			LwOptRole lwOptRole) {
		this.lwOptPersonnel = lwOptPersonnel;
		this.lwOptRole = lwOptRole;
	}

	// Property accessors

	public LwOptPersonnel getLwOptPersonnel() {
		return this.lwOptPersonnel;
	}

	public void setLwOptPersonnel(LwOptPersonnel lwOptPersonnel) {
		this.lwOptPersonnel = lwOptPersonnel;
	}

	public LwOptRole getLwOptRole() {
		return this.lwOptRole;
	}

	public void setLwOptRole(LwOptRole lwOptRole) {
		this.lwOptRole = lwOptRole;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof LwOptPersonnelRoleId))
			return false;
		LwOptPersonnelRoleId castOther = (LwOptPersonnelRoleId) other;

		return ((this.getLwOptPersonnel() == castOther.getLwOptPersonnel()) || (this
				.getLwOptPersonnel() != null
				&& castOther.getLwOptPersonnel() != null && this
				.getLwOptPersonnel().equals(castOther.getLwOptPersonnel())))
				&& ((this.getLwOptRole() == castOther.getLwOptRole()) || (this
						.getLwOptRole() != null
						&& castOther.getLwOptRole() != null && this
						.getLwOptRole().equals(castOther.getLwOptRole())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getLwOptPersonnel() == null ? 0 : this.getLwOptPersonnel()
						.hashCode());
		result = 37 * result
				+ (getLwOptRole() == null ? 0 : this.getLwOptRole().hashCode());
		return result;
	}

}
package com.lw.sch.entity;

/**
 * LwOptPersonnelClassId entity. @author MyEclipse Persistence Tools
 */

public class LwOptPersonnelClassId implements java.io.Serializable {

	// Fields

	private LwOptClass lwOptClass;
	private LwOptPersonnel lwOptPersonnel;

	// Constructors

	/** default constructor */
	public LwOptPersonnelClassId() {
	}

	/** full constructor */
	public LwOptPersonnelClassId(LwOptClass lwOptClass,
			LwOptPersonnel lwOptPersonnel) {
		this.lwOptClass = lwOptClass;
		this.lwOptPersonnel = lwOptPersonnel;
	}

	// Property accessors

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

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof LwOptPersonnelClassId))
			return false;
		LwOptPersonnelClassId castOther = (LwOptPersonnelClassId) other;

		return ((this.getLwOptClass() == castOther.getLwOptClass()) || (this
				.getLwOptClass() != null && castOther.getLwOptClass() != null && this
				.getLwOptClass().equals(castOther.getLwOptClass())))
				&& ((this.getLwOptPersonnel() == castOther.getLwOptPersonnel()) || (this
						.getLwOptPersonnel() != null
						&& castOther.getLwOptPersonnel() != null && this
						.getLwOptPersonnel().equals(
								castOther.getLwOptPersonnel())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getLwOptClass() == null ? 0 : this.getLwOptClass()
						.hashCode());
		result = 37
				* result
				+ (getLwOptPersonnel() == null ? 0 : this.getLwOptPersonnel()
						.hashCode());
		return result;
	}

}
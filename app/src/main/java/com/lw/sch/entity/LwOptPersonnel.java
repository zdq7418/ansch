package com.lw.sch.entity;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * LwOptPersonnel entity. @author MyEclipse Persistence Tools
 */

public class LwOptPersonnel implements java.io.Serializable {

	// Fields

	private Integer personnelId;
	private String personnelName;
	private String personnelSex;
	private Timestamp inductionDate;
	private String personnelPosition;
	private Integer roomId;
	private String isUse;
	private Integer isTeacher;
	private transient Set lwOptCredits = new HashSet(0);
	private transient Set lwOptLogins = new HashSet(0);
	private transient Set lwOptFractions = new HashSet(0);
	private transient Set lwOptPersonnelRoles = new HashSet(0);
	private transient Set lwOptPersonnelClasses = new HashSet(0);
	private transient Set lwOptCurriculumStudentses = new HashSet(0);

	// Constructors

	/** default constructor */
	public LwOptPersonnel(String personnelName) {
		this.personnelName = personnelName;
	}

	public LwOptPersonnel() {
	}

	/** full constructor */
	public LwOptPersonnel(String personnelName, String personnelSex,
			Timestamp inductionDate, String personnelPosition, Integer roomId,
			String isUse, Integer isTeacher, Set lwOptCredits, Set lwOptLogins,
			Set lwOptFractions, Set lwOptPersonnelRoles,
			Set lwOptPersonnelClasses, Set lwOptCurriculumStudentses) {
		this.personnelName = personnelName;
		this.personnelSex = personnelSex;
		this.inductionDate = inductionDate;
		this.personnelPosition = personnelPosition;
		this.roomId = roomId;
		this.isUse = isUse;
		this.isTeacher = isTeacher;
		this.lwOptCredits = lwOptCredits;
		this.lwOptLogins = lwOptLogins;
		this.lwOptFractions = lwOptFractions;
		this.lwOptPersonnelRoles = lwOptPersonnelRoles;
		this.lwOptPersonnelClasses = lwOptPersonnelClasses;
		this.lwOptCurriculumStudentses = lwOptCurriculumStudentses;
	}

	// Property accessors

	public Integer getPersonnelId() {
		return this.personnelId;
	}

	public void setPersonnelId(Integer personnelId) {
		this.personnelId = personnelId;
	}

	public String getPersonnelName() {
		return this.personnelName;
	}

	public void setPersonnelName(String personnelName) {
		this.personnelName = personnelName;
	}

	public String getPersonnelSex() {
		return this.personnelSex;
	}

	public void setPersonnelSex(String personnelSex) {
		this.personnelSex = personnelSex;
	}

	public Timestamp getInductionDate() {
		return this.inductionDate;
	}

	public void setInductionDate(Timestamp inductionDate) {
		this.inductionDate = inductionDate;
	}

	public String getPersonnelPosition() {
		return this.personnelPosition;
	}

	public void setPersonnelPosition(String personnelPosition) {
		this.personnelPosition = personnelPosition;
	}

	public Integer getRoomId() {
		return this.roomId;
	}

	public void setRoomId(Integer roomId) {
		this.roomId = roomId;
	}

	public String getIsUse() {
		return this.isUse;
	}

	public void setIsUse(String isUse) {
		this.isUse = isUse;
	}

	public Integer getIsTeacher() {
		return this.isTeacher;
	}

	public void setIsTeacher(Integer isTeacher) {
		this.isTeacher = isTeacher;
	}

	public Set getLwOptCredits() {
		return this.lwOptCredits;
	}

	public void setLwOptCredits(Set lwOptCredits) {
		this.lwOptCredits = lwOptCredits;
	}

	public Set getLwOptLogins() {
		return this.lwOptLogins;
	}

	public void setLwOptLogins(Set lwOptLogins) {
		this.lwOptLogins = lwOptLogins;
	}

	public Set getLwOptFractions() {
		return this.lwOptFractions;
	}

	public void setLwOptFractions(Set lwOptFractions) {
		this.lwOptFractions = lwOptFractions;
	}

	public Set getLwOptPersonnelRoles() {
		return this.lwOptPersonnelRoles;
	}

	public void setLwOptPersonnelRoles(Set lwOptPersonnelRoles) {
		this.lwOptPersonnelRoles = lwOptPersonnelRoles;
	}

	public Set getLwOptPersonnelClasses() {
		return this.lwOptPersonnelClasses;
	}

	public void setLwOptPersonnelClasses(Set lwOptPersonnelClasses) {
		this.lwOptPersonnelClasses = lwOptPersonnelClasses;
	}

	public Set getLwOptCurriculumStudentses() {
		return this.lwOptCurriculumStudentses;
	}

	public void setLwOptCurriculumStudentses(Set lwOptCurriculumStudentses) {
		this.lwOptCurriculumStudentses = lwOptCurriculumStudentses;
	}

	@Override
	public String toString() {
		return personnelName;
	}
}
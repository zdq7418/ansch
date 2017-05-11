package com.lw.sch.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * LwOptRole entity. @author MyEclipse Persistence Tools
 */

public class LwOptRole implements java.io.Serializable {

	// Fields

	private Integer roleId;
	private String roleName;
	private String roleIntroduce;
	private Set lwOptRoleResources = new HashSet(0);
	private Set lwOptPersonnelRoles = new HashSet(0);

	// Constructors

	/** default constructor */
	public LwOptRole() {
	}

	/** full constructor */
	public LwOptRole(String roleName, String roleIntroduce,
			Set lwOptRoleResources, Set lwOptPersonnelRoles) {
		this.roleName = roleName;
		this.roleIntroduce = roleIntroduce;
		this.lwOptRoleResources = lwOptRoleResources;
		this.lwOptPersonnelRoles = lwOptPersonnelRoles;
	}

	// Property accessors

	public Integer getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleIntroduce() {
		return this.roleIntroduce;
	}

	public void setRoleIntroduce(String roleIntroduce) {
		this.roleIntroduce = roleIntroduce;
	}

	public Set getLwOptRoleResources() {
		return this.lwOptRoleResources;
	}

	public void setLwOptRoleResources(Set lwOptRoleResources) {
		this.lwOptRoleResources = lwOptRoleResources;
	}

	public Set getLwOptPersonnelRoles() {
		return this.lwOptPersonnelRoles;
	}

	public void setLwOptPersonnelRoles(Set lwOptPersonnelRoles) {
		this.lwOptPersonnelRoles = lwOptPersonnelRoles;
	}

}
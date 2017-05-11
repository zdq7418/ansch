package com.lw.sch.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * LwOptResource entity. @author MyEclipse Persistence Tools
 */

public class LwOptResource implements java.io.Serializable {

	// Fields

	private Integer resourceId;
	private String resourceName;
	private Integer resourceParent;
	private String resourcePath;
	private Set lwOptRoleResources = new HashSet(0);

	// Constructors

	/** default constructor */
	public LwOptResource() {
	}

	/** full constructor */
	public LwOptResource(String resourceName, Integer resourceParent,
			String resourcePath, Set lwOptRoleResources) {
		this.resourceName = resourceName;
		this.resourceParent = resourceParent;
		this.resourcePath = resourcePath;
		this.lwOptRoleResources = lwOptRoleResources;
	}

	// Property accessors

	public Integer getResourceId() {
		return this.resourceId;
	}

	public void setResourceId(Integer resourceId) {
		this.resourceId = resourceId;
	}

	public String getResourceName() {
		return this.resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public Integer getResourceParent() {
		return this.resourceParent;
	}

	public void setResourceParent(Integer resourceParent) {
		this.resourceParent = resourceParent;
	}

	public String getResourcePath() {
		return this.resourcePath;
	}

	public void setResourcePath(String resourcePath) {
		this.resourcePath = resourcePath;
	}

	public Set getLwOptRoleResources() {
		return this.lwOptRoleResources;
	}

	public void setLwOptRoleResources(Set lwOptRoleResources) {
		this.lwOptRoleResources = lwOptRoleResources;
	}

}
package com.lw.sch.entity;

/**
 * LwOptMansion entity. @author MyEclipse Persistence Tools
 */

public class LwOptMansion implements java.io.Serializable {

	// Fields

	private Integer mansionId;
	private String mansionName;
	private Integer floorId;
	private String mansionIntroduce;

	// Constructors

	/** default constructor */
	public LwOptMansion() {
	}

	/** full constructor */
	public LwOptMansion(String mansionName, Integer floorId,
			String mansionIntroduce) {
		this.mansionName = mansionName;
		this.floorId = floorId;
		this.mansionIntroduce = mansionIntroduce;
	}

	// Property accessors

	public Integer getMansionId() {
		return this.mansionId;
	}

	public void setMansionId(Integer mansionId) {
		this.mansionId = mansionId;
	}

	public String getMansionName() {
		return this.mansionName;
	}

	public void setMansionName(String mansionName) {
		this.mansionName = mansionName;
	}

	public Integer getFloorId() {
		return this.floorId;
	}

	public void setFloorId(Integer floorId) {
		this.floorId = floorId;
	}

	public String getMansionIntroduce() {
		return this.mansionIntroduce;
	}

	public void setMansionIntroduce(String mansionIntroduce) {
		this.mansionIntroduce = mansionIntroduce;
	}

}
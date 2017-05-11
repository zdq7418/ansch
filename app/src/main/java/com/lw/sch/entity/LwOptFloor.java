package com.lw.sch.entity;

/**
 * LwOptFloor entity. @author MyEclipse Persistence Tools
 */

public class LwOptFloor implements java.io.Serializable {

	// Fields

	private Integer floorId;
	private String floorName;
	private Integer roomId;
	private String floorIntroduce;

	// Constructors

	/** default constructor */
	public LwOptFloor() {
	}

	/** full constructor */
	public LwOptFloor(String floorName, Integer roomId, String floorIntroduce) {
		this.floorName = floorName;
		this.roomId = roomId;
		this.floorIntroduce = floorIntroduce;
	}

	// Property accessors

	public Integer getFloorId() {
		return this.floorId;
	}

	public void setFloorId(Integer floorId) {
		this.floorId = floorId;
	}

	public String getFloorName() {
		return this.floorName;
	}

	public void setFloorName(String floorName) {
		this.floorName = floorName;
	}

	public Integer getRoomId() {
		return this.roomId;
	}

	public void setRoomId(Integer roomId) {
		this.roomId = roomId;
	}

	public String getFloorIntroduce() {
		return this.floorIntroduce;
	}

	public void setFloorIntroduce(String floorIntroduce) {
		this.floorIntroduce = floorIntroduce;
	}

}
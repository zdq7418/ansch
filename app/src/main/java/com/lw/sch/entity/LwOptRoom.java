package com.lw.sch.entity;

/**
 * LwOptRoom entity. @author MyEclipse Persistence Tools
 */

public class LwOptRoom implements java.io.Serializable {

	// Fields

	private Integer roomId;
	private String roomNo;
	private Integer maxNumber;
	private Integer minNumber;

	// Constructors

	/** default constructor */
	public LwOptRoom() {
	}

	/** full constructor */
	public LwOptRoom(String roomNo, Integer maxNumber, Integer minNumber) {
		this.roomNo = roomNo;
		this.maxNumber = maxNumber;
		this.minNumber = minNumber;
	}

	// Property accessors

	public Integer getRoomId() {
		return this.roomId;
	}

	public void setRoomId(Integer roomId) {
		this.roomId = roomId;
	}

	public String getRoomNo() {
		return this.roomNo;
	}

	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
	}

	public Integer getMaxNumber() {
		return this.maxNumber;
	}

	public void setMaxNumber(Integer maxNumber) {
		this.maxNumber = maxNumber;
	}

	public Integer getMinNumber() {
		return this.minNumber;
	}

	public void setMinNumber(Integer minNumber) {
		this.minNumber = minNumber;
	}

}
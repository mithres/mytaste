package com.vc.entity;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Room {

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String roomId = null;

	private String roomName = null;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private UserInfo creator = null;
	
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private UserInfo moderator = null;

	private Integer maxPeopleCount = null;

	private Integer currentPeopleCount = null;

	private RoomPrivacy roomPrivacy = null;

	private RoomType roomType = null;

	private String password = null;
	
	private Timestamp createdTime = null;
	
	//The rtmp url that connect to this room
	private String streamUrl = null;

	// Red5 publish stream name if the room type is conference there need be
	// more than one stream
	// and all members in room need to receive streams
	@Transient
	private String[] streamNames = null;

	public String getRoomId() {
		return roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public Integer getMaxPeopleCount() {
		return maxPeopleCount;
	}

	public void setMaxPeopleCount(Integer maxPeopleCount) {
		this.maxPeopleCount = maxPeopleCount;
	}

	public Integer getCurrentPeopleCount() {
		return currentPeopleCount;
	}

	public void setCurrentPeopleCount(Integer currentPeopleCount) {
		this.currentPeopleCount = currentPeopleCount;
	}

	public RoomPrivacy getRoomPrivacy() {
		return roomPrivacy;
	}

	public void setRoomPrivacy(RoomPrivacy roomPrivacy) {
		this.roomPrivacy = roomPrivacy;
	}

	public RoomType getRoomType() {
		return roomType;
	}

	public void setRoomType(RoomType roomType) {
		this.roomType = roomType;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Timestamp getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Timestamp createdTime) {
		this.createdTime = createdTime;
	}

	public String[] getStreamNames() {
		return streamNames;
	}

	public void setStreamNames(String[] streamNames) {
		this.streamNames = streamNames;
	}

	public UserInfo getModerator() {
		return moderator;
	}

	public void setModerator(UserInfo moderator) {
		this.moderator = moderator;
	}

	public UserInfo getCreator() {
		return creator;
	}

	public void setCreator(UserInfo creator) {
		this.creator = creator;
	}

	public String getStreamUrl() {
		return streamUrl;
	}

	public void setStreamUrl(String streamUrl) {
		this.streamUrl = streamUrl;
	}

}

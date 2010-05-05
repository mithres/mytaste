package com.vc.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Channels {

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String id = null;

	private String channelName = null;

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
	@OrderBy("addedTime desc")
	private List<PlayList> playLists = new ArrayList<PlayList>();

	@Column(name = "parent_id")
	private String parentId = null;

	@OneToMany(targetEntity = Channels.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_id")
	@OrderBy("channelName")
	private List<Channels> childChannels = new ArrayList<Channels>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public List<PlayList> getPlayLists() {
		return playLists;
	}

	public void setPlayLists(List<PlayList> playLists) {
		this.playLists = playLists;
	}

	public List<Channels> getChildChannels() {
		return childChannels;
	}

	public void setChildChannels(List<Channels> childChannels) {
		this.childChannels = childChannels;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

}

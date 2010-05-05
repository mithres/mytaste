package com.vc.service.vod;

import com.vc.entity.PlayListType;

public class PlayListSearchCondition {
	
	private PlayListType playListType = null;
	
	private String channelId = null;
	
	private String orderBy = null;
	
	private boolean withComments = true;
	private boolean withTags = false;
	private boolean withChannel = false;

	public PlayListType getPlayListType() {
		return playListType;
	}

	public void setPlayListType(PlayListType playListType) {
		this.playListType = playListType;
	}

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public boolean isWithComments() {
		return withComments;
	}

	public void setWithComments(boolean withComments) {
		this.withComments = withComments;
	}

	public boolean isWithTags() {
		return withTags;
	}

	public void setWithTags(boolean withTags) {
		this.withTags = withTags;
	}

	public boolean isWithChannel() {
		return withChannel;
	}

	public void setWithChannel(boolean withChannel) {
		this.withChannel = withChannel;
	}
	
}

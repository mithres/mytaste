package com.vc.service.vod;

import com.vc.entity.PlayListType;

public class PlayListSearchCondition {
	
	private PlayListType playListType = null;
	
	private String channelId = null;
	
	private String orderBy = null;

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
	
}

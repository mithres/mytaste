package com.vc.presentation.action.vod;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.Action;
import com.vc.core.action.BaseAction;
import com.vc.entity.Channels;
import com.vc.entity.PlayListType;
import com.vc.service.system.ISystemService;

public class ShowPlayListTypeAndChannelAction extends BaseAction {

	private static final long serialVersionUID = 1977453568017556256L;
	
	@Autowired
	private ISystemService systemService = null;
	
	private List<PlayListType> playListTypes = null;
	
	private List<Channels> channelList = null;
	private List<Channels> subChannelList = null;
	
	private String action = null;
	private String timeFrame = null;
	private String parentChannelId = null;

	
	@Override
	public String process() {
	
		playListTypes = Arrays.asList(PlayListType.values());
		channelList = systemService.findParentChannels();
		if(parentChannelId != null){
			subChannelList = systemService.findAllSubChannels(parentChannelId);
		}
		return Action.SUCCESS;
	}


	public List<PlayListType> getPlayListTypes() {
		return playListTypes;
	}


	public List<Channels> getChannelList() {
		return channelList;
	}


	public String getAction() {
		return action;
	}


	public void setAction(String action) {
		this.action = action;
	}


	public String getTimeFrame() {
		return timeFrame;
	}


	public void setTimeFrame(String timeFrame) {
		this.timeFrame = timeFrame;
	}


	public void setParentChannelId(String parentChannelId) {
		this.parentChannelId = parentChannelId;
	}


	public List<Channels> getSubChannelList() {
		return subChannelList;
	}

}

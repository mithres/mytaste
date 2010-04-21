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
	
	
	@Override
	public String process() {
		
		playListTypes = Arrays.asList(PlayListType.values());
		channelList = systemService.findParentChannels();
		
		return Action.SUCCESS;
	}


	public List<PlayListType> getPlayListTypes() {
		return playListTypes;
	}


	public List<Channels> getChannelList() {
		return channelList;
	}

}

package com.vc.presentation.action.vod;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.Action;
import com.vc.core.action.BaseAction;
import com.vc.core.dao.Hints;
import com.vc.core.entity.IPageList;
import com.vc.entity.Channels;
import com.vc.entity.PlayList;
import com.vc.service.system.ISystemService;
import com.vc.service.vod.IPlayListService;

public class ChannelDetailAction extends BaseAction {

	private static final long serialVersionUID = -5904773823523945932L;
	
	@Autowired
	private IPlayListService playListService = null;
	@Autowired
	private ISystemService systemService = null;
	
	private IPageList<PlayList> playLists = null;
	
	private String cid = null;
	
	private Channels channel = null;
	
	@Override
	public String process() {
		playLists = playListService.findPlayListByChannel(new Hints(getStartRow(),getPageCount()), cid);
		channel = systemService.findChannelById(cid);
		return Action.SUCCESS;
	}

	public IPageList<PlayList> getPlayLists() {
		return playLists;
	}

	public Channels getChannel() {
		return channel;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}


}

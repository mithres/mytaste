package com.vc.presentation.action.vod;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.Action;
import com.vc.core.action.BaseAction;
import com.vc.core.dao.Hints;
import com.vc.core.entity.IPageList;
import com.vc.entity.Channels;
import com.vc.entity.PlayList;
import com.vc.service.system.ISystemService;
import com.vc.service.vod.IPlayListService;
import com.vc.service.vod.PlayListSearchCondition;

public class ChannelDetailAction extends BaseAction {

	private static final long serialVersionUID = -5904773823523945932L;
	
	@Autowired
	private IPlayListService playListService = null;
	@Autowired
	private ISystemService systemService = null;
	
	private IPageList<PlayList> playLists = null;
	
	private List<Channels> channelList = null;
	
	private Channels channel = null;
	
	private String cid = null;
	
	private String sort = null;
	
	@Override
	public String process() {
		
		if(cid == null && sort == null){
			channelList = systemService.findAllChannels();
			return "channels";
		}else{
			PlayListSearchCondition condition = new PlayListSearchCondition();
			condition.setChannelId(cid);
			condition.setOrderBy(sort);
			condition.setWithTags(true);
			condition.setWithComments(false);
			condition.setWithChannel(true);
			playLists = playListService.findPlayListByCondition(new Hints(getStartRow(),getPageCount()), condition);
			channel = systemService.findChannelWithSubChannel(cid);
			return Action.SUCCESS;
		}
		
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

	public List<Channels> getChannelList() {
		return channelList;
	}


}

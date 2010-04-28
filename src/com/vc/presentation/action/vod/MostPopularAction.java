package com.vc.presentation.action.vod;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.Action;
import com.vc.core.action.BaseAction;
import com.vc.core.constants.Constants;
import com.vc.core.dao.Hints;
import com.vc.core.entity.IPageList;
import com.vc.entity.PlayList;
import com.vc.entity.PlayListType;
import com.vc.service.vod.IPlayListService;
import com.vc.service.vod.PlayListSearchCondition;

public class MostPopularAction extends BaseAction {

	private static final long serialVersionUID = -6835692798165059935L;

	@Autowired
	private IPlayListService playListService = null;

	
	private IPageList<PlayList> playLists = null;
	
	
	private String timeFrame = "Today";
	
	private String type = "Popular";
	
	private PlayListType vt = null;
	
	private String channel = Constants.SEARCH_CONDITION_ALL;
	
	private String subChannel = Constants.SEARCH_CONDITION_ALL;


	@Override
	public String process() {

		PlayListSearchCondition condition = new PlayListSearchCondition();
		if(vt != null){
			condition.setPlayListType(vt);
		}
		if(channel != Constants.SEARCH_CONDITION_ALL && subChannel == Constants.SEARCH_CONDITION_ALL){
			condition.setChannelId(channel);
		}else if(channel != Constants.SEARCH_CONDITION_ALL && subChannel != Constants.SEARCH_CONDITION_ALL){
			condition.setChannelId(subChannel);
		}
		condition.setOrderBy(timeFrame);
		
		playLists = playListService.findPlayListByCondition(new Hints(getStartRow(),getPageCount()), condition);
		return Action.SUCCESS;
	}

	public IPageList<PlayList> getPlayLists() {
		return playLists;
	}

	public String getTimeFrame() {
		return timeFrame;
	}

	public void setTimeFrame(String timeFrame) {
		this.timeFrame = timeFrame;
	}

	public String getType() {
		return type;
	}

	public PlayListType getVt() {
		return vt;
	}

	public void setVt(PlayListType vt) {
		this.vt = vt;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getSubChannel() {
		return subChannel;
	}

	public void setSubChannel(String subChannel) {
		this.subChannel = subChannel;
	}
}

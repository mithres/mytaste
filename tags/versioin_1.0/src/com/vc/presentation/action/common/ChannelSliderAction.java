package com.vc.presentation.action.common;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.Action;
import com.vc.core.action.BaseAction;
import com.vc.core.constants.Constants;
import com.vc.core.dao.Hints;
import com.vc.core.entity.IPageList;
import com.vc.entity.PlayList;
import com.vc.service.vod.IPlayListService;
import com.vc.service.vod.PlayListSearchCondition;

public class ChannelSliderAction extends BaseAction {

	private static final long serialVersionUID = 8173800434311609225L;
	
	@Autowired
	private IPlayListService playListService = null;
	
	private String channelId = null;
	
	private IPageList<PlayList> playLists = null;
	
	@Override
	public String process() {
		PlayListSearchCondition condition = new PlayListSearchCondition();
		condition.setChannelId(channelId);
		condition.setOrderBy(Constants.SEARCH_CONDITION_ALL);
		condition.setWithComments(false);
		condition.setWithTags(false);
		condition.setWithChannel(false);
		playLists =  playListService.findPlayListByCondition(new Hints(0,15), condition);
		return Action.SUCCESS;
	}

	public IPageList<PlayList> getPlayLists() {
		return playLists;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

}

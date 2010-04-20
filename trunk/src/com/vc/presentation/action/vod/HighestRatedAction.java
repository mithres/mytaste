package com.vc.presentation.action.vod;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.Action;
import com.vc.core.action.BaseAction;
import com.vc.core.dao.Hints;
import com.vc.core.entity.IPageList;
import com.vc.entity.Channels;
import com.vc.entity.PlayList;
import com.vc.entity.PlayListType;
import com.vc.service.system.ISystemService;
import com.vc.service.vod.IPlayListService;
import com.vc.service.vod.PlayListSearchCondition;

public class HighestRatedAction extends BaseAction {

	private static final long serialVersionUID = 8639580324727467646L;

	@Autowired
	private ISystemService systemService = null;
	@Autowired
	private IPlayListService playListService = null;

	private List<PlayListType> playListTypes = null;

	private IPageList<PlayList> playLists = null;

	private List<Channels> channelList = null;

	private String timeFrame = "All";
	
	private String type = "Rate";

	@Override
	public String process() {

		playListTypes = Arrays.asList(PlayListType.values());
		channelList = systemService.findParentChannels();

		PlayListSearchCondition condition = new PlayListSearchCondition();
		condition.setOrderBy(timeFrame);
		playLists = playListService.findPlayListByCondition(new Hints(getStartRow(), getPageCount()), condition);
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

	public List<PlayListType> getPlayListTypes() {
		return playListTypes;
	}

	public List<Channels> getChannelList() {
		return channelList;
	}

	public String getType() {
		return type;
	}
}

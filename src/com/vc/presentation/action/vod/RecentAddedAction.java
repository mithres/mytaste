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

public class RecentAddedAction extends BaseAction {

	private static final long serialVersionUID = 8317111728828502574L;

	@Autowired
	private IPlayListService playListService = null;

	private IPageList<PlayList> playLists = null;

	private PlayListType vt = null;

	private String channel = Constants.SEARCH_CONDITION_ALL;

	private String subChannel = Constants.SEARCH_CONDITION_ALL;

	@Override
	public void prepare() throws Exception {
		this.getSession().setAttribute(Constants.NAV_STAT, Constants.NAV_RECENTLYADDED);
	}

	@Override
	public String process() {

		PlayListSearchCondition condition = new PlayListSearchCondition();
		if (vt != null) {
			condition.setPlayListType(vt);
		}
		if (channel != Constants.SEARCH_CONDITION_ALL && subChannel == Constants.SEARCH_CONDITION_ALL) {
			condition.setChannelId(channel);
		} else if (channel != Constants.SEARCH_CONDITION_ALL && subChannel != Constants.SEARCH_CONDITION_ALL) {
			condition.setChannelId(subChannel);
		}
		condition.setOrderBy("AddedTime");
		playLists = playListService.findPlayListByCondition(new Hints(getStartRow(), getPageCount()), condition);
		return Action.SUCCESS;
	}

	public IPageList<PlayList> getPlayLists() {
		return playLists;
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

	public PlayListType getVt() {
		return vt;
	}

	public void setVt(PlayListType vt) {
		this.vt = vt;
	}

}

package com.vc.presentation.action.vod;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.Action;
import com.vc.core.action.BaseAction;
import com.vc.core.dao.Hints;
import com.vc.core.entity.IPageList;
import com.vc.entity.PlayList;
import com.vc.service.vod.IPlayListService;
import com.vc.service.vod.PlayListSearchCondition;

public class MostPopularAction extends BaseAction {

	private static final long serialVersionUID = -6835692798165059935L;

	@Autowired
	private IPlayListService playListService = null;

	private IPageList<PlayList> playLists = null;
	
	private String timeFrame = null;

	@Override
	public String process() {
		
		PlayListSearchCondition condition = new PlayListSearchCondition();
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

}

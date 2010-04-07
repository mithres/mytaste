package com.vc.presentation.action.vod;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.Action;
import com.vc.core.action.BaseAction;
import com.vc.core.dao.Hints;
import com.vc.core.entity.IPageList;
import com.vc.entity.PlayList;
import com.vc.service.vod.IPlayListService;

public class MostPopularAction extends BaseAction {

	private static final long serialVersionUID = -6835692798165059935L;

	@Autowired
	private IPlayListService playListService = null;

	private IPageList<PlayList> playLists = null;

	@Override
	public String process() {
		playLists = playListService.findPlayListByViewCount(new Hints(getStartRow(), getPageCount()));
		return Action.SUCCESS;
	}

	public IPageList<PlayList> getPlayLists() {
		return playLists;
	}

}

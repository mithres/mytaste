package com.vc.presentation.action.vod;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.Action;
import com.vc.core.action.BaseAction;
import com.vc.core.dao.Hints;
import com.vc.core.entity.IPageList;
import com.vc.entity.PlayList;
import com.vc.service.vod.IPlayListService;

public class PlayListIndexAction extends BaseAction {

	private static final long serialVersionUID = 6373087375653567380L;

	@Autowired
	private IPlayListService playListService = null;
	
	private IPageList<PlayList> playList = null;
	
	@Override
	public String process() {
		playList = playListService.findPlayList(new Hints(getStartRow(),getPageCount()));
		return Action.SUCCESS;
	}
	
	public IPlayListService getPlayListService() {
		return playListService;
	}

	public IPageList<PlayList> getPlayList() {
		return playList;
	}

}
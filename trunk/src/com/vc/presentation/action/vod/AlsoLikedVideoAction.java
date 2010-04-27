package com.vc.presentation.action.vod;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.Action;
import com.vc.core.action.BaseAction;
import com.vc.core.dao.Hints;
import com.vc.core.entity.IPageList;
import com.vc.entity.PlayList;
import com.vc.service.vod.IPlayListService;

public class AlsoLikedVideoAction extends BaseAction {

	private static final long serialVersionUID = -5312656910830583976L;

	@Autowired
	private IPlayListService playListService = null;

	private IPageList<PlayList> videos = null;
	
	private String type = "alsoLiked";
	
	@Override
	public String process() {
		videos = playListService.recommentedVideo(new Hints(getStartRow(), getPageCount()));
		return Action.SUCCESS;
	}

	public IPageList<PlayList> getVideos() {
		return videos;
	}

	public String getType() {
		return type;
	}


}

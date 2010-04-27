package com.vc.presentation.action.vod;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.Action;
import com.vc.core.action.BaseAction;
import com.vc.core.dao.Hints;
import com.vc.core.entity.IPageList;
import com.vc.entity.VideoComments;
import com.vc.service.vod.IPlayListService;

public class ShowPlayListCommentsAction extends BaseAction {

	private static final long serialVersionUID = -5419202189536054406L;
	
	@Autowired
	private IPlayListService playListService = null;
	
	private IPageList<VideoComments> videoComments = null;
	
	private String playListID = null;
	
	@Override
	public String process() {
		videoComments = playListService.findPlayListComments(playListID, new Hints(getStartRow(), getPageCount()));
		return Action.SUCCESS;
	}

	public IPageList<VideoComments> getVideoComments() {
		return videoComments;
	}

	public void setPlayListID(String playListID) {
		this.playListID = playListID;
	}

}

package com.vc.presentation.action.vod;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.context.SecurityContextHolder;

import com.opensymphony.xwork2.Action;
import com.vc.core.action.BaseAction;
import com.vc.core.dao.Hints;
import com.vc.core.entity.IPageList;
import com.vc.entity.PlayList;
import com.vc.entity.VideoComments;
import com.vc.service.cluster.ILoadBalancer;
import com.vc.service.vod.IPlayListService;

public class PlayVideoAction extends BaseAction {

	private static final long serialVersionUID = -567277263679099396L;

	@Autowired
	private IPlayListService playListService = null;
	@Autowired
	private ILoadBalancer loadBalancer = null;

	private IPageList<VideoComments> videoComments = null;

	private PlayList playList = null;

	private String playListID = null;

	private String sid = null;

	private String nodeUrl = null;

	@Override
	public String process() {

		if (playListID != null) {
			if (!playListService.canPlay(SecurityContextHolder.getContext().getAuthentication(), playListID)) {
				addActionError("Your account has not enough money to play the movie.");
				return Action.INPUT;
			}
		} else {
			addActionError("The movie you want to play does not exist.");
			return Action.INPUT;
		}

		playList = playListService.findPlayListById(playListID);
		videoComments = playListService.findPlayListComments(playListID, new Hints(getStartRow(), getPageCount()));

		sid = this.getRequest().getSession().getId();
		nodeUrl = loadBalancer.getLBNode().getVodServiceUrl();
		log.info(loadBalancer.getLBNode().toString());

		return Action.SUCCESS;
	}

	public String getPlayListID() {
		return playListID;
	}

	public void setPlayListID(String playListID) {
		this.playListID = playListID;
	}

	public String getSid() {
		return sid;
	}

	public String getNodeUrl() {
		return nodeUrl;
	}

	public PlayList getPlayList() {
		return playList;
	}

	public IPageList<VideoComments> getVideoComments() {
		return videoComments;
	}

}

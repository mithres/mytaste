package com.vc.presentation.action.vod;

import org.acegisecurity.context.SecurityContextHolder;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.Action;
import com.vc.core.action.BaseAction;
import com.vc.service.vod.IPlayListService;

public class PlayVideoAction extends BaseAction {

	private static final long serialVersionUID = -567277263679099396L;

	@Autowired
	private IPlayListService playListService = null;
	
	private String playListID = null;

	@Override
	public String process() {

		if (playListID != null) {
			if(!playListService.canPlay(SecurityContextHolder.getContext().getAuthentication(), playListID)){
				addActionError("Your account has not enough money to play the movie.");
				return Action.INPUT;
			}
		}else{
			addActionError("The movie you want to play does not exist.");
			return Action.INPUT;
		}

		return Action.SUCCESS;
	}

	public String getPlayListID() {
		return playListID;
	}

	public void setPlayListID(String playListID) {
		this.playListID = playListID;
	}

}

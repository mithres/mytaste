package com.vc.presentation.vod.action;

import com.opensymphony.xwork2.Action;
import com.vc.core.action.BaseAction;

public class PlayVideoAction extends BaseAction{

	private static final long serialVersionUID = -567277263679099396L;
	
	private String playListID = null;
	
	@Override
	public String process() {
		
		//TODO: Check user account balance		
		
		return Action.SUCCESS;
	}

	public String getPlayListID() {
		return playListID;
	}

	public void setPlayListID(String playListID) {
		this.playListID = playListID;
	}

}

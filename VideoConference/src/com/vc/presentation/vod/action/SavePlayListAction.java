package com.vc.presentation.vod.action;

import com.vc.core.action.BaseAction;
import com.vc.entity.PlayList;

public class SavePlayListAction extends BaseAction {

	private static final long serialVersionUID = -3518841049039573039L;

	private PlayList playList = null;

	@Override
	public String process() {

		return null;
	}

	public PlayList getPlayList() {
		return playList;
	}

	public void setPlayList(PlayList playList) {
		this.playList = playList;
	}

}

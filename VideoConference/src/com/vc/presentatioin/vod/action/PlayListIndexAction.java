package com.vc.presentatioin.vod.action;

import org.springframework.beans.factory.annotation.Autowired;

import com.vc.core.action.BaseAction;
import com.vc.entity.PlayList;
import com.vc.service.vod.IPlayListService;

public class PlayListIndexAction extends BaseAction{

	private static final long serialVersionUID = 6373087375653567380L;
	
	@Autowired
	private IPlayListService playListService = null;
	
	@Override
	public String process() {
		log.info("Hello World");
		PlayList pl = new PlayList();
		pl.setId("001");
		pl.setName("My Playlist");
		playListService.savePlayList(pl);
		return null;
	}
		
}
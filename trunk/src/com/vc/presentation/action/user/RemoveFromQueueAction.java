package com.vc.presentation.action.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.context.SecurityContextHolder;

import com.opensymphony.xwork2.Action;
import com.vc.core.action.BaseAction;
import com.vc.core.dao.Hints;
import com.vc.core.entity.IPageList;
import com.vc.entity.PlayListQueue;
import com.vc.service.vod.IPlayListService;

public class RemoveFromQueueAction extends BaseAction {

	private static final long serialVersionUID = 1392201520556397673L;
	
	private IPageList<PlayListQueue> queue = null;
	
	@Autowired
	private IPlayListService playListService = null;
	
	private String id = null;
	
	@Override
	public String process() {
		
		//playListService.removePlayListQueue(id);
		queue = playListService.findUserPlayListQueue(SecurityContextHolder.getContext().getAuthentication().getName(),	new Hints(getStartRow(), getPageCount()));
		
		return Action.SUCCESS;
	}

	public IPageList<PlayListQueue> getQueue() {
		return queue;
	}

	public void setId(String id) {
		this.id = id;
	}

}

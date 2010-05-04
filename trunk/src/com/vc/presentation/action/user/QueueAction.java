package com.vc.presentation.action.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.context.SecurityContextHolder;

import com.opensymphony.xwork2.Action;
import com.vc.core.action.BaseAction;
import com.vc.core.dao.Hints;
import com.vc.core.entity.IPageList;
import com.vc.entity.PlayList;
import com.vc.service.vod.IPlayListService;

public class QueueAction extends BaseAction {

	private static final long serialVersionUID = -2831915310182553759L;

	@Autowired
	private IPlayListService playListService = null;

	private IPageList<PlayList> queue = null;

	@Override
	public String process() {
		queue = playListService.findUserPlayListQueue(SecurityContextHolder.getContext().getAuthentication().getName(),
				new Hints(getStartRow(), getPageCount()));
		return Action.SUCCESS;
	}

	public IPageList<PlayList> getQueue() {
		return queue;
	}

}

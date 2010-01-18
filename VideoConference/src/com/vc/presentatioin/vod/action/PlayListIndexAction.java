package com.vc.presentatioin.vod.action;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.Action;
import com.vc.core.action.BaseAction;
import com.vc.service.vod.IPlayListService;

public class PlayListIndexAction extends BaseAction {

	private static final long serialVersionUID = 6373087375653567380L;

	@Autowired
	private IPlayListService playListService = null;

	@Override
	public String process() {
		getActionContext().getSession().put("UserName", "Ammen");
		return Action.SUCCESS;
	}

}
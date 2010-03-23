package com.vc.presentation.action.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.Action;
import com.vc.core.action.BaseAction;
import com.vc.entity.Channels;
import com.vc.service.system.ISystemService;

public class CreateChannelAction extends BaseAction {

	private static final long serialVersionUID = -8372884825873140999L;

	@Autowired
	private ISystemService systemService = null;

	private List<Channels> parentChannels = null;

	@Override
	public String process() {
		parentChannels = systemService.findParentChannels();
		return Action.SUCCESS;
	}

	public List<Channels> getParentChannels() {
		return parentChannels;
	}

}

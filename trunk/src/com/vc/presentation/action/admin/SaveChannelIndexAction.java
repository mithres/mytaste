package com.vc.presentation.action.admin;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.Action;
import com.vc.core.action.BaseAction;
import com.vc.entity.Channels;
import com.vc.service.system.ISystemService;

public class SaveChannelIndexAction extends BaseAction {

	private static final long serialVersionUID = -6765352544438648845L;

	@Autowired
	private ISystemService systemService = null;

	private Channels channel = null;

	private String parentChannel = null;

	@Override
	public String process() {

		if (parentChannel != null) {
			channel.setParentChannel(systemService.findChannelById(parentChannel));
		}
		systemService.createChannel(channel);

		return Action.SUCCESS;
	}

	public void setParentChannel(String parentChannel) {
		this.parentChannel = parentChannel;
	}

	public Channels getChannel() {
		return channel;
	}

	public void setChannel(Channels channel) {
		this.channel = channel;
	}

}

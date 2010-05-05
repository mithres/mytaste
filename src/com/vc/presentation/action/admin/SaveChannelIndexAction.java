package com.vc.presentation.action.admin;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.Action;
import com.vc.core.action.BaseAction;
import com.vc.core.constants.Constants;
import com.vc.entity.Channels;
import com.vc.service.system.ISystemService;
import com.vc.vo.MenuVO;

public class SaveChannelIndexAction extends BaseAction {

	private static final long serialVersionUID = -6765352544438648845L;

	@Autowired
	private ISystemService systemService = null;

	private Channels channel = null;

	private String parentChannel = null;

	@Override
	public String process() {

		if (!parentChannel.equals("00")) {
			channel.setParentId(parentChannel);
		}
		systemService.createChannel(channel);
		
		MenuVO menuVO = new MenuVO();
		menuVO.setChannels(systemService.findParentChannels());
		getSession().setAttribute(Constants.MENU_STAT, menuVO);

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

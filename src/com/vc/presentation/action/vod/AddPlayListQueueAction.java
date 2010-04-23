package com.vc.presentation.action.vod;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.context.SecurityContextHolder;

import com.opensymphony.xwork2.Action;
import com.vc.core.action.BaseAction;
import com.vc.entity.PlayList;
import com.vc.service.user.IUserService;
import com.vc.service.vod.IPlayListService;

public class AddPlayListQueueAction extends BaseAction {

	private static final long serialVersionUID = -8840478111077096215L;

	@Autowired
	private IPlayListService playListService = null;
	@Autowired
	private IUserService userService = null;

	private String id = null;

	@Override
	public String process() {

		String messageToClient = "";

		if (id != null) {
			PlayList playList = playListService.findPlayListById(id);
			if (playList != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getName();
				playListService.addPlayListToQueue(userService.findUserByName(userName), playList);
				messageToClient = "{ \"success\": true,\"messages\":\"Added to queue success.\" }";
			} else {
				messageToClient = "{ \"success\": true, \"errors\": \" Play list not exist.\"}";
			}
		}else{
			messageToClient = "{ \"success\": true, \"errors\": \" Play list id is empty.\"}";
		}
		write(messageToClient);
		return Action.NONE;
	}

	public void setId(String id) {
		this.id = id;
	}

}

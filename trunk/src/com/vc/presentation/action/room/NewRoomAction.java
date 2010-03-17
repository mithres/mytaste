package com.vc.presentation.action.room;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.context.SecurityContextHolder;

import com.opensymphony.xwork2.Action;
import com.vc.core.action.BaseAction;
import com.vc.entity.Room;
import com.vc.entity.UserInfo;
import com.vc.service.conference.IRoomManageService;
import com.vc.service.user.IUserService;
import com.vc.util.server.TimeUtil;

public class NewRoomAction extends BaseAction {

	private static final long serialVersionUID = 6939388830607620418L;

	@Autowired
	private IRoomManageService roomManager = null;
	@Autowired
	private IUserService userService = null;

	private Room room = null;

	public String index() {
		return Action.SUCCESS;
	}

	@Override
	public String process() {
		room.setCreatedTime(TimeUtil.getCurrentTime());
		String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
		UserInfo currentUser = userService.findUserByName(currentUserName);
		room.setCreator(currentUser);
		roomManager.createRoom(room);
		return Action.SUCCESS;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

}

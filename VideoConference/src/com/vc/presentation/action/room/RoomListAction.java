package com.vc.presentation.action.room;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.Action;
import com.vc.core.action.BaseAction;
import com.vc.core.dao.Hints;
import com.vc.core.entity.IPageList;
import com.vc.entity.Room;
import com.vc.service.conference.IRoomManageService;

public class RoomListAction extends BaseAction {

	private static final long serialVersionUID = -5265461725451697714L;

	@Autowired
	private IRoomManageService roomManager = null;

	private IPageList<Room> rooms = null;

	@Override
	public String process() {
		rooms = roomManager.findAllRooms(new Hints(getStartRow(), getPageCount()));
		return Action.SUCCESS;
	}

	public IPageList<Room> getRooms() {
		return rooms;
	}

}